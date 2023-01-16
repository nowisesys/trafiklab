# trafiklab

Display top 10 bus lines having largest number of route stops in Stockholm local area traffic.

## Backend

Developed using Apache Netbeans 16 and tested with OpenJDK 17. 

### Libraries

Requires the org.json library that can be downloaded from https://mvnrepository.com/artifact/org.json/json/20220924.

### Building

```bash
ant -f backend -Dnb.internal.action.name=rebuild clean jar
```

### Running

Run server from within the `trafiklab/backend` directory. Server listens on localhost port 8081 by default, pass custom address/port arguments if required.

```bash
java -jar dist/backend.jar 
java -jar dist/backend.jar 192.168.68.53 9090
```

## Frontend

Serve application from within the `trafiklab/frontend/public_html` directory. For example using PHP's builtin server.

```bash
php -S localhost:8082
```
