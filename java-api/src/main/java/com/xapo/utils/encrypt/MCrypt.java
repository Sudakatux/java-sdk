package com.xapo.utils.encrypt;

import org.bouncycastle.util.encoders.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

/**
 * Encryption and decryption utils for XAPO services.
 *
 * @author Federico C. Repond
 */
public class MCrypt {
    static {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }

    private Padding padding;
    private Cipher cipher;

    public MCrypt() {
        this(new PKCS7Padding());
    }

    /**
     * Create a new encryption and decryption class with a given padding scheme.
     *
     * @param padding the padding scheme to be used.
     */
    public MCrypt(Padding padding) {
        try {
            this.cipher = Cipher.getInstance("AES/ECB/NoPadding", "BC");
            this.padding = padding;
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            e.printStackTrace();
        }
    }

    /**
     * Encrypt a text with the given key. The result is base 64 encoded.
     *
     * @param secretKey the key used to encrypt the data.
     * @param text the text to encrypt.
     * @return base 64 encode of the encrypted text.
     * @throws Exception
     */
    public String encrypt(String secretKey, String text) throws Exception {
        SecretKeySpec keyspec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");

        if (text == null || text.length() == 0)
            throw new Exception("Empty string");

        byte[] encrypted = null;
        cipher.init(Cipher.ENCRYPT_MODE, keyspec);

        encrypted = cipher.doFinal(padding.pad(text).getBytes("UTF-8"));

        return new String(Base64.encode(encrypted));
    }

    /**
     * Decrypt a base 64 encoded and encrypted text.
     *
     * @param secretKey
     * @param code
     * @return
     * @throws Exception
     */
    public String decrypt(String secretKey, String code) throws Exception {
        SecretKeySpec keyspec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");

        if (code == null || code.length() == 0)
            throw new Exception("Empty string");

        byte[] decrypted = null;
        cipher.init(Cipher.DECRYPT_MODE, keyspec);

        decrypted = cipher.doFinal(Base64.decode(code));
        decrypted = padding.unpad(decrypted);

        return new String(decrypted);
    }
}