#!/bin/sh
 
sudo apt-get install gradlew
 
../gradlew build --stacktrace

docker build -f Dockerfile -t beautymakeup-springboot .

#docker run -d -p 33060:3306 --name=beautymakeup-mysql --env="MYSQL_ROOT_PASSWORD=root" --env="MYSQL_PASSWORD=root" --env="MYSQL_USER=test" --env="MYSQL_PASSWORD=test" --env="MYSQL_DATABASE=beautymakeup" mysql:5.7
#docker run -d --name beautymakeup-springboot --link db -p 8095:8080 beautymakeup-springboot

docker-compose -f environment/docker-compose.yml up -d       

#java -Dspring.profiles.active=dev -jar beautymakeup-0.0.1-SNAPSHOT.jar --spring.config.location=application-dev.yml -Xmx512m -XX:MaxPermSize=768m
