FROM openjdk:17-alpine
ARG JAR_FILE=./temp_money_service1/com/MoneyService/0.0.1-SNAPSHOT/MoneyService-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /app/
EXPOSE 8090
CMD ["java", "-jar", "/app/MoneyService-0.0.1-SNAPSHOT.jar"]