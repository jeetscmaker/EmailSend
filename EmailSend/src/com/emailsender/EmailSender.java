package com.emailsender;

import java.util.Calendar;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailSender {
public static Properties prop = MainRunner.getProperties("application.properties");

private static int counter = 1;
private final int exceptionCounterLimit = 3;
	
	private static final String MAIL_SMTP_HOST1 = prop.getProperty("mail.smtp.host");
	private static final String SOCKET_FACTORY_PORT1 = prop.getProperty("mail.smtp.socketFactory.port");
	private static final String SOCKET_FACTORY_CLASS1 = prop.getProperty("mail.smtp.socketFactory.class");
	private static final String SMTP_AUTH_1 = prop.getProperty("mail.smtp.auth");
	private static final String SMTP_PORT_1 = prop.getProperty("mail.smtp.port");
	private static final String EMAIL_SENDER1 = prop.getProperty("mail.sender");
	private static final String EMAIL_SENDER_PASSWORD1 = prop.getProperty("mail.sender.password");
	
	private static final String MAIL_SMTP_HOST2 = prop.getProperty("mail.smtp.host2");
	private static final String SOCKET_FACTORY_PORT2 = prop.getProperty("mail.smtp.socketFactory.port2");
	private static final String SOCKET_FACTORY_CLASS2 = prop.getProperty("mail.smtp.socketFactory.class2");
	private static final String SMTP_AUTH_2 = prop.getProperty("mail.smtp.auth2");
	private static final String SMTP_PORT_2 = prop.getProperty("mail.smtp.port2");
	private static final String EMAIL_SENDER2 = prop.getProperty("mail.sender2");
	private static final String EMAIL_SENDER_PASSWORD2 = prop.getProperty("mail.sender.password2");
	
	private static final String MAIL_SMTP_HOST3 = prop.getProperty("mail.smtp.host3");
	private static final String SOCKET_FACTORY_PORT3 = prop.getProperty("mail.smtp.socketFactory.port3");
	private static final String SOCKET_FACTORY_CLASS3 = prop.getProperty("mail.smtp.socketFactory.class3");
	private static final String SMTP_AUTH_3 = prop.getProperty("mail.smtp.auth3");
	private static final String SMTP_PORT_3 = prop.getProperty("mail.smtp.port3");
	private static final String EMAIL_SENDER3 = prop.getProperty("mail.sender3");
	private static final String EMAIL_SENDER_PASSWORD3 = prop.getProperty("mail.sender.password3");
	
	private static final String SET_AS_SENDER = prop.getProperty("mail.setAsSender");
	
	/*public static void main(String[] args) {
		sendMail("jitendrakumar@zebi.co", "123456", "sendOTP");
		System.out.println("mail sent successfully");
	}*/
	
	public boolean sendMail(String email, String subjectLine, String Text) {
//		System.out.println("sendMail invoked ...");
		boolean b =  false;
		int exceptionCounter = 0;
		if(counter == 1){
			b = sendMail1(email, subjectLine, Text, exceptionCounter);
			counter++;
//			System.out.println("Email sent through sendMail1");
		}
		else if(counter == 2){
			b = sendMail2(email, subjectLine, Text, exceptionCounter);
			counter++;
//			System.out.println("Email sent through sendMail2");
		}else{
			b = sendMail3(email, subjectLine, Text, exceptionCounter);
			counter = 1;
//			System.out.println("Email sent through sendMail3");
		}
		return b;
	}

	public boolean sendMail1(String email, String subjectLine, String Text, int exceptionCounter) {
		if(exceptionCounter == exceptionCounterLimit){
			return false;
		}
		Calendar cal = Calendar.getInstance();
		boolean b = true;
//		System.out.println("Inside sendEmail1...");
//		System.out.println("email starting time: "
//				+ System.currentTimeMillis());
		Properties props = new Properties();
		props.put("mail.smtp.host", MAIL_SMTP_HOST1);
		props.put("mail.smtp.starttls.enable",true);
		props.put("mail.smtp.socketFactory.port", SOCKET_FACTORY_PORT1);
		props.put("mail.smtp.socketFactory.class", SOCKET_FACTORY_CLASS1);
		props.put("mail.smtp.auth", SMTP_AUTH_1);
		props.put("mail.smtp.port", SMTP_PORT_1);
		
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								EMAIL_SENDER1, EMAIL_SENDER_PASSWORD1);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(SET_AS_SENDER));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email));
			setEmailSubjectAndText(subjectLine, message, Text);

			Transport.send(message);
			System.out.println("email sending time: "
					+ cal.getTime());
			
			return b;

		}  catch (Exception e) {
			exceptionCounter++;
			e.printStackTrace();
			b = sendMail2(email, subjectLine, Text, exceptionCounter);
			return b;
		}
		
	}
	
	
	public boolean sendMail2(String email, String subjectLine, String Text, int exceptionCounter) {
		if(exceptionCounter == exceptionCounterLimit){
			return false;
		}
		boolean b = true;
		Calendar cal = Calendar.getInstance();
//		System.out.println("Inside sendEmail2...");
//		System.out.println("email starting time: "
//				+ System.currentTimeMillis());
		Properties props = new Properties();
		props.put("mail.smtp.host", MAIL_SMTP_HOST2);
		props.put("mail.smtp.socketFactory.port", SOCKET_FACTORY_PORT2);
		props.put("mail.smtp.socketFactory.class", SOCKET_FACTORY_CLASS2);
		props.put("mail.smtp.auth", SMTP_AUTH_2);
		props.put("mail.smtp.port", SMTP_PORT_2);
		
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								EMAIL_SENDER2, EMAIL_SENDER_PASSWORD2);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(SET_AS_SENDER));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email));
			setEmailSubjectAndText(subjectLine, message, Text);

//			System.out.println(EMAIL_SENDER2);
			Transport.send(message);
			System.out.println("email sending time: "
					+ cal.getTime());
			return b;

		} catch (Exception e) {
			exceptionCounter++;
			e.printStackTrace();
			b = sendMail3(email, subjectLine, Text, exceptionCounter);
			return b;
		}
	}
	
	public boolean sendMail3(String email, String subjectLine, String Text, int exceptionCounter) {
		if(exceptionCounter == exceptionCounterLimit){
			return false;
		}
		boolean b = true;
		Calendar cal = Calendar.getInstance();
//		System.out.println("Inside sendEmail3...");
//		System.out.println("email starting time: "
//				+ System.currentTimeMillis());
		Properties props = new Properties();
		props.put("mail.smtp.host", MAIL_SMTP_HOST3);
		props.put("mail.smtp.socketFactory.port", SOCKET_FACTORY_PORT3);
		props.put("mail.smtp.socketFactory.class", SOCKET_FACTORY_CLASS3);
		props.put("mail.smtp.auth", SMTP_AUTH_3);
		props.put("mail.smtp.port", SMTP_PORT_3);
		
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(
								EMAIL_SENDER3, EMAIL_SENDER_PASSWORD3);
					}
				});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(SET_AS_SENDER));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(email));
			setEmailSubjectAndText(subjectLine, message, Text);

			Transport.send(message);
			System.out.println("email sending time: "
					+ cal.getTime());
			return true;

		} catch (Exception e) {
			exceptionCounter++;
			e.printStackTrace();
			b = sendMail1(email, subjectLine, Text, exceptionCounter);
			return b;
		}
	}

	/**
	 * @param email
	 * @param password
	 * @param method
	 * @param message
	 * @throws MessagingException
	 */
	public void setEmailSubjectAndText(String subjectLine, Message message, String Text)
			throws MessagingException {
			message.setSubject(subjectLine);
			message.setText(Text);
	}
}
