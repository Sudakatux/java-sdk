package com.xapo.utils.url;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Utility class for building http query strings.
 */
public class QueryString {

    private StringBuilder query = new StringBuilder();

    /**
     * Add a new query parameter.
     *
     * @param name query parameter's name.
     * @param value query parameter's value.
     */
    public void add(String name, String value) {
        if (query.length() != 0)
            query.append("&");

        encode(name, value);
    }

    /**
     * Return the query string representation of this class.
     *
     * @return the encoded query string.
     */
    public String getQuery() {
        return query.toString();
    }

    public String toString() {
        return getQuery();
    }

    private void encode(String name, String value) {
        try {
            query.append(URLEncoder.encode(name, "UTF-8"));
            query.append("=");
            query.append(URLEncoder.encode(value, "UTF-8"));
        } catch (UnsupportedEncodingException ex) {
            throw new RuntimeException("Broken VM does not support UTF-8");
        }
    }
}