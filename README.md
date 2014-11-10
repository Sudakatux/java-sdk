# README
>This is the Java version of the Xapo's SDK & Widget Tools. These tools allow you (Third Party Application, TPA) to easily develop your bitcoins applications to manage *Accounts, Wallets, Transactions*, etc., or embed tools like *Payments Buttons, Donation Buttons* and other kind of widgets into your web application using your language of choice. In this way, tedious details like encryption and HTML snippet generation are handled for you in a simple and transparent way.

For more information please visit: http://developers.xapo.com

---

[Changelog](CHANGELOG.md)

<!-- START doctoc generated TOC please keep comment here to allow auto update -->
<!-- DON'T EDIT THIS SECTION, INSTEAD RE-RUN doctoc TO UPDATE -->
## Table of Contents

- [Table of Contents](#table-of-contents)
  - [Notes](#notes)
  - [Build](#build)
  
<!-- END doctoc generated TOC please keep comment here to allow auto update -->

## Notes

The Java Widgets Tools requires Java 1.6+. Since Xapo uses 256 bit encryption keys you may need to download and install the _Java Cryptographic Extensions_ depending to your Java version:

- [Java 6](http://www.oracle.com/technetwork/java/javase/downloads/jce-6-download-429243.html)
- [Java 7](http://www.oracle.com/technetwork/java/javase/downloads/jce-7-download-432124.html)
- [Java 8](http://www.oracle.com/technetwork/java/javase/downloads/jce8-download-2133166.html)

Decompress the zip file and copy the whole contents to ${java.home}/jre/lib/security/.* (overwrite previous files)

## Build
To build the library you need to include in your code do:

``` bash
# build the lib without deps
mvn package

# it generates 2 jars: 
#   - xapo-api.jar (see pom.xml for runtime dependencies)
#   - xapo-api-jar-with-dependencies.jar (bundled with dependencies)
```
