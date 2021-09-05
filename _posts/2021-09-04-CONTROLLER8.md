

---
title: CONTROLLER
layout: single
author_profile: true
read_time: true
comments: true
share: true
related: true
categories:

meta_keywords:
toc: true
toc_sticky: false
toc_label: 목차
---

 {% raw %}

J SESSION ID -> 서버에서 무조건 생성됨

브라우저는 JSESSION에 쿠키 전송하여 세션객체를 찾아서 이용한다.

![image-20210905091721445](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20210905091721445.png)

세션범위로 객체를 전달하는 방법2가지

![image-20210905091823066](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20210905091823066.png)

로그인폼 띄우는부분

![image-20210905092133407](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20210905092133407.png)

![image-20210905093404410](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20210905093404410.png)

세션을 넘겨줘서 로그인 상태를 유지한다.



같은 url 다른 메서드로 받는다.

content는 



![image-20210905093554514](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20210905093554514.png)





@모델 어트리뷰트

@리퀘스트파람 ->

![image-20210905093056024](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20210905093056024.png)



ajax 로그인

```java
@PostMapping(value="/loginAjax", produces="application/json;charset=UTF-8")
@ResponseBody
public String loginAjax(String mid, String mpassword, HttpSession session) {
    logger.info("실행");
    String result = "";

    if(!mid.equals("spring")) {
        result = "wrongMid";
    }else if(!mpassword.equals("12345")) {
        result = "wrongMpassword";
    }else {
        result = "success";
        session.setAttribute("sessionMid", mid);
    }

    JSONObject obj = new JSONObject();
    obj.put("result", result);
    String json = obj.toString();
    return json;
}
```



![image-20210905094121811](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20210905094121811.png)



ㅁajax 로그아웃

![image-20210905095013205](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20210905095013205.png)

![image-20210905095100095](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20210905095100095.png)









![image-20210905095241904](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20210905095241904.png)





한번만 실행하기위해서 사용 -> 지정한 객체가 세션에 없을 때 한번만 실행함

![image-20210905095336718](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20210905095336718.png)



![image-20210905095645931](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20210905095645931.png)

나중에 EL로 이렇게 사용함





![image-20210905100030718](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20210905100030718.png)

컨트롤러에 있는 inputform --> modelattribute의 inputform -> 

 

![image-20210905100142734](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20210905100142734.png)



![image-20210905100532345](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20210905100532345.png)

setComplete() -> 세션에 있는 정보가 사라짐 -> inputform 객체 자체가 사라짐



sesstionarrtibutes 어노테이션을 통해서 세션에 등록가능







9장

![image-20210905100929994](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20210905100929994.png)





![image-20210905101005955](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20210905101005955.png)

![image-20210905101020241](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20210905101020241.png)



![image-20210905101040292](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20210905101040292.png)





![image-20210905111603587](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20210905111603587.png)



![image-20210905112008971](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20210905112008971.png)



FormData 는 미리 정의 된 객체

multipartresolver과 호환됨



파일을 저장할 때는 Multipart



다운로드방식 2가지

1 권장하지 않는 방식

2 권장되는 방식 - > 

![image-20210905112504293](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20210905112504293.png)

![image-20210905112540374](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20210905112540374.png)

set Header는 고정

![image-20210905112626360](C:\gitblog\gyeongmu.github.io\_posts\image-20210905112626360.png)



리스폰스 바디에 





 {% endraw %}