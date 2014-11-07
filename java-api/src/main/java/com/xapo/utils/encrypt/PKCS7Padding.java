package com.xapo.utils.encrypt;

/**
 * Created by frepond on 6/11/14.
 */
public class PKCS7Padding implements Padding {

    public String pad(String source) {
        int size = 16;
        int x = source.length() % size;
        int padLength = size - x;


        for (int i = 0; i < padLength; i++) {
            source += (char) padLength;
        }

        return source;
    }

    public byte[] unpad(byte[] source) {
        //Remove trailing zeroes
        if (source.length > 0) {
            int trim = 0;
            for (int i = source.length - 1; i >= 0; i--) if (source[i] < 16) trim++;

            if (trim > 0) {
                byte[] newArray = new byte[source.length - trim];
                System.arraycopy(source, 0, newArray, 0, source.length - trim);
                source = newArray;
            }
        }
        return source;
    }
}
