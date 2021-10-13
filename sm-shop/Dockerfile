FROM adoptopenjdk/openjdk11-openj9:alpine
RUN mkdir /opt/app
COPY target/shopizer.jar /opt/app
CMD ["java", "-jar", "/opt/app/shopizer.jar"]