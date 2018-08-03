package com.fynamix.beans;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component("ta")
@EnableScheduling
@EnableAsync
public class TransferAsync {

	@Async
	public void executeTaskT(Transaction t,int time) {
		
		System.out.println("Transfer 1st time");
	    try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println("Transfer 2nd time");
	    t.addTransaction(t);
	}
}
