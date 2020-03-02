## Emails configuration

Shopizer supports 2 methods for sending emails:

- smtp (**default**)
- AWS Simple Email Service (**ses**)

#### SMTP Configuration (Default method)

This can be done by configuring SMTP connectivity at the server level from Shopizer administration console. When logging in the console move to Configurations and then to Email settings. SMTP configuration uses underlying Spring SpringHtmlEmailSender for sending HTML based emails.

**Protocol**: SMTP or SMTPS (this depends on SMTP provider. It usualy is SMTPS)
**Host**: SMTP server host
**Post**: Most of the time either 25 or 465
**Username**: SMTP server attributed username
**Password**: SMTP server attributed password
**Requires authentication**: Should always be true
**TLS**: Should always be true (Usualy mentioned by SMTP provider)

**Shopizer configuration**

Make sure shopizer-core.properties in sm-core project is configured fot smtp method before starting the server.

```sh
config.emailSender=default
```


#### SES (AWS Simple Email SDK)

This method uses AWS SDK implementation to perform SMTP(S) connectivity. AWS SES SDK is already included in Shopizer Maven POM file.

    <!-- https://mvnrepository.com/artifact/com.amazonaws/aws-java-sdk-ses -->
    <dependency>
      <groupId>com.amazonaws</groupId>
      <artifactId>aws-java-sdk-ses</artifactId>
    </dependency>

**Shopizer configuration**

Make sure shopizer-core.properties in sm-core project is configured fot ses method before starting the server. It requires 2 specific property for connecting to AWS Simple Email Service (ses method and region in which ses is configured in your AWS account)

```sh
config.emailSender=ses
config.emailSender.region=US_EAST_1
```