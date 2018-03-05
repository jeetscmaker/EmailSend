package com.emailsender;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainRunner {
	
	Properties prop = getProperties("application.properties");
	
	public static void main(String[] args){
		String[] arr = readMessageFile();
//		EmailSender se = new EmailSender(); 
		BufferedReader br = null;
		try {
			ExecutorService e = Executors.newFixedThreadPool(10);
			br = new BufferedReader(new FileReader("recipients.txt"));
		    String line = br.readLine();
		    String email = null;
		    while (line != null) {
		    	email = line.trim();
		    	e.execute(new WorkerThread(email, arr[0], arr[1]));
		        line = br.readLine();
		        /*se.sendMail(email, arr[0], arr[1]);
		        System.out.println(email);*/
		    }
		    e.shutdown();
		    br.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

	/**
	 * 
	 */
	public static String[] readMessageFile() {
		String[] subjectAndMessage = new String[2];
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader("message.txt"));
		    String subjectLine = br.readLine();
		    String line = br.readLine();
		    StringBuilder content = new StringBuilder();
		    while (!line.contains("*****")) {
		    	if(line.equals("###")){
			        line = br.readLine();
		    	}
		    	content.append(line);
		    	content.append(System.lineSeparator());
		        line = br.readLine();
		    }
		    String messageContent = content.toString();
		    br.close();
		    subjectAndMessage[0] = subjectLine;
		    subjectAndMessage[1] = messageContent;
		    return subjectAndMessage;
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	public static Properties getProperties(String file) {
		Properties prop = new Properties();
		InputStream input = null;
		try {
			input = new FileInputStream(file);
			prop.load(input);
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return prop;
	}
}

class WorkerThread implements Runnable{
	private String email;
	private String subjectLine;
	private String messageText;
	EmailSender se = new EmailSender();
	public WorkerThread(String email, String subjectLine, String messageText){
		this.email = email;
		this.subjectLine = subjectLine;
		this.messageText = messageText;
	}
	@Override
	public void run() {
		se.sendMail(email, subjectLine, messageText);
        System.out.println(email);
	}
}
