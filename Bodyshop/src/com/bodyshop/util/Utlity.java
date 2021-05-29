package com.bodyshop.util;

import java.io.File;
import java.io.FileInputStream;

import org.apache.tomcat.util.codec.binary.Base64;

public class Utlity {
	
   
public static String encodeImage(String image)
{
	File f =  new File(image);
    String encodstring="";
	try {
		encodstring = encodeFileToBase64Binary(f);
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return encodstring;
	}
    
    
    private static String encodeFileToBase64Binary(File file) throws Exception{
         FileInputStream fileInputStreamReader = new FileInputStream(file);
         byte[] bytes = new byte[(int)file.length()];
         fileInputStreamReader.read(bytes);
         return new String(Base64.encodeBase64(bytes), "UTF-8");
     }
}
