package com.aamir.service.impl;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import com.aamir.dto.EmailRequest;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailService {
	@Autowired
private JavaMailSender mailSender;
	
	@Value("${spring.mail.username}") // set krna hai application.property me
	private String mailFrom;
	
	//dto me EmailRequest class bna lunga
	public void sendEmail(EmailRequest emailReq) throws Exception
	{
		MimeMessage message = mailSender.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(message);
		//email kaha se jana chaiye
		helper.setFrom(mailFrom,emailReq.getTitle());
		//kis ko send krna hai
		helper.setTo(emailReq.getTo());
		helper.setSubject(emailReq.getSubject());
		helper.setText(emailReq.getMessage(),true);//html enalble ke liye true kr rhiya
		//ab send krdo
		mailSender.send(message);
	
		
	}
}
