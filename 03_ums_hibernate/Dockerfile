FROM maven:3.8.1-adoptopenjdk-11 as builder

COPY src /app/src
COPY pom.xml /app

RUN mvn -f /app/pom.xml clean package


FROM openjdk:11 as jdk_build
COPY --from=builder /app/target/03_jpa_hibernate-0.0.1-SNAPSHOT-jar-with-dependencies.jar /app/app.jar

ENTRYPOINT ["java", "-jar", "/app/app.jar"]