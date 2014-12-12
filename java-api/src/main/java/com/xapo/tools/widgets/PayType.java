package com.xapo.tools.widgets;

/**
 * Kind of micro payment actions supported by the widget.
 *
 * Created by frepond on 17/11/14.
 */
public interface PayType {
    public static final String NONE = "";
    public static final String TIP = "tip";
    public static final String PAY = "pay";
    public static final String DEPOSIT = "deposit";
    public static final String DONATE = "donate";
    public static final String OAUTH = "oauth2";
}
