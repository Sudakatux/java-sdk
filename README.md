# README
>This is the Java version of the Xapo's Widget Tools. These tools allow you to easily embedded the tools like Micro Payment iFrames/DIVs, Tipping Buttons and other kind of widgets into your application using your language of choice. In this way, tedious details like encryption and html snippet generation is handle for you in a simple and transparent way.   

---

[Changelog](CHANGELOG.md)

# Table of Contents

- [Notes:](#notes)
- [Build](#build)
- [Micro Payment Widgets](#micro-payment-widgets)
  - [IFrame Widget](#iframe-widget)
  - [Div Widget](#div-widget)

## Notes:

The Micro Payment Java Widgets requires Java 1.6+. Since Xapo uses 256 bit encryption keys you may need to download and install the _Java Cryptographic Extensions_ depending to your Java version:

- [Java 6](http://www.oracle.com/technetwork/java/javase/downloads/jce-6-download-429243.html)
- [Java 7](http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html)
- [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html)

Decompress the zip file and copy the whole contents to ${java.home}/jre/lib/security/.* (overwrite previous files)

## Build
To build the library you need to include in your code do:

``` bash
# build the lib without deps
mvn package

# build the lib with the deps bundled
mvn package-with-deps 
```

## Micro Payment Widgets
MicroPayment widgets allow to dynamically get a HTML snippet pre-configured to allow blah, blah.... 

### IFrame Widget
```java
import com.xapo.tools.widgets.MicroPayment;
import com.xapo.tools.widgets.MicroPaymentConfig;

...

microPayment = new MicroPayment(XAPO_URL,
                                APP_ID, APP_SECRET);

config = new WidgetConfig();

config.setAmountBIT("0.01");
config.setPayObjectId("to0210");
config.setReceiverUserEmail("jhon.doe@leapsight.com");
config.setReceiverUserId("r0210");
config.setSenderUserCellphone("+5491112341234");
config.setSenderUserEmail("jane.doe@xapo.com");
config.setSenderUserId("");

// PAY_TYPE_TIP | PAY_TYPE_PAY | PAY_TYPE_DEPOSIT  | PAY_TYPE_DONATE
config.setPayType(MicroPaymentConfig.PAY_TYPE_TIP);

// Get IFRAME snippet
String iframe = microPayment.buildIframeWidget(request);
```

With this you get the following snippet:

```html
<iframe id='tipButtonFrame' scrolling='no' frameborder='0' style='border:none; overflow:hidden; height:22px;' allowTransparency='true' src='http://dev.xapo.com:8089/pay_button/show?customization=%7B%22button_text%22%3A%22Tip%22%7D&app_id=b91014cc28c94841&button_request=C%2F6OaxS0rh3jMhH90kRYyp3y%2BU5ADcCgMLCyz2P5ssFG%2FJoGf55ccvicyRMuIXpU5xhDeHGffpZAvVeMCpJhGFyIPwLFh%2FVdnjnDUjYgJCQeB4mCpGsEW5SC6wNvg69ksgeAtr108Wc5miA8H4JG99EWTTlC7WtIGg5rFKkbjrop15fSJfhv5cTs02jSC5f2BaLlh1mKh5hSPW3HGcWcl%2BdyZj%2F9m1lPB4gKfky2%2FnT0tYjbEFo5aU6WtowWrf2xE8OYejyI0poEFkClBkv2eDkp4Gel4tGb%2Bkwszcyb18ztK89RlBwhe8sX4HeM2KJM8ZaWuDOGH2VW4kbThMCZEw%3D%3D'></iframe>
```

and the resulting widget:

![payment button](https://bitbucket.org/leapsight/xapo.java-sdk/raw/master/img/donate_button.png)

![payment phone](https://bitbucket.org/leapsight/xapo.java-sdk/raw/master/img/mpayment1.png)

![payment email](https://bitbucket.org/leapsight/xapo.java-sdk/raw/master/img/mpayment2.png)

![payment pin](https://bitbucket.org/leapsight/xapo.java-sdk/raw/master/img/mpayment3.png)

### Div Widget
```java
import com.xapo.tools.widgets.MicroPayment;
import com.xapo.tools.widgets.MicroPaymentConfig;

...

microPayment = new MicroPayment(XAPO_URL,
                                APP_ID, APP_SECRET);

config = new WidgetConfig();

config.setAmountBIT("0.01");
config.setPayObjectId("to0210");
config.setReceiverUserEmail("jhon.doe@leapsight.com");
config.setReceiverUserId("r0210");
config.setSenderUserCellphone("+5491112341234");
config.setSenderUserEmail("jane.doe@xapo.com");
config.setSenderUserId("");

// PAY_TYPE_TIP | PAY_TYPE_PAY | PAY_TYPE_DEPOSIT  | PAY_TYPE_DONATE
config.setPayType(MicroPaymentConfig.PAY_TYPE_DONATE);

// Get IFRAME snippet
String iframe = microPayment.buildDivWidget(request);
```

With this you get the following snippet:

```html
<div id='tipButtonDiv' class='tipButtonDiv'></div>
<div id='tipButtonPopup' class='tipButtonPopup'></div>
<script>
$(document).ready(function() {$('#tipButtonDiv').load('http://dev.xapo.com:8089/pay_button/show?customization=%7B%22button_text%22%3A%22Donate%22%7D&app_id=b91014cc28c94841&button_request=C%2F6OaxS0rh3jMhH90kRYyp3y%2BU5ADcCgMLCyz2P5ssFG%2FJoGf55ccvicyRMuIXpU5xhDeHGffpZAvVeMCpJhGFyIPwLFh%2FVdnjnDUjYgJCQeB4mCpGsEW5SC6wNvg69ksgeAtr108Wc5miA8H4JG99EWTTlC7WtIGg5rFKkbjrop15fSJfhv5cTs02jSC5f2BaLlh1mKh5hSPW3HGcWcl%2BdyZj%2F9m1lPB4gKfky2%2FnT0tYjbEFo5aU6WtowWrf2xE8OYejyI0poEFkClBkv2eDkp4Gel4tGb%2Bkwszcyb18ztK89RlBwhe8sX4HeM2KJMHVfAM8NQXQu8oiIyCAl0vg%3D%3D');});
</script>
```

and the resulting widget:

![payment button](https://bitbucket.org/leapsight/xapo.java-sdk/raw/master/img/donate_button.png)
![payment phone](https://bitbucket.org/leapsight/xapo.java-sdk/raw/master/img/mpayment1.png)
![payment email](https://bitbucket.org/leapsight/xapo.java-sdk/raw/master/img/mpayment2.png)
![payment pin](https://bitbucket.org/leapsight/xapo.java-sdk/raw/master/img/mpayment3.png)