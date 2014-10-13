package com.xapo.micro.payment.encrypt;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;

public class AESencrypt {

	private static final String ALGORITHM = "AES";

	public static String encrypt(byte[] keyValue, String data) throws Exception {
		Key key = generateKey(keyValue);
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.ENCRYPT_MODE, key);
		byte[] encVal = c.doFinal(data.getBytes());
		String encryptedValue = Base64.getEncoder().encodeToString(encVal);
		return encryptedValue;
	}

	public static String decrypt(byte[] keyValue, String encryptedData)
			throws Exception {
		Key key = generateKey(keyValue);
		Cipher c = Cipher.getInstance(ALGORITHM);
		c.init(Cipher.DECRYPT_MODE, key);
		byte[] decordedValue = Base64.getDecoder().decode(encryptedData);
		byte[] decValue = c.doFinal(decordedValue);
		String decryptedValue = new String(decValue);
		return decryptedValue;
	}

	private static Key generateKey(byte[] keyValue) throws Exception {
		Key key = new SecretKeySpec(keyValue, ALGORITHM);
		return key;
	}

}
