package com.bodyshop.service;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;



public class Utility {

	 
    private static SecretKeySpec secretKey;
    private static byte[] key;
 
    public static void setKey(String myKey)
    {
        MessageDigest sha = null;
        try {
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 16);
            secretKey = new SecretKeySpec(key, "AES");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
    }
    /************ here we are performing encryption************/
    public static String encrypt(String stringToEncrypt, String secret)
    {
        try
         {
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(stringToEncrypt.getBytes("UTF-8")));
        }
        catch (Exception e)
        {
            System.out.println("Error arised while encrypting: " + e.toString());
        }
        return null;
    }
    /************ here we are performing decryption************/
    public static String decrypt(String stringToDecrypt, String secret)
    {
        try
        {//cipher is enc/dec algorithm
            setKey(secret);
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(stringToDecrypt)));
        }
        catch (Exception e)
        {
            System.out.println("Error arised while decrypting: " + e.toString());
        }
        return null;
    }
    
    public static String getRandomNo()
    {
    
    	Timestamp t=new Timestamp(System.currentTimeMillis());
    	long k=t.getTime();
    	String random=String.valueOf(k);
    	
    	//System.out.println("system time "+random.length() + " value "+random.substring(8, 12));
   

    	return random.substring(8, 12);
    }
    
    public static String getOrderId()
    {
    	String orderId="ORID"+getRandomNo();
    	
    	
    	//System.out.println("system time "+random.length() + " value "+random.substring(8, 12));
   

    	return orderId;
    }
    
    
    public static String getAddressId()
    {
    	String AddressrId="ADRID"+getRandomNo();
    	
    	
    	//System.out.println("system time "+random.length() + " value "+random.substring(8, 12));
   

    	return AddressrId;
    }
    
    public static String getCookieId()
    {
    	String cookieId="CARTID"+getRandomNo();
    	
    	
    	//System.out.println("system time "+random.length() + " value "+random.substring(8, 12));
   

    	return cookieId;
    }
    
    public static void main(String[] args) {
    	System.out.println("addr id "+getAddressId());
	}
}
