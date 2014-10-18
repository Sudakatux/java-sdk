package com.xapo.micro.payment.encrypt;

import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;
public class AESTest {

	private static String APP_SECRET = "bc4e142dc053407b0028accffc289c18";

	
	@Test
	public void test() throws Exception {
		
		
		
		AES aes = new AES();		
		
		final String strToEncrypt = "My text to encrypt";
		final String strPssword = "encryptor key";
		aes.setKey(strPssword);
		aes.encrypt(strToEncrypt.trim());
//		System.out.println("String to Encrypt: " + strToEncrypt);
//		System.out.println("Encrypted: " + aes.getEncryptedString());
		final String strToDecrypt = aes.getEncryptedString();
		aes.decrypt(strToDecrypt.trim());
//		System.out.println("String To Decrypt : " + strToDecrypt);
//		System.out.println("Decrypted : " + aes.getDecryptedString());
	
	assertEquals(strToEncrypt, aes.getDecryptedString());
	}
	
	@Test
	@Ignore("No encrypta de la misma forma que el PHP")
	public void testEncryptAppSecretRdequest() throws Exception {
		String request = "{\"amount_BIT\": 0.01, \"sender_user_cellphone\": \"+5491112341234\", \"receiver_user_email\": \"fernando.taboada@xapo.com\", \"pay_object_id\": \"to0210\", \"sender_user_id\": \"\", \"receiver_user_id\": \"r0210\", \"timestamp\": 1413494551587, \"sender_user_email\": \"sender@xapo.com\"}";
		String expedtedEncryptedRequest = "Ji%2F9T5w8UAou0Qo4vPIn39OmOKBds5rkezFHi8Z788JJwDo339g%2FvbJqZHcSIPcbmDoFm3eGFq4PtnEpRgSLTy4fDWdJz7s8pUi%2FEaI6QEyCdYkaT9siN3S7XXhCJyhetGQwr1cK6GmbKzuVlpV29TvqvNuQfegzUFMLb02qm5QOhFV13vIN1T1%2FpH81KzqFDIYuuY8Xm%2FI%2FLj4aC9R13hoZD9t0T13LgPGFn9%2FX7D7G2mrgjYINBQkEJxbJrCLiXe6wbOsu9udS%2BmX4WEBOVkOojl7WVScNTv91c8yQgd6t96esp3SpGSXRg7mWQzG%2BEsIWpc%2BP%2BQz1sFmnUXMNNVKHwGaqPhNNXjdGCHVxqow";

		AES aes = new AES();	
		aes.setKey(APP_SECRET);
		String	encryptedReq = aes.encrypt(request);
		
		assertEquals("encrypted request ", expedtedEncryptedRequest, encryptedReq);
	}	
	
	
}
