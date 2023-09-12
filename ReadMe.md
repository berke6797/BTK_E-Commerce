# PostgreSQL Docker bağlantı
docker run --name btk-postgres -e POSTGRES_PASSWORD=root -p 6060:5432  -d postgres

# MongoDB Docker bağlantı
docker run --name dockermongo -p 27017:27017 -e MONGO_INITDB_ROOT_USERNAME=admin -e MONGO_INITDB_ROOT_PASSWORD=root -d mongo
db.createUser({user: "Java", pwd: "root", roles: ["readWrite","dbAdmin"]})
Db adı: e-commerce

# RabbitMQ Docker bağlantı
docker run -d --name my-rabbitmq -e RABBITMQ_DEFAULT_USER=java -e RABBITMQ_DEFAULT_PASS=root -p 5672:5672 -p 15672:15672 rabbitmq:3-management

