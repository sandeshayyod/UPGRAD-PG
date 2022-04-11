FROM openjdk:14-jdk-alpine
MAINTAINER upgrad
ADD ./target/user-service.jar /opt/app/user-service.jar
WORKDIR /opt/app
ENV PATH="${PATH}:${JAVA_HOME}/bin"
EXPOSE 8083
ENTRYPOINT [ "java", "-jar", "/opt/app/user-service.jar"]
