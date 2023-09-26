# ***Дипломный проект по курсу «Тестировщик ПО»***

## ***О проекте***
Проект редставляет собой автоматизированое тестирование веб-сервиса, который предлагает купить тур по определённой цене двумя способами:

 - Обычная оплата по дебетовой карте.
 - Уникальная технология: выдача кредита по данным банковской карты.

Само приложение не обрабатывает данные по картам, а пересылает их банковским сервисам:

 - сервису платежей, далее Payment Gate;
 - кредитному сервису, далее Credit Gate.

# Документация
- [План автоматизации тестирования](https://github.com/ElenaObed/QADiplom/blob/main/documentation/Plan.md)
- [Отчет по итогам тестирования](https://github.com/ElenaObed/QADiplom/blob/main/documentation/Report.md)
- [Отчет по итогам автоматизации](https://github.com/ElenaObed/QADiplom/blob/main/documentation/Summary.md)

# Задача:
Автоматизировать позитивные и негативные сценарии покупки тура.
<img width="705" alt="service" src="https://github.com/ElenaObed/QADiplom/assets/130370912/708af60e-ef3f-47e8-bbed-9d3ced221e1a">

## ***Начало работы***
***1.*** Необходимо склонировать [репозиторий](https://github.com/ElenaObed/QADiplom)
```
git clone
```
***2.*** Открыть склонированный проект в Intellij IDEA

***3.*** Открыть терминал и запустить контейнеры из MySQL, PostgreSQL и Node.js командой в терминале:
```
docker-compose up -d
```
***4.*** Открыть новую вкладку в терминале IDEA и ввести следующую команду:

- для СУБД ***MySQL***:

```
java -jar artifacts/aqa-shop.jar -Dspring.datasource.url=jdbc:mysql://localhost:3306/app
```
- для СУБД ***PostgreSQL***:
```
java -jar artifacts/aqa-shop.jar -Dspring.datasource.url=jdbc:mysql://localhost:5432/app
```
Сервис запускается на "http://localhost:8080/

***5.*** Запустить автотесты. Открыть новую вкладку в терминале IDEA и ввести следующую команду:
- для СУБД ***MySQL***:

```
.\gradlew clean test "-Ddb.url=jdbc:mysql://localhost:3306/app"
```

- для СУБД ***PostgreSQL***:

```
.\gradlew clean test "-Ddb.url=jdbc:mysql://localhost:5432/app"
```
***6.*** Генерируем отчет Allure по итогам тестирования
Для запуска и просмотра отчета выполняем две команды по очереди:
```
.\gradlew allureReport
```
```
.\gradlew allureServe
```
***7.*** После запуска автотестов, завершить работу приложения, нажав сочетание клавиш в терминале.
        ```
    Ctrl+C
    ```

***8.*** Остановить работу контейнеров введя в терминале команду:
        ```
    docker-compose down
    ```
