package com.fynamix.controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import com.fynamix.beans.Beneficiary;
import com.fynamix.beans.Cibil;
import com.fynamix.beans.Customer;
import com.fynamix.beans.Transaction;
import com.fynamix.beans.TransferAsync;
import com.fynamix.beans.TryAsync;

@Configuration
@EnableScheduling
@EnableAsync
@Controller
public class TransferController {

	private Customer customer;
	private Beneficiary beneficiary;
	private Transaction transaction;
	private PasswordEncoder passenc;

	@Autowired
	public void setPassenc(PasswordEncoder passenc) {
		this.passenc = passenc;
	}

	@Autowired
	public void setTransaction(Transaction transaction) {
		this.transaction = transaction;
	}


	private String user_id;
	private TryAsync as;

	@Autowired
	public void setAs(TryAsync as) {
		this.as = as;
	}

	private TransferAsync ta;

	@Autowired
	public void setTa(TransferAsync ta) {
		this.ta = ta;
	}

	
	@Autowired
	public void setBeneficiary(Beneficiary beneficiary) {
		this.beneficiary = beneficiary;
	}

	@Autowired
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	@RequestMapping("/")
	public String showIndex(Model model) {
		return "home_main";
	}
	
	@RequestMapping("/loanProcess")
	public ModelAndView showLoan() {
		ModelAndView mov=new ModelAndView();
		RestTemplate r=new RestTemplate();
		String url="http://localhost:8080/Fynamix/rest/cibil/v1/fetch/B12345";
		Cibil c=r.getForObject(url, Cibil.class);
		mov.setViewName("loan");
		mov.addObject("cibil", c);
		return mov;
	}
	
	//display password reset form
	@RequestMapping(value = "/sign-up", method = RequestMethod.POST)
	public String showSignUp(Model model,@Valid Customer c,BindingResult result) {
		if (result.hasErrors()) {
			return "sign_up";
		}
		this.customer.setUid(c.getUid());
		String password=this.customer.fetchPassword(this.customer.getUid());
		System.out.println("form password:"+c.getPassword());
		System.out.println("DB password:"+password);
		if (!c.getPassword().equals(password)){
		model.addAttribute("msg","Invalid user");
		return "sign_up";}
		
		this.customer.insertCustomer(c);
		model.addAttribute("uid", c.getUid());
	return "preset";
	}
	
	//display sign-up form
	@RequestMapping("/sign-up-form")
	public String showSignUpForm(Model model) {
		
		return "sign_up";
	}
	
	//display loan eligibility calculator
	@RequestMapping("/loan-calc-form")
	public String showLoanCalcForm(Model model) {
		
		return "loan_eligibility";
	}
	
	//display login page
	@RequestMapping("/Show-login")
	public String showLogIn(Model model) {
		
		return "login";
	}
	
	//re-direction after login
	@RequestMapping("/login-success")
	public String showLogInSuccess(Model model) {
		
		return "index";
	}
	
	//add customer to users after password reset
	@RequestMapping(value = "/sign-up-success", method = RequestMethod.POST)
	public String showPasswordResetStatus(Model model,@Valid Customer customer) {
		model.addAttribute("msg", "Account activated successfully");
		customer.setUid(this.customer.getUid());
		customer.setEnabled("true");
		customer.setAuthority("customer");
		customer.setActivation("yes");
		this.customer.insertIntoUsers(customer);
		
		return "index";
	}
	
	//
	@RequestMapping("/transfer-funds")
	public String showTransferFunds(Model model) {
		
		return "index";
	}
	
	//display portfolio
	@RequestMapping("/portfolio")
	public String showPortfolio(Model model,HttpSession session) {
		user_id=(String)session.getAttribute("uid");
					return "portfolio";
	}
	
	
	
	//display form for adding beneficiary
	@RequestMapping("/add-beneficiary-form")
	public String showAddBeneficiary(Model model,HttpSession session) {
			return "add_beneficiary";
	}
	
	//display form for funds transfer
	@RequestMapping("/funds-transfer-form")
	public String showTransferFunds(Model model,HttpSession session) {
		
		return "transfer_funds";
	}
	
	//activate beneficiary
	@RequestMapping(value = "/ben-success", method = RequestMethod.POST)
	public String addBeneficiaryToDb(Model model,@Valid Beneficiary b,HttpSession session,WebRequest request) {
		System.out.println("I came here");
		beneficiary.setBname(b.getBname());
		beneficiary.setBid(b.getBid());
		beneficiary.setUid(user_id);
		this.customer.setUid(user_id);
		as.executeTaskT(beneficiary);
		model.addAttribute("msg", "Wait for 20secs for the beneficiary to get activated, only after this activation perform transactions or add a new a beneficiary");
		return "index";
	}
	
	@RequestMapping("/add-customer")
	public String showAddCustomer(Model model) {
		
		return "add_customer";
	}
	
	
	//add new customer to DB
	@RequestMapping(value = "/add-customer-form", method = RequestMethod.POST)
	public String showAddCustomerSuccess(Model model,@Valid Customer c,BindingResult result) {
		if (result.hasErrors()) {
			model.addAttribute("msg", "customer not added ");
			return "add_customer";
		}
		
		this.customer.addCustomer(c);
		model.addAttribute("msg", "customer added successfully");
	return "homeadmin";
	}
	
	//displaying list of beneficiaries
	@RequestMapping("/beneficiary")
	public String showMoneyTransfer(Model model,HttpSession session) {
		user_id=(String)(session.getAttribute("uid"));
		this.customer.setUid(user_id);
		if(session.getAttribute("stop_fetch")==null)
		customer=this.customer.fetchCustomer(user_id);
		session.setAttribute("stop_fetch", "stop");
		String cid=customer.getId();
		Float bal=customer.getBalance();
		session.setAttribute("cid", cid);
		session.setAttribute("cbal", bal);
		return "beneficiary";
	}
	
	//process transfer funds form
	@RequestMapping(value = "/Transfer-details", method = RequestMethod.POST)
	public String showTransferConfirmation(Model model,@Valid Transaction t,HttpSession session,@RequestParam("amount") String amount) throws ParseException {
		System.out.println("got transfer details as:");
		System.out.println("Sender account no "+t.getUid());
		System.out.println("Transfer description "+t.getDetails());
		System.out.println("Transfer type "+t.getType());
		System.out.println("Sender balance "+session.getAttribute("cbal"));
		t.setDebit(Float.parseFloat(amount));
		t.setCredit(0);
		t.setBalance(Float.parseFloat(session.getAttribute("cbal").toString()));
		System.out.println("Amount to be sent: "+t.getDebit());
		System.out.println("Beneficiary account no : "+t.getBid());
		
		   //t.setInit_date_time(time_now);
		   transaction.setBid(t.getBid());
		   transaction.setUid(t.getUid());
		   transaction.setBalance(t.getBalance());
		   transaction.setInit_date_time(t.getInit_date_time());
		   transaction.setDebit(t.getDebit());
		   transaction.setCredit(t.getCredit());
		   transaction.setDetails(t.getDetails());
		   transaction.setType(t.getType());
		   //customer=this.customer.fetchCustomer(user_id);
		   if(t.getType().equals("neft")) {
			   if(t.getDebit()>=1&&t.getDebit()<=1000000) {
				   if(t.getDebit()>=10000&&t.getDebit()<=100000)
					   t.setExtra_charges(5);
				   else if(t.getDebit()>100000&&t.getDebit()<=200000)
					   t.setExtra_charges(15);
				   else if(t.getDebit()>200000&&t.getDebit()<=500000)
					   t.setExtra_charges(25);
				   else if(t.getDebit()>500000&&t.getDebit()<=1000000)
					   t.setExtra_charges(25);
				   else
					   t.setExtra_charges((float)2.5);
				   transaction.setExtra_charges(t.getExtra_charges());
				   //transaction.setComplete_date_time(newTime);
				   session.setAttribute("transact", transaction);
				   session.setAttribute("time", 30);
				   if(transaction.getBalance()-transaction.getDebit()-transaction.getExtra_charges()<0) {
					   model.addAttribute("msg", "Insufficient funds");
					   return "transfer_funds";
				   }
				   ArrayList<Float> debits =(ArrayList<Float>)transaction.getAverage(transaction.getUid());
				   float sum=0;
				   for(float amt: debits) {sum=sum+amt;}
				   float avg=sum/(debits.size());
				   if(transaction.getDebit()>1.5*avg) {
					   return "extra_check";
				   }
				   
				   return "confirm";
			   //ta.executeTaskT(transaction,30000);
				   }
		   }
		   if(t.getType().equals("rtgs")) {
			   if(t.getDebit()>=200000&&t.getDebit()<=1000000) {
				   if(t.getDebit()>200000&&t.getDebit()<=500000)
					   t.setExtra_charges(25);
				   else 
					   t.setExtra_charges(50);
				   transaction.setExtra_charges(t.getExtra_charges());
				   //transaction.setComplete_date_time(newTime);
				   session.setAttribute("transact", transaction);
				   session.setAttribute("time", 20);
				   if(transaction.getBalance()-transaction.getDebit()-transaction.getExtra_charges()<0) {
					   model.addAttribute("msg", "Insufficient funds");
					   return "transfer_funds";
				   }
				   ArrayList<Float> debits =(ArrayList<Float>)transaction.getAverage(transaction.getUid());
				   float sum=0;
				   for(float amt: debits) {sum=sum+amt;}
				   float avg=sum/(debits.size());
				   if(transaction.getDebit()>1.5*avg) {
					   return "extra_check";
				   }
				   return "confirm";
			   //ta.executeTaskT(transaction,20000);
			   }
			   model.addAttribute("msg", "RTGS is only allowed value between 2lac and 10lac");
		   }
		   if(t.getType().equals("imps")) {
			   if(t.getDebit()<=500000) {
				   if(t.getDebit()>=10000&&t.getDebit()<=100000)
					   t.setExtra_charges(5);
				   else if(t.getDebit()>100000&&t.getDebit()<=200000)
					   t.setExtra_charges(5);
				   else
					   t.setExtra_charges(15);
			   transaction.setExtra_charges(t.getExtra_charges());
			   //transaction.setComplete_date_time(time_now);
			   session.setAttribute("transact", transaction);
			   session.setAttribute("time", 0);
			   if(transaction.getBalance()-transaction.getDebit()-transaction.getExtra_charges()<0) {
				   model.addAttribute("msg", "Insufficient funds");
				   return "transfer_funds";
			   }
			   ArrayList<Float> debits =(ArrayList<Float>)transaction.getAverage(transaction.getUid());
			   float sum=0;
			   for(float amt: debits) {sum=sum+amt;}
			   float avg=sum/(debits.size());
			   if(transaction.getDebit()>1.5*avg) {
				   return "extra_check";
			   }
			   return "confirm";
			   //this.transaction.addTransaction(transaction);
			   }
			   model.addAttribute("msg", "IMPS value should be less than 5lac");
		   }
		return "transfer_funds";
	}
	
	//transaction_delay
	@RequestMapping("/confirm-transaction")
	public String updateTransDB(Model model,HttpSession session) {
		
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
		   String time_now=(dateFormat.format(cal.getTime()));
		   System.out.println("confirming transaction "+transaction);
		   transaction.setInit_date_time(time_now);
		   if(transaction.getType().equals("neft")) {
			   cal.add(Calendar.SECOND, 30);
			   String newTime=(dateFormat.format(cal.getTime()));
			   transaction.setComplete_date_time(newTime);
			   ta.executeTaskT(transaction,30000);
			   
		   }
		   if(transaction.getType().equals("rtgs")) {
			   cal.add(Calendar.SECOND, 20);
			   String newTime=(dateFormat.format(cal.getTime()));
			   transaction.setComplete_date_time(newTime);
			   ta.executeTaskT(transaction,20000);
			   
		   }
		   if(transaction.getType().equals("imps")) {
			   transaction.setComplete_date_time(time_now);
			   this.transaction.addTransaction(transaction);			   
		   }
		//transaction.setBalance(balance);
		model.addAttribute("msg", "Transaction successful");
		return "index";
	}
	
	//extra step authentication
	@RequestMapping("/verification")
	public String Verify(Model model,HttpSession session,@RequestParam String transpass) {
		this.customer.setUid(session.getAttribute("uid").toString());
		System.out.println("transaction pass at verification "+customer.getTranspass());
		
		if (!customer.getTranspass().equals(transpass)) {
			return "logout";
		}
		return "confirm";
	}
	
	//loan eligibility calculator
	@RequestMapping(value = "/calc-loan", method = RequestMethod.POST)
	public String showEligibleLoan(Model model,HttpSession session,@RequestParam("age") String age,@RequestParam("income") String income,@RequestParam("on_emi") String on_emi) {
	int calc_age=Integer.parseInt(age);
	float calc_income=Integer.parseInt(income);
	float calc_on_emi=Integer.parseInt(on_emi);
	float rate=(float)8.5/100;
		if(calc_age > 55) {
		model.addAttribute("msg", "You are not eligible for the loan as upper age limit is 55 years");
		return "loan_eligibility";
	}
	if(calc_age < 21) {
		model.addAttribute("msg", "You are not eligible for the loan as lower age limit is 21 years");
		return "loan_eligibility";
	}
	int tenure=60-calc_age;
	if(tenure>30)
		tenure=30;
	float emi=(float)0.5*(calc_income-calc_on_emi);
	float total_emi=emi*12;
	float loan_amount=(float) ((float)total_emi*((Math.pow(1+rate, tenure))-1)/((Math.pow(1+rate, tenure))*rate));
	
	model.addAttribute("emi", emi);
	model.addAttribute("tenure", tenure);
	model.addAttribute("loan_amount", loan_amount);
	model.addAttribute("result","show");
	return "loan_eligibility";
}
}

