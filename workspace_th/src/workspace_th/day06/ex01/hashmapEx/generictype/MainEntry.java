package workspace_th.day06.ex01.hashmapEx.generictype;

import java.io.IOException;
import java.io.InputStream;

public class MainEntry{

	public static void main(String[] args) throws IOException {
		InputStream is = System.in;
		
		System.out.println("단일문자 입력 요망 : ");
		int num = is.read();
		
		System.out.println(num);
		System.out.println((char)num);
	}

}
