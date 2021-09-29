---
title: Comparator
layout: single
author_profile: true
read_time: true
comments: true
share: true
related: true

toc: true
toc_sticky: false
toc_label: 목차
---



 {% raw %}

 

Comparator 의 compare 메서드를 오버라이드 하여서 대소관계를 직접 정해줄 수 있다.



a=5 일때

a 와 7 이라면 a가 작으니  - 값을 반환 (즉 < 는 음수)

a 와 3이면 a가 크니 + 값을 반환 (즉 > 는 양수)



return 할 값을

앞-뒤 (오름차순)

뒤-앞(내림차순)이 된다.

5와 7 이라면

5-7 이라 음수가 나오는데 

음수는 

5 < 7 이라는 의미로 작동한다.



만약에 

5,7 을 인자로 받았는데

7-5를 진행하여 양수를 return 하면

양수는

5>7로 작동한다.

즉 큰수에서 작은 수 순으로 배열한다.





참고 : https://st-lab.tistory.com/110

```java
Arrays.sort(arr, new Comparator<int[]>() {		
	@Override
	public int compare(int[] e1, int[] e2) {
		if(e1[0] == e2[0]) {		// 첫번째 원소가 같다면 두 번째 원소끼리 비교
			return e1[1] - e2[1];
		}
		else {
			return e1[0] - e2[0];
		}
	}
});
```



```java
Arrays.sort(arr, (e1, e2) -> {
	if(e1[0] == e2[0]) {
		return e1[1] - e2[1];
	}
	else {
		return e1[0] - e2[0];
	}
});
```





 {% endraw %}