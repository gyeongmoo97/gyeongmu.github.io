package jungol.etc_problom;

import java.util.Scanner;

public class 참외밭_1 {

	public static void main(String[] args) {
		solve();
	}

	static private void solve() {
		Scanner sc = new Scanner(System.in);
	    int k = sc.nextInt();
	    int[] array = new int[6];
	    int w = 0;
	    int h = 0;
	    int ww = 0;
	    int hh = 0;
	    for (int i = 0; i < 6; i++) {
	        sc.nextInt();
	        array[i] = sc.nextInt();
	    }
	 
	    // Get, max width and height
	    for (int i = 0; i < 6; i++) {
	        if (i % 2 == 0) {
	            if (w < array[i]) {
	                w = array[i];
	            }
	        } else {
	            if (h < array[i]) {
	                h = array[i];
	            }
	        }
	    }
	 
	    // 한 변을 기준으로 앞 뒤 변의 길이의 합이 길이와 같다면 파인 지점
	    for (int i = 0; i < 6; i++) {
	        if (i % 2 == 0) {
	            if (h == array[(i + 5) % 6] + array[(i + 1) % 6]) {
	                ww = array[i];
	            }
	        } else {
	            if (w == array[(i + 5) % 6] + array[(i + 1) % 6]) {
	                hh = array[i];
	            }
	        }
	    }
	    System.out.println((w * h - ww * hh) * k);
	}


}