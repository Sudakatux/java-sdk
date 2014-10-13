package com.xapo.micro.payment.encrypt;

import static org.junit.Assert.*;

import org.junit.Test;

public class AESencryptTest {

 
	@Test
	public void testEncrypt() throws Exception {
		 byte[] keyValue = new byte[] { 'T', 'h', 'e', 'B', 'e', 's', 't','S', 'e', 'c', 'r','e', 't', 'K', 'e', 'y' };
		String textToEncrypt = "This text  must be encrypted";

		String	encryptedText = AESencrypt.encrypt(keyValue, textToEncrypt);
		assertNotEquals("encrypted text must be different that orignal text ",textToEncrypt, encryptedText);
		
		String decryptedText = AESencrypt.decrypt(keyValue, encryptedText);
		assertEquals("decripted text must be equals to the original one",textToEncrypt, decryptedText);	
	}
}
