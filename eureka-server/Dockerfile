FROM maven:3-jdk-11-slim AS MAVEN_TOOL_CHAIN
LABEL maintainer="coungard@mail.ru"
VOLUME /tmp
COPY build/libs/*.jar app.jar

ENV POSTGRES $POSTGRES
ENV AUTHMS $AUTHMS
ENV EUREKA $EUREKA
ENTRYPOINT ["java","-jar","/app.jar"]