FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY . .
RUN javac -d out $(find src -name "*.java")
CMD ["java", "-cp", "out", "backend.Main"]
