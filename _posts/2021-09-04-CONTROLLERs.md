

---
title: CONTROLLER04
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



```java
package com.mycompany.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.mycompany.webapp.dto.Ch08InputForm;

@Controller
@RequestMapping("/ch08")
@SessionAttributes({"inputForm"})
public class Ch08Controller {
	
	private Logger logger =LoggerFactory.getLogger(Ch08Controller.class);
	
	@GetMapping("/content")
	public String content() {
		logger.info("실행");
		return "/ch08/content";
	}
	//0. 세션에 클라이언트에서 요청을 받고  요청에 대한 결과를 JSON으로 넘겨주고싶다. 
//	1. response에 내용을 보내기위한 @ResponseBody 어노테이션을 추가한다.
//	
//	@GetMapping(value="/savedata", produces="application/json; charset=UTF-8")
	@GetMapping(value="/savedata")
	@ResponseBody
	public String saveData(String name, HttpServletRequest request, HttpSession session) {
		logger.info("실행");
		logger.info("name: " + name);
		
		//HttpSession session = request.getSession();
		session.setAttribute("name", name);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "성공");
		String json = jsonObject.toString(); //{"result":"success"}
		return json;
	}
//	 produces="application/json; charset=UTF-8 없으면??
	
//	@GetMapping(value="/readdata", produces="application/json; charset=UTF-8")
	@GetMapping(value="/readdata")
	@ResponseBody
	public String readData(HttpSession session, @SessionAttribute String name) {
		logger.info("실행");

		//방법1
		//String name = (String) session.getAttribute("name");
		//logger.info("name: " + name);
		
		//방법2
		logger.info("name: " + name);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("name", name);
		String json = jsonObject.toString(); //{"name":"홍길동"}
		return json;
	}
	
	
	@GetMapping("/login")
	public String loginForm() {
		logger.info("실행");
		return "ch08/loginForm";
	}
	
	@PostMapping("/login")
	public String login(String mid,  String mpassword,  HttpSession session) {
		logger.info("실행");
		if(mid.equals("spring") && mpassword.equals("12345")) {
			session.setAttribute("sessionMid", mid);
		}
		return "redirect:/ch08/content";
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		logger.info("실행");
		
		//방법1
		session.removeAttribute("sessionMid");
		
		//방법2
		//session.invalidate();
		
		return "redirect:/ch08/content";
	}
	
	@PostMapping(value="/loginAjax", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String loginAjax(String mid, String mpassword, HttpSession session) {
		logger.info("실행");
		String result = "";
		
		if(!mid.equals("spring")) {
			result = "wrongMid";
		} else if(!mpassword.equals("12345")) {
			result = "wrongMpassword";
		} else {
			result = "success";
			session.setAttribute("sessionMid", mid);
		}
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", result);
		String json = jsonObject.toString(); 
		return json;
	}
	
	@GetMapping(value="/logoutAjax", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String logoutAjax(HttpSession session) {
		logger.info("실행");
		
		session.invalidate();
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		String json = jsonObject.toString(); 
		return json;
	}
	
	//
//	세션에 "inputForm" 이름이 존재하지 않을 경우 딱 한번 실행
	
	@ModelAttribute("inputForm")
	public Ch08InputForm getInputForm() {
		logger.info("실행");
		Ch08InputForm inputForm = new Ch08InputForm();
		return inputForm;
	}
	
	@GetMapping("/inputStep1")
	public String inputStep1(@ModelAttribute("inputForm") Ch08InputForm inputForm) {
		logger.info("실행");
		return "ch08/inputStep1";
	}
	
	@PostMapping("/inputStep2")
	public String inputStep2(@ModelAttribute("inputForm") Ch08InputForm inputForm) {
		logger.info("실행");
		logger.info("data1: " + inputForm.getData1());
		logger.info("data2: " + inputForm.getData2());
		logger.info("data3: " + inputForm.getData3());
		logger.info("data4: " + inputForm.getData4());
		return "ch08/inputStep2";
	}
	
	@PostMapping("/inputDone")
	public String inputDone(@ModelAttribute("inputForm") Ch08InputForm inputForm, SessionStatus sessionStatus) {
		logger.info("실행");
		logger.info("data1: " + inputForm.getData1());
		logger.info("data2: " + inputForm.getData2());
		logger.info("data3: " + inputForm.getData3());
		logger.info("data4: " + inputForm.getData4());
		//처리 내용~
		//세션에 저장되어 있는 inputForm을 제거
		sessionStatus.setComplete();
		return "redirect:/ch08/content";
	}
}
```



```jsp
<%@ page contentType="text/html; charset=UTF-8"%>

<%@ include file="/WEB-INF/views/common/header.jsp"%>

<!--카드 시작  -->
<div class="card m-2">
	Session SUpport
	<div class="card-header">
		세션원리 : jsession 쿠키

		<!--카드 내용 시작  -->
		<div class="card-body">
			<p>서버: 세션 객체 생성->JSESSIONID 쿠기 발생</p>
			<p>브라우저: JSESSIONID 쿠키 전송 -> 세션 객체 찾음 -> 세션 객체 이용</p>
			<a href="javascript:savedata()" class="btn btn-info btn-sm">세션
				데이터 저장</a> <a href="javascript:readdata()" class="btn btn-info btn-sm">세션
				데이터 읽기</a>
		</div>
		<!--카드 내용 끝 -->

		<script>
			function savedata() {
				$.ajax({
					url: "savedata",
					data: {name:"박경무"}
				})
				.done((data) => {
					console.log(data);
				});
			}
			
			function readdata() {
				$.ajax({
					url: "readdata"
				})
				.done((data) => {
					console.log(data); 
					console.log(data.name);
				});
			}
		</script>

	</div>
	<!--카드 끝 -->
	<div class="card">
		<div class="card-header">form을 통한 login 처리</div>
		<div class="card-body">
			<c:if test="${sessionMid == null}">
				<a href="login" class="btn btn-info btn-sm">로그인 폼 요청</a>
			</c:if>
			<c:if test="${sessionMid != null}">
				<a href="logout" class="btn btn-info btn-sm">로그아웃</a>
			</c:if>
		</div>
	</div>

	<div class="card">
		<div class="card-header">AJAX을 통한 login 처리</div>
		<div class="card-body">
			<c:if test="${sessionMid == null}">
				<form>
					<div class="input-group">
						<div class="input-group-prepend">
							<span class="input-group-text">mid</span>
						</div>
						<input id="mid" type="text" name="mid" class="form-control">
						<span id="mid-error" class="error"></span>
					</div>
					<div class="input-group">
						<div class="input-group-prepend">
							<span class="input-group-text">mpassword</span>
						</div>
						<input id="mpassword" type="password" name="mpassword"
							class="form-control"> <span id="mpassword-error"
							class="error"></span>
					</div>
				</form>
			</c:if>
			<div class="mt-2">
				<c:if test="${sessionMid == null}">
					<a href="javascript:login()" class="btn btn-info btn-sm">로그인</a>
				</c:if>
				<c:if test="${sessionMid != null}">
					<a href="javascript:logout()" class="btn btn-info btn-sm">로그아웃</a>
				</c:if>
			</div>
			<script>
					function login() {
						let mid = $("#mid").val();
						let mpassword = $("#mpassword").val();
						$.ajax({
							url: "loginAjax",
							data: {mid, mpassword},
							method: "post"
						}).done((data) => {
							//data = {result:"success"}
							//data = {result:"wrongMid"}
							//data = {result:"wrongMpassword"}
							
							const midError = $("#mid-error");
							const mpasswordError = $("#mpassword-error");
							midError.html("");
							mpasswordError.html("");
							
							if(data.result==="success") {
								//현재 페이지 전체를 다시 서버에서 받아오도록 함
								window.location.reload();
							} else if(data.result==="wrongMid") {
								midError.html("아이디 잘못됨");
							} else if(data.result==="wrongMpassword") {
								mpasswordError.html("비밀번호 잘못됨");
							}
						});
					}
					
					function logout() {
						$.ajax({
							url: "logoutAjax"
						}).done((data) => {
							//data = {result:"success"}
							//현재 페이지 전체를 다시 서버에서 받아오도록 함
							window.location.reload();
						});
					}
				</script>
		</div>
	</div>


	<div class="card">
		<div class="card-header">@SesseionAttributes를 이용한 다단계 입력처리</div>
		<div class="card-body">
			<a href="inputStep1" class="btn btn-danger btn-sm">입력 단계 진입</a>
		</div>
	</div>
	<!--카드 끝 -->


	<%@ include file="/WEB-INF/views/common/footer.jsp"%>
```







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