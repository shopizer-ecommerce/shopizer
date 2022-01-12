FROM adoptopenjdk/openjdk11-openj9:alpine
RUN mkdir /opt/app
COPY target/shopizer.jar /opt/app
COPY SALESMANAGER.h2.db /
COPY ./files /
CMD ["java", "-jar", "/opt/app/shopizer.jar"]