package com.xapo.utils.url;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class QueryString {

    private StringBuilder query = new StringBuilder();

    public void add(String name, String value) {
        if (query.length() != 0)
            query.append("&");

        encode(name, value);
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

    public String getQuery() {
        return query.toString();
    }

    public String toString() {
        return getQuery();
    }

}