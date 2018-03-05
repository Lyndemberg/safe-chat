sudo docker stop app
sudo docker stop postgres
sudo docker stop mongo
sudo docker rm app
sudo docker rm postgres
sudo docker rm mongo
sudo docker rmi -f safe-chat/app
sudo docker rmi -f safe-chat/postgres
