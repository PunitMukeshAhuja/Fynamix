package com.fynamix.beans;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

@Component("as")
@EnableScheduling
@EnableAsync
public class TryAsync {

	@Async
	public void executeTaskT(Beneficiary b) {
		
		System.out.println("Trying 1st time");
		b.setEnabled("no");
		b.insertBeneficiary(b, b.getUid());
	    try {
			Thread.sleep(20000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println("Trying 2nd time");
	   // b.setEnabled("yes");
	    b.updateBeneficiary(b, b.getUid());
	}

}
