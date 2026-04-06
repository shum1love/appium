# Wikipedia Appium Android Tests (Java + Maven + JUnit 5)

## Requirements
- Java 17+
- Maven 3.9+
- Running local Appium server on `http://127.0.0.1:4723`
- Android emulator/device with installed Wikipedia app (`org.wikipedia`)

## Run smoke tests
```bash
mvn test
```

## Optional device capabilities via system properties
```bash
mvn test -DdeviceName="Pixel_7_API_34" -DplatformVersion="14"
```
