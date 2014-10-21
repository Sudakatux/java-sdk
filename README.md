# README #


You may need to download this file since encryption uses 256 bits keys:
*Java Cryptography Extension (JCE)*


### jdk 6:
http://www.oracle.com/technetwork/java/javase/downloads/jce-6-download-429243.html

### jdk 7:
http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html

### jdk 8:
 http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html

Install the file in `${java.home}/jre/lib/security/.*`
decompress zip file and copy all files the path (override previous files)

### Usage Example:
```Java
// Java 1.6+
import package com.xapo.micro.payment.api.XapoMicroPaymentSDK;
import com.xapo.micro.payment.api.model.ButtonRequest;

...

xapoMicroPaymentSDK = new XapoMicroPaymentSDK(XAPO_URL,
                                APP_ID, APP_SECRET);

request = new ButtonRequest();

request.setAmountBIT("0.01");
request.setPayObjectId("to0210");
request.setReceiverUserEmail("federico.repond@leapsight.com");
request.setReceiverUserId("r0210");
request.setSenderUserCellphone("+5491112341234");
request.setSenderUserEmail("fernando.taboada@xapo.com");
request.setSenderUserId("");

// PAY_TYPE_TIP | PAY_TYPE_PAY | PAY_TYPE_DEPOSIT  | PAY_TYPE_DONATE
request.setPayType(ButtonRequest.PAY_TYPE_DONATE);

// Get the DIV snippet
String div = xapoMicroPaymentSDK.buildDivWidget(request);

// Get IFRAME snippet
String iframe = xapoMicroPaymentSDK.buildIframeWidget(request);
```