package com.xapo.tools.widgets;


/**
 * Micro payment button configuration options.
 *
 * This function is intended to be a helper for creating empty micro
 * payments buttons configuration but also serves for documenting. A
 * hash with the intended fields would give the same results.
 *
 * @author Fernando D. García
 */
public class MicroPaymentConfig {
    /**
     * The id of the user sending the payment.
     */
    private String senderUserId = "";

    /**
     * The email of the user sending the payment.
     */
    private String senderUserEmail = "";

    /**
     * The cellphone number of the user sending the payment.
     */
    private String senderUserCellphone = "";

    /**
     * The id of the user receiving the payment.
     */
    private String receiverUserId = "";

    /**
     * The email of the user receiving the payment.
     */
    private String receiverUserEmail = "";

    /**
     * A payment identifier in the TPA context.
     */
    private String payObjectId = "";

    /**
     * The amount of bitcoins to be payed by the widget. If not specified here, it
     * must be entered on payment basis.
     */
    private String amountBIT = "";

    /**
     * The string representing the type of operation ("Tip", "Pay", "Deposit", "Donate" or "OAuth").
     */
    private String payType = PayType.NONE;

    /**
     * A custom code to be tracked by the TPA. It's sent back to the TPA in the specified callback.
     * It could be used also to search with the micro payments query API.
     */
    private String referenceCode = "";

    /**
     * The callback URL to notify a successful micro payment. The callback will be called with parameters
     * "reference_code" and "request_UID".
     */
    private String endMpaymentUri = "";

    /**
     * An URL to be redirected to after a successful micro payment.
     */
    private String endMpaymentRedirectUri = "";

    /**
     * Redirect URL after a successful OAuth flow. The URL must accept a "code" parameter if access is
     * granted or "error" and "error_description" in case of denial.
     */
    private String redirectUri = "";


    public String getSenderUserId() {
        return senderUserId;
    }

    public void setSenderUserId(String senderUserId) {
        this.senderUserId = senderUserId;
    }

    public String getSenderUserEmail() {
        return senderUserEmail;
    }

    public void setSenderUserEmail(String senderUserEmail) {
        this.senderUserEmail = senderUserEmail;
    }

    public String getSenderUserCellphone() {
        return senderUserCellphone;
    }

    public void setSenderUserCellphone(String senderUserCellphone) {
        this.senderUserCellphone = senderUserCellphone;
    }

    public String getReceiverUserId() {
        return receiverUserId;
    }

    public void setReceiverUserId(String receiverUserId) {
        this.receiverUserId = receiverUserId;
    }

    public String getReceiverUserEmail() {
        return receiverUserEmail;
    }

    public void setReceiverUserEmail(String receiverUserEmail) {
        this.receiverUserEmail = receiverUserEmail;
    }

    public String getPayObjectId() {
        return payObjectId;
    }

    public void setPayObjectId(String payObjectId) {
        this.payObjectId = payObjectId;
    }

    public String getAmountBIT() {
        return amountBIT;
    }

    /**
     * Sets the amount to send e.g.: 0.01
     *
     * @param amountBIT String number fin #.## format
     */
    public void setAmountBIT(String amountBIT) {
        this.amountBIT = amountBIT;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getReferenceCode() {
        return referenceCode;
    }

    public void setReferenceCode(String referenceCode) {
        this.referenceCode = referenceCode;
    }

    public String getEndMpaymentUri() {
        return endMpaymentUri;
    }

    public void setEndMpaymentUri(String endMpaymentUri) {
        this.endMpaymentUri = endMpaymentUri;
    }

    public String getEndMpaymentRedirectUri() {
        return endMpaymentRedirectUri;
    }

    public void setEndMpaymentRedirectUri(String endMpaymentRedirectUri) {
        this.endMpaymentRedirectUri = endMpaymentRedirectUri;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }
}
