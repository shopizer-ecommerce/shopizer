FROM adoptopenjdk/openjdk11-openj9:latest
RUN mkdir /opt/app
COPY target/sm-shop.jar /opt/app
CMD ["java", "-jar", "/opt/app/sm-shop.jar"]