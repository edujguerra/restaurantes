FROM openjdk:17-jdk-alpine
COPY /target/restaurantes-0.0.1-SNAPSHOT.jar .
ENTRYPOINT ["java","-jar","restaurantes-0.0.1-SNAPSHOT.jar"]
EXPOSE 8080