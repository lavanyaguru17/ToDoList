# Use a base image with Java 17 (or your JDK version)
FROM openjdk:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the executable jar (make sure your app is built first)
COPY target/ToDoListApp-0.0.1-SNAPSHOT.jar app.jar

# Expose the port your Spring Boot app runs on (default 8080)
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java","-jar","app.jar"]
