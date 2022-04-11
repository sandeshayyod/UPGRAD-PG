FROM amazoncorretto:11-alpine-jdk
EXPOSE 8090
ARG JAR_FILE=target/*.jar
COPY ${JAR_FILE} payment-service.jar
ENTRYPOINT ["java","-jar","/payment-service.jar"]