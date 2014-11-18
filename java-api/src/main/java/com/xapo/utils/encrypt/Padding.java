package com.xapo.utils.encrypt;

/**
 * Padding scheme interface.
 *
 * Created by frepond on 6/11/14.
 */
public interface Padding {
    /**
     * Pad a string.
     *
     * @param source the string to be padded.
     * @return
     */
    public String pad(String source);

    /**
     * Unpad a string.
     *
     * @param source the string to be unpadded.
     * @return
     */
    public byte[] unpad(byte[] source);
}
