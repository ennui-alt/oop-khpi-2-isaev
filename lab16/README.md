\# Лабораторна робота №16



\## Тема



Автоматизована збірка, тестування і CI для Java-проєкту.



\## Варіант



\*\*N = 3 — середнє значення.\*\*



У роботі реалізовано обчислення середнього арифметичного значення елементів масиву цілих чисел.



Операція виконана у двох варіантах:



\- звичайна Java-реалізація;

\- native-реалізація мовою C++ через JNI.



\## Використані технології



\- Java / JDK;

\- Maven;

\- JUnit 5;

\- JNI;

\- C++;

\- clang++;

\- GitHub Actions.



\## Структура проєкту



```text

src/main/java/ua.khpi.oop.lab16/demo/Main.java

src/main/java/ua.khpi.oop.lab16/service/AverageCalculator.java

src/main/java/ua.khpi.oop.lab16/service/CalculationService.java

src/main/java/ua.khpi.oop.lab16/service/NativeAverageCalculator.java

src/test/java/ua.khpi.oop.lab16/demo/MainTest.java

src/test/java/ua.khpi.oop.lab16/service/AverageCalculatorTest.java

src/test/java/ua.khpi.oop.lab16/service/CalculationServiceTest.java

src/test/java/ua.khpi.oop.lab16/service/NativeAverageCalculatorTest.java

native/nativeaverage.cpp

native-headers/ua\_khpi\_oop\_lab16\_service\_NativeAverageCalculator.h

.github/workflows/ci.yml

pom.xml

```



\## Основні класи



\- `AverageCalculator` — Java-реалізація обчислення середнього значення.

\- `NativeAverageCalculator` — клас із native-методом.

\- `CalculationService` — сервіс для порівняння Java та native-реалізації.

\- `Main` — демонстраційний запуск програми.



\## Генерація JNI header-файла



```powershell

javac -encoding UTF-8 -h native-headers -d target/classes src/main/java/ua/khpi/oop/lab16/service/NativeAverageCalculator.java

```



\## Збірка native-бібліотеки на Windows



```powershell

clang++ -std=c++17 -shared `

\-I"$env:JAVA\_HOME\\include" `

\-I"$env:JAVA\_HOME\\include\\win32" `

\-Inative-headers `

native\\nativeaverage.cpp `

\-o build\\native\\nativeaverage.dll

```



\## Запуск програми



```powershell

java -Djava.library.path=build/native -cp target/classes ua.khpi.oop.lab16.demo.Main

```



Або через IntelliJ IDEA потрібно додати у VM options:



```text

\-Djava.library.path=D:\\IntelRaboti2\\oop-khpi-2-isaev\\lab16\\build\\native --enable-native-access=ALL-UNNAMED

```



\## Запуск тестів



```bash

mvn test

```



Для тестів із JNI також потрібно вказати шлях до native-бібліотеки:



```text

\-Djava.library.path=D:\\IntelRaboti2\\oop-khpi-2-isaev\\lab16\\build\\native --enable-native-access=ALL-UNNAMED

```



\## GitHub Actions



У проєкті створено workflow:



```text

.github/workflows/ci.yml

```



Workflow виконує такі дії:



\- завантажує код репозиторію;

\- встановлює JDK;

\- генерує JNI header-файл;

\- збирає native-бібліотеку;

\- компілює Java-проєкт;

\- запускає JUnit-тести.



\## Результат роботи



Для масиву:



```text

\[10, 20, 30, 40, 50]

```



програма повертає:



```text

Результат Java-реалізації: 30.0

Результат native-реалізації: 30.0

Результати збігаються: true

```



Це підтверджує, що Java та native-реалізації працюють однаково.

