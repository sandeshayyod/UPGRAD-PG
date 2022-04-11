FROM openjdk:14-jdk-alpine
MAINTAINER upgrad
ADD ./target/notification-service-jar-with-dependencies.jar /opt/app/notification-service-jar-with-dependencies.jar
WORKDIR /opt/app
ENV PATH="${PATH}:${JAVA_HOME}/bin"
EXPOSE 8084
ENTRYPOINT [ "java", "-jar", "/opt/app/notification-service-jar-with-dependencies.jar", "kafka:9092" ]