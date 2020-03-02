## Services

Shopizer implements B2B, B2C and C2C functionality offering the following services

* Catalog management
* User management
* Customer management
* Content management
* Order management
* Pricing management
* Shopping cart management
* Configuration management
* Shipping
* Payment
* Promotions
* Search

Technology stack is built on Java (requires java 8), Spring Boot (Core, Security, DATA JPA), JBoss Drools rules engine, Elastic Search and cloud services offered by GCP and AWS for infrastructure, storage and security.


## Shopizer high level architecture

![Shopizer high level architecture](/static/img/documentation/shopizer-architecture.png "Shopizer high level architecture")

Shopizer is a platform aiming to provide services, tools and connectors for building your own commerce cloud or on premise commerce application. 

Services are built using Spring Framework providing packages for building enterprise applications. Shopizer persistence uses JPA for connecting to MySQL and has extensions for Google FirebaseNoSQL database and a set of interfaces for connecting to other external data sources.

Shopizer supports integration with external payment and shipping modules such as Stripe, Fedex, Braintree, USPS and more. A set of modules extensions allows using various content management storage such as JBoss Infinispan, AWS S3 and external web servers such as NGINX or Apache server. Elastic tools provide searching functionality.

A REST api exposes all commerce functionality (B2C, B2B, C2C, Multi-Stores) as well as complete system administration. Spring Security configured of the box with JWT Bearer token authentication provides application interface authentication and authorization.

## Main foundation technology

|  |  |
|----------------|:---------:|
| **Spring Boot** Shopizer is based on [Spring Boot](https://spring.io/projects/spring-boot) which makes it easy to create stand-alone, production-grade Applications that you can "just run". | ![SpringBoot](/static/img/springboot.png) |
| **Spring Security** Shopizer is also based on [Spring Security](https://projects.spring.io/spring-security/) which is a framework that focuses on providing both authentication and authorization to Java applications. | ![SpringSecurity](/static/img/spring-security.png) |
| **Spring Data JPA** Data access is implemented using [Spring Data JPA](https://spring.io/projects/spring-data-jpa) which makes easy data operations in a Java application. | ![Spring Data JPA](/static/img/spring-data.png) |
| **Elastic search** Search functionality is built on Elastic Search a tool for searching, analyzing, and visualizing commerce data data [Elastic Search](https://www.elastic.co) which makes easy data operations in a Java application. | ![Spring Data JPA](/static/img/elastic.png) |


## Shopizer components


![Shopizer components](/static/img/documentation/shopizer-components.jpg "Shopizer components")

The Component Model describes the entire hierarchy of components in terms of their responsibilities, their interfaces, their (static) relationships, and the way they collaborate to deliver required functionality.

A component is a relatively independent part of a system. It is characterized by its responsibilities and eventually by the interface(s) it offers. Components can be decomposed into smaller components or composed into  arger components.

**Core**

- Spring Data JPA (entity, repositories)
- Services
- Rules (static and dynamic)

**Model**

- Entity

**Modules**

- Interfaces
- Internal modules
- External modules

**Shop**

- Controller
- Security
- API
- Façade
- DTOs


---

## See also

* [What is Shopizer](/#/starting/whatisshopizer)
* [Beginning with Shopizer](/#/starting/starting)

