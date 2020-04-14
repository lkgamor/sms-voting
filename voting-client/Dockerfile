FROM openjdk:latest

COPY ./voting-client/target/voting.jar voting.jar

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "voting.jar"]