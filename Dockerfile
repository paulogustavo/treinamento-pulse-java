FROM openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/treinamento-0.1.0.jar treinamento.jar
ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar", "/treinamento.jar"]