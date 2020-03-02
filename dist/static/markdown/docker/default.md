Shopizer's team deliver on a continuous basis improvements on Shopizer which are almost available immediately from DockerHub latest Shopizer image.


### Build Shopizer

From a terminal or console
  
```sh
cd shopizer
```

**Build using Maven**

```sh
mvnw clean install
```

This command should result in a success message. If you have any errors during this process and require assistance,feel free to use Shopizer forum. See [Shopizer forum](https://groups.google.com/forum/#!forum/shopizer) for asking or searching Shopizer build related questions.

**Start Shopizer**

```sh
cd sm-shop
mvnw spring-boot:run
```

Once terminal or console displays that Shopizer is running and listening on service port and ready to be used.

Open a browser and tpe url http://localhost:8080

This will open Shopizer demo store. In order to open administration tool type url http://localhost:8080/admin. You can login admin tool with user **admin@shopizer.com** and password **password**

Another alternative to run Shopizer locally is to run the application from latest Docker image.

---

## See also

* [Beginning with Shopizer](/#/starting/starting)
* [Run from Docker image](/#/starting/docker)