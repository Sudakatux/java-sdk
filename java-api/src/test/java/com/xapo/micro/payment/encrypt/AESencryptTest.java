package com.xapo.micro.payment.encrypt;

import static org.junit.Assert.*;

import javax.crypto.Cipher;

import org.junit.Before;
import org.junit.Test;

public class AESencryptTest {

	private AESencrypt aesEncrypt;

	@Before
	public void setUp() throws Exception {
		aesEncrypt = new AESencrypt(); 
	}
	
 
	@Test
	public void testEncryptBytArray() throws Exception {
		 byte[] keyValue = new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't','S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };
		String textToEncrypt = "This text is not encrypted";

		String	encryptedText = aesEncrypt.encrypt(keyValue, textToEncrypt);
		assertNotEquals("encrypted text must be different that orignal text ",textToEncrypt, encryptedText);
		
		String decryptedText = aesEncrypt.decrypt(keyValue, encryptedText);
		assertEquals("decripted text must be equals to the original one",textToEncrypt, decryptedText);	
	}

	
	@Test
	public void testEncryptAppSecret() throws Exception {
		String APP_SECRET = "bc4e142dc053407b0028accffc289c18";
		 
		String textToEncrypt = "This text is not encrypted";

		String	encryptedText = aesEncrypt.encrypt(APP_SECRET, textToEncrypt);
		assertNotEquals("encrypted text must be different that orignal text ",textToEncrypt, encryptedText);
		
		String decryptedText = aesEncrypt.decrypt(APP_SECRET, encryptedText);
		assertEquals("decripted text must be equals to the original one",textToEncrypt, decryptedText);	
	}
	

}
