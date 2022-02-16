FROM openjdk:14-jdk-alpine
MAINTAINER upgrad
ADD ./target/movie-svc.jar /opt/app/movie-svc.jar
WORKDIR /opt/app
ENV PATH="${PATH}:${JAVA_HOME}/bin"
EXPOSE 8080
ENTRYPOINT [ "java", "-jar", "/opt/app/movie-svc.jar"]
