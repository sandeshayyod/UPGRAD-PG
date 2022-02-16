FROM openjdk:14-jdk-alpine
MAINTAINER upgrad
ADD ./target/service-registry.jar /opt/app/service-registry.jar
WORKDIR /opt/app
ENV PATH="${PATH}:${JAVA_HOME}/bin"
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/opt/app/service-registry.jar"]
