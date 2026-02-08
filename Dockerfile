# Use an official OpenJDK 23 image as the base image
FROM openjdk:23-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the Spring Boot JAR file from target folder to the container and rename it to app.jar
COPY target/dtect-springboot-0.0.1-SNAPSHOT.jar app.jar

# Expose port 8080 to allow external access to the Spring Boot application
EXPOSE 8080

# Use environment variable PORT if provided (for platforms like Render or Railway)
ENV PORT=8080

# Command to run the Spring Boot application
CMD ["sh", "-c", "java -jar app.jar --server.port=$PORT"]


#docker image of our project,
/*
docker --version
mvn clean install
*/

#push it to docker hub, from docker hub we are going to reference our image in render
