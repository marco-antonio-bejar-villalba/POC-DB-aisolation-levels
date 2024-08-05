# POC-DB-aisolation-levels

### Introduction

The microservice only connects to a mariadb db, it has the configuration to manage the isolation levels.
Consult UserService to know about.

### How to run

1. Clone the repository
2. Run mvn clean install
3. Run the command docker build -t poc .
4. Run docker run -p 9001:9001 --network infrastructure_general poc
5. Take care of the network name, it should be the same as the one in the docker-compose file.

### TODO

- Enable configuration local and split it for docker.
  - Add more tests.
  - Add more documentation.
  - Do test for blocking records.  