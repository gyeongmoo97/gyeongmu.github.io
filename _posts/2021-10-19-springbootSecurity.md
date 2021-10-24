---
title: springbootSecurity
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



boot 는 가능하면 XML 설정을 하지 않는다.



# security 설정

프레임워크의 xml 을 클래스 파일로 옮기는 과정

1. config를 작성할 클래스 파일 생성
2. WebSecurityConfigurerAdapter 상속 받기
3. @EnableWebSecurity 어노테이션 작성
4. configure(HttpSecurity http)
5. configure(AuthenticationManagerBuilder auth)
6. configure(WebSecurity web)
7. 4,5,6의 내용 오버라이드 하여 작성

![image](https://user-images.githubusercontent.com/65274952/137823294-91b0fdac-cbe3-45a0-ae24-0b3b3862c8ef.png)



# configure(HttpSecurity http)

![image](https://user-images.githubusercontent.com/65274952/137823718-66747998-9df0-44bc-819d-b25641b5fa97.png)
	http.formLogin() 로 호출한 객체의
loginPage 메서드 사용할 것이라는 내용

xml 과 비교


![image](https://user-images.githubusercontent.com/65274952/137823848-c8f81062-0f9e-42e5-8ac2-bf4a2a0620cc.png)
![image](https://user-images.githubusercontent.com/65274952/137823863-43d874cb-e0f3-4844-8b70-e58d41c32918.png)



![image](https://user-images.githubusercontent.com/65274952/137824813-4fc34ae4-c063-4995-a2be-f43cc73a5398.png)

![image](https://user-images.githubusercontent.com/65274952/137824858-851ba637-5546-471c-9834-ffe493c90377.png)


default 값은 username, password 인데 
mid , mpassword 로 쓰고싶다면 
그것이
usernameParameter 라는것을 알려줘야한다. 그방법이 
![image](https://user-images.githubusercontent.com/65274952/137825051-67b34285-de7c-4ade-94c9-d12f3f6ed6c1.png)
이렇게 작성하는 것이다



![image](https://user-images.githubusercontent.com/65274952/137826465-e4aa42fb-afb8-4b66-8722-a23790eaf551.png)

```java
http.authorizeRequests()
    .antMatchers("/security/admin/**").hasAuthority("ROLE_ADMIN") //ant를 사용하면 /security/admin/** 같은 표기법 사용가능
    .antMatchers("/security/manager/**").hasAuthority("ROLE_MANAGER")
    .antMatchers("/security/user/**").hasAuthority("ROLE_USER")
    .antMatchers("/**").permitAll();
```



# 권한이 없을 때

```java
//권한 없음(403)일 경우 이동할 경로
		http.exceptionHandling().accessDeniedPage("/security/accessDenid");
		
```





# DB로그인 연동

![image](https://user-images.githubusercontent.com/65274952/137828603-233d12da-c8e2-4a99-b43c-544472653046.png)
![image](https://user-images.githubusercontent.com/65274952/137828878-e1becbce-eb6b-4491-8331-72bb811ce509.png)



기본이  DelegatingPasswordEncoder

면

DB에 저장할 때 

{bcrypt}$2a$10$vI7tC2h4pDre.YqStwOl5uiT.H2bE/T5IkiZ0bDsWGw9wTgcDdUOa

이런 형태로 저장해야한다. 이렇게 저장하면 앞에 붙어있는 알고리즘으로 뒤의 내용을 해석한다.



# @bean

bean 어노테이션은 아래의 메서드가 리턴하는 값을 관리 객체로 만들어준다.

설정파일에서 자주 사용하는 문법이다.



ex

```java
@Bean
public PasswordEncoder passwordEncoder() {
    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    return passwordEncoder;
}
```

@Bean 이 메서드 위에 있으면 그 메서드는 스프링이 자동으로 실행시켜서 리턴 값을 관리bean 으로 등록한다 (직접호출할 필요없음)



관리객체의 사용법

1. 관리 객체를 만드는 메서드를 그냥 사용하기

```java
passwordEncoder(passwordEncoder());
```

2. 주입

```
@Resource
private PasswordEncoder passwordEncoder;

passwordEncoder(passwordEncoder);
```



# Delegating

```java
@Bean
public PasswordEncoder passwordEncoder() {
    PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    //		PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(); //이렇게 사용하려면 bcrypt로 인코딩된 비밀번호만 저장되어있어야 한다.
    return passwordEncoder;
}
```



# 권한 계층 설정

**RoleHierarchyImpl** 객체는 권한 계층 관계설정 외에도 스프링 시큐리티에서 **권한을 확인할 때 사용**해야하는 객체이므로 **관리bean으로 설정한다**

​	***필수 권한 계층을 참조하기위해 HttpSecurity 에서 사용하기 때문에 관리 bean 으로 반드시 설정해야한다***





![image](https://user-images.githubusercontent.com/65274952/137831670-fab9d3dc-1d70-4f58-8372-3a25d945354c.png)
![image](https://user-images.githubusercontent.com/65274952/137832836-3ca3c2f8-9ad2-4f6d-a6f9-d2c1629ecf92.png)



lhttps://github.com/thymeleaf/thymeleaf-extras-springsecurity



![image](https://user-images.githubusercontent.com/65274952/137833471-245eeb69-93db-4239-baa8-51fbbc42164d.png)









![image-20211019113929194](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20211019113929194.png)



# #의 유무의 차이

#이 없으면 미리 선언된 네임스페이스에 값이 있는것이다 ex) session, application 



#이 붙어있는 내용은 객체 참조다

![image-20211019120442527](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20211019120442527.png)

#request.

을 작성하면 requset 가 가지고 있는 객체가 가지고 있는 값을 사용할 수 있다.

 

# 유저의 정보를 thymeleaf 에  표현하기

![image-20211019120637781](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20211019120637781.png)

Authentication authentication 사용한 적 있었다.

Authentication 객체를 통해서

getName()

getPrincipal()

메서드를 사용하고 사용자의 정보를 얻을 수 있다.



![image-20211019120747973](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20211019120747973.png)





![image-20211019120935033](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20211019120935033.png)

![image-20211019121120324](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20211019121120324.png)





![image-20211019121449788](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20211019121449788.png)



# 원하는 정보를 인증정보로 관리하는 방법 (커스텀 인증 관리자)

![image-20211019122520515](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20211019122520515.png)

인증할때 email을 가져와서 사용할 수 있으면 좋겠다

하지만 여기서는 추가할 수 없다.







![image-20211019124053387](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20211019124053387.png)

![image-20211019124146180](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20211019124146180.png)



![image-20211019141151645](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20211019141151645.png)





![image-20211019143451886](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20211019143451886.png)











 {% endraw %}

