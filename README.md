shopizer
========

Shopizer java e-commerce software


Shopizer 2.0
-------------------
Java open source e-commerce software

- Shopping cart
- Catalogue
- Search
- Checkout
- Administration


To get the code:
-------------------
Clone the repository:
$ git clone git://github.com/shopizer-ecommerce/shopizer.git

If this is your first time using Github, review http://help.github.com to learn the basics.

To run the application:
-------------------	
From the command line with Maven:
$ cd sm-core
$ mvn clean install

$ cd sm-shop
$ mvn clean install

copy sm-shop/target/sm-shop.war to tomcat or any other application server deployment dir

edit catalina.bat for windows users or catalina.sh for linux / max users
```
export JAVA_OPTS="-Xms1024m -Xmx1024m -XX:MaxPermSize=256m" in Linux / Mac
set JAVA_OPTS="-Xms1024m -Xmx1024m -XX:MaxPermSize=256m" in Windows
```
Access the deployed web application at: http://localhost:8080/sm-shop/shop

Acces the admin section at: http://localhost:8080/sm-shop/admin

username : admin
password : password

The instructions above will let you run the application with default settings and configurations.
Please read the instructions on how to connect to MySQL, configure an email server and configure other subsystems
The documentation is available here http://www.shopizer.com
