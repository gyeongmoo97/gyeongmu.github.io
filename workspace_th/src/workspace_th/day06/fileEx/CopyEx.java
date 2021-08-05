package workspace_th.day06.fileEx;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class CopyEx {

	public static void main(String[] args) throws IOException {
		File src = new File("C:\\알고리즘\\heoolo.txt");
		File dist = new File("C:\\Users\\mwe22\\OneDrive - 서울과학기술대학교\\2021-2학기");
		
		FileInputStream fis = null;
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		//stream 은 바이트 단위로 처리해서 속도가 느림
		
		try {
			fis = new FileInputStream(src); //파일 입력 스트림 연결
			fos = new FileOutputStream(dist);//파일 출력바이트 스트림 연결
			bis = new BufferedInputStream(fis);//버퍼 입력 스트림 연결
			bos = new BufferedOutputStream(fos);//버퍼 출력 스트림 연결
			
			
		}catch (Exception e) {
			System.out.println("파일 복사 오류 발생!");
		}finally {
			bis.close(); bos.close(); fis.close(); fos.close();
		}
		// 복사할 원본

	}

}
