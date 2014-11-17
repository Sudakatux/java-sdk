package com.xapo.tools.widgets;

/**
 * Kind of micro payment actions supported by the widget.
 *
 * Created by frepond on 17/11/14.
 */
public interface PayType {
    public static final String NONE = "";
    public static final String TIP = "Tip";
    public static final String PAY = "Pay";
    public static final String DEPOSIT = "Deposit";
    public static final String DONATE = "Donate";
    public static final String OAUTH = "OAuth";
}
