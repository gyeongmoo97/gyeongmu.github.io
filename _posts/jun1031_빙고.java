package jungol.etc_problom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class jun1031_빙고 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static boolean[][] bingo = new boolean[5][5];

	public static void main(String[] args) throws IOException {
		Map<Integer, Integer> map = new HashMap();
		// map 에 내가 작성한 빙고판 저장
		for (int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				map.put(Integer.parseInt(st.nextToken()), i * 5 + j);
			}
		}
		//
		for (int i = 0; i < 5; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 5; j++) {
				int position = map.get(Integer.parseInt(st.nextToken()));
				bingo[(position) % 5][(position) / 5] = true;
				// bingo
//				for (int k = 0; k < 5; k++) {
//					for (int k2 = 0; k2 < 5; k2++) {
//						if(bingo[k2][k]) {
//							System.out.print("★");
//						}else {
//							System.out.print("☆");
//						}
//					}
//					System.out.println();
//				}
//				System.out.println("______________________");
//				System.out.println("______________________");
				if (i > 1) {
					// 가로방향 확인
					int chk = 0;
					Loop1: 
					for (int k = 0; k < 5; k++) {
						for (int k2 = 0; k2 < 5; k2++) {
							if (!bingo[k2][k]) {
								continue Loop1;
							}
						}
						chk++;
						if (chk == 3) {
							System.out.println(i*5+j+1);
							return;
						}
					}
					// 세로방향 확인
					Loop1: 
					for (int k = 0; k < 5; k++) {
						for (int k2 = 0; k2 < 5; k2++) {
							if (!bingo[k][k2]) {
								continue Loop1;
							}
						}
						chk++;
						if (chk == 3) {
							System.out.println(i*5+j+1);
							return;
						}
					}
					//대각선 확인
					for (int k = 0; k < 5; k++) {
						if(!bingo[k][k]) {
							break;
						}
						if(k==4&&bingo[k][k]) {
							chk++;
							if (chk == 3) {
								System.out.println(i*5+j+1);
								return;
							}
						}
					}
					
					for (int k = 0; k < 5; k++) {
						if(!bingo[k][4-k]) {
							break;
						}
						if(k==4&&bingo[k][4-k]) {
							chk++;
							if (chk == 3) {
								System.out.println(i*5+j+1);
								return;
							}
						}
					}
					
					
					

				}
			}
		}

	}

}
