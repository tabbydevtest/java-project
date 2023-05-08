# Use an OpenJDK runtime as a parent image
FROM openjdk:11-jre-slim

# Set the working directory to /app
WORKDIR /app

# Copy the pom.xml file to the container
COPY pom.xml .
RUN apt-get update && apt-get install -y maven
# Run Maven to download dependencies
RUN mvn dependency:go-offline

# Copy the rest of the project files to the container
COPY src ./src

# Build the project using Maven
RUN mvn package

# Expose port 8080
EXPOSE 8080

# Run the Spring app
CMD ["java", "-jar", "target/task_backend.jar"]