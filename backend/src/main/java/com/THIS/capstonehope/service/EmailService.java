// package com.THIS.capstonehope.service;

// import java.io.UnsupportedEncodingException;
// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;
// import java.util.HashMap;
// import java.util.Map;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.i18n.LocaleContextHolder;
// import org.springframework.core.env.Environment;
// import org.springframework.core.io.ClassPathResource;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.mail.javamail.JavaMailSender;
// import org.springframework.mail.javamail.MimeMessageHelper;
// import org.springframework.security.core.context.SecurityContextHolder;
// import org.springframework.stereotype.Component;

// import org.thymeleaf.TemplateEngine;
// import org.thymeleaf.context.Context;

// import com.THIS.capstonehope.security.models.User;
// import com.THIS.capstonehope.security.util.OtpUtil;

// import jakarta.mail.MessagingException;
// import jakarta.mail.internet.InternetAddress;
// import jakarta.mail.internet.MimeMessage;




// @Component
// public class EmailService{
// 	  private  String TEMPLATE_NAME;
// 	  private static final String SPRING_LOGO_IMAGE = "templates/images/spring.png";
// 	  private static final String PNG_MIME = "image/png";
// 	//   private static final String MAIL_SUBJECT = "Registration Confirmation";
// 	private String MAIL_SUBJECT;

// 	  private final Environment environment;

// 	  private final JavaMailSender mailSender;

// 	  private final TemplateEngine htmlTemplateEngine;
// 	  Context ctx;
	  
// 	@Autowired
// 	OtpUtil otpUtil;
	
// 	  public EmailService(Environment environment, JavaMailSender mailSender, TemplateEngine htmlTemplateEngine) {
// 	    this.environment = environment;
// 	    this.mailSender = mailSender;
// 	    this.htmlTemplateEngine = htmlTemplateEngine;
// 	  }
// 	  private ResponseEntity<Object> sendMail(String sendTo) throws MessagingException, UnsupportedEncodingException
// 	  {
// 		String mailFrom = environment.getProperty("spring.mail.properties.mail.smtp.from");
// 	    String mailFromName = environment.getProperty("mail.from.name", "Identity");

// 	    final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
// 	    final MimeMessageHelper email;
// 	    email = new MimeMessageHelper(mimeMessage, true, "UTF-8");

// 	    email.setTo(sendTo);
// 	    email.setSubject(MAIL_SUBJECT);
// 	    email.setFrom(new InternetAddress(mailFrom, mailFromName));

// 		final String htmlContent = this.htmlTemplateEngine.process(TEMPLATE_NAME, ctx);

// 	    email.setText(htmlContent, true);

// 	    ClassPathResource clr = new ClassPathResource(SPRING_LOGO_IMAGE);

// 	    email.addInline("springLogo", clr, PNG_MIME);

// 	    mailSender.send(mimeMessage);

// 	    Map<String, String> body = new HashMap<>();
// 	    body.put("message","mail sent successfully");
	    
// 	    return new ResponseEntity<>(body, HttpStatus.OK);

// 	  }
	  
	  
// 	//   @SuppressWarnings("null")
// 	public void register(String emailString, String userNameString)
// 	      throws MessagingException, UnsupportedEncodingException {
// 			MAIL_SUBJECT = "Account Confirmation!";
// 			TEMPLATE_NAME = "registration";
// 			   ctx = new Context(LocaleContextHolder.getLocale());
// 			   ctx.setVariable("email", emailString);
// 			   ctx.setVariable("name", userNameString);
// 			   ctx.setVariable("springLogo", SPRING_LOGO_IMAGE);
// 				sendMail(emailString);
			
// 			}

	


// 	  public void login(String emailString, String userNameString) throws UnsupportedEncodingException, MessagingException{
// 				String OTP ="abc";
// 				//String OTP=otpUtil.generateOtp();
// 				  MAIL_SUBJECT = "Good to have you back!";
// 				  TEMPLATE_NAME = "login";
// 				  ctx = new Context(LocaleContextHolder.getLocale());
// 				  //TO DO add otp methods here
// 					ctx.setVariable("OTP", OTP);
// 					ctx.setVariable("name", userNameString);
// 					ctx.setVariable("springLogo", SPRING_LOGO_IMAGE);
// 					TEMPLATE_NAME = "login.html";
// 					sendMail(emailString);
// 	  }
	  
// 	  public void donation(String emailString, String userNameString,String amount, String hostedBy, String transactionId,LocalDateTime donationDate) throws UnsupportedEncodingException, MessagingException{
// 			MAIL_SUBJECT = "Thank you for your donation";
// 			TEMPLATE_NAME="donation";
// 			/// TODO pdf generation to be added here
// 			ctx = new Context(LocaleContextHolder.getLocale());
// 			ctx.setVariable("donation", "templates/images/donation_email.png");
// 			ctx.setVariable("name", userNameString);
// 			ctx.setVariable("donationfee", amount);
// 			ctx.setVariable("hostedBy", hostedBy);
// 			ctx.setVariable("donorName", userNameString);
// 			ctx.setVariable("orderId", transactionId);
// 			ctx.setVariable("donationDate", donationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

// 			sendMail(emailString);
// 	  }


// 	  public void volunteering(String emailString, String userNameString) throws UnsupportedEncodingException, MessagingException{

// 	  }
 
	  
	   

// 	}

// package com.THIS.capstonehope.service;

// import java.io.UnsupportedEncodingException;
// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;
// import java.util.HashMap;
// import java.util.Map;

// import jakarta.mail.MessagingException;
// import jakarta.mail.internet.InternetAddress;
// import jakarta.mail.internet.MimeMessage;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.i18n.LocaleContextHolder;
// import org.springframework.core.env.Environment;
// import org.springframework.core.io.ClassPathResource;
// import org.springframework.core.io.FileSystemResource;
// import org.springframework.http.HttpStatus;
// import org.springframework.http.ResponseEntity;
// import org.springframework.mail.javamail.JavaMailSender;
// import org.springframework.mail.javamail.MimeMessageHelper;
// import org.springframework.stereotype.Component;
// import org.thymeleaf.TemplateEngine;
// import org.thymeleaf.context.Context;

// import com.THIS.capstonehope.Models.Campaign;

// @Component
// public class EmailService {
//     private static final String SPRING_LOGO_IMAGE = "templates/images/spring.png";
//     private static final String PNG_MIME = "image/png";
//     private String MAIL_SUBJECT;
//     private String TEMPLATE_NAME;

//     @Autowired
//     private Environment environment;

//     @Autowired
//     private JavaMailSender mailSender;

//     @Autowired
//     private TemplateEngine htmlTemplateEngine;

//     @Autowired
//     private HtmltoPdf htmlToPdfService;
//     Context ctx;

//     private CampaignService campaign;

// private ResponseEntity<Object> sendMail(String sendTo) throws MessagingException, UnsupportedEncodingException
// 	  {
// 		String mailFrom = environment.getProperty("spring.mail.properties.mail.smtp.from");
// 	    String mailFromName = environment.getProperty("mail.from.name", "Identity");

// 	    final MimeMessage mimeMessage = this.mailSender.createMimeMessage();
// 	    final MimeMessageHelper email;
// 	    email = new MimeMessageHelper(mimeMessage, true, "UTF-8");

// 	    email.setTo(sendTo);
// 	    email.setSubject(MAIL_SUBJECT);
// 	    email.setFrom(new InternetAddress(mailFrom, mailFromName));

// 		final String htmlContent = this.htmlTemplateEngine.process(TEMPLATE_NAME, ctx);

// 	    email.setText(htmlContent, true);

// 	    ClassPathResource clr = new ClassPathResource(SPRING_LOGO_IMAGE);

// 	    email.addInline("springLogo", clr, PNG_MIME);

// 	    mailSender.send(mimeMessage);

// 	    Map<String, String> body = new HashMap<>();
// 	    body.put("message","mail sent successfully");
	    
// 	    return new ResponseEntity<>(body, HttpStatus.OK);

// 	  }
	  
	  
// 	//   @SuppressWarnings("null")
// 	public void register(String emailString, String userNameString)
// 	      throws MessagingException, UnsupportedEncodingException {
// 			MAIL_SUBJECT = "Account Confirmation!";
// 			TEMPLATE_NAME = "registration";
// 			   ctx = new Context(LocaleContextHolder.getLocale());
// 			   ctx.setVariable("email", emailString);
// 			   ctx.setVariable("name", userNameString);
// 			   ctx.setVariable("springLogo", SPRING_LOGO_IMAGE);
// 				sendMail(emailString);
			
// 			}

    

//     public void login(String emailString, String userNameString) throws UnsupportedEncodingException, MessagingException{
//         String OTP ="abc";
//         //String OTP=otpUtil.generateOtp();
//           MAIL_SUBJECT = "Good to have you back!";
//           TEMPLATE_NAME = "login";
//           ctx = new Context(LocaleContextHolder.getLocale());
//           //TO DO add otp methods here
//             ctx.setVariable("OTP", OTP);
//             ctx.setVariable("name", userNameString);
//             ctx.setVariable("springLogo", SPRING_LOGO_IMAGE);
//             TEMPLATE_NAME = "login.html";
//             sendMail(emailString);
// }

// public void sendCertificate(String emailString, String userNameString,String titleString, LocalDateTime endDate) throws MessagingException, UnsupportedEncodingException {
//     MAIL_SUBJECT = "Certificate of Appreciation";
//     TEMPLATE_NAME = "certificate";
//     ctx = createContext();
//     ctx.setVariable("name", userNameString); // Assuming 'hostedBy' represents the name of the campaign host
//     ctx.setVariable("campaignName", titleString);
//     ctx.setVariable("date", endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))); // Assuming 'endDate' represents the date of the campaign
//     sendMail(emailString);
// }
  


// public void donation(String emailString, String userNameString, String amount, String hostedBy, String transactionId, LocalDateTime donationDate) throws UnsupportedEncodingException, MessagingException {
//     MAIL_SUBJECT = "Thank you for your donation";
//     TEMPLATE_NAME = "certificate";
//     Context ctx = createContext();
//     ctx.setVariable("donation", "templates/images/donation_email.png");
//     ctx.setVariable("name", userNameString);
//     ctx.setVariable("donationfee", amount);
//     ctx.setVariable("hostedBy", hostedBy);
//     ctx.setVariable("donorName", userNameString);
//     ctx.setVariable("orderId", transactionId);
//     ctx.setVariable("donationDate", donationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

//     // Send email
//     sendMail(emailString);

//     // Generate PDF
//     String finalHtml = this.htmlTemplateEngine.process(TEMPLATE_NAME, ctx);
//     String pdfFileName = htmlToPdfService.htmlToPdf(finalHtml);

//     // If PDF generation fails, log an error
//     if (pdfFileName == null) {
//         // Log error or handle it appropriately
//         System.err.println("Error generating PDF receipt for donation.");
//         return; // Exit method if PDF generation fails
//     }

//     // Send email with PDF attachment
//     sendMailWithAttachment(emailString, ctx, pdfFileName, "donation_certificate.pdf");
// }

//     private void sendMailWithAttachment(String sendTo, Context ctx, String pdfFileName, String attachmentName) throws MessagingException, UnsupportedEncodingException {
//         MimeMessage mimeMessage = this.mailSender.createMimeMessage();
//         MimeMessageHelper email = new MimeMessageHelper(mimeMessage, true, "UTF-8");

//         String mailFrom = environment.getProperty("spring.mail.properties.mail.smtp.from");
//         String mailFromName = environment.getProperty("mail.from.name", "Identity");

//         email.setTo(sendTo);
//         email.setSubject(MAIL_SUBJECT);
//         email.setFrom(new InternetAddress(mailFrom, mailFromName));

//         final String htmlContent = this.htmlTemplateEngine.process(TEMPLATE_NAME, ctx);
//         email.setText(htmlContent, true);

//         FileSystemResource file = new FileSystemResource(pdfFileName);
//         email.addAttachment(attachmentName, file);

//         mailSender.send(mimeMessage);
//     }

//     private Context createContext() {
//         Context ctx = new Context();
//         // Add necessary variables to the context if needed
//         return ctx;
//     }

 

// }


package com.THIS.capstonehope.service;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import jakarta.activation.DataSource;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.THIS.capstonehope.Models.Campaign;

@Component
public class EmailService {
    private static final String SPRING_LOGO_IMAGE = "templates/images/spring.png";
    private static final String PNG_MIME = "image/png";
    private String MAIL_SUBJECT;
    private String TEMPLATE_NAME;

    @Autowired
    private Environment environment;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine htmlTemplateEngine;

    @Autowired
    private HtmltoPdf htmlToPdfService;
    Context ctx;

    private CampaignService campaign;

    private ResponseEntity<Object> sendMail(String sendTo) throws MessagingException, UnsupportedEncodingException {
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
        body.put("message", "mail sent successfully");

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    public void register(String emailString, String userNameString) throws MessagingException, UnsupportedEncodingException {
        MAIL_SUBJECT = "Account Confirmation!";
        TEMPLATE_NAME = "registration";
        ctx = new Context(LocaleContextHolder.getLocale());
        ctx.setVariable("email", emailString);
        ctx.setVariable("name", userNameString);
        ctx.setVariable("springLogo", SPRING_LOGO_IMAGE);
        sendMail(emailString);
    }

    public void login(String emailString, String userNameString) throws UnsupportedEncodingException, MessagingException {
        String OTP = "abc";
        MAIL_SUBJECT = "Good to have you back!";
        TEMPLATE_NAME = "login";
        ctx = new Context(LocaleContextHolder.getLocale());
        ctx.setVariable("OTP", OTP);
        ctx.setVariable("name", userNameString);
        ctx.setVariable("springLogo", SPRING_LOGO_IMAGE);
        sendMail(emailString);
    }

    public void sendCertificate(String emailString, String userNameString, String titleString, LocalDateTime endDate) throws MessagingException, UnsupportedEncodingException {
        MAIL_SUBJECT = "Certificate of Appreciation";
        TEMPLATE_NAME = "certificate";
        ctx = createContext();
        ctx.setVariable("name", userNameString);
        ctx.setVariable("campaignName", titleString);
        ctx.setVariable("date", endDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        sendMail(emailString);
    }

    public void donation(String emailString, String userNameString, String amount, String hostedBy, String transactionId, LocalDateTime donationDate) throws UnsupportedEncodingException, MessagingException {
        MAIL_SUBJECT = "Thank you for your donation";
        TEMPLATE_NAME = "certificate";
        Context ctx = createContext();
        ctx.setVariable("donation", "templates/images/donation_email.png");
        ctx.setVariable("name", userNameString);
        ctx.setVariable("donationfee", amount);
        ctx.setVariable("hostedBy", hostedBy);
        ctx.setVariable("donorName", userNameString);
        ctx.setVariable("orderId", transactionId);
        ctx.setVariable("donationDate", donationDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        // Send email
        sendMail(emailString);

        // Generate PDF
        String finalHtml = this.htmlTemplateEngine.process(TEMPLATE_NAME, ctx);
        InputStream pdfInputStream = htmlToPdfService.htmlToPdf(finalHtml);

        // If PDF generation fails, log an error
        if (pdfInputStream == null) {
            // Log error or handle it appropriately
            System.err.println("Error generating PDF receipt for donation.");
            return; // Exit method if PDF generation fails
        }

        // Send email with PDF attachment
        sendMailWithAttachment(emailString, ctx, pdfInputStream, "donation_certificate.pdf");
    }

    private void sendMailWithAttachment(String sendTo, Context ctx, InputStream attachmentInputStream, String attachmentName) throws MessagingException, UnsupportedEncodingException {
        MimeMessage mimeMessage = this.mailSender.createMimeMessage();
        MimeMessageHelper email = new MimeMessageHelper(mimeMessage, true, "UTF-8");

        String mailFrom = environment.getProperty("spring.mail.properties.mail.smtp.from");
        String mailFromName = environment.getProperty("mail.from.name", "Identity");

        email.setTo(sendTo);
        email.setSubject(MAIL_SUBJECT);
        email.setFrom(new InternetAddress(mailFrom, mailFromName));

        final String htmlContent = this.htmlTemplateEngine.process(TEMPLATE_NAME, ctx);
        email.setText(htmlContent, true);

        // Attach the InputStream as the attachment
        email.addAttachment(attachmentName, new InputStreamSource() {
            @Override
            public InputStream getInputStream() {
                return attachmentInputStream;
            }
        });
    }

    private Context createContext() {
        Context ctx = new Context();
        // Add necessary variables to the context if needed
        return ctx;
    }
}





