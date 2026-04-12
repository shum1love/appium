# Appium Smoke Tests (Android Wikipedia)

Простой проект смок-тестов для Android-приложения Wikipedia на `Java + Appium + JUnit 5 + Maven`.

Тесты покрывают базовый пользовательский путь:
- запуск приложения;
- открытие поиска;
- ввод поискового запроса;
- проверка появления результатов;
- открытие статьи и возврат назад.

## 1. Что нужно установить

- Java 17+
- Maven 3.9+
- Node.js LTS (для Appium)
- Android Studio (с Android SDK и эмулятором)
- Appium Server 2.x
- Appium драйвер `uiautomator2`

Команды для Appium (один раз):

```bash
npm install -g appium
appium driver install uiautomator2
```

Проверка:

```bash
appium -v
appium driver list --installed
```

## 2. Подготовка эмулятора

1. Открой Android Studio.
2. Запусти AVD через Device Manager.
3. Убедись, что устройство видно:

```bash
adb devices
```

4. Установи Wikipedia на эмулятор (если ещё не установлена):
   - через Play Market на эмуляторе, или
   - через apk:

```bash
adb install path\to\wikipedia.apk
```

По умолчанию в проекте используются:
- `appPackage=org.wikipedia`
- `appActivity=org.wikipedia.main.MainActivity`

## 3. Запуск Appium Server

В отдельном терминале:

```bash
appium
```

Сервер должен слушать `http://127.0.0.1:4723`.

## 4. Запуск тестов

Из корня проекта:

```bash
mvn test
```

Запуск одного теста:

```bash
mvn -Dtest=SmokeTests#searchResultsShouldBeDisplayed test
```

## 5. Параметры запуска через `-D`

Можно переопределить параметры без изменений в коде:

```bash
mvn test -DdeviceName="Pixel_7_API_34" -DplatformVersion="14" -DappiumServerUrl="http://127.0.0.1:4723" -DnoReset=true -DnewCommandTimeoutSec=120
```

Доступные параметры:
- `appiumServerUrl`
- `deviceName`
- `platformName` (по умолчанию `Android`)
- `platformVersion`
- `automationName` (по умолчанию `UiAutomator2`)
- `appPackage`
- `appActivity`
- `noReset`
- `newCommandTimeoutSec`

## 6. Структура проекта

- `src/test/java/com/mobile/config/TestConfig.java` — чтение всех `-D` параметров.
- `src/test/java/com/mobile/driver/DriverFactory.java` — создание `AndroidDriver`.
- `src/test/java/com/mobile/base/BaseTest.java` — общий setup/teardown.
- `src/test/java/com/mobile/pages/*` — page objects (экраны приложения).
- `src/test/java/com/mobile/tests/SmokeTests.java` — сами смок-тесты.
- `src/test/java/com/mobile/utils/WaitUtils.java` — ожидания элементов.

## 7. Что делать, если тесты упали

1. Проверь, что эмулятор запущен и виден в `adb devices`.
2. Проверь, что Appium сервер запущен.
3. Проверь, что Wikipedia открывается вручную на эмуляторе.
4. Если изменился UI приложения, обнови локаторы в `src/test/java/com/mobile/pages`.
5. Если тест стартует не с главного экрана, выставь `-DnoReset=false` и запусти снова.

## 8. Что показать на ревью

- Демонстрацию полного прогона `mvn test`.
- Пример точечного прогона одного теста.
- Где меняются capability-параметры через `-D...`.
- Где лежат page objects и почему такая структура удобна для поддержки.
