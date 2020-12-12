FROM openjdk:11

RUN \
  sed -i 's/# \(.*multiverse$\)/\1/g' /etc/apt/sources.list && \
  apt-get update -y && \
  apt-get install -y build-essential && \
  apt-get install -y maven

WORKDIR /root

COPY . .

CMD ./mvnw spring-boot:run > log.txt