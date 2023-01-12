FROM maven:3.6.3 AS maven
# Create a workdir for our app
WORKDIR /usr/src/app
COPY . /usr/src/app

ENV TZ=America/Los_Angeles

# Compile and package the application to an executable JAR
RUN mvn clean package -DskipTests
# Using java 11
FROM openjdk:11-jdk

ARG JAR_FILE=/usr/src/app/target/*.jar
# Copying JAR file
COPY --from=maven ${JAR_FILE} app.jar

ENTRYPOINT ["java","-jar","-Dspring.profiles.active=prod","/app.jar"]

