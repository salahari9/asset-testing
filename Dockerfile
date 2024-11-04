# Use OpenJDK 17 image for running the application
FROM openjdk:17-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the jar file from the builder stage
COPY target/assetRequest-testing-*.jar asset-testing.jar

# Expose the port the app runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "asset-testing.jar"]
