package workspace_th.day06.ex01.brQuiz;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


//문제 연산자1개 입력 정수 2개입력 사칙연산 
public class MainEntriy {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws Exception {
		while(true) {
			System.out.print("연산자를 입력하세요 (+, - , /, *) \n op = ");
			String str = br.readLine().trim();
			if(str.equals("+")) {
				plus();
				end();
			}else if(str.equals("-")) {
				minus();
				end();
			}else if(str.equals("/")) {
				div();
				end();
			}else if(str.equals("*")) {
				mul();
				end();
			}else {
				System.out.print("잘못된 입력입니다 다시 입력 해주세요");
			}
			
		}
		
	}
	public static void plus() throws NumberFormatException, IOException {
		System.out.print("su1 = ");
		int N = Integer.parseInt(br.readLine());
		System.out.print("su2 = ");
		int M = Integer.parseInt(br.readLine());
		System.out.println("_______________________");
		System.out.println("result > "+ N +" + "+ M +" = "+ N+M); 
		System.out.println("_______________________");
	}
	
	public static void minus() throws NumberFormatException, IOException {
		System.out.print("su1 = ");
		int N = Integer.parseInt(br.readLine());
		System.out.print("su2 = ");
		int M = Integer.parseInt(br.readLine());
		System.out.println("_______________________");
		System.out.println("result > "+ N +" - "+ M +" = "+ (N-M)); 
		System.out.println("_______________________");
	}
	
	public static void div() throws Exception {
		System.out.print("su1 = ");
		int N = Integer.parseInt(br.readLine());
		System.out.print("su2 = ");
		int M = Integer.parseInt(br.readLine());
		System.out.println("_______________________");
		try {
			System.out.println("result > "+ N +" - "+ M +" = "+ (N/M)); 
		}catch (Exception e) {
			System.out.println("0으로는 나눌 수 없습니다.");
		}{
			
		}
		
		System.out.println("_______________________");
	}
	
	public static void mul() throws NumberFormatException, IOException {
		System.out.print("su1 = ");
		int N = Integer.parseInt(br.readLine());
		System.out.print("su2 = ");
		int M = Integer.parseInt(br.readLine());
		System.out.println("_______________________");
		System.out.println("result > "+ N +" - "+ M +" = "+ (N*M)); 
		System.out.println("_______________________");
	}
	public static void end() throws IOException {
		System.out.print("계산을 종료합니까? (y or n)");
		String str = br.readLine();
		if(str.equals("y") ||(str.equals("Y"))){
			System.exit(0);
		}else if(str.equals("n") ||(str.equals("N"))){
			System.out.println("\n");
			System.out.print("___________연산 다시 시작____________\n");
		}
	}

}
