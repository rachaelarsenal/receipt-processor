FROM gradle:8.5.0-jdk21-alpine AS builder

WORKDIR /app

COPY --chown=gradle:gradle . /app

RUN gradle clean build --no-daemon

FROM bellsoft/liberica-openjdk-alpine:21

WORKDIR /app

COPY --from=builder /app/build/libs/*.jar /app/receipt-processor.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "receipt-processor.jar"]