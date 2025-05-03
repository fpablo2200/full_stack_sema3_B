FROM openjdk:18-jdk-slim

WORKDIR /app

COPY target/demo-0.0.1-SNAPSHOT.jar app.jar

COPY Wallet_T57UGESLFEFULCW7 /app/wallet

EXPOSE 8080

ENTRYPOINT [ "java", "-jar", "app.jar" ]