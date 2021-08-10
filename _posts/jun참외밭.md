---
title: 참외밭
layout: single
author_profile: true
read_time: true
comments: true
share: true
related: true
categories:
- DB
description: .

meta_keywords: java,static,memory
last_modified_at: '2021-08-10 12:00:00 +0800'
toc: true
toc_sticky: true
toc_label: 목차
---








```java
package jungol.etc_problom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class jun2259참외다시 {
	static int board[][] = new int[1200][1200];
	static boolean visited[][] = new boolean[1200][1200];
	static int x = 600, y = 600;
	static int sum;
	static int[] dx = { 1, -1, 0, 0 };
	static int[] dy = { 0, 0, 1, -1 };
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws NumberFormatException, IOException {
		int n = Integer.parseInt(br.readLine());
		// 그리는 파트
		for (int j = 0; j < 6; j++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int direct = Integer.parseInt(st.nextToken());
			int dist = Integer.parseInt(st.nextToken());
//			sum += dist;
			if (direct == 1) {
				for (int i = 0; i <= dist; i++) {
					board[x + i][y] = 1;
//					visited[x + i][y] = true;
				}
				x += dist;
			} else if (direct == 2) {
				for (int i = 0; i <= dist; i++) {
					board[x - i][y] = 1;
//					visited[x - i][y] = true;
				}
				x -= dist;
			} else if (direct == 3) {
				for (int i = 0; i <= dist; i++) {
					board[x][y - i] = 1;
//					visited[x][y - i] = true;
				}
				y -= dist;
			} else if (direct == 4) {
				for (int i = 0; i <= dist; i++) {
					board[x][y + i] = 1;
//					visited[x][y + i] = true;
				}
				y += dist;
			}
		}

		// 도형안에 한 점을 기준으로 bfs 돌린다
		// 나가려고 하면 멈춤

		Loop1: for (int i = 0; i < 1200; i++) {
			for (int j = 0; j < 1200; j++) {
				if (board[j][i] == 1) {
					if (board[j + 1][i] == 1) {
						break;
					} else {
						bfs((j + 2), i);
						break Loop1;
					}
				}
			}
		}

		for (int i = 0; i < 1200; i++) {
			for (int j = 0; j < 1200; j++) {
				if (board[j][i] == 1) {
					sum++;
				}
			}
		}
//		bfs(590,600);
		sum --;
//		System.out.println(sum);
//		System.out.println(sum * n);
		System.out.println((sum * n)/2);
	}

	private static void bfs(int i, int i2) {
		visited[i][i2] = true;
		board[i][i2] = 1;
		Queue<int[]> q = new LinkedList<int[]>();
		q.add(new int[] { i, i2 });
		while (!q.isEmpty()) {
			int[] arr = q.poll();
			int x = arr[0];
			int y = arr[1];
			for (int j = 0; j < 4; j++) {
				int nx = x + dx[j];
				int ny = y + dy[j];

				if (nx >= 1200 || ny >= 1200 || nx < 0 || ny < 0) {
					continue;
				} else if (board[nx][ny] == 1) {
					continue;
				} else if (board[nx][ny] == 0) {
					board[nx][ny] = 1;
					sum++;
					q.add(new int[] { nx, ny });
				}

			}

		}

	}

}

//for (int i = 0; i < board.length; i++) {
//for (int j = 0; j < board.length; j++) {
//	if(board[j][i]==1) {
//		System.out.print("★");
//		sum++;
//	}
////	else {
////		System.out.print("☆");
////	}
//}
//}
//System.out.println(sum);

```



