MVC - приложение с использованием SpringBoot, gradle и Hibernate
=======================
В качестве СУБД используется [MySQL](https://dev.mysql.com/)
Для запуска MySQL использовать:

    C:\> "C:\Program Files\MySQL\MySQL Server 8.0\bin\mysqld"
    
Для остановки: 

    C:\> "C:\Program Files\MySQL\MySQL Server 8.0\bin\mysqladmin" -u root -p shutdown
    
Инструмент сборки - [Gradle](https://gradle.org/install/)

Если возникает проблема с подтягиванием зависимостей, необходимо выполнить (Run -> edit configurations -> + -> Gradle -> Tasks: clean build)
    
    clean build
    
Почистить кеш gradle:

    rm -rf $HOME/.gradle/caches/
    
Или: 

    ./gradlew build --refresh-dependencies
    
