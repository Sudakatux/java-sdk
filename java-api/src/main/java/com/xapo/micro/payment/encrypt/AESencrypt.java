package com.xapo.micro.payment.encrypt;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;

public class AESencrypt {

	private static final String ALGORITHM = "AES";

	
	public String encrypt(String keyString, String data) throws Exception {
		byte[] keyBytes = convertToByteArray(keyString);
		return encrypt(keyBytes, data);
	}

	
	public String encrypt(byte[] keyBytes, String data) throws Exception {
		Key key = generateKey(keyBytes);
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(data.getBytes());
		String encryptedValue = Base64.getEncoder().encodeToString(encVal);
		return encryptedValue;
	}

	
	public String decrypt(String keyString, String encryptedData) throws Exception {
		byte[] keyBytes = convertToByteArray(keyString);
		return decrypt(keyBytes, encryptedData);
	}
	
	public String decrypt(byte[] keyBytes, String encryptedData)
			throws Exception {
		Key key = generateKey(keyBytes);
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decordedValue = Base64.getDecoder().decode(encryptedData);
		byte[] decValue = c.doFinal(decordedValue);
		String decryptedValue = new String(decValue);
		return decryptedValue;
	}

	private Key generateKey(byte[] keyValue) throws Exception {
		Key key = new SecretKeySpec(keyValue, ALGORITHM);
		return key;
	}
	
	
	
	
	public byte[] convertToByteArray(String key) {
	    byte[] b = new byte[key.length()/2];

	    for(int i=0, bStepper=0; i<key.length()+2; i+=2)
	        if(i !=0)
	            b[bStepper++]=((byte) Integer.parseInt((key.charAt(i-2)+""+key.charAt(i-1)), 16));

	    return b;
	}
	

}
