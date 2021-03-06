                    User management system (Servlets and jsp). 
                    Веб приложение для управления пользователями с использованием сервлетов и страниц jsp.

Есть две роли user и admin.
UI создана с использованием JSP и JSTL. Есть страница логина:
![](https://github.com/Ruslan5/javaR2EE/blob/master/02_servlets_jsp/src/main/resources/screenshot/01login.jpg)
страница для роли user:

![](https://github.com/Ruslan5/javaR2EE/blob/master/02_servlets_jsp/src/main/resources/screenshot/userp.jpg)

и страница для роли admin. На которой выводится:
список всех пользователей в БД, а также доступны следующие функции:
- добавить нового пользователя;
- редактировать существующего пользователя;
- удалить пользователя.

![](https://github.com/Ruslan5/javaR2EE/blob/master/02_servlets_jsp/src/main/resources/screenshot/02list.jpg)

    Все поля имеют валидацию ввода данных.
Манипуляции с БД выполняются сервлетами.
Интерыейс UI выполнен при помощи фреймворка Bootstrap.

    Приложение развертывается при помощи сервера tomcat на порту 8080.
В системе предварительно создатся два юзера с ролью user и admin.
Проект содержит dockerfile и docker-compose для сборки и запуска.
для входа в логинку используйте
    для юзера с ролью admin:
login: login1
password: pass1
    для юзера с ролью user:
    login: login2
    password: pass2

Для сборки запуска проекта в докере нужно установить докер на ПК,
проверка установлен ли докер можно командой docker --version.
Затем в командной строке в корневой папке выполнить команду: docker-compose up.
После сборки приложение будет лоступно по адресу http://localhost:8080
