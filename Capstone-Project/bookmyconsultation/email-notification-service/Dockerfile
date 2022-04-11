FROM openjdk:14-jdk-alpine
MAINTAINER upgrad
ADD ./target/email-notification-service.jar /opt/app/email-notification-service.jar
WORKDIR /opt/app
ENV PATH="${PATH}:${JAVA_HOME}/bin"
EXPOSE 8087
ENTRYPOINT [ "java", "-jar", "/opt/app/email-notification-service.jar"]
