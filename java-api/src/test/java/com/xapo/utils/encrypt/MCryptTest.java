package com.xapo.utils.encrypt;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.security.Security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class MCryptTest {

	private MCrypt aesEncrypt;
	private MCrypt zeroPaddingEncrypt;
	private static String APP_SECRET = "c533a6e606fb62ccb13e8baf8a95cbdc";

	@Before
	public void setUp() throws Exception {
		Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
		aesEncrypt = new MCrypt();
		zeroPaddingEncrypt = new MCrypt(new ZeroPadding());
	}
	
	@Test
	public void testEncryptDecrypt() throws Exception {
		String textToEncrypt = "This text is not encrypted";

		String	encryptedText = aesEncrypt.encrypt(APP_SECRET, textToEncrypt);
		assertNotEquals("encrypted text must be different that orignal text ",textToEncrypt, encryptedText);
		
		String decryptedText = aesEncrypt.decrypt(APP_SECRET, encryptedText);

		assertEquals("decripted text must be equals to the original one", textToEncrypt, decryptedText);
	}


	@Test
	public void testEncryptDecryptZeroPadding() throws Exception {
		String textToEncrypt = "This text is not encrypted";

		String	encryptedText = zeroPaddingEncrypt.encrypt(APP_SECRET, textToEncrypt);
		assertNotEquals("encrypted text must be different that orignal text ",textToEncrypt, encryptedText);

		String decryptedText = zeroPaddingEncrypt.decrypt(APP_SECRET, encryptedText);

		assertEquals("decripted text must be equals to the original one", textToEncrypt, decryptedText);
	}
	
	
	@Test
	public void testEncryptJson() throws Exception {
		String request = "{\"sender_user_id\":\"\",\"sender_user_email\":\"sender@xapo.com\",\"sender_user_cellphone\":\"+5491112341234\",\"receiver_user_id\":\"r0210\",\"receiver_user_email\":\"fernando.taboada@xapo.com\",\"pay_object_id\":\"to0210\",\"amount_BIT\":0.01,\"timestamp\":1413908141835}";
		String expedtedEncryptedRequest = "C/6OaxS0rh3jMhH90kRYyp3y+U5ADcCgMLCyz2P5ssGngf3AjyMMaE548XpQ7sdFcJvpShTGq2QlsaUgakMzoA1aHLITCfNMGTQAkTrD6Ga31BWQbgJ8/alHfaMyBnbLXalaTOSM2zGmHHhn0ku9pxCfWQ3kCPqkurDTYazQLmfYRVYFHe7W326sA9+2pNSb5OPWtt39ZmSTkol+wtJYjyO0fiJlvEyXVdzEVms0JPdFl8ks+aDCLBme7XQyJ8ExUkFaKGA67Q7zukT3clUXYGFMpwAH8N5Ep/G8dm6oU34ECygnziA23Fm+VzSyaElv3v8lQ5WLCAgvkYGN4EfOTQ==";

		String	encryptedReq = aesEncrypt.encrypt(APP_SECRET, request);
		assertEquals("encrypted request ", expedtedEncryptedRequest, encryptedReq);
	}

	@Test
	public void testEncryptHello() throws Exception {
		String textToEncrypt = "hello from aes encryption!";

		String encryptedText = aesEncrypt.encrypt(APP_SECRET, textToEncrypt);
		String expected = "Jr/PhEWZBiqW/iMnoObyPm5M4azsUYLwLxk50Wi9eoo=";
		assertEquals("encrypted text must be equals ", expected, encryptedText);
	}
}
