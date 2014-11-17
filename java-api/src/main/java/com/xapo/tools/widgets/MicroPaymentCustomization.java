package com.xapo.tools.widgets;

/**
 * Micro payment button customization options.
 *
 * This function is intended to be a helper for creating empty micro
 * payments buttons customization but also serves for documenting. A
 * hash with the intended fields would give the same results.
 *
 * Created by frepond on 17/11/14.
 */
public class MicroPaymentCustomization {
    /**
     * Text to appear in the login screen. Default: "Support content creators by sending them bits.
     * New users receive 50 bits to get started!"
     */
    private String predefinedPayValues = "";

    /**
     * A string of comma separated amount values, e.g. "1, 5, 10".
     */
    private String loginCellphoneHeaderTitle = "";

    /**
     * Optional CSS button customization ("red" | "grey").
     */
    private String buttonCss = "";

    public String getPredefinedPayValues() {
        return predefinedPayValues;
    }

    public void setPredefinedPayValues(String predefinedPayValues) {
        this.predefinedPayValues = predefinedPayValues;
    }

    public String getLoginCellphoneHeaderTitle() {
        return loginCellphoneHeaderTitle;
    }

    public void setLoginCellphoneHeaderTitle(String loginCellphoneHeaderTitle) {
        this.loginCellphoneHeaderTitle = loginCellphoneHeaderTitle;
    }

    public String getButtonCss() {
        return buttonCss;
    }

    public void setButtonCss(String buttonCss) {
        this.buttonCss = buttonCss;
    }
}
