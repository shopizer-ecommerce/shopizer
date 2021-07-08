FROM adoptopenjdk/opendjdk11:latest
EXPOSE 8080
ARG JAR_FILE=target/sm-shop.jar
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]