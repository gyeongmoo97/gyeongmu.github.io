package workspace_th.day06.ex1.QUIZ1;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class mapScore {

	public static void main(String[] args) {
		Map<String, Integer> map = new HashMap();
		Scanner sc = new Scanner(System.in);
		Loop1 :
		while(true) {
			System.out.println("학생이름을 입력하세요");
			String name = sc.next();
			System.out.println("학생 성적을 입력하세요");
			int score = sc.nextInt();
			
			if(map.containsKey(name)) {
				System.out.println("이름 중복 덮어 씁니까? (y/n)");
				String yOrN = sc.next();
				if(yOrN.equals("n") || yOrN.equals("N")) {
					continue;
				}
			}
			map.put(name, score);
			
			while(true) {
				System.out.println("계속 입력하겠습니까? (y/n)");
				String yOrN = sc.next();
				if(yOrN.equals("n") || yOrN.equals("N")) {
					break;
				}else if(yOrN.equals("y") || yOrN.equals("Y")) {
					break Loop1;
				}else {
					System.out.println("잘못된 입력입니다. y 또는 n 을 입력하세요");
				}
			}
			
			
			
			
			
		}
		
		
	
	
	}

}
