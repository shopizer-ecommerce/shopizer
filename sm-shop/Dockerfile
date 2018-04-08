FROM tomcat:8.5.15-jre8
MAINTAINER shopizerecomm

VOLUME /tmp

# Delete existing ROOT folder
RUN rm -rf /usr/local/tomcat/webapps/ROOT

ADD ./target/ROOT.war /usr/local/tomcat/webapps/

ENV JAVA_OPTS="-Xmx1024m"

CMD ["catalina.sh", "run"]

EXPOSE 8080

