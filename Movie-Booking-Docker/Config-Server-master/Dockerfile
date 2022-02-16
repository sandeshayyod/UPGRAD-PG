FROM openjdk:14-jdk-alpine
MAINTAINER upgrad
ADD ./target/Config-Server-master.jar /opt/app/Config-Server-master.jar
WORKDIR /opt/app
ENV PATH="${PATH}:${JAVA_HOME}/bin"
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/opt/app/Config-Server-master.jar"]
