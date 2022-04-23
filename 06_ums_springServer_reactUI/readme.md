                    Web application User Management System
    Server:
<a href="https://github.com/Ruslan5/javaR2EE/tree/master/06_ums_springServer_reactUI/restSpringServer">RestSpringServer</a>

Сервер выполнен на java с использованием фреймворка Spring MVC.
Rest Controller предоставляет API для манипуляций с базой данных.
Приложение использует базу данных H2 database.
На API доступны endpoints на:
1) Add - добавление нового пользователя.
2) Edit - редактирование сущиствующиго пользователя.
3) Delete - удаление существующего пользователя.
Spring security выполняет разграничение достпа по ролям.
Вход в приложение осуществляется по логину и паролю c генерацией jwt token`a.
   Client:

   <a href="https://github.com/Ruslan5/javaR2EE/tree/master/06_ums_springServer_reactUI/reactclientui">ReactClientUI</a>

Клиентская часть UI выполнена при помощи фреймфорка React.
Используемая версия node -v v16.4.2.

![](https://github.com/Ruslan5/javaR2EE/tree/master/06_ums_springServer_reactUI/reactdemo.gif)
