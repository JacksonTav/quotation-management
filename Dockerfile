FROM adoptopenjdk/openjdk8:ubi
ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME
COPY out/artifacts/app_jar/*.jar app.jar
EXPOSE 8081
CMD {"java", "-jar", "app.jar"}