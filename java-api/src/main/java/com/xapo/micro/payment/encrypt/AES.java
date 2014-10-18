package com.xapo.micro.payment.encrypt;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * Aes encryption
 */
public class AES {

	private SecretKeySpec secretKey;
	private byte[] key;
	private String decryptedString;
	private String encryptedString;

	public void setKey(String myKey) throws Exception {
		key = myKey.getBytes("UTF-8");
		MessageDigest sha = MessageDigest.getInstance("SHA-1");
		key = sha.digest(key);
		key = Arrays.copyOf(key, 16); // use only first 128 bit
		secretKey = new SecretKeySpec(key, "AES");
	}

	public String getDecryptedString() {
		return decryptedString;
	}

	public void setDecryptedString(String decryptedString) {
		this.decryptedString = decryptedString;
	}

	public String getEncryptedString() {
		return encryptedString;
	}

	public void setEncryptedString(String encryptedString) {
		this.encryptedString = encryptedString;
	}

	public String encrypt(String strToEncrypt) throws Exception {

		Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		setEncryptedString(Base64.encodeBase64String(cipher
				.doFinal(strToEncrypt.getBytes("UTF-8"))));
		
		return encryptedString;

	}

	public String decrypt(String strToDecrypt)  throws Exception {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5PADDING");
			cipher.init(Cipher.DECRYPT_MODE, secretKey);
			setDecryptedString(new String(cipher.doFinal(Base64
					.decodeBase64(strToDecrypt))));
		return decryptedString;
	}
	
	

}