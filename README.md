# Shopizer

Shopizer is a free open source Java e-commerce software that is ideal for every online shop or business. Our technology stack is based on well known, proven technology such as Java, Spring, Hibernate and JQuery. 

Create your online store in minutes using the secure, fast and reliable application right out of the box or integrate it to your existing technology by using Shopizer's set of tools, APIs and a fully open source customizable environment.

<br/>
**To find out more, please check out the [Shopizer website - http://www.shopizer.com] [website]**

## Why Shopizer

* **Moble Ready** Responsive web and responsive emails, easy theming with frameworks such as bootstrap css and Zurb foundation.
* **Commerce Engine** Lean and fast application that will help you build your online store effectively and efficiently. Built in shopping cart, inventory management and order flow that run standalone or can be integrated to an existing application. Java and RESTFul API. 
* **Open source** The code is open source under LGPL licence and is built with reliable Java frameworks such as Spring and Hibernate  

## Quick start guide

* **Get the code**<br/>
$ git clone git://github.com/shopizer-ecommerce/shopizer.git <br/>If this is your first time using Github, review http://help.github.com to learn the basics.

* **Compile the code**<br/>
From the command line using Maven

```
$ cd sm-core-model
$ mvn clean generate-sources install

$ cd sm-core
$ mvn clean install

$ cd sm-shop
$ mvn clean install
```
* **Compile the generated war file**<br/>
copy sm-shop/target/sm-shop.war to tomcat or any other application server deployment dir

* **Adjust memory allocation**<br/>
Increase heap space to 1024 m<br/>
If you are using Tomcat edit catalina.bat for windows users or catalina.sh for linux / max users

in Linux / Mac

```
export JAVA_OPTS="-Xms1024m -Xmx1024m -XX:MaxPermSize=256m" 
```

in Windows

```
set JAVA_OPTS="-Xms1024m -Xmx1024m -XX:MaxPermSize=256m" 
```
A
ccess the deployed web application at: **http://localhost:8080/sm-shop/shop**

Acces the admin section at: **http://localhost:8080/sm-shop/admin**

username : admin<br/>
password : password

## Read more

| **Technical Docs (Coming soon)**        | **[Setup Guide] [setup]**     | **[Contributing] [contributing]**           |
|-----------------------------------------|-------------------------------|---------------------------------------------|
| [![i4] [techdocs-image]][doc]                  | [![i5] [setup-image]] [setup]     | [![i6] [contributing-image]][contributing]                |


[agility-image]: http://umeshawasthi.github.io/shopizer-documentation/images/agility.PNG
[community-image]: http://umeshawasthi.github.io/shopizer-documentation/images/community.PNG
[portability-image]: http://umeshawasthi.github.io/shopizer-documentation/images/portability.PNG
[website]: http://www.shopizer.com

[setup]:https://github.com/shopizer-ecommerce/shopizer/wiki/Setup
[contributing]:https://github.com/shopizer-ecommerce/shopizer/wiki/Contribution
[doc]:https://github.com/shopizer-ecommerce/shopizer
[techdocs-image]: http://umeshawasthi.github.io/shopizer-documentation/images/document.png
[setup-image]: http://umeshawasthi.github.io/shopizer-documentation/images/setup.png
[contributing-image]: http://umeshawasthi.github.io/shopizer-documentation/images/document.png

