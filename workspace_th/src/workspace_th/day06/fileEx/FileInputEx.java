package workspace_th.day06.fileEx;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class FileInputEx {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		try {
			OutputStream os = new FileOutputStream("test.txt");  // 상대경로
			
			while( true ) {
				System.out.println("문자열 입력 요망 : ");
				String str = sc.nextLine() + "\r\n";
				
				if( str.equalsIgnoreCase("EXIT\r\n")) break;// if( str.toUpperCase().equals("EXIT\r\n")) break;
				
				os.write(str.getBytes());  // 읽어들인 문자 str의 길이(getBytes())만큼 써주세요
				System.out.println(str);  // 화면출력
			} // end while
			
			System.out.println("test.txt save success!!!");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			System.out.println("정상 처리 되던 비정상처리 되던 항상 실행된다.");
		} // end try
	}
}






