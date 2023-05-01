FROM tomcat:9
COPY target/Hibernate_2.0.Final.war /usr/local/tomcat/webapps/
RUN apt-get update \
    && apt-get install -y postgresql-client \
    && wget -O /usr/local/tomcat/lib/postgresql.jar https://jdbc.postgresql.org/download/postgresql-9.4-1205-jdbc42.jar