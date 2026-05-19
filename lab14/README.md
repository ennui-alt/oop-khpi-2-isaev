\# Лабораторна робота №14



\## Тема



Базова багатопотоковість у Java.



\## Варіант



Варіант №3 — інтернет-магазин.



\## Опис



Проєкт демонструє базову роботу з потоками в Java на прикладі інтернет-магазину.



У програмі паралельно виконуються три незалежні задачі:



\- обробка замовлень;

\- перевірка описів товарів;

\- формування накладних.



\## Основні класи



\- `Product` — товар інтернет-магазину.

\- `Order` — замовлення покупця.

\- `Invoice` — накладна для замовлення.

\- `StoreService` — сервіс для обробки логіки магазину.



\## Багатопотоковість



У роботі використано два способи створення потоків:



\- `OrderProcessingThread` наслідує `Thread`;

\- `ProductDescriptionTask` та `InvoiceTask` реалізують `Runnable`.



Потоки запускаються через `start()`, виконують поетапну роботу з використанням `Thread.sleep()`, а головна програма очікує їх завершення через `join()`.



Порядок повідомлень у консолі може змінюватися, оскільки він залежить від планувальника потоків Java.



\## Структура проєкту



```text

src/main/java/ua/khpi/oop/lab14/demo/Main.java

src/main/java/ua/khpi/oop/lab14/model/Product.java

src/main/java/ua/khpi/oop/lab14/model/Order.java

src/main/java/ua/khpi/oop/lab14/model/Invoice.java

src/main/java/ua/khpi/oop/lab14/service/StoreService.java

src/main/java/ua/khpi/oop/lab14/threads/OrderProcessingThread.java

src/main/java/ua/khpi/oop/lab14/threads/ProductDescriptionTask.java

src/main/java/ua/khpi/oop/lab14/threads/InvoiceTask.java

src/test/java/ua/khpi/oop/lab14/demo/MainTest.java

src/test/java/ua/khpi/oop/lab14/service/StoreServiceTest.java

src/test/java/ua/khpi/oop/lab14/threads/OrderProcessingThreadTest.java

src/test/java/ua/khpi/oop/lab14/threads/ProductDescriptionTaskTest.java

src/test/java/ua/khpi/oop/lab14/threads/InvoiceTaskTest.java

## Запуск програми



Головний клас:



```text

ua.khpi.oop.lab14.demo.Main

```



\## Запуск тестів



Для запуску модульних тестів потрібно виконати команду:



```bash

mvn test

```



\## Технології



У роботі використано:



\- Java 21

\- Maven

\- JUnit 5

\- PlantUML

