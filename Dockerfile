#For Java 11

FROM maven:3.6.3-jdk-11-openj9

WORKDIR /springbootrestdemo

COPY . .

RUN mvn clean install

CMD mvn spring-boot:run
