FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY . .
RUN ./gradlew build --no-daemon && mv build/libs/*.jar build/libs/app.jar
CMD ["java", "-jar", "build/libs/app.jar"]
