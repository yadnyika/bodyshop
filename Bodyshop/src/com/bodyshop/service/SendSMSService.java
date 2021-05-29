package com.bodyshop.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/*import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class SendSMSService {
	// Find your Account Sid and Token at twilio.com/user/account
	  public static final String ACCOUNT_SID = "AC3bb77e9da655e8e3e7a064a940fc02ac";
	  public static final String AUTH_TOKEN = "bd6c7ca6664f915879d4a67ed338e42a";

	  public static void main(String[] args) {
	    Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

	    Message message = Message.creator(new PhoneNumber("+919867402725"),
	        new PhoneNumber("+919404786012"), 
	        "This is the ship that made the Kessel Run in fourteen parsecs?").create();

	    System.out.println(message.getSid());
	  }
	public static String sendSMS()
	{
		try {
			// Construct data
			String apiKey = "apikey=" + "XFSN/9OCngA-xxOOvX5nt0k0xPbdTqZFmtJfJraz8c";
			String message = "&message=" + "This is your message";
			String sender = "&sender=" + "TXTLCL";
			String numbers = "&numbers=" + "918329719275";
			
			// Send data
			HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?").openConnection();
			String data = apiKey + numbers + message + sender;
			conn.setDoOutput(true);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
			conn.getOutputStream().write(data.getBytes("UTF-8"));
			final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			final StringBuffer stringBuffer = new StringBuffer();
			String line;
			while ((line = rd.readLine()) != null) {
				stringBuffer.append(line);
			}
			rd.close();
			
			return stringBuffer.toString();
		} catch (Exception e) {
			System.out.println("Error SMS "+e);
			return "Error "+e;
		}
	}
	
	public static void main(String[] args) {
		String message=sendSMS();
		System.out.println("message "+message);
	}
}
*/