package jungol.etc_problom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class jun1836연속부분합찾기_답지봄 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int max = 0, N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		int sum = 0;
		StringTokenizer st = new StringTokenizer(br.readLine());
		int[] arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		for (int i = 0; i < arr.length; i++) {
			sum += arr[i];
			if(sum > max) {
				max =sum;
			}
			if(sum <0) {
				sum=0;
			}
		}
		System.out.println(max);
	}

}
