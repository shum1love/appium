# Appium Smoke Tests (Android Wikipedia)

Небольшой, но аккуратный проект на `Java + Appium + JUnit 5 + Maven` с Allure-отчетами. Структура разделяет тестовую логику и прикладной код (драйвер, экраны, утилиты), чтобы легко добавлять новые кейсы.

## Требования
- Java 17+
- Maven 3.9+
- Node.js LTS (для Appium)
- Android SDK + эмулятор или реальное устройство
- Appium Server 2.x и драйвер `uiautomator2`  
  ```bash
  npm install -g appium
  appium driver install uiautomator2
  ```

## Структура проекта (главное)
- `src/main/java/com/mobile/helper/DriverFactory.java` — создание `AndroidDriver` с учетом `-D` параметров.
- `src/main/java/com/mobile/utils/TestConfig.java` — все конфигурации и capability-параметры.
- `src/main/java/com/mobile/utils/WaitUtils.java` — ожидания и клики с таймаутами.
- `src/main/java/com/mobile/screens/*` — Screen Objects (действия и проверки экранов).
- `src/main/java/com/mobile/dto/SearchData.java` — пример хранения тестовых данных.
- `src/main/java/com/mobile/enums`, `locators` — заготовки под перечисления и общие локаторы.
- `src/test/java/com/mobile/base/BaseTest.java` — общий setup/teardown.
- `src/test/java/com/mobile/tests/smoke/*` — по одному smоke-кейсу на класс.
- `src/test/java/com/mobile/architecture/ArchitectureTest.java` — архитектурные правила (ArchUnit) для поддержки чистой структуры.

## Запуск
1) Запусти эмулятор/устройство (`adb devices` должен показать девайс).  
2) Подними Appium:
   ```bash
   appium
   ```
3) Запусти все тесты:
   ```bash
   mvn test
   ```
4) Посмотреть отчёт Allure:
   ```bash
   mvn allure:serve
   ```
   или с сохранением отчёта:
   ```bash
   mvn allure:report
   ```

### Архитектурные проверки (ArchUnit)
Запуск вместе со всеми тестами уже включён в обычный `mvn test`.

Если нужно прогнать только архитектурные правила:
```bash
mvn test -Dtest=ArchitectureTest
```

### Параметры через `-D` (пример)
```bash
mvn test -DdeviceName="Pixel_7_API_34" -DplatformVersion="14" -DappiumServerUrl="http://127.0.0.1:4723"
```
Доступны также: `automationName`, `appPackage`, `appActivity`, `noReset`, `newCommandTimeoutSec`, `platformName`.

## Как писать новые тесты
1) Найди локаторы через Appium Inspector и добавь действия/проверки в нужный Screen (`src/main/java/com/mobile/screens`), используя `@Step` для красивых шагов в отчёте.
2) Общие утилиты кладём в `utils`, работу с драйвером — в `helper`.
3) Для каждого сценария создай отдельный тест-класс в `src/test/java/com/mobile/tests/smoke` (или другой пакет по смыслу). Наследуйся от `BaseTest`.
4) Добавляй аннотации Allure (`@Epic`, `@Feature`, `@Story`, `@Severity`, `@Owner`, `@DisplayName`) прямо на тесты.
5) Держи тесты короткими: `Arrange` (подготовка данных/экрана) → `Act` (действие) → `Assert` (проверка).

## Быстрый чеклист, если что-то упало
- Устройство видно в `adb devices`, Appium запущен на `http://127.0.0.1:4723`.
- Пакет/активити совпадают с установленным приложением (`org.wikipedia/.main.MainActivity` по умолчанию).
- Если приложение открывается не с главной страницы — попробуй `-DnoReset=false`.
- Если поменялись идентификаторы элементов — обнови локаторы в соответствующем Screen.
