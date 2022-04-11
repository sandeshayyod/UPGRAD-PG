FROM openjdk:14-jdk-alpine
MAINTAINER upgrad
ADD ./target/bmc-gateway.jar /opt/app/bmc-gateway.jar
WORKDIR /opt/app
ENV PATH="${PATH}:${JAVA_HOME}/bin"
EXPOSE 9191
ENTRYPOINT [ "java", "-jar", "/opt/app/bmc-gateway.jar"]
