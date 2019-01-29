package com.hellokoding.auth.service;

import org.springframework.mail.SimpleMailMessage;

public interface EmailService {
	
	public void sendEmails(SimpleMailMessage email);

}
