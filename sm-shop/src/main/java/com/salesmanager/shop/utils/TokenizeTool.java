package com.salesmanager.shop.utils;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TokenizeTool {
	
	private final static String CIPHER = "AES/ECB/PKCS5Padding";

	private static final Logger LOGGER = LoggerFactory.getLogger(TokenizeTool.class);
	
	private TokenizeTool(){}
	
	private static SecretKey key = null;
	
	static {
		
		try {
			
			KeyGenerator keygen = KeyGenerator.getInstance("DES");
		    key = keygen.generateKey();
			
		} catch (Exception e) {
			LOGGER.error("Cannot generate key",e);
		}
		


		
		
	}
	
	public static String tokenizeString(String token) throws Exception {
		
		Cipher aes = Cipher.getInstance(CIPHER); 
		aes.init(Cipher.ENCRYPT_MODE, key); 
		byte[] ciphertext = aes.doFinal(token.getBytes()); 
		
		return new String(ciphertext);
		
		
	}

}
