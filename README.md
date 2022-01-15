# kafka-demo

Простое микросервисное демо-приложение с использованием Spring Boot и Kafka. 

Многомодульный проект:
* producerservice - данные, пришедшие через http-запрос, посылает в очередь
* consumerservice - принимает сообщения из очереди
* commonmodel - содержит общую модель данных для обмена сообщениями

Для работы приложения должен быть установлен и запущен сервер Kafka.
Обращение к Kafka происходит по адресу localhost:9092.

В Kafka сообщения передаются через REST-сервис **producerservice**:

http://localhost:8080//messages?message=Hello&number=1

Из Kafka сообщения принимает сервис **consumerservice** и выводит их в консоль.
