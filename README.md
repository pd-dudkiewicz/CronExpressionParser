# CronExpressionParser

Application parsing a cron string expanding each field to show the times at which it will run.

## Running the script

Prerequisites:
* JDK 21: https://www.oracle.com/java/technologies/downloads/

Building jar:
```
./mvnw package
```

Running script:
```
java -jar target/CronExpressionParser-1.0.jar "INPUT_CRON_STRING"
```

Example usage:
```
java -jar target/CronExpressionParser-1.0.jar "*/15 0 1,15 * 1-5 /usr/bin/find"
```

Expected output:
```
minute          0 15 30 45
hour            0
day of month    1 15
month           1 2 3 4 5 6 7 8 9 10 11 12
day of week     1 2 3 4 5
command         /usr/bin/find
```

## Running tests
```
./mvnw test
```