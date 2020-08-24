------------------------------------------------------------------------
English
------------------------------------------------------------------------
Shopizer (for java 1.8 +)
-------------------

[![last_version](https://img.shields.io/badge/last_version-v2.12.0-blue.svg?style=flat)](https://github.com/shopizer-ecommerce/shopizer/tree/2.12.0)
[![Official site](https://img.shields.io/website-up-down-green-red/https/shields.io.svg?label=official%20site)](http://www.shopizer.com/)
[![Docker Pulls](https://img.shields.io/docker/pulls/shopizerecomm/shopizer.svg)](https://hub.docker.com/r/shopizerecomm/shopizer)
[![stackoverflow](https://img.shields.io/badge/shopizer-stackoverflow-orange.svg?style=flat)](http://stackoverflow.com/questions/tagged/shopizer)


Java open source e-commerce software

- Shopping cart
- Catalogue
- Search
- Checkout
- Administration
- REST API

See the demo (jsp):
-------------------
http://aws-demo.shopizer.com:8080/

See the demo (angular):
-------------------
Available soon


Get the code:
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
	

Run the application from Tomcat 
-------------------
copy sm-shop/target/ROOT.war to tomcat or any other application server deployment dir

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

### Access the application:
-------------------

Access the deployed web application at: http://localhost:8080/

Access the admin section at: http://localhost:8080/admin

username : admin@shopizer.com

password : password

The instructions above will let you run the application with default settings and configurations.
Please read the instructions on how to connect to MySQL, configure an email server and configure other subsystems


### Documentation:
-------------------

Documentation available from the wiki <http://shopizer-ecommerce.github.io/shopizer/#>

ChatOps <https://shopizer.slack.com>  - Join our Slack channel https://shopizer-slackin.herokuapp.com/

More information is available on shopizer web site here <http://www.shopizer.com>

### Participation:
-------------------

If you have interest in giving feedback or for participating to Shopizer project in any way
Feel to use the contact form <http://www.shopizer.com/contact.html> and share your email address
so we can send an invite to our Slack channel



------------------------------------------------------------------------
FRENCH
------------------------------------------------------------------------

Shopizer (pour java 1.8 +)
-------------------

[![last_version](https://img.shields.io/badge/last_version-v2.12.0-blue.svg?style=flat)](https://github.com/shopizer-ecommerce/shopizer/tree/2.12.0)
[![Official site](https://img.shields.io/website-up-down-green-red/https/shields.io.svg?label=official%20site)](http://www.shopizer.com/)
[![Docker Pulls](https://img.shields.io/docker/pulls/shopizerecomm/shopizer.svg)](https://hub.docker.com/r/shopizerecomm/shopizer)
[![stackoverflow](https://img.shields.io/badge/shopizer-stackoverflow-orange.svg?style=flat)](http://stackoverflow.com/questions/tagged/shopizer)


Logiciel de commerce électronique open source Java

- Panier
- Catalogue
- Chercher
- Check-out
- Administration
- API REST

Voir la démo (jsp):
-------------------
http://aws-demo.shopizer.com:8080/

Voir la démo (angular):
-------------------
Bientôt disponible


Obtenez le code:
-------------------
Clonez le référentiel:
     
	 $ git clone git://github.com/shopizer-ecommerce/shopizer.git

Si c'est la première fois que vous utilisez Github, consultez  http://help.github.com to learn the basics.

 Vous pouvez également télécharger le fichier zip contenant le code depuis https://github.com/shopizer-ecommerce/shopizer 

Pour créer l'application:
-------------------	
Depuis la ligne de commande:

	$ cd shopizer
	$ mvnw clean install
	

Exécutez l'application depuis Tomcat
-------------------
copier sm-shop / target / ROOT.war vers tomcat ou tout autre répertoire de déploiement du serveur d'applications

Augmenter l'espace du tas à 1024 m （Heap space)

### Configuration de l'espace de tas dans Tomcat (Heap space):


Si vous utilisez Tomcat, modifiez catalina.bat pour les utilisateurs Windows ou catalina.sh pour les utilisateurs Linux / Mac

	sous Windows
	set JAVA_OPTS="-Xms1024m -Xmx1024m -XX:MaxPermSize=256m" 
	
	sous Linux / Mac
	export JAVA_OPTS="-Xms1024m -Xmx1024m -XX:MaxPermSize=256m" 

Exécutez l'application à partir de Spring Boot
-------------------

       $ cd sm-shop
       $ mvnw spring-boot:run

Exécutez l'application à partir de Spring Boot dans eclipse
-------------------

Faites un clic droit sur com.salesmanager.shop.application.ShopApplication

exécuter en tant qu'application Java

### Accédez à l'application:
-------------------

Accédez à l'application Web déployée à l'adresse: http: // localhost: 8080 /

Accédez à la section d'administration à l'adresse: http: // localhost: 8080 / admin

nom d'utilisateur: admin@shopizer.com

mot de passe: password

Les instructions ci-dessus vous permettront d'exécuter l'application avec les paramètres et configurations par défaut.
Veuillez lire les instructions pour vous connecter à MySQL, configurer un serveur de messagerie et configurer d'autres sous-systèmes


### Documentation:
-------------------

Documentation disponible sur le wiki <http://shopizer-ecommerce.github.io/shopizer/#>

ChatOps <https://shopizer.slack.com> - Rejoignez notre chaîne Slack https://shopizer-slackin.herokuapp.com/

Plus d'informations sont disponibles sur le site Web de Shopizer ici <http://www.shopizer.com>

### Participation:
-------------------

Si vous souhaitez donner votre avis ou participer de quelque manière que ce soit au projet Shopizer
N'hésitez pas à utiliser le formulaire de contact <http://www.shopizer.com/contact.html> et à partager votre adresse email
afin que nous puissions envoyer une invitation sur notre chaîne Slack


