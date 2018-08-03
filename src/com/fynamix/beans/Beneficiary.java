package com.fynamix.beans;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fynamix.model.Account;

@Component("beneficiary")
public class Beneficiary {

	private Customer customer;
	private String bid;
	private String bname;
	private String uid;
	

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	private Timestamp request_time;
	private String enabled;
	private Account account;
	@Autowired
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	@Autowired
	public void setAccount(Account account) {
		this.account = account;
	}

	public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public Timestamp getRequest_time() {
		return request_time;
	}

	public void setRequest_time(Timestamp request_time) {
		this.request_time = request_time;
	}

	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getEnabled() {
		return enabled;
	}
	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public void insertBeneficiary(Beneficiary b, String user_id) {
		// TODO Auto-generated method stub
		account.insertBeneficiary(b,user_id);
		
	}

	public void updateBeneficiary(Beneficiary b,String user_id) {
		// TODO Auto-generated method stub
		//Account ac=new Account();
		System.out.println(account);
		System.out.println(this);
		System.out.println(user_id);
		account.updateBeneficiary(b,user_id);
	}

	public void remove(Beneficiary beneficiary, String string) {
		// TODO Auto-generated method stub
		this.account.remove(beneficiary,string);
	}
	
}
