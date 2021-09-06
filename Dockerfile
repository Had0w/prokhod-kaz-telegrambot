FROM adoptopenjdk/openjdk8:ubi
ARG JAR_FILE=target/*.jar
ENV BOT_NAME=test_prokhod_kaz_bot
ENV BOT_TOKEN=1663915339:AAGXKCDq4ye355K5WlrMklsSyAkJYaPRsWg
ENV SPRING_DATASOURCE_USERNAME=postgres
ENV SPRING_DATASOURCE_PASSWORD=11Kosmea9
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}"
, "-Dspring.datasource.username=${SPRING_DATASOURCE_USERNAME}"
, "-Dspring.datasource.password=${SPRING_DATASOURCE_PASSWORD}"
, "-jar","/app.jar"]