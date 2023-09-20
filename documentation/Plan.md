# План автоматизации тестирования веб-сервиса по продаже тура в Марракеш.
## Перечень автоматизируемых сценариев:
### ***1. Позитивные сценарии:***
***а. Покупка тура и оплата по дебетовой карте со статусом APPROVED***

***б. Покупка тура и оплата по дебетовой карте со статусом DECLINED***

***в. Покупка тура и оплата в кредит по данным карты со статусом APPROVED***

***с. Покупка тура и оплата в кредит по данным карты со статусом DECLINED***

### ***2. Негативные сценарии:***
***а. Отправка формы с номером другой карты***

***б. Отправка формы с неполным номером карты***

***в. Отправка формы с пустым полем "Номер карты"***

***г. Отправка формы с невалидным месяцем срока действия карты***

***д. Отправка формы с пустым полем "Месяц"***

***е. Отправка формы с невалидным сроком года действия карты***

***ж. Отправка формы с пустым полем "Год"***

***з. Отправка формы с данными владельца на кириллице***

***и. Отправка формы с указанием только фамилии или имени владельца на латинице***

***к. Отправка формы с некорректными данными владельца, в поле имя одна буква***

***л. Отправка формы с некорректными данными владельца, в поле имя много букв***

***м. Отправка формы с некорректными данными владельца, в поле цифры или спецсимволы***

***н. Отправка формы с пустым полем "Владелец"***

***о. Отправка формы с некорректным значением "CVC/CVV", ввести одну цифру***

***п. Отправка формы с пустым полем "CVC/CVV":***

## Перечень используемых инструментов с обоснованием выбора
***1. IntelliJ IDEA - интегрированная среда разработки программного обеспечения для многих языков программирования, в частности Java, JavaScript, Python, разработанная компанией JetBrains.***

***2. Java - язык программирования, имеет набор готового ПО для разработки и запуска приложений.***

***3. JUnit - фреймворк для модульного тестирования программного обеспечения на языке Java***

***4. Selenide - это автоматизированная система тестирования программного обеспечения, используемая для написания программных кодов***

***5. Lombok - библиотека для сокращения кода в классах и расширения функциональности языка Java***

***6. Faker - библиотека для генерации случайных тестовых данных***

***7. Git и GitHub - Git — это система контроля версий, а GitHub — онлайн-сервис, по сути социальная сеть. Одна из основных целей GitHub — быть единым местом для проектов с исходным кодом***

***8. Docker - программная платформа для быстрой разработки, тестирования и развертывания приложений***

***9. Rest Assured - java-библиотека для тестирования REST API***

***10. Gradle - система управления зависимостями.***

***11. Allure - фреймворк для создания простых и понятных отчётов автотестов***

## Перечень и описание возмоных рисков при автоматизации
***1. Остутствие документации и ТЗ, нет четкогопонимания как работает программа и что можно считать за ошибку***

***2. Возможно, что произойдет изменение в заполнение обязательных полях тура***

***3. Возможно тур закончится и его исключат из список***

***4. Для удобства тестрирования нет параметра test-id в css-селекторах***

***5. Возможность удаления, переменования и изменения кнопок на сайте***

## Интервальная оценка с учётом рисков
Для подготовки и настройки программ, написание тест-плана, разработка тестов и подготовка отчетность необходимо ....?