---
title: REST와 JWT 인증
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



# REST API의 

브라우저 이외에서 요청하는 경우 이외 안드로이드 프로그램에서 요청,

리아 (리치인터넷..?)

윈도우 프로그램이 실행되다가

보내면 데이터만 받으면된다 페이지를 제공할 필요가 없다.



REST API는 브라우저 환경에서만 사용하는 것은 아니다.



vue

Single page

모든 ui 클라이언트에서 만들어짐 

REST API로 데이터만 받아서 사용함 



임베디드 환경에서 센서데이터 같은걸 rest api로 보내고 임베디드 제어 명령어를 rest api 로 받아서 사용가능



http 통신으로 html 페이지만 받아왔지만

rest api는 브라우저 제외 여러가지 환경에서 데이터를 요청할 수 있다.



# JWT 를 활용한 로그인



## 스프링 시큐리티 폼 로그인 비활성화

클라이언트가 restful한 요청을하면 서버가 폼을 제공하지 못한다. (데이터를 전달하기 때문에)

스프링 시큐리티의 폼 로그인을 비활성화 했다. 

![image](https://user-images.githubusercontent.com/65274952/138618462-c910c327-2e59-45bd-bfd2-8460ec2b2cae.png)



![image](https://user-images.githubusercontent.com/65274952/138618860-6c5001b6-b014-4539-bf05-6b7fa7868136.png)

만약 disable 하지 않으면 

응답으로 form을 제공하려고 한다.

![image](https://user-images.githubusercontent.com/65274952/138618896-a31b8aca-28b4-4ac7-8dca-bf6a7fde1b00.png)

즉 HTML를 data 로 제공하려고한다. 안드로이드는 이걸 못받는다.

그래서 data 로는 실패 (403)이렇게만 보내려고 하는데 그러면 자동으로 폼을 제공하는 옵션을 꺼야한다.



이런 폼은 클라이언트에서 가지고 있고 

데이터로 보내고 데이터로만 받는다.

![image](https://user-images.githubusercontent.com/65274952/138618533-c5f89f52-678f-46d4-87fe-7182507b8751.png)



**서버단에서restful 하게 통신하려면 form 을 제공할 수없다.**



그 상황에서 form 을 주지 않으면 어떤 데이터를 전달해야할까? -> 권한 없음에 대한 데이터를 전달해야함.



# JWT 인증



![image](https://user-images.githubusercontent.com/65274952/138618625-b5dab1a9-9870-4f80-8a62-75f1cabcad18.png)

로그인 인증이 되면 인증정보를 서버에서 저장하지 않는다.

즉 서버에서 세션정보를 바탕으로 인증을 진행하지 않는다.

1. POST로 id pw를 서버에전송

2. 서버가 확인하고 JWT 발급

   - ID PW를 DB와 대조하여 인증 처리

3. 브라우저의 세션 스토리지 같은 저장소에 JWT 보관

4. 일반적으로 Authoriztion Header 라는 요청헤더에 JWT를 실어서 서버로 보낸다.

5. 서버는JWT를 시그니처를 통해서 유효성을 검사함 (해당 서버에서 발행한 것인지 확인)

   - 인증확인은 하지 않음 즉 ID PW 확인하지 않음

   - JWT가유효 하다면 인증객체 만들어서 인증처리해줌

6. 인증객체를 보내줘서 인증상태로 만들어줌?





# Collections

![image](https://user-images.githubusercontent.com/65274952/138619584-af88ffc6-d4dc-4f73-ab00-31009ba5b566.png)

get authorities 했는데 Collection 이 반환된 상황

Collection 은 인덱스로 출력할 수 없는 값이다.



![image](https://user-images.githubusercontent.com/65274952/138619643-1f3f0cfb-815c-43b9-a9f4-fa8b2d3d48bd.png)

iterator 는 반복자다.



![image](https://user-images.githubusercontent.com/65274952/138619723-82a2ffa9-0451-41a2-88e6-d24e15901930.png)



iterator를 활용해서 collection 안의 개체를 하나씩 가져와서 사용하는 것 가능하다. next로 하나씩가져온다.



# 세션 비활성화

```java
//세션 비활성화
http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

```



```java
@RequestMapping("/login1")
public Map<String, String> login1(String mid, String mpassword){
    log.info("실행");
    if(mid==null || mpassword ==null) {
        throw new BadCredentialsException("아이디 또는 패스워드 제공되지 않음");	
    }
    //Spring Security 사용자 인증

    //UsernamePasswordAuthenticationToken token  은 ID와 PW에 대한 정보를 가지고 있는 객체
    UsernamePasswordAuthenticationToken token = 
        new UsernamePasswordAuthenticationToken(mid, mpassword);

    // token 객체를 manager 의 authenticate 라는 메서드에 제공하면 메서드가 확인해서 인증정보가 맞으면 
    //인증 성공 했다는 Authentication authentication  객체 반환 틀리면 예외발생
    Authentication authentication = authenticationManager.authenticate(token);
    // 환경정보를 저장하는 객체를 얻어내고 거기에 인증정보 객체를 저장하면 끝 -> 스프링 시큐리티가 인증정보를 가지고 있게됨.
    //스프링 시큐리티는 기본적으로 세션에 인증정보 저장하는데 그 세션에 인증정보가 저장되게 된다.
    SecurityContext securityContext = SecurityContextHolder.getContext();
    securityContext.setAuthentication(authentication);
    //응답내용
    //		String authority = authentication.getAuthorities(); //여기까지 하면 Collection 얻는것
    //mrole 이 여러개 있을 수 있어서 Collection 으로 얻는다.
    String authority = authentication.getAuthorities().iterator().next().toString();
    log.info(authority);

    Map<String, String> map = new HashMap<>();
    map.put("reslut", "success");
    map.put("mid", mid);
    map.put("jwt", JwtUtil.createToken(mid, authority));
    return map;
}
```



세션에 인증정보 저장하지 않으면서 추가로 해야할 일이 생겼다.



```java
Map<String, String> map = new HashMap<>();
map.put("reslut", "success");
map.put("mid", mid);
map.put("jwt", JwtUtil.createToken(mid, authority));
```





```html
<script th:inline="javascript">
				function login1() {
					var mid = $("#loginForm [name=mid]").val();
					var mpassword = $("#loginForm [name=mpassword]").val();
					$.ajax({
						url: "[(@{/member/login1})]",
						method: "post",
						data: {mid, mpassword}
					}).done((data) => {
						console.log(data);
						sessionStorage.setItem("mid", data.mid);
					    sessionStorage.setItem("jwt", data.jwt);
					});
				}
</script>
```



```javascript
sessionStorage.setItem("mid", data.mid);
sessionStorage.setItem("jwt", data.jwt);
```

세션에 mid 와 jwt를 저장한다.



## 브라우저의 저장공간

![image](https://user-images.githubusercontent.com/65274952/138621528-da507b2d-40db-4a4d-9e64-1f2930885b22.png)

로컬 스토리지- 

세션 스토리지 - 브라우저가 닫히지 않는 동안 유지

쿠키



![image](https://user-images.githubusercontent.com/65274952/138621669-f54d6cb2-47d2-4183-8187-dd0a449a3aad.png)



### mid를 받는이유??

프론트에서 사용자를 구분하기위함

일단은 jwt가 핵심





# JWT 헤더 미포함,포함 인증

```html
<script th:inline="javascript">
    function authTest1() {
        $.ajax({
            url: "[(@{/board/test})]"
        }).done((data) => {
            console.log(data);
        });
    }

    function authTest2() {
        $.ajax({
            url: "[(@{/board/test})]",
            headers: {Authorization:`Bearer ${sessionStorage.getItem("jwt")}`},
        }).done((data) => {
            console.log(data);
        });
    }
</script>
```

백틱 -> 데이터 바인딩

````${}`
`${}`
```

사이의 값에 데이터 바인딩



```
 headers: {Authorization:`Bearer ${sessionStorage.getItem("jwt")}`},
```

Authorization : 헤더명

Bearer : Bearer+공백한칸 뒤 jwt 보내는게 올바른 양식이다.



JWT 미포함 인증확인
![image](https://user-images.githubusercontent.com/65274952/138625766-c5ce7ebe-f110-42c8-ab85-f1f99f752fc1.png)

JWT 포함 인증확인

![image](https://user-images.githubusercontent.com/65274952/138625706-1dd6f287-b1ed-4e99-bc42-2274b9aa96b1.png)



# JWT 인증 체크

Spring Security는 여러 필터의 연결로 구성되어있다.

id 와 pw로 DB조회하여서 로그인되었는지 확인해주는 기본 필터가 있지만 지금은 

id pw로 로그인여부를 확인하는게 아니라 jwt로 확인함.



jwt를 체크해서 인증확인하는 필터를 만들고 연결하여 스프링 시큐리티가 jwt를 활용할 수 있게 해준다.



## 새로운 필터 생성

```java
@Slf4j
public class JwtCheckFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        log.info("시큐리티 필터 실행");
        
        //다음연결된 필터를 실행하라는 구문
        filterChain.doFilter(request, response);

    }
}
```

요청이 발생할 때 마다 실행하는 필터를 만들기 위해

OncePerRequestFilter 상속받음



## JWTCheckFilter의 위치

원래 spring security는 id pw로 인증을 처리하는 필터가 있다.
**그 필터 이전에 JWT 로 인증을 처리하는 필터가 있어야한다.**





ID PW 로 인증하지 않기 때문에 그 필터가 작동된다면 에러가 나서 필터의 다음과정이 진행되지 않고 예외처리가 된다.



ID PW 로 check하는 필터 이전에 JWT로 check 하는 필터에서 인증 인증이 성공하고 ID PW check 하는 필터로 가면 필터는 실행하지 않고 지나간다.



```java
//JWTCheckFilter 추가
//		원래 spring security는 id pw로 인증을 처리하는 필터가 있다.
//		그 이전에 JWT 로 인증을 처리하는 필터가 있어야한다.

//jwtcheck 필터 생성
JwtCheckFilter jwtCheckFilter = new JwtCheckFilter();
//ID와 PW를 check하는 필터 UsernamePasswordAuthenticationFilter 앞에 JWT 인증작업 필터를 삽입한다.
http.addFilterBefore(jwtCheckFilter, UsernamePasswordAuthenticationFilter.class);
```



# JWT 유효성 검사



## JWT 받기

```java
@Slf4j
public class JwtCheckFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException {
        log.info("시큐리티 필터 실행");


        //JWT 열기
        String jwt = null;
        if(request.getHeader("Authorization") != null &&
           request.getHeader("Authorization").startsWith("Bearer")){
            jwt = request.getHeader("Authorization").substring(7);
        }
        log.info("jwt : "+jwt);

        //다음연결된 필터를 실행하라는 구문
        filterChain.doFilter(request, response);

    }

}
```



![image](https://user-images.githubusercontent.com/65274952/138623429-1385de8b-61f6-4928-9342-32d0b618fc8a.png)

헤더에

Authorization 

있으면 받는다.



```http
INFO [0;39m [35mc.m.w.s.JwtCheckFilter:[34mdoFilterInternal[0;39m                             [0;39m [32m:[0;39m 시큐리티 필터 실행
[32mINFO [0;39m [35mc.m.w.s.JwtCheckFilter:[34mdoFilterInternal[0;39m                             [0;39m [32m:[0;39m jwt : null
[32mINFO [0;39m [35mc.m.w.s.JwtCheckFilter:[34mdoFilterInternal[0;39m                             [0;39m [32m:[0;39m 시큐리티 필터 실행
[32mINFO [0;39m [35mc.m.w.s.JwtCheckFilter:[34mdoFilterInternal[0;39m                             [0;39m [32m:[0;39m jwt : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE2MzUyMTMzOTUsIm1pZCI6IjMiLCJhdXRob3JpdHkiOiJST0xFX1VTRVIifQ.4-3chmUM1dF7XiU_thH1GX6BCBudDB6P-8_3aJz7_pA

```



## JWT 유효성 검사 + Claims 값 받기

```java
//JWT 유효성 검사
if(jwt !=null) {
    Claims claims = JwtUtil.validateToken(jwt);
    //jwt 가 유효하지 않으면 claims 는 "null" 리턴됨
    if(claims != null) {
        log.info("유효한 토큰");
        String mid = JwtUtil.getMid(claims);
        String authority = JwtUtil.getauthority(claims);
        log.info("authority : "+authority);
        log.info("mid : "+mid);
    }else {
        log.info("유효하지 않은 토큰");
    }
}
```







```http
authority : ROLE_USER
mid : 3
```



사용자 인증

![image](https://user-images.githubusercontent.com/65274952/138624491-58499f43-d6ab-4725-8d97-18ee1e2de1ab.png)



```java
UsernamePasswordAuthenticationToken authentication = 
    new UsernamePasswordAuthenticationToken(mid, null, AuthorityUtils.createAuthorityList(authority));
//스프링 시큐리티는 기본적으로 세션에 인증정보 저장하는데 그 세션에 인증정보가 저장되게 된다.
SecurityContext securityContext = SecurityContextHolder.getContext();
securityContext.setAuthentication(authentication);
```



![image](https://user-images.githubusercontent.com/65274952/138624593-8fb72b02-39ed-47c9-9185-7cd67b907170.png)

DB에 있는 정보를 검증하여 인증한것이 아니라 그냥 JWT에서 받은 값 사용하기 때문에

이부분이 빠진다 -(확인작업이 필요없어서) 





id 와 권한에 대한 정보를 가지고 있는 **authentication**

객체를 만들고

그것을 DB 검증 거치지 않고 인증객체로 만듦.

**AuthorityUtils.createAuthorityList(authority));**







![image](https://user-images.githubusercontent.com/65274952/138624308-ca08eec8-1caf-4964-942b-0ef4bd30eac4.png)







 {% endraw %}