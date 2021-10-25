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





# Cors (Cross-Origin Resource Sharing, 교차 출처 리소스 공유) 설정



A 서버에서 받은 JS를 통해 B라는 서버에 접근하면 브라우저는 그것을 막는다.

![image](https://user-images.githubusercontent.com/65274952/138625995-b5ec6b51-29e6-4f01-bea4-c79df1d3bc7e.png)

A 가 준 JS 외의 다른 도메인에서 온 JS 가 A에 접근 가능하게 하려면 Cors 설정을 해야함



## Cors 설정이 없을 때 생기는 문제

외부 도메인으로 데이터를 보내려고할 때 cors 설정없으면 (기본 CORS 설정이면)어떤 에러가 날까?

```javascript
function authTest3() {
    $.ajax({
        // 외부 url로 데이터 보내기
        url: "http://kosa3.iptime.org/board/test",
        headers: {Authorization:`Bearer ${sessionStorage.getItem("jwt")}`},
    }).done((data) => {
        console.log(data);
    });
}
```



![image](https://user-images.githubusercontent.com/65274952/138626515-40f82114-0934-4408-9327-811315a6f1c5.png)

cors 정책 위반으로 실패



## 스프링 시큐리티 CORS 활성화 설정

```java
//CORS 설정 활성화
http.cors();
```

이 구문이  configure에 있으면 



스프링 시큐리티는 

**CorsConfigurationSource** 이 객체를 찾는다.

그래서 **CorsConfigurationSource** 객체는 관리빈으로 등록되어있어야한다.



```java
@Bean
public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration conf = new CorsConfiguration();
    //모든 요청사이트 허용
    //모든 사이트가 현 서버로 요청 가능함
    conf.addAllowedOrigin("*");

    //모든 요청 방식 허용
    //get put delete post 등등 요청방식 허용 
    conf.addAllowedMethod("*");

    //모든 요청 헤드 허용
    //인증정보는 요청 헤더에 실려서 오는데 그것을 통과시켜주는 내용
    conf.addAllowedHeader("*");
    //		conf.addAllowedHeader("Authorization"); 
    //이렇게 해도 괜찮다. 이거 받으려고 모든 요청해더 허용하는것

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    //모든 요청에 대해 CorsConfiguration conf 에 설정한대로 Cors 정책 변경 하겠다.
    source.registerCorsConfiguration("/**", conf);

    return source;
}
```



##### RESTAPI인 경우 CORS 설정을 반드시 해야한다 다른 도메인에서 접근하는 경우가 아주 많기 때문이다.

---



# REST API를 적용한 게시판 만들기





쿼리문을 통해서 회원가입
![image](https://user-images.githubusercontent.com/65274952/138637383-95643da1-1920-436d-a82f-16e75ae397f7.png)



JSON을 통해서 회원가입

![image](https://user-images.githubusercontent.com/65274952/138637537-15db3596-eae1-4d5e-9ee0-59798c0c8335.png)



login1 (쿼리로 로그인)

![image](https://user-images.githubusercontent.com/65274952/138637665-09aa5344-ca98-490c-a96b-f66e282807a5.png)

login2 (JSON 로그인)

![image](https://user-images.githubusercontent.com/65274952/138637786-ca9d8dff-8f97-490e-b7f6-3620f08a82e5.png)



## board 요청

그냥 요청 넣으면  (헤더에 jwt 없이)

![image](https://user-images.githubusercontent.com/65274952/138637881-954c7563-196d-42e1-a0b4-4e539385d3d6.png)



403 에러가 발생한다

![image](https://user-images.githubusercontent.com/65274952/138637888-4fa5d77b-f3bb-4f86-a086-e75f976c871c.png)

권한 설정을 해준 페이지 이기 때문이다.





JWT를 복사하여 Auth 헤더에 넣어서 보내주면 

403 에러 없이 정상적으로 값을 받아온다 

![image](https://user-images.githubusercontent.com/65274952/138638036-a546ab0b-e58f-4228-85d1-d419ae81bba7.png)





## 특정 게시물 보기

auth 없이 전송시 403
![image](https://user-images.githubusercontent.com/65274952/138639660-8c6415f0-32a8-439b-bc2d-e9fed74e6e42.png)
auth 포함
![image](https://user-images.githubusercontent.com/65274952/138639817-a0e82de0-2f47-4e46-9ead-a5cb7278218f.png)





## 게시물 생성

```java
@PostMapping("/create")

// 넘어오는 데이터 title content, mid, 첨부 있을 경우 battach 넘어옴
// 응답은 완전한 게시물을 보낼 예정.
public Board create(Board board) {
    log.info("실행");

    // if 부분이 false 면 첨부파일이 없는 것
    if (board.getBattach() != null && !board.getBattach().isEmpty()) {
        // Battach 멀티파트 타입이다. 첨부가 없어도 null 이 아니다. //파일이 없어도 Battach는 생성이된다.
        // Battach가 있는지 확인하고 (Battach없는데 파일이 있는 지 확인하려고 하면 null pointer exception)그 뒤에
        // Battach에 파일이 있는지를 확인해야한다.
        MultipartFile mf = board.getBattach();
        board.setBattachoname(mf.getOriginalFilename());
        board.setBattachsname(new Date().getTime() + "-" + mf.getOriginalFilename());
        board.setBattachtype(mf.getContentType());
        // 가장 중요한 파일로 저장하는 부분
        try {
            File file = new File("C:/hyndai/test/" + board.getBattachsname());
            mf.transferTo(file);
        } catch (Exception e) {

        }
    }
    boardService.writeBoard(board);
    // 생성 이후 생성한 게시물을 보여주는데 그걸로 조회수를 올리면 안되니까 hit ==> false
    board = boardService.getBoard(board.getBno(), false);
    return board;
}
```









## 동적쿼리





**boardService.writeBoard(board);** 에서 DB의 시퀀스값을 bno 로 넣어준다. 뿐만아니라 **date** 같이 DB에서 설정해야하는 값도 들어간다.

 

```html
<selectKey order="BEFORE" resultType="int" keyProperty="bno">
    SELECT SEQ_BNO.nextval FROM dual
</selectKey>
```





**battachoname 의 여부**에 따라 즉 첨부파일의 여부에 따라 쿼리문을 바꾼다.



```html
<insert id="insert" parameterType="board">
    <selectKey order="BEFORE" resultType="int" keyProperty="bno">
        SELECT SEQ_BNO.nextval FROM dual
    </selectKey>
    <if test="battachoname == null">
        INSERT INTO board 
        (bno, btitle, bcontent, mid, bdate, bhitcount)
        VALUES
        (#{bno}, #{btitle}, #{bcontent}, #{mid}, SYSDATE, 0)
    </if>
    <if test="battachoname != null">
        INSERT INTO board 
        (bno, btitle, bcontent, mid, bdate, bhitcount, battachoname, battachsname, battachtype)
        VALUES 
        (#{bno}, #{btitle}, #{bcontent}, #{mid}, sysdate, 0, #{battachoname}, #{battachsname}, #{battachtype})
    </if>
</insert>	
```







첨부 파일 없는 경우
![image](https://user-images.githubusercontent.com/65274952/138642263-ef89c48a-1b47-4d91-a7f6-a94f6b60be80.png)

첨부 파일 있는 경우
![image](https://user-images.githubusercontent.com/65274952/138642244-b408522f-7fae-43a7-bf93-addf96cbf4d4.png)

battach 는 DB에 저장할 값이 아님



## update





```java
//multipart/form-data 로 데이터를 전송할 때에는 반드시 post 사용해야함 (put, patch 사용 불가능)
@PostMapping("/update")
// 원래 수정은 put 을 쓰는게 맞으나 첨부파일이 있는 경우 put patch 사용할 수 없고 post 써야한다.
public Board update(Board board) {
    log.info("실행");
    // if 부분이 false 면 첨부파일이 없는 것
    if (board.getBattach() != null && !board.getBattach().isEmpty()) {
        MultipartFile mf = board.getBattach();
        board.setBattachoname(mf.getOriginalFilename());
        board.setBattachsname(new Date().getTime() + "-" + mf.getOriginalFilename());
        board.setBattachtype(mf.getContentType());
        // 가장 중요한 파일로 저장하는 부분
        try {
            File file = new File("C:/hyndai/test/" + board.getBattachsname());
            mf.transferTo(file);
        } catch (Exception e) {

        }
    }
    boardService.updateBoard(board);
    // 생성 이후 생성한 게시물을 보여주는데 그걸로 조회수를 올리면 안되니까 hit ==> false
    board = boardService.getBoard(board.getBno(), false);
    return board;
}

```



# Delete



```java
@DeleteMapping("/{bno}")
public Map<String, String> delete(@PathVariable int bno) {
    boardService.removeBoard(bno);
    log.info("실행");
    boardService.removeBoard(bno);
    Map<String, String> map = new HashMap<>();
    map.put("result", "success");
    return map;

}
```





![image](https://user-images.githubusercontent.com/65274952/138646808-183ba47e-39b0-4198-ad55-a38c8a9ae44c.png)









# 첨부 다운로드



![image-20211025155603030](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20211025155603030.png)



# img 태그로 board 사진보기

## 불가피하게 요청헤더에 jwt 추가 못하는 경우 



 이미지를 요청할때는 http 헤더에 뭔가 추가할 수 없다, Authorization 헤더를 추가 못시킨다.
그러면 에러가 난다.

```html
<img id="attachImg" class="mt-2" width="300px" src="http://localhost/board/battach/346"/>
```

결론

```html
src="http://localhost/board/battach/346?jwt=xxx"
```

쿼리스트링에 jwt를 추가하는 것 외에는 방법이 없다.



불가피하게 요청헤더에 jwt 추가 못하는 경우 

쿼리에 있는 jwt를 확인하게 할 수 있다.



### 필터 수정



무조건 요청헤더에서만 jwt 찾는다.

```java
//JWT 열기
String jwt = null;
if(request.getHeader("Authorization") != null &&
   request.getHeader("Authorization").startsWith("Bearer")){
    jwt = request.getHeader("Authorization").substring(7);
}
log.info("jwt : "+jwt);

```





요청헤더에 없다면 쿼리에서도 jwt를 찾는다.



```java
//JWT 열기
String jwt = null;
if(request.getHeader("Authorization") != null &&
   request.getHeader("Authorization").startsWith("Bearer")){
    jwt = request.getHeader("Authorization").substring(7);
}else if(request.getParameter("jwt") !=null) {
    jwt =request.getParameter("jwt");
}
```





## 결론





```java
<img id="attachImg" class="mt-2" width="300px"/>
    <!-- 이미지를 요청할때는 http 헤더에 뭔가 추가할 수 없다, Authorization 헤더를 추가 못시킨다.
    그러면 에러가 난다. -->

    <script th:inline="javascript">
        function downloadAttach() {
        const bno = $("#downloadAttachForm [name=bno]").val();
        const url = "[(@{/board/battach/})]" + bno;
        const jwt = "jwt=" + sessionStorage.getItem("jwt");
        $("#attachImg").attr("src", `${url}?${jwt}`);
    }
</script>
```





# jwt 를 받는 여러가지 방법

jwt 를 받는 방법은 요청 헤더에 포함된 jwt 받는게 기본이지만



img 태그같이 헤더에 jwt 포함 못시키는 경우 쿼리 스트링에 포함시킨 jwt 도 받을 수 있어야한다.



# home 의 이유

home 은 jquery로도 rerst api 를 사용가능하다는 점을 보기 위해 사용해보았다. 





 {% endraw %}

