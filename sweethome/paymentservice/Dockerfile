FROM openjdk:14-jdk-alpine
MAINTAINER upgrad
ADD ./target/paymentService.jar /opt/app/paymentService.jar
WORKDIR /opt/app
ENV PATH="${PATH}:${JAVA_HOME}/bin"
EXPOSE 8083
ENTRYPOINT [ "java", "-jar", "/opt/app/paymentService.jar"]
