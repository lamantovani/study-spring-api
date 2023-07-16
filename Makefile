
data-base-test-start:
	@ docker start mysql_teste

package: data-base-test-start
	@ mvn clean package

data-base-test-stop: package
	@ docker stop mysql_teste

docker-image-build: data-base-test-stop
	@ docker build -t alura/vollmed.api .

run: docker-image-build
	@ docker-compose up -d

stop:
	@ docker-compose down -v