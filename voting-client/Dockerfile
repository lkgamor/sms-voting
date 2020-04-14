#Download base maven image
FROM maven:3.6-jdk-11 AS maven-dependencies

#Set our working directory
WORKDIR /build

#Copy project pom file to working directory
COPY voting-client/pom.xml .

#Build and cache all dependencies
RUN mvn dependency:go-offline -B

#Copy all required files and folders to working directory
COPY ./flyway.properties .
COPY ./src ./src

#Build application binary
RUN mvn clean package -DskipTests

#Download final package image
FROM openjdk:11-jre-slim

#Set project deployment directory
WORKDIR /voting-app

#Copy over built artifacts from maven-dependencies
COPY --from=maven-dependencies target/voting.jar ./

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "./target/voting.jar"]