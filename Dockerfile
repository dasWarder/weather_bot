FROM maven:3.8.1-openjdk-11-slim

COPY ./ ./

RUN mvn clean package -Dmaven.test.skip=true

ARG FILE_NAME=target/*.jar

ENV BOT_USER ${WEATHER_BOT_USER}
ENV BOT_TOKEN ${WEATHER_BOT_TOKEN}
ENV WEATHER_STACK_KEY ${WEATHER_STACK_KEY}
ENV WEATHER_API_KEY ${WEATHER_API_KEY}
ENV REDIS_HOST=redis
ENV REDIS_PORT=6379

COPY $FILE_NAME /app.jar

CMD ["java", "-jar", "-Dspring.redis.host=$REDIS_HOST", "-Dspring.redis.port=$REDIS_PORT", "/app.jar"]

