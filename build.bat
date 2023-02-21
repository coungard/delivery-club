.\gradlew clean build -p gateway
.\gradlew clean build -p auth-service
.\gradlew clean build -p eureka-server
.\gradlew clean build -p order-service

docker build --tag coungard/delivery-club:gateway ./gateway
docker build --tag coungard/delivery-club:auth-service ./auth-service
docker build --tag coungard/delivery-club:eureka-server ./eureka-server
docker build --tag coungard/delivery-club:order-service ./order-service
