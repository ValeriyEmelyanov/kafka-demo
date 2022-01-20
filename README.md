# kafka-demo

Простое микросервисное демо-приложение с использованием Spring Boot и Kafka. 

Многомодульный проект:
* **producerservice** данные, пришедшие через http-запрос, посылает в очередь
* **consumerservice** принимает сообщения из очереди
* **commonmodel** содержит общую модель данных для обмена сообщениями

Для работы приложения должен быть установлен и запущен сервер Kafka.
Обращение к Kafka происходит по адресу localhost:9092.

В Kafka сообщения передаются через REST-сервис **producerservice**:

http://localhost:8080//messages?message=Hello&number=1

Из Kafka сообщения принимает сервис **consumerservice** и выводит их в консоль.


### Запуск Kafka в Docker с использованием docker-сети и выполнение операций

Cоздать отдельную сеть *kafkanet*

```docker network create kafkanet```

Вывести список сетей docker

```docker network ls```

Запустить контейнер с ZooKeeper

```docker run -d --network=kafkanet --name=zookeeper -e ZOOKEEPER_CLIENT_PORT=2181 -e ZOOKEEPER_TICK_TIME=2000 -p 2181:2181 confluentinc/cp-zookeeper```

Запустить контейнера с Kafka

```docker run -d --network=kafkanet --name=kafka -e KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181 -e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092 -e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 -p 9092:9092 confluentinc/cp-kafka```

Можно посмотреть лог Kafka

```docker logs kafka```

Подключиться к контейнеру *kafka*

```docker exec -it kafka bash```

Создать топик *demo-topic*

```kafka-topics --create --topic demo-topic --bootstrap-server kafka:9092```

Вывеcти список всех топиков

```kafka-topics --list --bootstrap-server kafka:9092```

Вывести описание топика

```kafka-topics --describe --topic demo-topic --bootstrap-server kafka:9092```

Подключиться к консоли продьюсера

```kafka-console-producer --topic demo-topic --bootstrap-server kafka:9092```

После этого можно водить сообщения. Ctrl+С - завершить работу с консолью.

Подключиться к консоли консьюмера

```kafka-console-consumer --topic demo-topic --from-beginning --bootstrap-server kafka:9092```

В течении некоторого времени, сообщения, находящиеся в топике, 
будут выведены в консоль. Ctrl+С - завершить работу с консолью.

Выйти из контейнера *kafka*

```exit```

Чтобы остановить контейнеры, необходимо выполнить команды

```docker stop zookeeper```

```docker stop kafka```

Повторный запуск осуществляется с помощью команды *start* (подставьте вместо *stop*).

Удалить контейнеры можно следующим образом

```docker rm zookeeper```

```docker rm kafka```

Сеть *kafkanet* можно удалить с помощью команды

```docker network rm kafkanet```

### Использование docker-compose

Предварительно необходимо контейнеры с именами *zookeeper* и *kafka* остановить и удалить, 
если такие имеются.

В папке проекта *./_files* лежит файл *docker-compose.yml*.

Необходимо открыть командную консоль, 
перейти в папку, где лежит файл *docker-compose.yml*,
выполнить команду

```docker-compose up```

по завершению команды будут запущены два контейнера *zookeeper* и *kakfa*,
а в текущее окно будут выводится логи контейнера *kafka*.