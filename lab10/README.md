\# Лабораторна робота №10



\## Тема



Узагальнені контейнери та ітератори в Java.



\## Варіант



Варіант 3 — \*\*Список задач команди\*\*.



\## Мета роботи



Метою лабораторної роботи є створення власного узагальненого контейнера в Java, реалізація власного ітератора та забезпечення можливості послідовного обходу елементів за допомогою `Iterator` і циклу `for-each`.



\## Опис предметної області



У роботі розглядається предметна область списку задач команди.



Кожна задача має назву, опис, відповідального розробника та поточний статус. Також для задачі зберігається історія зміни статусів.



Для опису предметної області створено такі класи:



\- `Task` — задача команди;

\- `Developer` — розробник, відповідальний за задачу або зміну статусу;

\- `StatusRecord` — запис про зміну статусу задачі;

\- `TaskStatus` — перелік можливих статусів задачі.



\## Структура проєкту



```text

src/main/java/ua/khpi/oop/lab10/container/TaskContainer.java

src/main/java/ua/khpi/oop/lab10/model/Developer.java

src/main/java/ua/khpi/oop/lab10/model/Task.java

src/main/java/ua/khpi/oop/lab10/model/StatusRecord.java

src/main/java/ua/khpi/oop/lab10/model/TaskStatus.java

src/main/java/ua/khpi/oop/lab10/demo/Main.java

src/test/java/ua/khpi/oop/lab10/container/TaskContainerTest.java

src/test/java/ua/khpi/oop/lab10/model/TaskTest.java

src/test/java/ua/khpi/oop/lab10/model/StatusRecordTest.java

pom.xml

README.md

REPORT.md

```



\## Реалізований контейнер



У роботі реалізовано власний узагальнений контейнер:



```java

TaskContainer<T>

```



Контейнер є generic-класом, тому може зберігати об'єкти різних типів. У цій лабораторній роботі він використовується для зберігання об'єктів типу `Task`.



Контейнер підтримує такі операції:



\- додавання елемента;

\- отримання елемента за індексом;

\- видалення елемента за індексом;

\- визначення кількості елементів;

\- перевірку на порожнечу;

\- отримання поточної місткості контейнера;

\- послідовний обхід елементів.



\## Внутрішнє подання контейнера



Внутрішнє сховище контейнера реалізовано за допомогою масиву:



```java

private T\[] data;

```



Для зберігання кількості фактично доданих елементів використовується поле:



```java

private int size;

```



Стандартні колекції Java, такі як `ArrayList`, не використовуються як основне сховище контейнера. Це означає, що контейнер є самописним і самостійно керує зберіганням елементів.



\## Ітератор



Для контейнера реалізовано власний внутрішній клас-ітератор:



```java

private class TaskIterator implements Iterator<T>

```



Ітератор підтримує два основні методи:



\- `hasNext()` — перевіряє, чи існує наступний елемент;

\- `next()` — повертає наступний елемент контейнера.



Контейнер реалізує інтерфейс:



```java

Iterable<T>

```



Завдяки цьому об'єкти контейнера можна обходити за допомогою циклу `for-each`.



\## Додаткове завдання



Як додаткове завдання реалізовано автоматичне розширення внутрішнього масиву контейнера.



Якщо під час додавання нового елемента масив уже заповнений, викликається метод `grow()`. Він створює новий масив у два рази більшого розміру та копіює до нього всі наявні елементи.



Приклад створення контейнера з початковою місткістю `2`:



```java

TaskContainer<Task> taskList = new TaskContainer<>(2);

```



Після додавання трьох задач місткість контейнера автоматично збільшується з `2` до `4`.



Це видно в результаті роботи програми:



```text

Size: 3

Capacity: 4

```



\## Демонстраційна програма



У класі `Main` продемонстровано:



\- створення розробників;

\- створення задач;

\- додавання задач до контейнера;

\- доступ до задач за індексом;

\- зміну статусів задач;

\- обхід контейнера через `Iterator`;

\- обхід контейнера через `for-each`;

\- перегляд історії статусів;

\- видалення задачі з контейнера.



\## UML-діаграма



Для роботи підготовлено UML-діаграму класів у форматі PlantUML.



На діаграмі показано:



\- класи предметної області `Task`, `Developer`, `StatusRecord`;

\- перелік `TaskStatus`;

\- власний generic-контейнер `TaskContainer<T>`;

\- внутрішній ітератор `TaskIterator`;

\- інтерфейси `Iterable` і `Iterator`;

\- демонстраційний клас `Main`.



Підпис до діаграми:

\## Юніт-тести



Для перевірки роботи програми написано JUnit-тести.



Тести перевіряють:



\- створення порожнього контейнера;

\- створення контейнера з власною місткістю;

\- додавання елементів;

\- отримання елементів за індексом;

\- автоматичне розширення контейнера;

\- видалення елемента;

\- винятки при неправильному індексі;

\- роботу власного ітератора;

\- роботу обходу через `for-each`;

\- створення задачі;

\- зміну статусу задачі;

\- створення запису історії статусу.



\## Запуск програми



Для компіляції проєкту:



```bash

mvn clean compile

```



Для запуску програми:



```bash

mvn exec:java

```



Для запуску тестів:



```bash

mvn clean test

```



\## Приклад результату роботи програми



```text

Adding tasks to container...

Size: 3

Capacity: 4



Task by index:

Task #1: Authorization | developer: Zakhar Isaiev (Backend developer) | status: NEW

Task #2: Main page | developer: Oleg Vinnik (Frontend developer) | status: NEW

Task #3: Testing | developer: Valentin Strikalo (Tester) | status: NEW



Traversal with Iterator:

Task #1: Authorization | developer: Zakhar Isaiev (Backend developer) | status: IN\_PROGRESS

Task #2: Main page | developer: Oleg Vinnik (Frontend developer) | status: CHECKING

Task #3: Testing | developer: Valentin Strikalo (Tester) | status: DONE



Traversal with for-each:

Task #1: Authorization | developer: Zakhar Isaiev (Backend developer) | status: IN\_PROGRESS

Task #2: Main page | developer: Oleg Vinnik (Frontend developer) | status: CHECKING

Task #3: Testing | developer: Valentin Strikalo (Tester) | status: DONE



Status history for first task:

status = NEW, comment = Task was created, changed by = Zakhar Isaiev, date = 2026-05-18T17:51:42.644575400

status = IN\_PROGRESS, comment = Developer started this task, changed by = Zakhar Isaiev, date = 2026-05-18T17:51:42.651124200



Removing second task...

Removed task: Task #2: Main page | developer: Oleg Vinnik (Frontend developer) | status: CHECKING



Tasks after removing:

Task #1: Authorization | developer: Zakhar Isaiev (Backend developer) | status: IN\_PROGRESS

Task #3: Testing | developer: Valentin Strikalo (Tester) | status: DONE```



\## Висновок



У лабораторній роботі було реалізовано власний узагальнений контейнер для зберігання задач команди.



Було створено класи предметної області `Task`, `Developer`, `StatusRecord` та перелік `TaskStatus`.



Контейнер `TaskContainer<T>` реалізовано на основі звичайного масиву без використання `ArrayList` як внутрішнього сховища.



Також було реалізовано власний ітератор і забезпечено підтримку обходу через цикл `for-each`.



Як додаткове завдання було реалізовано автоматичне розширення внутрішнього масиву контейнера.



Робота допомогла краще зрозуміти принципи роботи generic-контейнерів, ітераторів та інтерфейсу `Iterable<T>` у Java.

