FROM amazoncorretto:21-alpine

WORKDIR /app

# Copy gradle files
COPY gradle gradle
COPY gradlew .
COPY build.gradle.kts .
COPY settings.gradle.kts .

# Copy src folder
COPY src src

# Build a jar file
RUN ./gradlew build

# Run jar file
CMD ["java", "-jar", "build/libs/fundbox24-backend-0.0.1-SNAPSHOT.jar"]