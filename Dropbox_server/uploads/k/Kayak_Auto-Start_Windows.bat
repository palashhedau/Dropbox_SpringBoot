rd /s /q "C:\kafka_2.11-0.11.0.1\Data\kafka-logs"

rd /s /q "C:\kafka_2.11-0.11.0.1\Data\Zookeeper"

rd /s /q "C:\kafka_2.11-0.11.0.1\Data\Zookeeper\version-2"

start cmd.exe /K "cd C:\data\Projects\273_Kayak\kayak\frontend && npm run start"

timeout /t 10

start cmd.exe /K "cd C:\kafka_2.11-0.11.0.1\bin\windows && zookeeper-server-start.bat ../../config/zookeeper.properties"

timeout /t 10

start cmd.exe /K "cd C:\kafka_2.11-0.11.0.1\bin\windows && kafka-server-start.bat ../../config/server.properties"

timeout /t 20

start cmd.exe /K "cd C:\kafka_2.11-0.11.0.1\bin\windows && kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic kayak"

timeout /t 10

start cmd.exe /K "cd C:\kafka_2.11-0.11.0.1\bin\windows && kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic response_topic"

timeout /t 10

start cmd.exe /K "cd C:\data\Projects\273_Kayak\kayak\backend && nodemon app.js"

start cmd.exe /K "cd C:\data\Projects\273_Kayak\kayak\kafkaend && nodemon server.js"

start cmd.exe /K "cd C:\mongodb\bin && mongo"


