# safe-chat
Um chat web que implementa criptografia assimétrica

### Pré-requisitos
- Tenha o Docker instalado em sua máquina

### Comandos
 1. Para executar a aplicação
`sh run.sh`

 2. Para finalizar a aplicação:
`sh kill.sh`

### Acessando a aplicação pelo navegador

https://localhost:8081/safechat

#### Enquanto a aplicação estiver em execução, você poderá executar os seguintes comandos:

- Para acessar o bash do container do mongo:
	`sudo docker exec -it mongo /bin/bash`

- Para acessar o bash do container do postgres:
	`sudo docker exec -it postgres /bin/bash`
