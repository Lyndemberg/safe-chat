sudo docker build -t safe-chat/postgres ./postgres
sudo docker build -t safe-chat/app .
sudo docker run -d --name postgres safe-chat/postgres
sudo docker run -d --name mongo mongo
sudo docker run -p 8081:8181 -d --name app --link postgres:host-postgres --link mongo:host-mongo safe-chat/app
