FROM maven:3.8.1-openjdk-11-slim

COPY ./ ./

RUN mvn clean instal -Dmaven.test.skip=true

ARG FILE_NAME=target/*jar

ENV BOT_USER ${WEATHER_BOT_USER}
ENV BOT_TOKEN ${WEATHER_BOT_TOKEN}
ENV WEATHER_STACK_KEY ${WEATHER_STACK_KEY}
ENV REDIS_HOST=redis
ENV REDIS_PORT=6379

COPY $FILE_NAME /bot.jar

CMD ["java", "-jar", "-Dspring.redis.host=$REDIS_HOST", "-Dspring.redis.port=$REDIS_PORT", "/bot.jar"]

