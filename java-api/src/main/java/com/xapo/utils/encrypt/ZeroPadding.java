package com.xapo.utils.encrypt;

/**
 * Pad and unpad using Zero padding scheme.
 *
 * Created by frepond on 6/11/14.
 */
public class ZeroPadding implements Padding {
    public String pad(String source) {
        int size = 16;
        int x = source.length() % size;
        int padLength = size - x;


        for (int i = 0; i < padLength; i++) {
            source += (char) 0;
        }

        return source;
    }

    public byte[] unpad(byte[] decrypted) {
        //Remove trailing zeroes
        if (decrypted.length > 0) {
            int trim = 0;
            for (int i = decrypted.length - 1; i >= 0; i--) if (decrypted[i] == 0) trim++;

            if (trim > 0) {
                byte[] newArray = new byte[decrypted.length - trim];
                System.arraycopy(decrypted, 0, newArray, 0, decrypted.length - trim);
                decrypted = newArray;
            }
        }
        return decrypted;
    }
}
