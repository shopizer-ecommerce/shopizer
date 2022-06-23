# Shopizer 3 (for java 1.8 +) (tested with Java 11)


### Shopizer 2.X (for java 1.8 +) is still available


[![last_version](https://img.shields.io/badge/last_version-v3.0.0-blue.svg?style=flat)](https://github.com/shopizer-ecommerce/shopizer/tree/3.2.0)
[![Official site](https://img.shields.io/website-up-down-green-red/https/shields.io.svg?label=official%20site)](http://www.shopizer.com/)
[![Docker Pulls](https://img.shields.io/docker/pulls/shopizerecomm/shopizer.svg)](https://hub.docker.com/r/shopizerecomm/shopizer)
[![stackoverflow](https://img.shields.io/badge/shopizer-stackoverflow-orange.svg?style=flat)](http://stackoverflow.com/questions/tagged/shopizer)
[![CircleCI](https://circleci.com/gh/shopizer-ecommerce/shopizer.svg?style=svg)](https://circleci.com/gh/shopizer-ecommerce/shopizer)


Java open source e-commerce software

Headless commerce and Rest api for ecommerce

- Catalogue
- Shopping cart
- Checkout
- Merchant
- Order
- Customer
- User

Shopizer Headless commerce consists of the following components:

- Spring boot Java / Spring boot backend
- Angular administration web application
- React JS front end application



See the demo:
-------------------
Headless demo Available soon

Demo site for Shopizer 2.X is still available [Legacy Shopizer demo](http://demo.shopizer.com)

1.  Run from Docker images:

From the command line:

```
docker run -p 8080:8080 shopizerecomm/shopizer:latest
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
docker run \
 -e "APP_MERCHANT=DEFAULT"
 -e "APP_BASE_URL=http://localhost:8080"
 -p 80:80 shopizerecomm/shopizer-shop-reactjs
```

API documentation:
-------------------

https://app.swaggerhub.com/apis-docs/shopizer/shopizer-rest-api/3.0.1#/

Get the source code:
-------------------
Clone the repository:
     
	 $ git clone git://github.com/shopizer-ecommerce/shopizer.git
	 
	 $ git clone git://github.com/shopizer-ecommerce/shopizer-admin.git
	 
	 $ git clone git://github.com/shopizer-ecommerce/shopizer-shop-reactjs.git

If this is your first time using Github, review http://help.github.com to learn the basics.

You can also download the zip file containing the code from https://github.com/shopizer-ecommerce for each of the the projects above

To build the application:
-------------------

1. Shopizer backend


From the command line:

	$ cd shopizer
	$ mvnw clean install
	$ cd sm-shop
	$ mvnw spring-boot:run

2. Shopizer admin

Form compiling and running Shopizer admin consult the repo README file

3. Shop sample site

Form compiling and running Shopizer admin consult the repo README file


### Access the application:
-------------------

Access the headless web application at: http://localhost:8080/swagger-ui.html


The instructions above will let you run the application with default settings and configurations.
Please read the instructions on how to connect to MySQL, configure an email server and configure other subsystems


### Documentation:
-------------------

Documentation available <http://documentation.shopizer.com>

Api documentation <https://app.swaggerhub.com/apis-docs/shopizer/shopizer-rest-api/3.0.1#/>

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


Push your changes to Shopizer
-------------------

Please open a PR (pull request) in order to have your changes merged to the upstream