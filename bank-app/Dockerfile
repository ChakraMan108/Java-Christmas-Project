FROM openjdk:17
RUN mkdir -p /app
RUN mkdir -p /app/bank-app-data
COPY ./target/bank-app-1.0-SNAPSHOT-jar-with-dependencies.jar /app
WORKDIR /app
ENTRYPOINT ["java", "-jar", "bank-app-1.0-SNAPSHOT-jar-with-dependencies.jar"];