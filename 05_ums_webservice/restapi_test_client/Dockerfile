FROM maven:3.8.1-adoptopenjdk-11 as builder
COPY src /app/src
COPY pom.xml /app
ENTRYPOINT mvn -f ./app/pom.xml clean test