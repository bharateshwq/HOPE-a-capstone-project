package com.THIS.capstonehope.service;

import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.THIS.capstonehope.security.models.User;
import com.THIS.capstonehope.security.util.OtpUtil;

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
	  Context ctx;
	  
	@Autowired
	OtpUtil otpUtil;
	
	  public EmailService(Environment environment, JavaMailSender mailSender, TemplateEngine htmlTemplateEngine) {
	    this.environment = environment;
	    this.mailSender = mailSender;
	    this.htmlTemplateEngine = htmlTemplateEngine;
	  }
	  private ResponseEntity<Object> sendMail(String sendTo) throws MessagingException, UnsupportedEncodingException
	  {
		String mailFrom = environment.getProperty("spring.mail.properties.mail.smtp.from");
	    String mailFromName = environment.getProperty("mail.from.name", "Identity");

	    final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
	    final MimeMessageHelper email;
	    email = new MimeMessageHelper(mimeMessage, true, "UTF-8");

	    email.setTo(sendTo);
	    email.setSubject(MAIL_SUBJECT);
	    email.setFrom(new InternetAddress(mailFrom, mailFromName));

		final String htmlContent = this.htmlTemplateEngine.process(TEMPLATE_NAME, ctx);

	    email.setText(htmlContent, true);

	    ClassPathResource clr = new ClassPathResource(SPRING_LOGO_IMAGE);

	    email.addInline("springLogo", clr, PNG_MIME);

	    mailSender.send(mimeMessage);

	    Map<String, String> body = new HashMap<>();
	    body.put("message","mail sent successfully");
	    
	    return new ResponseEntity<>(body, HttpStatus.OK);

	  }
	  
	  
	//   @SuppressWarnings("null")
	public void register(String emailString, String userNameString)
	      throws MessagingException, UnsupportedEncodingException {
			MAIL_SUBJECT = "Account Confirmation!";
			TEMPLATE_NAME = "registration";
			   ctx = new Context(LocaleContextHolder.getLocale());
			   ctx.setVariable("email", emailString);
			   ctx.setVariable("name", userNameString);
			   ctx.setVariable("springLogo", SPRING_LOGO_IMAGE);
				sendMail(emailString);
			
			}

	


	  public void login(String emailString, String userNameString) throws UnsupportedEncodingException, MessagingException{
				String OTP ="abc";
				//String OTP=otpUtil.generateOtp();
				  MAIL_SUBJECT = "Good to have you back!";
				  TEMPLATE_NAME = "login";
				  ctx = new Context(LocaleContextHolder.getLocale());
				  //TO DO add otp methods here
					ctx.setVariable("OTP", OTP);
					ctx.setVariable("name", userNameString);
					ctx.setVariable("springLogo", SPRING_LOGO_IMAGE);
					TEMPLATE_NAME = "login.html";
					sendMail(emailString);
	  }
	  
	  public void donation(String emailString, String userNameString,String amount, String hostedBy, String transactionId,LocalDateTime donationDate) throws UnsupportedEncodingException, MessagingException{
			MAIL_SUBJECT = "Thank you for your donation";
			TEMPLATE_NAME="donation";
			/// TODO pdf generation to be added here
			ctx = new Context(LocaleContextHolder.getLocale());
			ctx.setVariable("donation", "templates/images/donation_email.png");
			ctx.setVariable("name", userNameString);
			ctx.setVariable("donationfee", amount);
			ctx.setVariable("hostedBy", hostedBy);
			ctx.setVariable("donorName", userNameString);
			ctx.setVariable("orderId", transactionId);
			ctx.setVariable("donationDate", donationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

			sendMail(emailString);
	  }


	  public void volunteering(String emailString, String userNameString) throws UnsupportedEncodingException, MessagingException{

	  }
 
	  
	   

	}

