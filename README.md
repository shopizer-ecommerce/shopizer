Shopizer (for java 1.8 +)
-------------------

Ignite Lab Documenation
-------------------

[Official Documentation](http://shopizer-ecommerce.github.io/shopizer/#)

![Shopizer Archicture](shopizer-arch.png)

The root of this project contains 4 directories:

The `sm-shop` repositories describe the webapp itself, in addition to
controllers for different services of the site.

The `sm-core` repositories describe the functions of the core services of the
application.

According to official documentation
this application can run on any Java servlet container or JEE application
server, including IBM Websphere application server.

Possible services that are candidates for porting to microservices may be:

- Shopping Cart
- User

Get the code:
-------------------
Clone the repository:

```
git clone git://github.com/liatrio/shopizer.git
```

To build the application:

Note: You need to be running Java 1.8 in order to correctly build the application
-------------------
From the command line with Maven installed:

```
cd shopizer
mvn clean install
```

Run the application from Tomcat
-------------------
Copy sm-shop/target/ROOT.war to Tomcat or any other
application server deployment directory.

Increase heap space to 1024 m

### Heap space configuration in Tomcat:


If you are using Tomcat, edit catalina.bat for windows users or
catalina.sh for linux / Mac users

in Windows

```
set JAVA_OPTS="-Xms1024m -Xmx1024m -XX:MaxPermSize=256m"
```

in Linux / Mac

```
export JAVA_OPTS="-Xms1024m -Xmx1024m -XX:MaxPermSize=256m"
```

Run the application from Spring boot
-------------------

```
cd sm-shop
mvn spring-boot:run
```

### Access the application:
-------------------

Access the deployed web application at: `http://localhost:8080/`

Acces the admin section at: `http://localhost:8080/admin`

`username : admin`

`password : password`

The above instructions configure and run Shopizer with default settings.

### Configuration:
-------------------

#### Database Configuration

Shopizer currently supports and has been tested with H2 and MySQL databases.

- Configure with H2 database

By default H2 files are saved into your application server runtime directory.
To change the location where the files have to be saved,
edit sm-shop/src/main/resources/database.properties and edit the line
```
db.jdbcUrl=jdbc\:h2\:file\:c:/SALESMANAGER;AUTOCOMMIT\=OFF;INIT\=CREATE SCHEMA IF NOT EXISTS SALESMANAGER
```

Make sure the file path is a valid existing directory. In this case the
configuration file will be saved in c:\

- Configure with MySQL

Log to your MySQL as root and create schema SALESMANAGER

```
mysql>CREATE DATABASE SALESMANAGER;
mysql>GRANT USAGE, SELECT ON *.* TO testuser@localhost IDENTIFIED BY 'password' with grant option;
mysql>GRANT ALL ON SALESMANAGER.* TO testuser@localhost;
mysql>FLUSH PRIVILEGES;
```

Edit sm-shop/src/main/resources/database.properties

```
db.jdbcUrl=jdbc:mysql://localhost:3306/SALESMANAGER?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8
db.user=YOUR USERNAME
db.password=YOUR PASSWORD
db.driverClass=com.mysql.jdbc.Driver
hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
db.preferredTestQuery=SELECT 1
db.schema=SALESMANAGER
hibernate.hbm2ddl.auto=update
```

Note: Database and table names are not case sensitive in Windows,
and case sensitive in most varieties of Unix-Linux. Consequently MySQL which
uses lower case schema and table names will not be able to see the schema
and tables which are created upper case in Shopizer. If you get an
exception of a table not being found and being displayed in lower case
in the error message, you will have to specify this property in My.cnf.
To resolve the issue set the lower_case_table_names to 1 in My.cnf
configuration file

Edit My.cnf

```
lower_case_table_names=1
```

