FROM adoptopenjdk/openjdk11-openj9:alpine

RUN mkdir -p /opt/app && mkdir -p /files

COPY sm-shop/target/shopizer.jar /opt/app/shopizer.jar
COPY sm-shop/SALESMANAGER.h2.db /
COPY sm-shop/files /files

EXPOSE 8080

# Don't set any profile - use default
ENV JAVA_OPTS="-Xms512m -Xmx1024m"

WORKDIR /

CMD ["java", "-jar", "/opt/app/shopizer.jar"]
