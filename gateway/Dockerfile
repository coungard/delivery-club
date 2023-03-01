FROM maven:3-jdk-11-slim AS MAVEN_TOOL_CHAIN
LABEL maintainer="coungard@mail.ru"
VOLUME /tmp
COPY build/libs/*.jar app.jar

ENV POSTGRES $POSTGRES
ENV AUTH-SERVICE $AUTH-SERVICE
ENV EUREKA-SERVER $EUREKA-SERVER
ENV GATEWAY $GATEWAY
ENV ORDER-SERVICE $ORDER-SERVICE
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=docker", "/app.jar"]
