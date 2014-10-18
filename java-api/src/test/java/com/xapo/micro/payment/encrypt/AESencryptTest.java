package com.xapo.micro.payment.encrypt;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class AESencryptTest {

	private AESencrypt aesEncrypt;
	private static String APP_SECRET = "bc4e142dc053407b0028accffc289c18";

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
		String textToEncrypt = "This text is not encrypted";

		String	encryptedText = aesEncrypt.encrypt(APP_SECRET, textToEncrypt);
		assertNotEquals("encrypted text must be different that orignal text ",textToEncrypt, encryptedText);
		
		String decryptedText = aesEncrypt.decrypt(APP_SECRET, encryptedText);
		assertEquals("decripted text must be equals to the original one",textToEncrypt, decryptedText);	
	}
	
	
	@Test
	@Ignore("No encrypta de la misma forma que el PHP")
	public void testEncryptAppSecretRdequest() throws Exception {
		String request = "{\"amount_BIT\": 0.01, \"sender_user_cellphone\": \"+5491112341234\", \"receiver_user_email\": \"fernando.taboada@xapo.com\", \"pay_object_id\": \"to0210\", \"sender_user_id\": \"\", \"receiver_user_id\": \"r0210\", \"timestamp\": 1413494551587, \"sender_user_email\": \"sender@xapo.com\"}";
		String expedtedEncryptedRequest = "Ji%2F9T5w8UAou0Qo4vPIn39OmOKBds5rkezFHi8Z788JJwDo339g%2FvbJqZHcSIPcbmDoFm3eGFq4PtnEpRgSLTy4fDWdJz7s8pUi%2FEaI6QEyCdYkaT9siN3S7XXhCJyhetGQwr1cK6GmbKzuVlpV29TvqvNuQfegzUFMLb02qm5QOhFV13vIN1T1%2FpH81KzqFDIYuuY8Xm%2FI%2FLj4aC9R13hoZD9t0T13LgPGFn9%2FX7D7G2mrgjYINBQkEJxbJrCLiXe6wbOsu9udS%2BmX4WEBOVkOojl7WVScNTv91c8yQgd6t96esp3SpGSXRg7mWQzG%2BEsIWpc%2BP%2BQz1sFmnUXMNNVKHwGaqPhNNXjdGCHVxqow";

		String	encryptedReq = aesEncrypt.encrypt(APP_SECRET, request);
		assertEquals("encrypted request ", expedtedEncryptedRequest, encryptedReq);
	}	

}
