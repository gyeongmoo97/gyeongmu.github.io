package workspace_th.day06.fileEx;

import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		File file = null;
		byte[] fileName = new byte[100];
		String strName;
		System.out.println("file name = ");
		System.in.read(fileName);
		strName = new String(fileName).trim();
		//클라이언트가 실수로 공백이나 enter 넣는 경우를 위해 trim()
		
		file = new File(strName);
	}

}
