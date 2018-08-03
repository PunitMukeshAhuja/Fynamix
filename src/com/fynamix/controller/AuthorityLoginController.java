package com.fynamix.controller;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.hibernate.validator.internal.util.logging.Log;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.mysql.cj.core.log.LogFactory;

public class AuthorityLoginController implements AuthenticationSuccessHandler {
	

	    @Override
	    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authResult) throws IOException{
	        Collection<? extends GrantedAuthority> auths = authResult.getAuthorities();
	        for (GrantedAuthority authorities : auths)
	        {
	            if (authorities.getAuthority().equals("admin")){
	            	
	                response.sendRedirect(request.getContextPath()+"/homeadmin");
	            }
	            else if(authorities.getAuthority().equals("customer")){
	                response.sendRedirect(request.getContextPath()+"/login-success");
	        }
}
	    }
}

