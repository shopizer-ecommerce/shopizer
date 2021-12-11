------------------------------------------------------------------------
English
------------------------------------------------------------------------
Shopizer 3 (for java 1.8 +) (tested with Java 11)
-------------------

[![last_version](https://img.shields.io/badge/last_version-v3.0.0-blue.svg?style=flat)](https://github.com/shopizer-ecommerce/shopizer/tree/3.0.0)
[![Official site](https://img.shields.io/website-up-down-green-red/https/shields.io.svg?label=official%20site)](http://www.shopizer.com/)
[![Docker Pulls](https://img.shields.io/docker/pulls/shopizerecomm/shopizer.svg)](https://hub.docker.com/r/shopizerecomm/shopizer)
[![stackoverflow](https://img.shields.io/badge/shopizer-stackoverflow-orange.svg?style=flat)](http://stackoverflow.com/questions/tagged/shopizer)
[![CircleCI](https://circleci.com/gh/shopizer-ecommerce/shopizer.svg?style=svg)](https://circleci.com/gh/shopizer-ecommerce/shopizer)


Java open source e-commerce software

Headless commerce and Rest api for ecommerce

Catalogue
Shopping cart
Checkout
Merchant
Order
Customer
User

Shopizer Headless commerce consists of the following components:

- Spring boot Java / Spring boot backend
- Angular administration web application
- React JS front end application



See the demo:
-------------------
Available soon

Demo site for Shopizer 2.X is still available [Legacy Shopizer demo](http://demo.shopizer.com)

Run from Docker images:
-------------------

1. Run java backend

```
       docker run -p 8080:8080 shopizerecomm/shopizer:3.0.0
```
       
2. Run the administration tool

⋅⋅⋅ Requires the java backend to be running

```
       docker run \
			-e "APP_BASE_URL=http://localhost:8080/api" \
			-p 82:80 shopizerecomm/shopizer-admin
```


3. Run react shop sample site

⋅⋅⋅ Requires the java backend to be running

```
	   docker run
			-e "APP_MERCHANT=DEFAULT"
             -e "APP_BASE_URL=http://localhost:8080"
             -p 80:80 shopizerecomm/shopizer-shop-reactjs
```

Get the source code:
-------------------
Clone the repository:
     
	 $ git clone git://github.com/shopizer-ecommerce/shopizer.git

If this is your first time using Github, review http://help.github.com to learn the basics.

You can also download the zip file containing the code from https://github.com/shopizer-ecommerce/shopizer 

To build the application:
-------------------	
From the command line:

	$ cd shopizer
	$ mvnw clean install
	$ cd sm-shop
	$ mvnw spring-boot:run
	

Run the application from Tomcat 
-------------------
copy sm-shop/target/sm-shop.jar to tomcat or any other application server deployment dir

Increase heap space to 1024 m

### Heap space configuration in Tomcat:


If you are using Tomcat, edit catalina.bat for windows users or catalina.sh for linux / Mac users

	in Windows
	set JAVA_OPTS="-Xms1024m -Xmx1024m -XX:MaxPermSize=256m" 
	
	in Linux / Mac
	export JAVA_OPTS="-Xms1024m -Xmx1024m -XX:MaxPermSize=256m" 

Run the application from Spring boot 
-------------------

       $ cd sm-shop
       $ mvnw spring-boot:run

Run the application from Spring boot in eclipse
-------------------

Right click on com.salesmanager.shop.application.ShopApplication

run as Java Application

Run the application from Spring boot in IntelliJ
-------------------

In Run/Debug Configurations, set to the option Working directory the path of the sm-shop project

run ShopApplication

Run Docker image with working demo
-------------------

	docker run -p 80:8080 shopizerecomm/shopizer:3.0.alpha


### Access the application:
-------------------

Access the headless web application at: http://localhost:8080/swagger-ui.html


The instructions above will let you run the application with default settings and configurations.
Please read the instructions on how to connect to MySQL, configure an email server and configure other subsystems


### Documentation:
-------------------

Documentation available <http://documentation.shopizer.com>

ChatOps <https://shopizer.slack.com>  - Join our Slack channel https://shopizer-slackin.herokuapp.com/

More information is available on shopizer web site here <http://www.shopizer.com>

### Participation:
-------------------

If you have interest in giving feedback or for participating to Shopizer project in any way
Feel to use the contact form <http://www.shopizer.com/contact.html> and share your email address
so we can send an invite to our Slack channel

### How to Contribute:
-------------------
Fork the repository to your GitHub account

Clone from fork repository
-------------------

       $ git clone https://github.com/yourusername/shopizer.git

Build application according to steps provided above

Synchronize lastest version with the upstream
-------------------

       $ git remote add upstream https://github.com/yourusername/shopizer.git
	   $ git pull upstream 3.0.0

Create new branch in your repository
-------------------

	   $ git checkout -b branch-name


Check your branch status before commit to the branch
-------------------

	   $ git status 
	   $ git commit 

Push changes to GitHub
-------------------

	   $ git push -u origin HEAD