---
title: Day Docker
layout: single
author_profile: true
read_time: true
comments: true
share: true
related: true

toc: true
toc_sticky: false
toc_label: 목차
---



 {% raw %}

도커 다운로드  후 실행 + 회원가입 후 로그인





실행하다보면 에러가 남



https://blog.nachal.com/1691

![image](https://user-images.githubusercontent.com/65274952/136650429-d6464132-d367-4544-aec0-034f31fc7c63.png)



https://emflant.tistory.com/235

https://basketdeveloper.tistory.com/85



1.  docker pull redis:alpine

2. docker images
3. yml 작성

```yml
# redis-server.yml
version: '3.7'
services:
    redis:
      image: redis:alpine
      command: redis-server --port 6379
      container_name: redis_boot
      hostname: redis_boot
      labels:
        - "name=redis"
        - "mode=standalone"
      ports:
        - 6379:6379
```

compose.yml 로 저장

-d 면 백그라운드에서 실행되는것

4. docker-compose -f ./docker-compose.yml up -d



![image](https://user-images.githubusercontent.com/65274952/136651602-bacce317-1514-48e4-84f9-8ac2ef6c553a.png)







![image](https://user-images.githubusercontent.com/65274952/136651810-0e21006d-d79d-493d-b477-9e1b4f266c46.png)

> docker container ls 로 리스트를 본다.

5. docker exec -it redis_boot redis-cli

로 실행시키기



+종료법 : docker-compose -f ./redis_server.yml down 





https://urame.tistory.com/entry/Redis-%EC%84%A4%EC%B9%98-%EB%B0%8F-%EC%82%AC%EC%9A%A9-%EB%B2%95-%EC%98%88%EC%A0%9C









 {% endraw %}

