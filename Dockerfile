# Use a multi-stage build to reduce the final image size

# Stage 1: Build the application
FROM gradle:8.2-jdk17 AS build

# Set working directory inside the build container
WORKDIR /home/gradle/src

# Copy necessary files FIRST for better caching
COPY gradle /home/gradle/src/gradle
COPY gradlew /home/gradle/src/gradlew
COPY build.gradle /home/gradle/src/
COPY settings.gradle /home/gradle/src/
COPY src /home/gradle/src/src

# Make the Gradle wrapper executable
RUN chmod +x /home/gradle/src/gradlew

# Build the application (using the Gradle wrapper)
RUN ./gradlew clean build --no-daemon

# Stage 2: Create the final image
FROM amazoncorretto:17.0.11-alpine

WORKDIR /app

# Copy the built JAR file from the build stage
COPY --from=build /home/gradle/src/build/libs/*.jar /app/data-ingestion-service.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/data-ingestion-service.jar"]

# Healthcheck
HEALTHCHECK --interval=10s --timeout=5s --retries=5 --start-period=10s CMD curl --fail http://localhost:8080/health || exit 1
