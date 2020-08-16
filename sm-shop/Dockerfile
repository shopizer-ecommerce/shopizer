FROM tomcat:8.5.40-jre8
MAINTAINER shopizerecomm

VOLUME /tmp

# Diagnose and analyze potential db connection issue and log files
#RUN apt-get update && apt-get install -y mysql-client
#RUN apt-get update && apt-get install -y vim

RUN apt-get update && \
    apt-get install -y \
    	vim \
    && apt-get clean


# Delete existing ROOT folder
RUN rm -rf /usr/local/tomcat/webapps/ROOT

RUN mkdir -p /usr/local/tomcat/files

ADD ./target/ROOT.war /usr/local/tomcat/webapps/
ADD ./SALESMANAGER.h2.db /usr/local/tomcat/
COPY ./files/ /usr/local/tomcat/files/
RUN ls -la /usr/local/tomcat/files/*

ENV JAVA_OPTS="-Xmx1024m"

CMD ["catalina.sh", "run"]

EXPOSE 8080

