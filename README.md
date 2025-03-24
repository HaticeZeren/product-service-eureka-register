Bu proje, mikroservis mimarisinde çalışan bir servisi ve Eureka Server'a register etme örneğidir.


NOT: Eureka server projesi Docker'a deploy edilmiş varsayılmaktadır.

# Gereksinimler:
- Docker
- Docker Compose
- IntelliJ

# Projenin Çalıştırılması

## Docker Image'larının Oluşturulması

  Proje mysql database'ini kullandığından docker hub'dan mysql image'in son sürümünü indirebiliriz.
  Projeyi lokalimize çekelim maven ile clean-install yapalım ve intelliJ'e ait terminalde aşağıdaki komutu yazarak product-service için image oluşturalım.

`    docker build -t product-service-eureka-register:0.0.1 .
`

##  Docker Compose File dosyası ile deploy etme

-  mysql.yml dosyası aşağıdaki gibidir.

```
version: '3.3'

services:
  mysql:
    container_name: mysql
    hostname: mysql
    image: mysql:latest
    volumes:
      - /Users/haticezeren/data/mysql:/var/lib/mysql
    networks:
      - springboot-mysql-network
    expose:
      - 3306
    ports:
      - 3306:3306
    deploy:
      replicas: 1
      restart_policy:
        condition: on-failure
        delay: 20s
        max_attempts: 3
        window: 120s
      resources:
        reservations:
          cpus: "0.50"
          memory: 512M
        limits:
          cpus: "1.0"
          memory: 1G
    environment:
      MYSQL_ROOT_PASSWORD: root
      MYSQL_DATABASE: product_example
      MYSQL_USER: root
      MYSQL_PASSWORD: root
networks:
  springboot-mysql-network:
    name: springboot-mysql-network
```

Docker deploy komutu aşağıdaki gibidir:

> docker-compose -p eureka-example  -f mysql.yml up -d


-  product-service.yml dosyası ise aşağıdaki gibidir.

```
version: '3.3'

services:
  product-service:
    container_name: product-service
    hostname: product-service
    image: product-service-eureka-register:0.0.1
    networks:
      - springboot-mysql-network
    expose:
      - 8090
    ports:
      - 8090:8090
    deploy:
      replicas: 1
      restart_policy:
        condition: on-failure
        delay: 5s
        max_attempts: 3
        window: 120s
      resources:
        reservations:
          cpus: "0.50"
          memory: 512M
        limits:
          cpus: "1.0"
          memory: 1G
    environment:
      - "SPRING_PROFILES_ACTIVE=stage"
    entrypoint: [ "java","-jar","app.jar" ]
networks:
  springboot-mysql-network:
    name: springboot-mysql-network
```

Docker deploy komutu aşağıdaki gibidir:

> docker-compose -p eureka-example  -f product-service.yml up -d



