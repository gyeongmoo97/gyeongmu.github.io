---
title: Spring Boot 설정
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

# 스프링 프레임워크 -> 스프링 부트

![image](https://user-images.githubusercontent.com/65274952/137261055-127b1403-5017-47b5-b7e5-8b2379df3c15.png)
![image](https://user-images.githubusercontent.com/65274952/137261067-40077904-4089-4c49-8769-d5335b382b6b.png)

name 프로젝트 이름,

location 빌드위치

type 라이브러리 관리 툴 - maven

packaging 빌드 이후 최종 산출물 형태

.war 파일로 나오게 됨 빌드를 하게되면 target에 생성된다.

![image](https://user-images.githubusercontent.com/65274952/137261347-a5785d57-f9c8-48ff-9a5d-796d2540c645.png)

빌드 방법은 run as maven install 



스프링 부트의 경우는 jar war 2개의 산출물 나옴

스프링 부트 프로그램 jar

별도의 WAS 필요없이 java로 실행할 수 있음

내부에 was 가 포함되어있어서 웹서버기능을 내부에 함축하고 있다.

jar를 사용하면 jsp 파일은 사용할 수 없다.

JSP를 사용하려면 war 로 만들어야 한다.



## jar vs war

jar는 클래스만 있으면된다.

war는 플래스와 webapplication의 구조를 포함해야한다?



![image](https://user-images.githubusercontent.com/65274952/137261776-28e0fb42-9823-46da-9320-01c88076dc9d.png)





Jar는 classes 파일만 사용한다.



jsp는 classes 에 포함되지 않는다.

그래서 JAR 사용하면 jsp 사용못한다 (억지로 사용 하려면 사용 가능)



![image](https://user-images.githubusercontent.com/65274952/137262502-1d72ecc3-c28e-4571-ba4e-87ded88d0a1c.png)

이거 2가지로 패키지명이 정해진다.



![image](https://user-images.githubusercontent.com/65274952/137262868-5e5c25a2-5ad1-4ee4-a680-54650d69249b.png)
의 의미
![image](https://user-images.githubusercontent.com/65274952/137262898-a3641ca4-e4e1-4efd-9fb3-632763e639ba.png)



![image](https://user-images.githubusercontent.com/65274952/137262945-b16ee1fe-2563-456a-bb13-9436b5e1abe6.png)

5.5.1 이상이 나왔다고 더 위의 버전을 자동으로 받지는 않는다.

하지만 SNAPSHOT 이라고 뒤에 붙어있는 버전은 버전이 올라갈 때 자동으로 maven 이 업데이터 버전을 받아서 사용함



스넵샷으로 버전을 지정해두면 메이븐 레포지토리에 저장해두고 업데이트 될 때 마다 자동으로 변경해준다.

![image](https://user-images.githubusercontent.com/65274952/137263297-9763522c-6828-4bc8-a034-c2462040682e.png)



## 의존성



![image](https://user-images.githubusercontent.com/65274952/137263485-0595396a-c83e-4d03-87ce-32c6e07ea0da.png)

소스 수정 후 자동으로 리로드



![image](https://user-images.githubusercontent.com/65274952/137263670-43450265-0e40-4128-8681-e7f9ed93eeb0.png)
스프링 프리임워크의 mvc 모듈 역할
![image](https://user-images.githubusercontent.com/65274952/137263749-c794380a-2590-45e7-9fd7-4fdc7a4cf467.png)
JSP 역할을 해줌

추후에 의존성 설정 추가로 할 예정



![image](https://user-images.githubusercontent.com/65274952/137263887-5a744d51-ea73-4ec4-ad00-77d2ba9d76c3.png)

의존성을 가져오는 위치가 표시된다. (finish 누르면 프로젝트 구성 완료됨)



## perspective 

![image](https://user-images.githubusercontent.com/65274952/137263987-fd9b204f-b2a5-4413-8435-92dc40dfee81.png)

JAVA 누름

최종산출물이 classes 파일이라 자바만 있어도 된다.

Boot 는 내장 웹서버가 있어서 



![image](https://user-images.githubusercontent.com/65274952/137264222-cd1d5859-1fd7-4e8c-852d-7db6f98ee040.png)

![image](https://user-images.githubusercontent.com/65274952/137264272-68252cc3-2bed-476e-b067-f2d948b06c2c.png)

부트의 서버 역할을 boot Dashborad 에서 할 수 있다.



![image](https://user-images.githubusercontent.com/65274952/137264359-2a31c1dd-32fc-4700-bc2d-d98a4bf35f39.png)



브라우저 버튼 누르면

![image](https://user-images.githubusercontent.com/65274952/137264387-18fad19b-50b1-4260-847b-d7917592629f.png)

이렇게 표시된다.



## 프로젝트 구조

![image](https://user-images.githubusercontent.com/65274952/137264532-ab217902-e682-427a-8f8b-fc327bd70b61.png)

src main java , src main resources 는 나중에 classes 로 결합된다 ( 산출물 안에서는 같은 폴더에 들어감 )



프레임워크 보다 간결해진 파일구조를 가진다.

이전에는 CSS JS IMG -> resource 에 넣었었다.

boot 는 static 에 위와같은 파일을 포함시킨다. 



![image](https://user-images.githubusercontent.com/65274952/137264873-f2453191-e3de-4443-888e-c6d652d89df6.png)
![image](https://user-images.githubusercontent.com/65274952/137264888-938e648d-c269-49f6-9fd4-6206116f2795.png)

views 에 해당하는 내용이 templates 





## return 값

![image](https://user-images.githubusercontent.com/65274952/137265328-6e135c9b-dd77-4e2f-b991-a9b47bf5175f.png)
![image](https://user-images.githubusercontent.com/65274952/137265340-c723a8ae-d967-47f2-8243-fa7cbceebcb8.png)

home 은 templates 에서 찾게된다.



타임리프 템플릿으로 작성하면 접미사 .html 을 붙혀 줘야한다.



# 스프링 프레임워크의 설정은 어디로??

![image](https://user-images.githubusercontent.com/65274952/137265936-b7a04bcb-578a-48d0-b8d1-4644e001f307.png)

많은 XML 파일은 boot 내장설정이 가지고 있다.

추가로 설정해야할 내용이 있다면 application.properties에서 설정한다.



![image](https://user-images.githubusercontent.com/65274952/137266075-7485926e-b22b-443e-b731-527be27297a5.png)

![image](https://user-images.githubusercontent.com/65274952/137266056-83b4b350-a6d2-4018-95a7-a33a74340b22.png)

많은 설정 내용이 application.properties 에  저장된다.



```properties
#port
server.port=80
```

포트를 변경시킨다.





# 로깅

[https://docs.spring.io/spring-boot/docs/2.5.5/reference/html/features.html#features.logging](https://docs.spring.io/spring-boot/docs/2.5.5/reference/html/features.html)



![image](https://user-images.githubusercontent.com/65274952/137269157-972ff0b4-003f-4af0-bc56-18e30b2e5359.png)

시간 로그 level 포트번호  클래스이름 등을 기록한다.



기본 log를 logback으로 사용함



![image](https://user-images.githubusercontent.com/65274952/137269911-003f3ff9-75ae-4d5a-925c-b77fd822ba09.png)

기본 설정을 변경할 때 application properties 에 설정하면된다.

application properties 에서 설정 불가능한 부분은 따로 xml 파일을 가져오던지 추가로 파일을 가져와야한다.



# properties 에디터 변경

![image](https://user-images.githubusercontent.com/65274952/137270299-248fe75b-8dee-4926-a1db-a77638905f9d.png)
![image](https://user-images.githubusercontent.com/65274952/137270364-56893abf-eadd-4122-91b2-e272e79217c1.png)

이제 자동완성 사용가능



## 로그level 의 순서

Fatal

Error

Warn

Info

Debug



만약 info level 로 설정하면 info 아래의 debug 는 출력안됨

info 이상인 warn 부터 fatal 은 출력됨



## 로그 level 의 순서 지정



```pro
logging.level.com.team3.shopping=info
```

com.team3.shopping

패키지에서는

info level 로 로깅한다는 의미

```java
private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
@RequestMapping("/")
public String home() {
    logger.error("error");
    logger.warn("warn");
    logger.info("info");
    logger.debug("debug");

    return "home.html";
}
```



```http
2021-10-14 16:28:06.436[0;39m [32m INFO[0;39m [35m50324[0;39m [2m---[0;39m [2m[p-nio-80-exec-1][0;39m [36mo.s.web.servlet.DispatcherServlet       [0;39m [2m:[0;39m Completed initialization in 6 ms
[2m2021-10-14 16:28:06.439[0;39m [31mERROR[0;39m [35m50324[0;39m [2m---[0;39m [2m[p-nio-80-exec-1][0;39m [36mc.t.shopping.controller.HomeController  [0;39m [2m:[0;39m error
[2m2021-10-14 16:28:06.440[0;39m [33m WARN[0;39m [35m50324[0;39m [2m---[0;39m [2m[p-nio-80-exec-1][0;39m [36mc.t.shopping.controller.HomeController  [0;39m [2m:[0;39m warn
[2m2021-10-14 16:28:06.440[0;39m [32m INFO[0;39m [35m50324[0;39m [2m---[0;39m [2m[p-nio-80-exec-1][0;39m [36mc.t.shopping.controller.HomeController  [0;39m [2m:[0;39m info
```



## lombok 의 log



@log

@log4j2

를 사용하면 

private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

안써도된다.

![image](https://user-images.githubusercontent.com/65274952/137271933-ec84a6a2-68f1-46f3-b9ac-a33e9e27dbd6.png)

![image](https://user-images.githubusercontent.com/65274952/137272191-8abb1c78-3a3a-4089-9ccd-2372b114c677.png)

![image](https://user-images.githubusercontent.com/65274952/137272216-78773ec5-35dd-4912-b022-4a2fc9a67413.png)

## log 출력 패턴 변경



%clr(%-5level) %clr(%-70(%logger{10}:%clr(%method){blue})){magenta} %clr(:) %m%n

%clr 색 설정

% -5뒷부분에 공백이 5개 들어간다

![image](https://user-images.githubusercontent.com/65274952/137273310-2defa91f-807a-46a2-acb0-c54a46afc15f.png)

왼쪽정렬 하겠다는 의미



level :  level 출력



![image](https://user-images.githubusercontent.com/65274952/137273189-336e446f-8113-43d8-a7da-55e99320c598.png)



```http
INFO [0;39m [35mc.t.s.c.HomeController:[34mhome[0;39m                               [0;39m [32m:[0;39m 실행1
```



: 는  " : "를 출력하려고 넣음

%clr(:) 는 : 를 기본색으로 나오게 하겠다 (기본색은 초록)

%m%n 메시지 넣고 개행



추가로 properties 로 설정 못하는 상세 설정을 하고싶다면

xml 파일을 만들어서 설정해야한다.

![image](https://user-images.githubusercontent.com/65274952/137273901-4120656d-66ed-4569-a499-7e889af2dab5.png)



# **Thymeleaf**

lHTML, XML, JavaScript, CSS 및 일반 텍스트를 처리 할 수 있는 템플릿 엔진

lHTML을 파싱해서 데이터 바인딩하여 웹 페이지 생성



jsp 같은 템플릿엔진이다.



타임리프작동



.html 파일이 있으면

타임리프가 먼저 해석을 진행한다 (was 가 아니라)



표현식이 나오면 data를 바인딩하여 값을 집어넣음

완성된 내용을 was 에 제공하여 응답함



.jsp 와 비교

.jsp -> .java 로 변환 -> .class 로 변환 -> 객체 -> 메서드 실행 -> 응답(html)



타임리프

.html -> tymeleaf 해석 --> 응답

자바코드를 거치지 않고 타임리프가 해석한 내용으로 가공하여 응답으로 제공됨

![image](https://user-images.githubusercontent.com/65274952/137275613-5b3313f8-902f-4526-9599-b8c55e593a56.png)



static 에 html 만들면 정적인 파일이다.

template 에 만들면 tymeleaf 에서 해석하게된다.

![image](https://user-images.githubusercontent.com/65274952/137275067-c6013637-3191-403a-909e-c012a0737454.png)



## 예제

```html
<h1 th:text="${'Hello Spring Boot'}">aaaaaaa</h1>
```

이렇게 작성하면 aaa 는 출력되지 않음





![image](https://user-images.githubusercontent.com/65274952/137275685-3ada042e-3759-4bb7-a121-3c69fe8072e9.png)

이런 부분이 없다. -> 디자이너가 더 알아보기 쉬운 문서가 생성된다.

디자이너가 was 없이도 실행가능



![image](https://user-images.githubusercontent.com/65274952/137275878-c9f06e02-d237-44c5-8423-71574f7db119.png)

이렇게 열어도 열리지만

th:text 안의 내용은 해석되지 않음

그래도 열리긴함

jsp 는 불가능한 부분이다.



## 플러그인 설치

![image](https://user-images.githubusercontent.com/65274952/137276093-8cc3f0b2-7dca-4e84-8d3f-497dcf1ed1c6.png)

![image](https://user-images.githubusercontent.com/65274952/137276274-c291d0d3-f1de-4c7b-8b09-1f791b9d9fcb.png)





![image-20211014170538105](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20211014170538105.png)

네임스페이스를 선언하고 

자동으로 네임스페이스의 내용 사용 할수 있음

```html
<html xmlns:th="http://www.thymeleaf.org">
```

이렇게 써주면  태그 자동완성 사용가능





## html 조각 삽입

![image](https://user-images.githubusercontent.com/65274952/137279225-78efb687-5185-4909-89c5-28ae6facd9cb.png)





![image](https://user-images.githubusercontent.com/65274952/137279875-d6f9b8c2-6ed4-417f-8f7a-011569153f92.png)

menu.html 전체 내용을 div 안에 모두 넣겠다는 의미



![image](https://user-images.githubusercontent.com/65274952/137279966-99bd59d4-7fd3-48a8-9821-07709998f128.png)

파일 전체가 아니라

파일안의 특정이름인 부분

![image](https://user-images.githubusercontent.com/65274952/137280036-90127bb9-2920-459a-9021-aa4f79ca74cd.png)

이렇게 정의된 부분을 menu 에서 가져온다.



![image](https://user-images.githubusercontent.com/65274952/137280179-0c5a4459-40f5-49b4-9d4e-e57ec7e54f61.png)

th:block은 앞으로 대치될 부분에 사용함

html 태그가 아니라 앞으로 추가될, 바뀔 부분이라는 의미로 사용함



# 실습



```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
   <th:block th:fragment="setContent(content)">
      <head>
         <meta charset="UTF-8"/>
         <meta name="viewport" content="width=device-width, initial-scale=1.0" />
         <title>Spring Boot</title>
         <link rel="stylesheet" th:href="@{/bootstrap-4.6.0-dist/css/bootstrap.min.css}"/>
         <script th:src="@{/jquery/jquery-3.5.1.min.js}"></script>
         <script th:src="@{/bootstrap-4.6.0-dist/js/bootstrap.bundle.min.js}"></script>
      
         <link rel="stylesheet" th:href="@{/css/app.css}"/>
      </head>
      
      <body>
         <div class="d-flex flex-column vh-100">
            <nav class="navbar navbar-expand-sm bg-dark navbar-dark text-white font-weight-bold justify-content-between">
               <a class="navbar-brand" href="/"> 
                  <img th:src="@{/images/logo-springboot.png}" height="30" class="d-inline-block align-top">
                  Spring Boot
               </a>
               <div>
                  <div sec:authorize="!isAuthenticated()">
                     <a th:href="@{/security/loginForm}" class="btn btn-info btn-sm">로그인</a>
                  </div>
                  <div sec:authorize="isAuthenticated()">
                     User: <span sec:authentication="name"></span>
                     <a th:href="@{/logout}" class="btn btn-info btn-sm ml-2">로그아웃</a>
                  </div>
               </div>
            </nav>
      
            <div class="flex-grow-1 container-fluid">
               <div class="row h-100">
                  <div class="col-md-4 p-3 bg-dark">
                     <div class="h-100 d-flex flex-column">
                        <div class="flex-grow-1" style="height: 0px; overflow-y: auto; overflow-x: hidden;">
                           <!-- ############################################### -->
                           <th:block th:replace="~{/common/menu}"></th:block>
                           <!-- ############################################### -->
                        </div>
                     </div>
                  </div>
      
                  <div class="col-md-8 p-3">
                     <div class=" h-100 d-flex flex-column">
                        <div class="flex-grow-1 overflow-auto pr-3" style="height: 0px">
                           <!-- ############################################### -->
                           <th:block th:replace="${content}"></th:block>
                           <!-- ############################################### -->
                        </div>
                     </div>
                  </div>
               </div>
            </div>
         </div>
      </body>
   </th:block>
</html>
```



  <th:block th:fragment="setContent(content)">

blcok 의 이름을 설정하는 부분



```html
 <th:block th:replace="~{/common/menu}"></th:block>
```

replace 쓰려면 ~를 넣어야 함

th:block 쓰는 이유는 저 태그에 뭘 넣어도 어차피 common/menu로 대체되니까 th:block 으로 대체됨을 표현

common 아래의 menu 전체내용이 들어간다.

### 문법

~를 사용해야 insert replace 사용가능하다



 {% endraw %}