package com.xapo.utils.encrypt;

import org.bouncycastle.util.encoders.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;

public class MCrypt {
    static {
        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
    }

    private Padding padding;
    private Cipher cipher;

    public MCrypt() {
        this(new PKCS7Padding());
    }

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

    public String encrypt(String secretKey, String text) throws Exception {
        SecretKeySpec keyspec = new SecretKeySpec(secretKey.getBytes("UTF-8"), "AES");

        if (text == null || text.length() == 0)
            throw new Exception("Empty string");

        byte[] encrypted = null;
        cipher.init(Cipher.ENCRYPT_MODE, keyspec);

        encrypted = cipher.doFinal(padding.pad(text).getBytes("UTF-8"));

        return new String(Base64.encode(encrypted));
    }

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