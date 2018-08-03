package com.fynamix.beans;

import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fynamix.model.Account;

@Component("customer")
public class Customer {

	private String id;
	private Account account;
	@Pattern(regexp="\\w+\\@\\w+\\.\\w+",message="Please Enter valid email")
	private String email;
	private String uid;
	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Pattern(regexp="\\w+",message="Please Enter name")
	private String username;
	
	@Size(min=4)
	private String password;

	public String getId() {
		return id;
	}

	private String enabled;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEnabled() {
		return enabled;
	}

	public void setEnabled(String enabled) {
		this.enabled = enabled;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	private String authority;
	
	@Autowired
	public void setAccount(Account account) {
		this.account = account;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTranspass() {
		return transpass;
	}

	public void setTranspass(String transpass) {
		this.transpass = transpass;
	}

	public String getActivation() {
		return activation;
	}

	public void setActivation(String activation) {
		this.activation = activation;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public void updateCdata(Customer c) {
		account.updateCdata(c);
	}
	private String transpass;
	private String activation;
	private float balance;

	public String fetchPassword(String id2) {
		String pass=account.fetchPassword(id2);
		return pass;
		
	}
	
	public void insertCustomer(Customer c) {
		account.insertCustomer(c);
	}

	public void insertIntoUsers(Customer c) {
		account.insertIntoUsers(c);
		
	}

	public void addCustomer(Customer c) {
		// TODO Auto-generated method stub
		account.addCustomer(c);
		
	}

	

	public Customer fetchCustomer(String user_id) {
		// TODO Auto-generated method stub
		return account.fetchCustomer(user_id);
	}

	public String fetchTranspass(Customer customer) {
		// TODO Auto-generated method stub
		//System.out.println("cust");
		return account.fetchTranspass(customer);
	}
}
