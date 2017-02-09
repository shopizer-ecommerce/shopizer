<!DOCTYPE html>
<html>
  <head>
    
    
  <meta charset="utf-8"> 
	
	

	<!-- <meta http-equiv="X-UA-Compatible" content="IE=edge"> -->
	<!--[if IE]><meta http-equiv='X-UA-Compatible' content='IE=edge,chrome=1'><![endif]-->	
	<meta name="description" content="Java open source shopping cart | E-commerce content management" />
	<meta name="keywords" content="Java open source shopping cart, java e-commerce, java shopping cart, responsive e-commerce, java open source, free Java shopping cart" />
	<meta name="author" content="Shopizer" />     
	<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=2.0, user-scalable=yes">
        <link href="../stylesheets/bootstrap.css" rel="stylesheet" type="text/css" media="all" />
        <link href="../stylesheets/layout.css" rel="stylesheet" type="text/css" media="all" /> 
        <link href="../stylesheets/technical.css" rel="stylesheet" type="text/css" media="all" />   
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,300,300italic,400italic,600,600italic,700,700italic,800,800italic' rel='stylesheet' type='text/css'>

    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->









	<script>
  	   	//var _gaq = _gaq || [];
  	   	//_gaq.push(['_setAccount', 'UA-4436714-6']);
  	   	//(function() {
    		//	var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    		//	ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
   		 	//var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  	   	//})();
	</script>

    <!--[if lt IE 9]>
    <script src="//html5shiv.googlecode.com/svn/trunk/html5.js"></script>
    <![endif]-->

  <title>Configure Shopizer</title>
  </head>

  <body>
  
  
  <div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
  <h2 id="requirements">Configure Shopizer</h2>
  <br/>

<br/>
Shopizer is configured out of the box with default configurations in order to be ran on a local machine or a development environment without having to configure anything!
It is recommended to revise the default configurations for production environments.<br/><br/>
The following section describes the configurations:<br/><br/>
<p class="well">
- <a href="#database">Database configuration</a><br/>
- <a href="#email">Email server configuration</a><br/>
- <a href="#search">Search component configuration</a><br/>
- <a href="#cms">Content management (CMS) configuration</a><br/>
- <a href="#application">Application configuration</a>
</p>


<!-- *** DATABASE *** -->
<h3 id="database">Database configuration</h3>
<br/>
Shopizer currently supports and has been tested with H2 and MySQL databases.
<br/><br/>
<p>
Shopizer comes pre-configured with H2 database. H2 is an embedded database (http://www.h2database.com) configured by default to save data on files.
This pre-configuration should only be used for testing and never be used in a production environment.
</p>

<strong>- Configure with H2 database</strong>
<br/><br/>
<p>
By default H2 files are saved into your application server runtime directory. 
To change the location where the files have to be saved,
edit sm-shop/src/main/resources/database.properties and edit the line
</p>
<pre class="well">
db.jdbcUrl=jdbc\:h2\:file\:c:/SALESMANAGER;AUTOCOMMIT\=OFF;INIT\=CREATE SCHEMA IF NOT EXISTS SALESMANAGER
</pre>


Make sure the file path is a valid existing directory. In this case the configuration file will be saved in c:\

<br/><br/>
<strong>- Configure with MySQL</strong>
<br/><br/>

<p>
Log to your MySQL as root and create schema SALESMANAGER
</p>

<pre class="command-line">
     <span class="command">mysql>CREATE DATABASE SALESMANAGER;</span>
     <span class="command">mysql>GRANT USAGE, SELECT ON *.* TO <YOUR USERNAME>@localhost IDENTIFIED BY '<YOUR PASSWORD>' with grant option;</span>
     <span class="command">mysql>GRANT ALL ON SALESMANAGER.* TO <YOUR USERNAME>@localhost;</span>
     <span class="command">mysql>GRANT FILE ON *.* TO <YOUR USERNAME>@localhost;</span>
     <span class="command">mysql>FLUSH PRIVILEGES;</span>
</pre>

<p>
<strong>Example</strong>
</p>
<pre class="command-line">
     <span class="command">mysql>CREATE DATABASE SALESMANAGER;</span>
     <span class="command">mysql>GRANT USAGE, SELECT ON *.* TO testuser@localhost IDENTIFIED BY 'password' with grant option;</span>
     <span class="command">mysql>GRANT ALL ON SALESMANAGER.* TO testuser@localhost;</span>
     <span class="command">mysql>FLUSH PRIVILEGES;</span>
</pre>

<p>
<strong>Edit</strong> sm-shop/src/main/resources/database.properties
</p>

<pre class="well">
db.jdbcUrl=jdbc:mysql://localhost:3306/SALESMANAGER?autoReconnect=true&useUnicode=true&characterEncoding=UTF-8
db.user=YOUR USERNAME
db.password=YOUR PASSWORD
db.driverClass=com.mysql.jdbc.Driver
hibernate.dialect=org.hibernate.dialect.MySQL5InnoDBDialect
db.preferredTestQuery=SELECT 1

db.schema=SALESMANAGER
hibernate.hbm2ddl.auto=update
</pre>

<p>
<span class="note"><strong>Note: </strong>Database and table names are not case sensitive in Windows, and case sensitive in most varieties of Unix-Linux. Consequently MySQL which uses lower case schema and table names will not be able to see the schema and tables which are created upper case in Shopizer. If you get an exception of a table not being found and being displayed in lower case in the error message, 
you will have to specify this property in My.cnf. To resolve the issue set the lower_case_table_names to 1 in My.cnf configuration file</strong>
</p>

<p>
<strong>Edit</strong> My.cnf
</p>
<pre class="well">
lower_case_table_names=1
</pre>

</p>

<!-- *** SEARCH *** -->
<h3 id="search">Search configuration</h3>
<br/>
<p>
Search functionality has been implemented on top of Elastic Search <a href="http://www.elasticsearch.com">www.elasticsearch.com</a>.
The latest version of Shopizer supports Elastic Search version 2.X. Shopiser uses Jest as Elastic Search client to connect using HTTP on Elastic Search.By default Elastic Search accept HTTP connections over port <strong>9200</strong>.
</p>
<p>
Install Elastic Search on your local machine or on a remote server. Specify the name of the cluster in Elastic Search configuration file 
<br/>
<p>
<strong>Edit</strong> elasticsearch.yml
</p>
<pre class="well">
# ---------------------------------- Cluster -----------------------------------
#
# Use a descriptive name for your cluster:
#
cluster.name: shopizer
</pre>

<p>Shopizer must be configured to communicate with Elastic Search server</p>
<p>
<strong>Edit</strong> shopizer\sm-core\src\main\resources\configs.properties and set the properties for host, port (9200 is Elastic Search default HTTP port) as well as a proxy username and password if you are going through a proxy server.
</p>
<pre class="well">
#Elastic Search configurations
elasticsearch.cluster.name=shopizer
elasticsearch.mode=remote
elasticsearch.server.host=http://localhost
elasticsearch.server.port=9200
elasticsearch.server.proxy.user=
elasticsearch.server.proxy.password=
</pre>


<!-- *** EMAIL *** -->
<h3 id="email">Email server configuration</h3>
<br/>
<p>
The email settings can be configured from the admin tool for each specific store. This section allows to create a global email server configuration that can be shared accross multiple stores or can be used if individual email servers are not configured from the admin tool.
</p>
<br/>
<p>
<strong>Edit</strong> sm-core/resources/email.properties
</p>
<pre class="well">
server=
port=
user=
password=
</pre>

<!-- *** CMS *** -->
<h3 id="cms">CMS configuration</h3>
<br/>
<p>
Shopizer stores images, files and content in the underlying CMS mplementation built on top of JBoss Infinispan. Infinispan is a distributed persistent caching system that can store files on disk or in a database. The content is also distributed across multiple nodes when configured in a cluster.
It is also possible to configure Shopizer to use a simple web server in order to serve files and content.
</p>
<p>
The default CMS implementation uses JBoss Infinispan that will store files on disk. To change Infinispan cache location
</p>
<p>
<strong>Edit</strong> shopizer\sm-core\src\main\resources\configs.properties
</p>
<pre class="well">
# Infinispan Configuration
infinispan.cache.location=./infinispan
</pre>
<br/>

<!-- *** APPLICATION CONFIG *** -->
<h3 id="application">Application configurations</h3>
<br/>
<Strong>Recaptcha configuration</strong>
<p>
Shopizer uses Recaptcha <a href="https://www.google.com/recaptcha/intro/index.html">https://www.google.com/recaptcha/intro/index.html</a> (free) in various online forms such as customer registration form. That component requires a public and private key to be configured.
<p>
<pre class="prettyprint">
Edit sm-shop/src/main/webapp/WEB-INF/spring/appServlet/shopizer-properties.xml

     &lt;prop key="shopizer.recapatcha_public_key"&gt;6Lc1Pe0SAAAAADQDlWbv3MYYj7lGEeCEanwC42bv&lt;/prop&gt;
     &lt;prop key="shopizer.recapatcha_private_key"&gt;6Lc1Pe0SAAAAAFMolDugwnZN9Xe3CnapokqoQjhg&lt;/prop&gt;
</pre>
<br/>
<Strong>Populate test data</strong>
<p>
It is possible to load test data in the initial startup, that will give an overview on how to configure an online store
<p>
<pre class="prettyprint">
Edit sm-shop/src/main/webapp/WEB-INF/spring/appServlet/shopizer-properties.xml

     &lt;prop key="POPULATE_TEST_DATA"&gt;false&lt;/prop&gt;
</pre>
<br/>



        </div>
      </div>
</div>

  
  
  </div>

  </body>
  
  </html>
