package jungol.etc_problom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class jun1671_색종이중 {
	static int[][] board=new int[101][101];
	static int N, cnt;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			for (int j = 0; j <8; j++) {
				for (int j2 = 0; j2 < 8; j2++) {
					board[x+1+j][y+1+j2] -= 101;
				}
			}
			for (int j = 0; j <= 9; j++) {
				board[x+j][y] += 1;
				board[x][y+j+1] += 1;
				board[x+10][y+j+1] += 1;
				board[x+j][y+10] += 1;
				
				
				
			}
			
		}
//		
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if(board[j][i]>=1) {
					cnt++;
					System.out.print(board[j][i]);
				}else {
					System.out.print(0);
				}
			}System.out.println();
		}
		System.out.println(cnt);
	}

}


//훑기는 요철이 심해지면 각 면에서 출발한 직선이 안닿는 곳이 있어서 폐기
//4 방향에서 바라봤을 때 true 가 나오면 종료
////오른쪽부터 왼쪽으로 훑기
//for (int j = 0; j <= 100; j++) {
//	for (int j2 = 0; j2 <= 100; j2++) {
//		if(board[100-j2][100-j]) {
//			cnt++;
//			break;
//		}
//	}
//}
//// 왼쪽에서 오른쪽 훑기
//for (int j = 0; j <= 100; j++) {
//	for (int j2 = 0; j2 <= 100; j2++) {
//		if(board[0+j2][0+j]) {
//			cnt++;
//			break;
//		}
//	}
//}
//
////위에서 아래
//for (int j = 0; j <= 100; j++) {
//	for (int j2 = 0; j2 <= 100; j2++) {
//		if(board[100-j][100-j2]) {
//			cnt++;
//			break;
//		}
//	}
//}
////아래에서 위
//for (int j = 0; j <= 100; j++) {
//	for (int j2 = 0; j2 <= 100; j2++) {
//		if(board[0+j][0+j2]) {
//			cnt++;
//			break;
//		}
//	}
//}

