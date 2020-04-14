FROM openjdk:latest

COPY ./sms-voting/voting-client/target/voting.jar voting.jar

EXPOSE 9000

ENTRYPOINT ["java", "-jar", "voting.jar"]