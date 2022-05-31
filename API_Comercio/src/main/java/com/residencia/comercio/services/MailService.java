package com.residencia.comercio.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	
	@Autowired
	JavaMailSender emailSender;
	
	@Value("${spring.mail.host}")
	private String mailHost;
	
	@Value("${spring.mail.port}")
	private String mailPort;
	
	@Value("${spring.mail.username}")
	private String mailUsername;
	
	@Value("${spring.mail.password}")
	private String mailPassword;
	
	public MailService(JavaMailSender javaMailSender) {
		this.emailSender = javaMailSender;
	}
	
	public void enviarEmailTexto(String para, String assunto, String corpo) {
		SimpleMailMessage sMailMessage = new SimpleMailMessage();
		sMailMessage.setTo(para);
		sMailMessage.setSubject(assunto);
		sMailMessage.setText(corpo);
		sMailMessage.setFrom("teste@teste.com");
		
		emailSender.send(sMailMessage);
	}
	
	public void enviarEmailHTML(String de, String para, String assunto, String corpo) {
		MimeMessage email = emailSender.createMimeMessage();
		
		try {
			email.setSubject(assunto);
			MimeMessageHelper helper;
			helper = new MimeMessageHelper(email, true);
			helper.setFrom(de);
			helper.setTo(para);
			helper.setText(corpo, true);
			emailSender.send(email);
		} catch (MessagingException e) {
			System.out.println(e.getStackTrace());
		}
	}
}
