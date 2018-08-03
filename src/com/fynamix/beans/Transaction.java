package com.fynamix.beans;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fynamix.model.Account;

@Component("transaction")
public class Transaction {

	private Customer customer;
	private int tid;
	private String init_date_time;
	private String complete_date_time;
	private String details;
	private float credit;
	private float debit;
	private float balance;
	private String type;
	private String uid;
	private String bid;
	private Account account;
	private float extra_charges;
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

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Customer getCustomer() {
		return customer;
	}
	
	@Autowired
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getInit_date_time() {
		return init_date_time;
	}
	public void setInit_date_time(String init_date_time) {
		this.init_date_time = init_date_time;
	}
	public String getComplete_date_time() {
		return complete_date_time;
	}
	public void setComplete_date_time(String complete_date_time) {
		this.complete_date_time = complete_date_time;
	}
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public float getCredit() {
		return credit;
	}
	public void setCredit(float credit) {
		this.credit = credit;
	}
	public float getDebit() {
		return debit;
	}
	public void setDebit(float debit) {
		this.debit = debit;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public void addTransaction(Transaction t) {
		// TODO Auto-generated method stub
		System.out.println(account);
		account.addTransaction(t);
	}

	public float getExtra_charges() {
		return extra_charges;
	}

	public void setExtra_charges(float extra_charges) {
		this.extra_charges = extra_charges;
	}

	public List<Float> getAverage(String string) {
		// TODO Auto-generated method stub
		return account.getAverage(string);
		//return 0;
	}
	
	
}
