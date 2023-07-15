FROM openjdk:17

WORKDIR /voll_med

COPY target/*.jar /voll_med/app.jar

EXPOSE 8080

CMD java -XX:+UseContainerSupport -jar -Dspring.profiles.active=prod app.jar