FROM maven:3.8.6-jdk-11-slim
RUN apt-get update
RUN apt-get -y install jq
RUN apt-get -y install curl
WORKDIR /amazon/
COPY src /amazon/src/
COPY pom.xml /amazon/
COPY healthcheck.sh /amazon/
ENTRYPOINT ["/bin/sh"]
CMD ["healthcheck.sh"]