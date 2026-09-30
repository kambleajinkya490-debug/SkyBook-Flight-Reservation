FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .

RUN chmod +x gradlew
RUN ./gradlew dependencies --no-daemon

COPY src src

RUN ./gradlew clean bootJar --no-daemon

EXPOSE 8081

CMD ["java","-jar","build/libs/backend-0.0.1-SNAPSHOT.jar"]
