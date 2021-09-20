---
title: Spring Security
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



# hasRole

xml 에서 권한 계층 설정 시에도 사용하고

Spring taglib 에서도 사용함 



```xml
<!-- 요청 URL 권한 설정 -->
<security:intercept-url pattern="/ch17/admin*" access="hasRole('ROLE_ADMIN')"/>
<security:intercept-url pattern="/ch17/manager*" access="hasRole('ROLE_MANAGER')"/>
<security:intercept-url pattern="/ch17/user*" access="isAuthenticated()"/>
<security:intercept-url pattern="/**" access="permitAll"/>
```





## Spring Security tag lib access 속성의 값





| 표현식                      | 설명                                                         |
| --------------------------- | ------------------------------------------------------------ |
| hasRole('권한')             | 해당 권한을 가지고 있으면 TRUE                               |
| hasAnyRole('권한1','권한2') | 해당 권한들을 가지고 있으면 TRUE                             |
| principal                   | 현재 사용자를 나타내는 주요 객체에 대한 정보                 |
| authentication              | Authentication객체에 직접 접근 가능                          |
| permitAll                   | 모두 허용                                                    |
| denyAll                     | 모두 불가                                                    |
| isAnonymous()               | 익명사용자라면 TRUE                                          |
| isRememberMe()              | remember-me 사용자라면 TRUE                                  |
| isAuthenticated()           | 사용자가 익명이 아니라면 TRUE                                |
| isFullyAuthenticated()      | 사용자가 익명이 아니고, remember-me 기능이 비활성화된 경우 TRUE |

https://cchoimin.tistory.com/entry/Spring-Security-%ED%83%9C%EA%B7%B8





```jsp
<sec:authorize access="hasRole('ROLE_ADMIN')">
    <li>Admin Menu</li>
</sec:authorize>
```



```jsp
<sec:authorize access="isAnonymous()">
    <a href="${pageContext.request.contextPath}/ch17/loginForm"
       class="btn btn-success btn-sm"
       >로그인</a>
</sec:authorize>


<!-- 사이트간 요청 위조 방지 활성화 되어있을 경우  -->
<sec:authorize access="isAuthenticated()">
    <form  method="post" action="${pageContext.request.contextPath}/logout">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
        <button class="btn btn-success btn-sm"> 로그아웃</button>
    </form>
</sec:authorize>
```





# handler



 {% endraw %}