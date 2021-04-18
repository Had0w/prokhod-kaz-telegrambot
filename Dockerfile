FROM adoptopenjdk/openjdk8:ubi
ARG JAR_FILE=target/*.jar
ENV BOT_NAME=test_prokhod_kaz_bot
ENV BOT_TOKEN=1663915339:AAGXKCDq4ye355K5WlrMklsSyAkJYaPRsWg
COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java", "-Dbot.username=${BOT_NAME}", "-Dbot.token=${BOT_TOKEN}", "-jar","/app.jar"]