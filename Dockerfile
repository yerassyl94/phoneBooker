FROM gradle:7.2-jdk11 as builder

ADD --chown=gradle . /app
WORKDIR /app
RUN gradle clean build -x test

FROM openjdk:11.0.2-jre-stretch

WORKDIR /app
COPY --from=builder /app/build/libs/*.jar .
ENV JVM_OPTS=""

RUN useradd -ms /bin/bash qa
USER qa
EXPOSE 8080
ENTRYPOINT exec java $JVM_OPTS -ea -jar /app/*.jar