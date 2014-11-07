package com.xapo.utils.encrypt;

/**
 * Created by frepond on 6/11/14.
 */
public interface Padding {
    public String pad(String source);

    public byte[] unpad(byte[] source);
}
