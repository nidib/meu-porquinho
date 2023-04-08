FROM gradle:7.6.1-jdk17 AS BUILDER
WORKDIR /usr/app/
COPY build.gradle settings.gradle .
COPY src src
RUN gradle clean build

FROM openjdk:17
ENV ARTIFACT_NAME=cofrinho-0.0.1-SNAPSHOT.jar
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY --from=BUILDER $APP_HOME/build/libs/$ARTIFACT_NAME .
EXPOSE 8080
CMD java -jar $ARTIFACT_NAME