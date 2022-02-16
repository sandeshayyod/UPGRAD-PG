FROM openjdk:14-jdk-alpine
MAINTAINER upgrad
ADD ./target/notificationService-jar-with-dependencies.jar /opt/app/notificationService-jar-with-dependencies.jar
WORKDIR /opt/app
ENV PATH="${PATH}:${JAVA_HOME}/bin"
EXPOSE 8084
ENTRYPOINT [ "java", "-jar", "/opt/app/notificationService-jar-with-dependencies.jar", "ec2-54-237-116-57.compute-1.amazonaws.com:9092" ]