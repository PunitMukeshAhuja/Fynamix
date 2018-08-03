package com.fynamix.controller;



import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController{

	@RequestMapping("/login")
	public String showLogin() {
		//session.setAttribute("uid", uid);
		return "login";
	}
	
	@RequestMapping("/logout")
	public String showLogout() {
		return "logout";
	}
	
	@RequestMapping("/admin")
	public String showadmin() {
		return "logout";
	}
	
	@RequestMapping("/homeadmin")
	public String showhomepageadmin() {
		return "homeadmin";
	}

	
	
	
	
}
