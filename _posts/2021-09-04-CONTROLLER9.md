

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

```jsp
<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		FileUpload & FileDownload
	</div>
	<div class="card-body">
		<div class="card">
			<div class="card-header">
				Form 태그를 이용한 FileUpload
			</div>
			<div class="card-body">
				<form method="post" enctype="multipart/form-data" action="fileupload">
					<div class="form-group">
						<label for="title">File Title</label> 
						<input type="text" class="form-control" id="title" name="title" placeholder="제목">
					</div>
					<div class="form-group">
						<label for="desc">File Description</label> 
						<input type="text" class="form-control" id="desc" name="desc" placeholder="설명">
					</div>
					<div class="form-group">
					    <label for="attach">Example file input</label>
					    <input type="file" class="form-control-file" id="attach" name="attach">
				  	</div>
				  	<button class="btn btn-info btn-sm">Form 파일 업로드</button>
				  	<a href="javascript:fileupload()" class="btn btn-info btn-sm">AJAX 파일 업로드</a>
				</form>
			</div>
			<script>
				function fileupload() {
					//입력된 정보를 얻기
					const title = $("#title").val();
					const desc = $("#desc").val();
					const attach = document.querySelector("#attach").files[0];
					
					//Multipart/form-data
					const formData = new FormData();
					formData.append("title", title);
					formData.append("desc", desc);
					formData.append("attach", attach);
					
					//Ajax로 서버로 전송
					$.ajax({
						url: "fileuploadAjax",
						method: "post",
						data: formData,
						cache: false,
						processData: false,
						contentType: false
					}).done((data) => {
						console.log(data);
						if(data.result === "success") {
							window.alert("파일 전송이 성공됨");
						}
					});
				}
			</script>
		</div>
	
		<div class="card">
			<div class="card-header">
				File Downlaod
			</div>
			<div class="card-body">
				<a href="filedownload?fileNo=1"
				   class="btn btn-info btn-sm">파일 다운로드</a>
				<hr/>
				<img src="filedownload?fileNo=1" width="200px"/>
			</div>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
```



```java
package com.mycompany.webapp.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Date;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/ch09")
public class Ch09Controller {
	private static final Logger logger = LoggerFactory.getLogger(Ch09Controller.class);
	
	@RequestMapping("/content")
	public String content() {
		return "ch09/content";
	}
	
	@PostMapping("/fileupload")
	public String fileUpload(String title, String desc, MultipartFile attach) throws Exception {
		logger.info("실행");
		
		//문자 파트 내용 읽기
		logger.info("title: " + title);
		logger.info("desc: " + desc);
		
		//파일 파트 내용 읽기
		logger.info("file originalname: " + attach.getOriginalFilename());
		logger.info("file contenttype: " + attach.getContentType());
		logger.info("file size: " + attach.getSize());
		
		//파일 파트 데이터를 서버의 파일로 저장
		String savedname = new Date().getTime() + "-" + attach.getOriginalFilename();
		File file = new File("C:/hyndai_it&e/uploadfiles/" + savedname);
		attach.transferTo(file);
		
		return "redirect:/ch09/content";
	}
	
	@PostMapping(value="/fileuploadAjax", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String fileUploadAjax(String title, String desc, MultipartFile attach) throws Exception {
		logger.info("실행");
		
		//문자 파트 내용 읽기
		logger.info("title: " + title);
		logger.info("desc: " + desc);
		
		//파일 파트 내용 읽기
		logger.info("file originalname: " + attach.getOriginalFilename());
		logger.info("file contenttype: " + attach.getContentType());
		logger.info("file size: " + attach.getSize());
		
		//파일 파트 데이터를 서버의 파일로 저장
		String savedname = new Date().getTime() + "-" + attach.getOriginalFilename();
		File file = new File("C:/hyndai_it&e/uploadfiles/" + savedname);
		attach.transferTo(file);
		
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("result", "success");
		jsonObject.put("savedname", savedname);
		String json = jsonObject.toString();
		
		return json;
	}
	
	//응답 바디의 데이터 형식 고정
	//toByteArray()에 리턴하는 배열의 길이 문제
	/*@GetMapping(value="/filedownload", produces="image/jpeg")
	@ResponseBody
	public byte[] filedownload(String savedname) throws Exception {
		String filePath = "D:/2021-hyndai-it&e/upload_files/" + savedname;
		InputStream is = new FileInputStream(filePath);
		byte[] data = IOUtils.toByteArray(is);
		return data;
	}*/
	
	@GetMapping("/filedownload")
	public void filedownload(
			int fileNo, 
			HttpServletResponse response,
			@RequestHeader("User-Agent") String userAgent) throws Exception {
		//fileNo를 이용해서 DB에서 파일 정보를 가져오기
		String contentType = "image/jpeg";
		String origianFilename = "bg2.jpg";
		String savedName = "1630809940836-bg2.jpg";
		
		//응답 바디의 데이터의 형식 설정
		response.setContentType(contentType);
		
		//브라우저별로 한글 파일명을 변환
		if(userAgent.contains("Trident") || userAgent.contains("MSIE")) {
			//IE일 경우
			origianFilename = URLEncoder.encode(origianFilename, "UTF-8");
		} else {
			//크롬, 엣지, 사라피일 경우
			origianFilename = new String(origianFilename.getBytes("UTF-8"), "ISO-8859-1");
		}
		
		//파일을 첨부로 다운로드하도록 설정
		response.setHeader("Content-Disposition", "attachment; filename=\"" + origianFilename + "\"");
		
		//파일로부터 데이터를 읽는 입력스트림 생성		
		String filePath = "C:/hyndai_it&e/uploadfiles/" + savedName;
		InputStream is = new FileInputStream(filePath);
		
		//응답 바디에 출력하는 출력스트림 얻기
		OutputStream os = response.getOutputStream();
		
		//입력스트림 -> 출력스트림
		FileCopyUtils.copy(is, os);
		is.close();
		os.flush();
		os.close();
	}
}

```





 {% endraw %}