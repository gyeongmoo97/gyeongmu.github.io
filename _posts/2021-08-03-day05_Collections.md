---
title:  "day05_Collections"
excerpt: " 배운내용에 대하여 정리하여 본다. "

categories:
  - 일일 학습
tags:
  - [Blog, Java, Collections, Git]

toc: true
toc_sticky: true
 
date: 2021-08-03
last_modified_at: 2021-08-03
---
-잘못 이해한 부분이 있을 수 있습니다. 이상한 부분이 있다면 알려주세요

email : mwe226@naver.com

# 1. String
String 클래스를 통해 문자열을 저장할 수 있다.

문자열 생성 방법 2가지

1. 문자열 리터럴을 지정하여 생성
```java
String str = "hello"
```
2. String 클래스의 생성자를 통해 생성
```java
String str = new String(hello)
```

각각의 방식은 Stack 공간상에서 참조변수로 선언되는 방식이 다르다. 
그로 인하여 
```java
str1 == str2
```
== 연산자는 stack 영역에서 가지고 있는 값이 같은지 비교한다.
Stack 영역의 레퍼런스 변수는 heap 영역에 있는 문자를 가리키기 위해 주소 값을 가진다.
 
같은 문자열이여도 다른 객체니까 다른 객체를 가리키기 때문에 다른 주소를 가르키킨다. 그렇기 때문에 
 == 연산을 했을 때의 결과도 다르게 생성된다.

문자열 자체가 같음을 비교하고자 한다면 equls 메서드를 활용하는 것이 좋다.

```java
String.equls(str)
```


문자열의 특정 부분을 얻기 위해서는 String.subString 을 사용한다.
시작값은 포함되고 마지막값은 포함 되지 않는다.
String.substring(0,1);
맨 처음 한글자만 하고싶으면
 이렇게하면 0번째 문자만 쏙 나옴

```java
String.subString(index);
//인덱스 부터 끝까지 출력
String.substring(a,b);
// a부터 b직전까지 출력
```

특정 문자열을 교체하고 싶다면 replace 메서드를 사용 할 수 있다.

```java
String str;
syso(str.replace("문자열에 포함된 내용","대체할 문자열"));

// 값을 단순 출력하는게 아니라 변경하려면 아래 코드 처럼 해야한다.
str = str.replace("문자열에 포함된 내용","대체할 문자열"));

```

공백제거 trim()
```java
String str;
syso(str.trim());

// 공백 없는 값을 단순 출력하는게 아니라 문자열에 적용하려면 아래 코드 처럼 해야한다.
str = str.trim());

```

끊어읽기를 위해 split 메서드를 사용할 수 있다.
```java
String str = "hello/world/i/am/ gyeong/mu";
String[] str_arr =str.split("/");
//-> 단어 단위로 저장됨. 
String str = "hello world i am gyeong mu";
String[]  str_arr =str.split();

// default 값은 공백기준으로 끊어 읽는 것.
```

문자열의 위치를 검색하는 메서드는
indexOf
lastIndexOf가 있다.
```java
str.indexOf("a")
//앞에서부터 가장 먼저 등장하는 a의 인덱스를 반환
str.lastIndexOf("b")
//뒤에서 부터 가장 먼저 등장하는 b 의 인덱스를 반환
```

대, 소문자 변환
```java
str.toLowerCase("A")
//소문자로 변환
str.toUpperCase("b")
//대문자로 변환
```

문자열을 Char 형 배열로 만들어주는 메서드 Str.toCharArray();
```java
char[] arr = Str.toCharArray();
```

(참고 : https://changhozz.tistory.com/entry/String%ED%81%B4%EB%9E%98%EC%8A%A4%EC%9D%98-%ED%8A%B9%EC%A7%95)

# 2. StringBuilder, StringBuffer
둘 다 초기 용량이 16Byte 로 할당되어있다.

새로운 값을 넣은 뒤 주소값을 반환해 주는 hashcode를 사용하면
String 이라면 주소값이 변하지만 둘 다 주소가 변하지 않는다.

```java
StringBuilder sb = new StringBuilder("hello");

syso(sb.hashcode);
sb.append(world);
syso(sb.hashcode);
// 2개의 주소값이 같음
```

String 객체는 선언 이후 변경할 수 없다는 특징과 참보변수로 선언 되어서 heap 영역의 데이터를 가르키는 방식에서 메모리를 비효율적으로 사용하는 경향이 있다.

자주 변경하는 문자열에 대하여
> StringBuilder, StringBuffer

클래스를 활용하여 문자열을 선언하는 것이 좋을 수 있다. 이는 stack 공간에서 값이 저장되고 변경되는 기본 자료형 처럼 문자열을 다루게 되어 문자열을 변경하면서 생기는 비효율적인 메모리 처리를 줄일 수 있다.


StringBuilder, StringBuffer 둘의 차이는 스레드에서의 사용이라고한다.

# 3. final
1. 클래스에 대하여 final 선언한다면 그 클래스를 다른 클래스가 상속받을 수 없다.
```java
final class Ex{
    ~~~~~
    //상속 받을 수 없는 클래스
}
```
2. 변수에 final 선언이 된다면 변수 값 변경을 할 수 없다. 이 경우 반드시 초기화 해줘야한다.

```java
final int num =10;
// num은 변경할 수 없다.
```

3. 메서드에 final 선언이 된다면 그 메서드는 오버라이드 할수 없다.
```java
final void start(){
    //이 메서드는 오버라이드 불가능
}
```

# 4. 예외처리 방법
자바의 의무 예외처리 상황이 있다
네트워크 데이터베이스 thread I/O(입출력) file 등등

예외처리 방법은 다음과 같다.
1.	Try ~ catch (직접처리하는부분 – 권장사항)
2.	Throws : 위임
3.	Throw 예외 던지기

Try ~ catch 에 대한 예제
```java
	public static void main(String[] args) {
		System.out.println("su1, su2 input");
		Scanner sc = new Scanner(System.in);
		int su1 =sc.nextInt();
		int su2 =sc.nextInt();
		
		System.out.println("op(+, -, /, *)");
		char op = sc.next().charAt(0);
		int result=0;
	
		try {
			if(op =='+') {
				result = su1 + su2;
			}else if(op=='-') {
				result = su1 - su2;
			}else if(op=='/') {
				result = su1 / su2;
			}else if(op=='*') {
				result = su1 * su2;
			}
			
		}catch(Exception e) {
			System.out.println(e.getMessage()+"0으로 나눌 수 없습니다");
			e.printStackTrace();
		}

		System.out.println("-----------------------------");
		System.out.println(su1 + " "+ op+" " + su2 +" = "+result);
		
	}
    ```


Exception 클래스에서 다양항 예외상황에 대한 처리기능을 제공한다.

```java
Exception e
e.getMessage()
//에러에 대한 메시지를 출력
e.printStackTrace();
//단계적으로 몇번째 줄의 어디에서 에러가 뜨는지 알려줌
```

(참고 : https://docs.oracle.com/javase/8/docs/api/)

# 5.Warpper class
기본타입의 데이터를 객체로 취급해야하는 경우에 사용하기 위해 만들어진 클래스

int -> integer
long -> Long
char -> Character

<> 안에는 객체의 값이 위치해야하기 때문에 <> 안에 넣기위해서 사용하거나

특정 메서드 사용을 위해서 사용하기도 한다.
```java
N = Integer.parseInt("1234567");
N = Integer.toOctalString(123);
N = Integer.toHexString(456);
N = Integer.toString(789);
```

추가로 현업에서는 toString을 오버라이드 해서 display하는 용으로  쓴다고 한다.


# 6. Set
데이터의 index가 없음
 중복을 허용하지 않음.

 index를 통해 호출이 불가능함
Iterator, Foreach문 활용하여 호출해야함.

```java
Iterator it = set.iterator();
// iterator
while( tree.hasNext() ) {
    System.out.println(tree.next());
}
// for each
for (TreeSet tree_element : tree) {
			System.out.println(tree_element);
		}
```
## TreeSet
중복을 허용하지 않지만 오름차순으로 값을 자동 정렬해 준다.
?? 어떻게 역순으로 출력할까?
1. descendingIterator() : 내림차순으로 정렬된 Iterator를 리턴

2. descendingSet() : 내림차순으로 정렬된 NavigableSet 반환

```java
 TreeSet<Integer> scores = new TreeSet<Integer>();
        scores.add(new Integer(100));
        scores.add(new Integer(12));
        scores.add(new Integer(64));
        scores.add(new Integer(88));
        scores.add(new Integer(97));
        scores.add(new Integer(45));
 
        
        //내림차순 정렬
        NavigableSet<Integer> decendingSet = scores.descendingSet();
        
        for(Integer score : decendingSet){
            System.out.print(score+" ");
            
        }
        
        System.out.println();
        
        //오름차순 정렬
        NavigableSet<Integer> ascendingSet = decendingSet.descendingSet();
        for(Integer score : ascendingSet){
            System.out.print(score+" ");
            
        }
 
 
//*********************결과*******************//
100 97 88 64 45 12 
12 45 64 88 97 100 
//출처 : https://blog.naver.com/kimstcool01/220896128159

```

특정 범위의 값만 출력하는 방법
```java
 TreeSet<Integer> scores = new TreeSet<Integer>();
        scores.add(new Integer(100));
        scores.add(new Integer(12));
        scores.add(new Integer(64));
        scores.add(new Integer(88));
        scores.add(new Integer(97));
        scores.add(new Integer(45));
        
        
        NavigableSet<Integer> result = scores.headSet(88, false);
 
        System.out.println("88 미만인 점수 검색");
        System.out.println("===================");
        for(Integer score : result){
            System.out.println(score);
        }
        
        System.out.println();
        
        NavigableSet<Integer> result2 = scores.tailSet(88, true);
        System.out.println("88 이상인 점수 검색");
        System.out.println("===================");
        for(Integer score : result2){
            System.out.println(score);
        }
        
        System.out.println();
        
        NavigableSet<Integer> result3 = scores.subSet(50, true, 100, false);
        System.out.println("50 이상 100 미만 사이의 점수 검색");
        System.out.println("===================");
        for(Integer score : result3){
            System.out.println(score);
        }
 
    
//****************************결과
88 미만인 점수 검색  
===================
12
45
64
 
88 이상인 점수 검색
===================
88
97
100
 
50 이상 100 미만 사이의 점수 검색
===================
64
88
97

//출처 : https://blog.naver.com/kimstcool01/220896128159

 TreeSet<String> scores = new TreeSet<String>();
        scores.add("가구");
        scores.add("나라");
        scores.add("다리미");
        scores.add("라디오");
        scores.add("마법사");
        scores.add("바톤");
 
        NavigableSet<String> result = scores.subSet("나", true, "마", false);
        System.out.println("나 부터 라 까지의 단어 검색");
        System.out.println("===================");
        for(String score : result){
            System.out.println(score);
        }
 
//**********************결과

나 부터 라 까지의 단어 검색
===================
나라
다리미
라디오
//출처 : https://blog.naver.com/kimstcool01/220896128159

```


# 7. List
데이터의 index가 있음
중복 허용.

대표적인 3가지 자료구조

1.Vector

ArrayList와 동일한 메서드 사용 동일한 특성을 가짐, 스레드 사용, 출시한 시기에서 차이가 있음.

초기용량 10 byte를 가짐.

```java
Vector(120); 
//초기용량 120 으로 설정하여 할당가능
Vector(120, 50);
//초기용량을 120으로 설정 가득 차면 50씩 공간 확장
```
2.ArrayList

검색과 대량 데이터 삽입 삭제 장점이 있다. (주소상에서 붙어있기 때문에 검색에 유리함 + 중간에 데이터를 삽입하기 위해서 기존에 있던 데이터 뒤의 값을 다 밀어내야 함  )

3.LinkedList

데이터 삭제 삽입이 수월한 장점이 있다. 대신 검색이 느리다 ( List 의 시작에서 검색하고자 하는 대상이 나올 때 까지 다음 대상의 주소를 확인하고 이동 확인하고 이동을 반복하기 때문에)
# 8. Map
Key Value 형태로 값을 저장
Key는 중복을 허용하고 index가 없다.
Value는 중복을 허용한다. 

# 9. 자투리
사전순서

영어 대문자 > 영어소문자 >숫자> 한글

```java
Vector v1 = new Vector();
Vector<integer> v2 = new Vector();
//v1은 모든 자료형 들어감 (사용자 정의 자료형 포함)
//v2는 정수형 자료형만 들어감. 

```