FROM tomcat:latest
MAINTAINER Jonas Hörnstein "jonas@bluebottle.se"
RUN ["rm", "-fr", "/usr/local/tomcat/webapps/ROOT"]
COPY ./sm-shop-2.0.5-SNAPSHOT.war /usr/local/tomcat/webapps/ROOT.war
CMD ["catalina.sh", "run"]
