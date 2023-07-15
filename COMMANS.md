
Para liberar acesso do root via Workbench
```sql
ALTER USER 'root'@'%' IDENTIFIED WITH mysql_native_password BY 'root123';
```
Para rodar p jar de PROD
```console
java -jar -Dspring.profiles.active=prod -DSPRING_DATASOURCE_URL=jdbc:mysql://localhost/vollmed_api -DSPRING_DATASOURCE_USERNAME=root -DSPRING_DATASOURCE_PASSWORD=root123 target/api-0.0.1-SNAPSHOT.jar
java -jar -Dspring.profiles.active=prod -Dspring.datasource.url=jdbc:mysql://localhost/vollmed_api -Dspring.datasource.username=root -Dspring.datasource.password=root123 target/api-0.0.1-SNAPSHOT.jar
```

Comando para gerar a imagem do docker para o backend

```console
docker build -t alura/voll_med.api .
```
