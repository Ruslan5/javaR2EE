                    User Management System Web Services  (Rest API)
Server:
<a href="https://github.com/Ruslan5/javaR2EE/tree/master/05_ums_webservice/restapi_spring_server">RESTful_API_Spring_Server</a>

Серверная часть приложения выполнена на языке java при полощи фреймворка Spring MVC.
Реализован отдельный веб-сервис Restful, который выполняет управление пользователями,
которые хранятся в базе данных.
Используемая база данных H2 database.
REST API использует формат JSON.

Client:
<a href="https://github.com/Ruslan5/javaR2EE/tree/master/05_ums_webservice/restapi_test_client">RESTful_API_Test_Client</a>

Клиент выполнен в виде отдельного приложенияЁ которое тестирует веб-сервис, 
а именно (пять) следующих тестов:
1) testGetAllUsers - поиск всех существующих пользователей.
2) testCreateUsers - созданме нового пользователя.
3) testUpdateUsers - редактирование существующего пользователя.
4) testFindUserByLogin - поиск пользователя по логину.
5) testDeleteUsers - удаление существующего пользователя.

Приложение также доступно для сборки в докер контейнере.
для сборки и запуска приложения необходимо открыть терминал в директории расположения
docker-compose.yml файла и выполнить соманду docker-compose up. 
Результатом работы веб-сервиса будет успешное прохождение тестов в терминале.

![](https://github.com/Ruslan5/javaR2EE/tree/master/05_ums_webservice/restapiwebservicestest.png)