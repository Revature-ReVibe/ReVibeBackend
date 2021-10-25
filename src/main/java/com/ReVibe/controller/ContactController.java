package com.ReVibe.controller;

//These go in application.properties:
//spring.mail.host=smtp.gmail.com
//spring.mail.port=587
//spring.mail.username=ReVibeNov5@gmail.com
//spring.mail.password=
//spring.mail.properties.mail.smtp.auth=true
//spring.mail.properties.mail.smtp.starttls.enable=true

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
@CrossOrigin(origins="*")
public class ContactController {

	@Autowired
	private JavaMailSender mailSender;
	
	@GetMapping("/contact")
	public String showContactForm() {
		return "contact_form";
	}
	
	@PostMapping("/contact")
	public String submitContact(HttpServletRequest request) {
		
		String fullname = request.getParameter("fullname");
		String email = request.getParameter("email");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		SimpleMailMessage message = new SimpleMailMessage();
		
		message.setFrom("revibenov5@gmail.com");
		message.setTo(email);
		
		String mailSubject = fullname + " has sent a message.";
		String mailContent = "Sender Name: " + fullname +  "\n";
		mailContent += "Sender E-mail: " + email + "\n";
		mailContent += "Subject: " + subject + "\n";
		mailContent += "Content: " + content + "\n";
		
		message.setSubject(mailSubject);
		message.setText(mailContent);
		
		mailSender.send(message);
	
		return "message";
	}
}
