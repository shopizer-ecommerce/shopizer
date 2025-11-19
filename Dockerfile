# Build stage - Compile the application
FROM maven:3.8-openjdk-11 AS build

WORKDIR /app

# Copy pom files first (for better Docker layer caching)
COPY pom.xml .
COPY sm-shop/pom.xml ./sm-shop/
COPY sm-core/pom.xml ./sm-core/
COPY sm-core-model/pom.xml ./sm-core-model/
COPY sm-core-modules/pom.xml ./sm-core-modules/
COPY sm-shop-model/pom.xml ./sm-shop-model/

# Download dependencies (cached layer if pom.xml doesn't change)
RUN mvn dependency:go-offline -B

# Copy source code
COPY sm-shop/src ./sm-shop/src
COPY sm-core/src ./sm-core/src
COPY sm-core-model/src ./sm-core-model/src
COPY sm-core-modules/src ./sm-core-modules/src
COPY sm-shop-model/src ./sm-shop-model/src

# Build the application
RUN mvn clean package -DskipTests -B

# Runtime stage - Run the application
FROM adoptopenjdk/openjdk11-openj9:alpine

RUN mkdir -p /opt/app && mkdir -p /files

# Copy the built JAR from build stage
COPY --from=build /app/sm-shop/target/shopizer.jar /opt/app/shopizer.jar

# Copy database and files
COPY sm-shop/SALESMANAGER.h2.db /
COPY sm-shop/files /files

EXPOSE 8080

# Set Spring profile to docker (so it uses profiles/docker/database.properties)
ENV SPRING_PROFILES_ACTIVE=cloud

CMD ["java", "-jar", "/opt/app/shopizer.jar"]

