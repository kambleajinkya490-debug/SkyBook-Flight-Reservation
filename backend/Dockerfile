FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY . .

RUN chmod +x gradlew
RUN ./gradlew build -x test

EXPOSE 8081

CMD ["./gradlew","bootRun"]
