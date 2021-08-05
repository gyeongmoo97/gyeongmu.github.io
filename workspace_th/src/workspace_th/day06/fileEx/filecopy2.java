package workspace_th.day06.fileEx;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class filecopy2 {

	public static void main(String[] args) throws IOException {
		URL url = new URL("https://www.google.com/webhp?hl=en&sa=X&ved=0ahUKEwim3L7C9JbyAhUJC94KHa5kBsMQPAgI");
		//net 객체 예외처리 필요
		InputStream is = url.openStream();//읽기 객체여서 예외처리 필요
		OutputStream os = new FileOutputStream("googleLogo.jpg"); //쓰기 객체 생성
		
		byte[] buffer = new byte[1024];
		
		while(true) {
			int inputData = is.read(buffer);
			if(inputData == -1) break;
			os.write(buffer, 0, inputData);
			
		}
		
		is.close(); os.close();
		System.out.println("성공");
		

	}

}
