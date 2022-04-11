FROM openjdk:14-jdk-alpine
MAINTAINER upgrad
ADD ./target/doctor-service.jar /opt/app/doctor-service.jar
WORKDIR /opt/app
ENV PATH="${PATH}:${JAVA_HOME}/bin"
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/opt/app/doctor-service.jar"]
