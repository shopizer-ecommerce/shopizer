FROM openjdk:8
COPY ./target/ROOT.war /usr/src/project/
ADD ./SALESMANAGER.h2.db /usr/src/project/
COPY ./files/ /usr/src/project/files/
WORKDIR /usr/src/project

FROM tomcat:9.0.10-jre8

RUN rm -rf /usr/local/tomcat/webapps/*
RUN mkdir -p /usr/local/tomcat/files

COPY --from=0 /usr/src/project/ROOT.war /usr/local/tomcat/webapps/ROOT.war
COPY --from=0 /usr/src/project/SALESMANAGER.h2.db /usr/local/tomcat/
COPY --from=0 /usr/src/project/files/ /usr/local/tomcat/files/

ENV JAVA_OPTS="-Xmx1024m"

CMD ["catalina.sh", "run"]

EXPOSE 8080