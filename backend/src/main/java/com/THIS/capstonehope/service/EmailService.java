package com.THIS.capstonehope.service;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.THIS.capstonehope.security.security.services.UserDetailsImpl;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

@Component
public class EmailService{
	  private  String TEMPLATE_NAME;
	  private static final String SPRING_LOGO_IMAGE = "templates/images/spring.png";
	  private static final String PNG_MIME = "image/png";
	//   private static final String MAIL_SUBJECT = "Registration Confirmation";
	private String MAIL_SUBJECT;

	  private final Environment environment;

	  private final JavaMailSender mailSender;

	  private final TemplateEngine htmlTemplateEngine;
	  
	  public EmailService(Environment environment, JavaMailSender mailSender, TemplateEngine htmlTemplateEngine) {
	    this.environment = environment;
	    this.mailSender = mailSender;
	    this.htmlTemplateEngine = htmlTemplateEngine;
	  }
	  
	  
	//   @SuppressWarnings("null")
	public ResponseEntity<Object> register(String emailString, String userNameString,String emailKind)
	      throws MessagingException, UnsupportedEncodingException {
	       Context ctx;
			switch (emailKind) {
				case "LOGIN":
				  MAIL_SUBJECT = "Good to have you back!";
				  TEMPLATE_NAME = "login";
				  ctx = new Context(LocaleContextHolder.getLocale());
				  //TO DO add otp methods here
					ctx.setVariable("OTP", OTP);
					ctx.setVariable("name", userNameString);
					ctx.setVariable("springLogo", SPRING_LOGO_IMAGE);
					TEMPLATE_NAME = "templates/login.html";
					break;
				case "REGISTER":
				 MAIL_SUBJECT = "Account Confirmation!";
				 TEMPLATE_NAME = "registration";
				    ctx = new Context(LocaleContextHolder.getLocale());
					ctx.setVariable("email", emailString);
					ctx.setVariable("name", userNameString);
					ctx.setVariable("springLogo", SPRING_LOGO_IMAGE);
	    //ctx.setVariable("url", confirmationUrl);
					break;
				default:
					 MAIL_SUBJECT = "How did you get this email!!";
					break;
			}


		
	    // TODO save user in the database here
	    
	    //String confirmationUrl = "generated_confirmation_url";
	    String mailFrom = environment.getProperty("spring.mail.properties.mail.smtp.from");
	    String mailFromName = environment.getProperty("mail.from.name", "Identity");

	    final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
	    final MimeMessageHelper email;
	    email = new MimeMessageHelper(mimeMessage, true, "UTF-8");

	    email.setTo(emailString);
	    email.setSubject(MAIL_SUBJECT);
	    email.setFrom(new InternetAddress(mailFrom, mailFromName));

	    // final Context ctx = new Context(LocaleContextHolder.getLocale());
	    // ctx.setVariable("email", emailString);
	    // ctx.setVariable("name", userNameString);
	    // ctx.setVariable("springLogo", SPRING_LOGO_IMAGE);
	    // ctx.setVariable("url", confirmationUrl);

	    final String htmlContent = this.htmlTemplateEngine.process(TEMPLATE_NAME, ctx);

	    email.setText(htmlContent, true);

	    ClassPathResource clr = new ClassPathResource(SPRING_LOGO_IMAGE);

	    email.addInline("springLogo", clr, PNG_MIME);

	    mailSender.send(mimeMessage);

	    Map<String, String> body = new HashMap<>();
	    body.put("message", "User created successfully.");
	    
	    return new ResponseEntity<>(body, HttpStatus.OK);
	  }
}
