FROM openjdk:14-jdk-alpine
MAINTAINER upgrad
ADD ./target/appointment-service.jar /opt/app/appointment-service.jar
WORKDIR /opt/app
ENV PATH="${PATH}:${JAVA_HOME}/bin"
EXPOSE 8082
ENTRYPOINT [ "java", "-jar", "/opt/app/appointment-service.jar"]
