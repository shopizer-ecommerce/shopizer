FROM adoptopenjdk/openjdk11-openj9:alpine

RUN mkdir /opt/app
RUN mkdir /files

# All files are in sm-shop/ directory
COPY sm-shop/target/shopizer.jar /opt/app
COPY sm-shop/SALESMANAGER.h2.db /
COPY sm-shop/files /files

CMD ["java", "-jar", "/opt/app/shopizer.jar"]