## Understanding configurations

For having the best e-commerce experience Shopizer requires a set of configurations including api keys and different pointers that can be used at runtime to provide functionality such as content management, sending emails, getting shipping quotes, displaying maps etc...

Those configurations use underlying api keys and subsystem configurations that require to be created prealabely. Those configurations are not mandatory and Shopizer will work with reduced features if not configured.

### External configurations

- Google Maps API
- Google Places API
- AWS SES (Simple Email Service)
- Google reCaptcha API
- AWS S3 Bucket
- Google Bucket

### What are those configurations used for

|Scope           |Provider                       |Description                                           |
|----------------|-------------------------------|------------------------------------------------------|
|Contact us      |Google Maps API                |Display a Map containing store location               |
|Contact us      |Google reCaptcha API           |' I am not a Robot ' - Contact Form                   |
|Contact Us      |Send emails from contact us    |Use AWS SES service for sending emails                |
|Shipping quote  |Google Geocoder API            |Determine shipping distance                           |
|Order           |Google Places API              |Validate customer shipping address                    |
|Order           |Google Maps API                |Display customer shipping location                    |
|Order           |Send order emails              |Use AWS SES service for sending emails                |
|Content         |Content images on AWS          |Use S3 buckets and cloudfront to serve images         |
|Content         |Content images on GCP Buckets  |Use GCP buckets and cloudfront to serve images        |

### How to get those configurations

- **Google Maps API, Geocoding API, Places API**
> [Get Google MAPS, Geocoding and Places API keys](https://developers.google.com/maps/documentation/javascript/get-api-key)
> Follow instructions for Getting a Key (* Requires Google Cloud Account)

Enabling 3 apis
   - Maps javascript API
   - Geocoding API
   - Places API

![Google APIs diagram](/static/img/documentation/google-api.png "Enable Google APIs")

Then once apis are enabled keep keys aside to configure Shopizer

![Google keys diagram](/static/img/documentation/keys.png "Keep keys aside")


- **Google Places API**

> See procedure above

- **Google Geocoder API**

> See procedure above

- **Google reCaptcha**

> [Get reCaptcha API keys](https://developers.google.com/recaptcha)
> [Register new keys here](https://www.google.com/recaptcha/admin/create)

Register your domains and subdomains but aslo localhost for testing the solution



- **AWS S3 Bucket**

Infinispan is the technology used out of the box for managing images and files from a ingle instance of Shopizer. This solution works fine when working with Single instance and low to mid size traffic but would not scale for large traffic size or to serve multiple Shopizer running instances.

Our recommendation for being able to manage images and files at scal is to use cloud based file management and use Cloudfront technology for serving files faster to visitors from any location.

Cration of S3 bucket and Cloudfront distribution requires an AWS account.

>See this instruction video for creating an S3 bucket and a Cloudfront distribution on AWS

- Google GCP Bucket

Google Cloud Platform (GCP) also offers bucket and cloudfront technology for scaling images and files distribution.

Creation of buckets and cloudfront requires a GCP account.

>See this instruction video for creating abucket and a Cloudfront distribution on GCP

### Shopizer configuration files

Shopizer configuration files are located in

**shopizer/sm-core/src/main/resources/shopizer-core.properties**

Specific configuration files also exist for each profile

**shopizer/sm-core/src/main/resources/profiles/.../shopizer-core.properties**

```sh
# Which CMS method to use [ default | httpd | aws | gcp ]
# default = infinispan
# httpd = requires http server
# aws = AWS S3 -> See AWS S3 configuration below
# gcp = Google Cloud Storage

config.cms.method=aws


#AWS S3 configuration
#Name of bucket files
#Credentials must be set as environment variables when launching Shopizer
#AWS_ACCESS_KEY_ID=<ACCESS KEY ID>
#AWS_SECRETE_ACCESS_KEY=<SECRET ACCESS KEY>
config.cms.contentUrl=AWS_BUCKET_URL
config.cms.aws.bucket=AWS_BUCKET
config.cms.aws.region=AWS_BUCKET_REGION

#GCP Cloud Storage configuration
#Name of the bucket file
#If the bucket does not exists it will be created
#export GOOGLE_APPLICATION_CREDENTIALS="/home/user/Downloads/[FILE_NAME].json"
config.cms.gcp.bucket=GCP_BUCKET

#You need an AWS access key ID and AWS secret access key to access Amazon SES using an SDK
#AWS keys need to be specified in environment variables
#Email implementation [default | ses]
#default=SpringHtmlEmailSender - through SMTP server and requires configurations from admin console
#ses=AWS SES service
config.emailSender=default
config.emailSender.region=US_EAST_1

#Google map API key
config.shippingDistancePreProcessor.apiKey=GOOGLE_MAPS_KEY

#checkout and signup address validation
config.googleMapsKey=GOOGLE_MAPS_KEY

#recaptcha https://developers.google.com/recaptcha/
#testing keys
config.recaptcha.secretKey=RECAPTCHA_SECRETKEY
config.recaptcha.siteKey=RECAPTCHA_SITEKEY
```

### Other configurations

There are other configuration keys requiring attention

```sh
# Which CMS method to use [ default | httpd | aws | gcp ]
config.cms.method=default

#Shipping activated in the system ?
config.displayShipping=true

config.shippingDistancePreProcessor.acceptedZones=QC,ON,AB,NY,MA

#shipping rules
config.shipping.rule.priceByDistance=PriceByDistance.drl
config.shipping.rule.shippingModuleDecision=ShippingDecision.drl
```

|Key                                        |Description                                     |Valeur defaut      |
|-------------------------------------------|------------------------------------------------|-------------------|
|config.cms.method                          |                                                |                   |
|config.displayShipping                     |Google reCaptcha API                            |' I am not a Robot |
|config.shipping.rule.priceByDistance       |Send emails from contact us                     |                   |
|config.shipping.rule.shippingModuleDecision|Send emails from contact us                     |                   |

####AWS S3 Bucket + CloudFront for servig images

Requirements:

Have an AWS account
Have AWS user access key and secret access key handy

This video is a tutorial on the configuration of S3 and CloudFront to work as content management underlying CMS for Shopizer. (**Comming soon**)

Bucket Policy
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Sid": "PublicReadGetObject",
            "Effect": "Allow",
            "Principal": "*",
            "Action": [
                "s3:GetObject"
            ],
            "Resource": [
                "arn:aws:s3:::example-bucket/*"
            ]
        }
    ]
}

