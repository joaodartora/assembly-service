FROM openjdk:11
ADD build/libs/assembly-service-0.0.1-SNAPSHOT.jar /
ADD './wait-for-it.sh' '/wait-for-it.sh'
RUN chmod +x /wait-for-it.sh
EXPOSE 8080
ENTRYPOINT ["./wait-for-it.sh","assembly-database:3306","--", "java", "-XX:MinRAMPercentage=50", "-XX:MaxRAMPercentage=50", "-Djava.security.egd=file:/dev/./urandom", "-Dfile.encoding=UTF8", "-jar", "/assembly-service-0.0.1-SNAPSHOT.jar"]
