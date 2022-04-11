FROM openjdk:14-jdk-alpine
MAINTAINER upgrad
ADD ./target/rating-service.jar /opt/app/rating-service.jar
WORKDIR /opt/app
ENV PATH="${PATH}:${JAVA_HOME}/bin"
EXPOSE 9191
ENTRYPOINT [ "java", "-jar", "/opt/app/rating-service.jar"]
