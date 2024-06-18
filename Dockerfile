# Build stage
FROM maven:3-amazoncorretto-21 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

# Debugging: List files in /app/target to confirm the jar is created
RUN ls -la /app/target

# Run stage
FROM amazoncorretto:21-alpine
WORKDIR /app
COPY --from=build /app/target/guestifyapi.jar guestifyapi.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "guestifyapi.jar"]