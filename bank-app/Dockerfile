# Download the latest alpine image
# FROM amazoncorretto:17.0.8-alpine3.18 as BUILD
FROM alpine:latest as BUILD

# Install the JDK
RUN sed -i 's/https/http/' /etc/apk/repositories
RUN apk update && apk add openjdk17

# Create app directory
RUN mkdir -m 777 -p /usr/app 

# Set the working directory to /usr/app
WORKDIR /usr/app

# Copy the JAR into the image
COPY target/bank-app-1.0-SNAPSHOT-jar-with-dependencies.jar /usr/app

# Create volume
VOLUME /home/.bank-app-data

# Run the JAR as the entrypoint
ENTRYPOINT ["java", "-jar", "/usr/app/bank-app-1.0-SNAPSHOT-jar-with-dependencies.jar"]