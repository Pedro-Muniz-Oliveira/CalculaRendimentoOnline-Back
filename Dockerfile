FROM openjdk:17-jdk-slim
WORKDIR /app
COPY . .
RUN ./gradlew build
EXPOSE 8080
CMD ["java", "-cp", "build/libs/backend.jar", "backend.App"]
