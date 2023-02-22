call .\gradlew clean build -p gateway
call .\gradlew clean build -p auth-service
call .\gradlew clean build -p eureka-server
call .\gradlew clean build -p order-service

docker build --tag coungard/delivery-club:gateway ./gateway
docker build --tag coungard/delivery-club:auth-service ./auth-service
docker build --tag coungard/delivery-club:eureka-server ./eureka-server
docker build --tag coungard/delivery-club:order-service ./order-service
