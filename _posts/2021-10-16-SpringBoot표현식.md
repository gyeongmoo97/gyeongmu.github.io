---
title: Spring Boot thymeleaf
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



# th:replace, th:insert name



```html
<th:block th:fragment="header">
    <head>
        <meta charset="UTF-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>Spring Boot</title>
        <link rel="stylesheet"
              href="/bootstrap-4.6.0-dist/css/bootstrap.min.css" />
        <script src="/jquery/jquery-3.5.1.min.js"></script>
        <script src="/bootstrap-4.6.0-dist/js/bootstrap.bundle.min.js"></script>

        <link rel="stylesheet" href="/css/app.css" />
    </head>

    <body>
        <div class="d-flex flex-column vh-100">
            <nav
                 class="navbar navbar-expand-sm bg-dark navbar-dark text-white font-weight-bold justify-content-between">
                <a class="navbar-brand" href="/"> <img
                                                       src="/images/logo-springboot.png" height="30"
                                                       class="d-inline-block align-top"> Spring Boot
                </a>
                <div>
                    <div>
                        <a href="/" class="btn btn-info btn-sm">로그인</a>
                    </div>
                    <!--  <div >
User: <span></span>
<a href="/" class="btn btn-info btn-sm ml-2">로그아웃</a>
</div> -->
                </div>
            </nav>

            <div class="flex-grow-1 container-fluid">
                <div class="row h-100">
                    <div class="col-md-4 p-3 bg-dark">
                        <div class="h-100 d-flex flex-column">
                            <div th:insert="~{/common/menu}" class="flex-grow-1"
                                 style="height: 0px; overflow-y: auto; overflow-x: hidden;">
                                <!--  <th:block th:replace="~{/common/menu}"></th:block> -->
                            </div>
                        </div>
                    </div>

                    <div class="col-md-8 p-3">
                        <div class=" h-100 d-flex flex-column">
                            <div class="flex-grow-1 overflow-auto pr-3" style="height: 0px">

                                </th:block>
```





```html
<!-- 헤더 넣기 -->
<th:block th:replace="~{/common/layout::header}"/>
<!-- 헤더 끝 -->

```

이렇게 th:block 안의 내용 가져올 수 있다.



## 매개변수화 된 th:fragment

고정된 내용이 아닌 매개변수를 통해서 변화하는 html

페이지마다 freagment 안의 내용을 다르게 정의



```html
<th:block th:fragment="setContent(content)">
```

setContent 라는 함수가 호출될 때

content 라는 내용을 어떻게 할 것인가를 서술한다.



```html
<div th:insert="~{/common/menu}" class="flex-grow-1">
    
</div>

<th:block th:replace"${content}"></th:block>
```

setContent 라는 함수가 호출될 때

매개변수로 받은 content를 replace 할때 사용함



```html
<th:block th:replace="~{/common/layout::setContent(~{this::content})}">
    <th:block th:fragment="content">
        Hello Spring Boot 
    </th:block>
</th:block>
```

 

this는 해당 html 을 가리킴

this 의 content를 매개변수로 넣는다.



# 매개변수로 html 일부를 받아오기

```html
layout 에서 함수 생성
<th:block th:fragment="setContent(content)">
    
  ~
   ~
    <th:block th:replace="${content}"></th:block>
   ~
  ~
    
----- 다른 문서에서 common아래의 layout안의 setContent를 호출 + (this::content)로 해당 문서의 이름이content인 fragment 를 매개변수로 사용 
    
    <html xmlns:th="http://www.thymeleaf.org">
        <th:block th:replace="~{/common/layout::setContent(~{this::content})}">
            <th:block th:fragment="content">
                Hello Spring Boot 
            </th:block>
        </th:block>
    </html>

```



![image](https://user-images.githubusercontent.com/65274952/137416380-2af262cd-0dd7-4189-b00f-273ee4608aae.png)



조각을 가져올 때는

~

를 사용한다.



## CSS 선택자로 선택

```html
<html xmlns:th="http://www.thymeleaf.org">
    <th:block th:replace="~{/common/layout::setContent(~{this::#content})}">
        <div id="content">
            Hello Spring Boot 
        </th:block>
    </div>
</html>

```



```html
<html xmlns:th="http://www.thymeleaf.org">
    <th:block th:replace="~{/common/layout::setContent(~{this::.content})}">
        <div class="content">
            Hello Spring Boot 
        </th:block>
    </div>
</html>
```



# 속성을 통한 삽입

th:text (HTML 유효 방식: data-th-text) - 속성값의 태그를 문자 참조로 변환해서 출력 -> 그냥 있는그대로 출력

th:utext (HTML 유효 방식: data-th-utext) - 속성값의 태그를 그대로 출력 -> html 문서에 작성한 것 처럼 인식한 뒤 출력



```html
<div>
    <h6 class="text-info">속성삽입: th:text, th:utext</h6>

    <div>bno: <span th:text="${board.bno}"></span>  </div>

    <div>btitle: <span th:text="${board.btitle}"></span>  </div>

    <div>bcontent: <span th:text="${board.bcontent}"></span>  </div>

    <div>bcontent(unescaped): <span th:utext="${board.bcontent}"></span>  </div>

    <div>mid: <span th:text="${board.mid}"></span>  </div>

    <div>bdate(포맷 설정 하지 않은 형태): <span th:text="${board.bdate}"></span>  </div>

    <div>bdate(#dates 라는 유틸리티 객체의 format 메서드 사용): <span th:text="${#dates.format(board.bdate, 'yyyy.MM.dd')}"></span>  </div>
</div>
```





# 인라인삽입: [[...]],[(...)]



```html
<h6 class="text-info">인라인삽입: [[...]],[(...)]</h6>


<div>bno: [[${board.bno}]] </div>

<!-- 스타일을 지정할 목적의 span 사이에 값 넣기  -->
<div>btitle: <span>[[${board.btitle}]]</span>  </div>

<div>bcontent: [[${board.bcontent}]]  </div>

<div>bcontent(unescaped): [(${board.bcontent})] </div>

<div>mid: [[${board.mid}]] </div>

<div>bdate(포맷 설정 하지 않은 형태): [[${board.bdate}]] </div>

<div>bdate(#dates 라는 유틸리티 객체의 format 메서드 사용): [[${#dates.format(board.bdate, 'yyyy.MM.dd')}]] </div>
</div>
```

[[]]하면 text

[()]하면 utext



# javaScript 데이터 삽입

![image](https://user-images.githubusercontent.com/65274952/137419709-fcaa71c9-b352-4a5b-b7a6-a3f5832c152f.png)

위의 방법처럼 문자열 선언 시 "" 없으면 오류가 발생하지만

자동으로 큰따옴표를 넣어준다.

```html
model.addAttribute("name", "홍길동");

model.addAttribute("hobby", new String[] {"영화", "여행", "드라이빙"});

Board board = new Board();
board.setBno(1);
board.setBtitle("스프링 부트 Template Engine");
board.setBcontent("Thymeleaf is a modern server-side Java template engine");
board.setMid("thymeleaf");
board.setBdate(new Date());
model.addAttribute("board", board);

JSONObject jsonObject = new JSONObject(board);
model.addAttribute("jsonBoard", jsonObject.toString());
```









```javascript
<script type="text/javascript">
    /* let name = "홍길동" */
    let name = [[${name}]];
console.log("name :", name);
</script>
```



![image](https://user-images.githubusercontent.com/65274952/137421823-8e1dcc0a-7fb8-4e91-9725-f74fc54877dd.png)

자동으로 문자열 이면 따옴표 넣어줌



```javascript
<script th:inline="javascript">
    //let name = "홍길동" 
    let name = [[${name}]];
console.log("name :", name);
</script>
```







![image](https://user-images.githubusercontent.com/65274952/137424387-ea50fad8-d324-4293-82c7-d049f3631f6c.png)

![image](https://user-images.githubusercontent.com/65274952/137424363-271b298e-7b3f-4a93-8421-e89e885647e1.png)



```java
<script th:inline="javascript">
    //let name = "홍길동" 
    let name = [[${name}]];
console.log("name :", name);

let hobby = [[${hobby}]];
console.log("hobby: ", hobby);
//원래 자바객체인데 자바 스크립트 객체로 변경해서 넣어준다.
//jsp 는 java 객체를 자동으로 javascript 객체로 변경 못함 
let board =[[${board}]];
console.log("board : ", board);

//json
let jsonBoard1 = [[${jsonBoard}]];
console.log("jsonBoard : ", jsonBoard1);		

//json을 객체로
let jsonBoard = JSON.parse([[${jsonBoard}]]);
console.log("jsonBoard : ", jsonBoard);									
</script>
```

![image](https://user-images.githubusercontent.com/65274952/137424602-877657a8-8878-4342-8b47-b66a76c4bf68.png)



기본적으로 

th:inline="javascript"

넣은뒤에

[[${~~}]]하면 자료형을 보고 적당히 자바스크립트에 맞게 변환시켜줌



json 의 경우 단순문자열로 받는데

JSON.parse() 로 객체로 변경가능

```javascript
<script>
    //Server Side 에서 데이터 바인딩
    name = [[${name}]];
//Clinent Side 에서 데이터 바인딩
let message = `Your's Name is ${name}`;
console.log("message: ", message);

//ClinentSide 에서 실행할 때 Backtick의 바인딩기호 : ${}
//let data = "타임리프";
//let message = `클라이언트 데이터 바인딩: ${data}`;
//console.log("message: ", message);

</script>
```

백틱`` + 백틱 안의 ${}는 자바스크립트의 데이터 바인딩 문법임
JSP의 경우 ${} 보이면 다 변경시켜버려서 백틱 + ${} 문법 사용 못했는데

thymeleaf 경우는 [[${}]] 와 같이 대괄호 안의 ${}만 값을 변경시킴



![image](https://user-images.githubusercontent.com/65274952/137425513-7593155a-8d66-402d-9552-331a67643863.png)





요약하면

```javascript
` ${내용} ` --> JS 바인딩
[[${내용}]] --> thymeleaf 바인딩
```







# 표현식1

![image](https://user-images.githubusercontent.com/65274952/137425644-6aa052c5-ad08-4f3d-ad49-af57cfbd1166.png)



## JSP와 thymeleaf의 차이점

```
${SessionMid}
```



jsp 는 리퀘스트에서 찾고 없으면 세션에서 찾고 없으면 어플리케이션범위에서 찾는다

하지만 thymeleaf 는 다르다

 

## session 의 값 불러오기

```html
<div>
    <!-- Login Id: [[${sessionMid}]] -->
    Login Id: [[${session.sessionMid}]]
</div>

```

그냥 세션의 값을 쓸 수 는 없다 

[[${sessionMid}]] 

앞에 session. 을 붙혀야 세션의 값을 사용 할 수 있다.

 [[${session.sessionMid}]]



## th:if (특정 태그를 조건에 따라 표시하기)

display : none 효과를 가짐



JSP 에서는

![image](https://user-images.githubusercontent.com/65274952/137426677-a5a05d87-48b0-42e7-8353-721b29aafcb8.png)



이런식으로 태그라이브러리를 활용하여 로그인 로그아웃 표현함



thymeleaf는

```html
<a th:if="${session.sessionMid == null}" href="#" class="btn btn-outline-success btn-sm">로그인</a>

<a th:if="${session.sessionMid != null}" href="#" class="btn btn-outline-success btn-sm">로그아웃</a>

```



# 표현식 2

![image](https://user-images.githubusercontent.com/65274952/137427946-50775577-b140-4511-8d60-8ae44af7e018.png)









null 처리를 3항연산자로 하기

![image](https://user-images.githubusercontent.com/65274952/137428101-2434af16-6c6a-49e5-94bf-a1ff96489e14.png)

![image](https://user-images.githubusercontent.com/65274952/137428146-8cc7b365-cc2a-4948-9bdc-f5785e8dec02.png)





# 표현식 3

```html
<div>
    btitle: <span>[[${board.btitle}]]</span>
</div>

<div>bcontent: [[${board.bcontent}]]</div>

<div>bcontent(unescaped): [(${board.bcontent})]</div>

<div>mid: [[${board.mid}]]</div>

<div>bdate(포맷 설정 하지 않은 형태): [[${board.bdate}]]</div>

<div>bdate(#dates 라는 유틸리티 객체의 format 메서드 사용):
    [[${#dates.format(board.bdate, 'yyyy.MM.dd')}]]</div>
```

board 너무 반복됨

줄이고싶다.



```html
<th:block th:object="${board}">
<div>bno: [[*{bno}]]</div>
<div>
btitle: <span>[[*{btitle}]]</span>
</div>

<div>bcontent: [[*{bcontent}]]</div>

<div>bcontent(unescaped): [(*{bcontent})]</div>

<div>mid: [[${board.mid}]]</div>

<div>bdate(포맷 설정 하지 않은 형태): [[*{bdate}]]</div>

<div>bdate(#dates 라는 유틸리티 객체의 format 메서드 사용):
[[*{#dates.format(bdate, 'yyyy.MM.dd')}]]</div>
</th:block>
```



미리 하나 골라놓고 그것을 사용하기



# message

중요한 점 

![image](https://user-images.githubusercontent.com/65274952/137435992-6e710731-e61b-4e37-9e82-89c6a1d85578.png)

messages 로 시작해야함



```html
<h6>[[#{welcome}]]</h6>
```



```html
<div class="card">
    <div class="card-header">
        Message Expressions
    </div>
    <div class="card-body">
        <h6>[[#{welcome}]]</h6>
        <button class="btn btn-outline-dark btn-sm">[[#{btn.join}]]</button>
        <button class="btn btn-outline-dark btn-sm" th:text="#{btn.join}"></button>
    </div>

</div>
```



properties 파일의 key 값으로 자동으로 변경해준다.



# 스프링 부트의 context root

스프링부트는 context root 개념이 없다.

단독으로 실행가능하기 때문에 was 가 없다

최종산출물 안에 내장 웹서버가 들어있기 때문에

was 안에서 실행되는게 아니다. 



stand alone 으로 실행

![image](https://user-images.githubusercontent.com/65274952/137437022-acc30346-8d3d-49be-a215-48abcae63645.png)



![image](https://user-images.githubusercontent.com/65274952/137437068-f7986d92-910c-491a-99d4-a46ef37904a0.png)
![image](https://user-images.githubusercontent.com/65274952/137437079-010ace43-6d64-41a1-b7f5-ad8dd89354fd.png)





톰캣에 

![image](https://user-images.githubusercontent.com/65274952/137437221-896e6853-aca6-4247-9178-df73e8a4c769.png)

war 파일 가져다 놓으면

그때 이 파일 이름이 context path 가 된다.



stand alone 으로 실행 시

localhost/ 로 실행



was 로 실행 시 

localhost/webapp 으로 실행



![image](https://user-images.githubusercontent.com/65274952/137437386-0ebacad9-6d6d-4c69-ae17-c36ed679d690.png)



이런 상황이 무슨 문제인가?

![image](https://user-images.githubusercontent.com/65274952/137437497-855c5aca-434f-4493-bfa5-9f3d30de4d2c.png)

이상황이면 was 에서 구동 시 에러가 난다.

context path 가 빠져있어서 그렇다

![image](https://user-images.githubusercontent.com/65274952/137437581-1eeeea9f-4581-4303-80a2-25ed7c91d98d.png)



# thymeleaf 경로

/ 있으면 절대경로

없으면 상대경로



## "@{/}"

"@{/}" 이거 넣으면 

was 또는 standalone 둘다로 실행가능하다.

없으면 context path 가들어간 경우 문제가 된다.





![image-20211015162346110](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20211015162346110.png)



![image-20211015161916937](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20211015161916937.png)









![image-20211015161925079](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20211015161925079.png)



java -jar war파일의이름.war 로 실행가능



# stand alone, tomcat 실행

stand alone 은 war 안의 내장 서버 (아마 톰캣)으로 실행

```
cmd
java -jar war파일의이름.war 
```



tomcat 은 

webapps 에 war 파일을 삽입 (war 파일이름은 context path 가된다.)

tomcat 의 bin 파일경로에서 start.bat 실행

실행되면 포트번호 확인

-> 포트번호/war파일의 이름













# !doctype  html

![image](https://user-images.githubusercontent.com/65274952/137423346-69484734-0017-41f8-9245-8b94f9c2d14f.png)

형식적으로 작성하는 것이 아님



모든 HTML 문서에 포함되어야함

![image](https://user-images.githubusercontent.com/65274952/137423378-5cef7d95-d2ff-4f7a-9556-a62579f78f62.png)

 











{% endraw %}

