FROM openjdk:14-jdk-alpine
MAINTAINER upgrad
ADD ./target/eureka-server.jar /opt/app/eureka-server.jar
WORKDIR /opt/app
ENV PATH="${PATH}:${JAVA_HOME}/bin"
EXPOSE 8761
ENTRYPOINT [ "java", "-jar", "/opt/app/eureka-server.jar"]
