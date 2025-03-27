FROM openjdk:21-jdk-slim
COPY target/service-0.0.1-SNAPSHOT.jar app.jar
COPY target/classes classes

ENTRYPOINT ["java", "-jar", "app.jar"]

