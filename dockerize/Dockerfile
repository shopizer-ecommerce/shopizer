FROM tomcat:latest
MAINTAINER Jonas Hörnstein "jonas@bluebottle.se"
ENV JAVA_OPTS="-Xms1024m -Xmx1024m -XX:MaxPermSize=256m"
ENV CATALINA_OPTS="-Xms1024M -Xmx1024M"
RUN ["rm", "-fr", "/usr/local/tomcat/webapps/ROOT"]
COPY ./sm-shop-2.0.5-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
CMD ["catalina.sh", "run"]
