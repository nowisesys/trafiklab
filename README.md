# trafiklab

Display top 10 bus lines with most traffic stops in Stockholm

## Backend

Tested with OpenJDK 17. Server listens on localhost port 8081 by default, pass arguments for custom address/port:

```bash
java nowise.Backend
java nowise.Backend 192.168.68.53 8082
```

### Libraries

Requires the org.json library that can be downloaded from https://mvnrepository.com/artifact/org.json/json/20220924.

### Building

```bash
ant -f backend -Dnb.internal.action.name=rebuild clean jar
```

## Frontend

Serve application from within the `trafiklab/frontend/public_html`
directory. For example:

```bash
php -S localhost:8082
```
