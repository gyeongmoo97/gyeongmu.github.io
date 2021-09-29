---
title: 순차적 이밴트 처리
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

프로세스 실행중인 하나의 프로그램을 프로세스라고 한다.

프그램을 여러번 실행하면 여러개의 프로세스가 생성된다.

![image](https://user-images.githubusercontent.com/65274952/135186990-fae6d843-77d2-4cf1-8cd9-c0c6449b7c20.png)

여러개의 작업이 있어서 여러개의 프로세스가 진행되는 상황

- 멀티프로세스라고 한다.





![image](https://user-images.githubusercontent.com/65274952/135187073-af16d742-cc61-4602-8989-bb5a364466c9.png)



하나의 프로세스 안에 스레드가 여러개 만들어져서 스레드마다 다른 작업을 진행하고 있는 상태는 멀티 스레드라고 한다.



---



![image](https://user-images.githubusercontent.com/65274952/135187219-3bb3da04-3825-4fcc-849d-8d0b11bd42a6.png)

스레드는 코드의 흐름이라고 생각하면된다.

main 문이 실행되면 main은 위에서 아래로 실행되는데 

위의 코드의 흐름(점선이 ) 스레드라고 생각하면 된다.



![image](https://user-images.githubusercontent.com/65274952/135187404-5f4eba71-5286-42c8-829d-903983ffaf72.png)



메인 스레드가 다른 스레드를 만들어서 다른 작업을 시작하게 만드는 것을 구성 할 수 있다





모든 스레드가 종료되어야 프로세스가 종료된다. 하나의 스레드라도 끝나지 않으면 프로세스가 종료되지 않는다.

![image](https://user-images.githubusercontent.com/65274952/135187462-262bdbdd-7f6c-4306-a3fa-bdd3b95c3399.png)

아주 편리하지만 설계를 잘못한다면 프로그램 실행 안정성이 떨어진다.

(스레드가 종료되지 않아 무한히 프로세스가 실행 될 수 있다.)



![image](https://user-images.githubusercontent.com/65274952/135187516-7e8cfb48-5e7b-4279-9084-794c57ad5c03.png)

프로그램에서 병렬적으로 실행할 작업을 결정한다 ( 동시에 실행해야할 작업이 몇개인지 분석)



메인스레드에서 필요에 따라 용도별 스레드를 생성한다.

웹 어플리케이션에서 스레드를 만들 일은 적다.

was 차원에서 스레드 처리해준다.

예를들어 하나의 요청이하나의 스레드라면

10명이 요청하면 요청마다 스레드를 10개 만들어서 요청을 처리한다.



# 스레드 생성법

![image](https://user-images.githubusercontent.com/65274952/135187724-c4a5e4c4-1551-4b62-996f-4ec6ad9c9889.png)

스레드는 자바 표준 API이다.





![image](https://user-images.githubusercontent.com/65274952/135187831-7802fadb-a221-4d0d-9520-e4b401ae947b.png)

Runnable 이라는 인터페이스를 구현한 객체를 만들어서

스레드를 생성할 때 제공해 준다면 

스레드가 실행 될 때 task의 내용을 수행한다.



## runnable 작성법

![image](https://user-images.githubusercontent.com/65274952/135187955-e5a26ad4-4b84-4b9e-b40e-6bedc7a4e908.png)



스레드는 run()이라는 내용을 실행한다.



## 익명 구현 객체

![image](https://user-images.githubusercontent.com/65274952/135188046-72b6ff41-c6d7-4265-bee7-bcd229fd6880.png)

## 람다

![image](https://user-images.githubusercontent.com/65274952/135188088-f95fbf41-210c-4ce9-918e-c321f19925d5.png)





# 스레드의 시작

![image](https://user-images.githubusercontent.com/65274952/135188163-67ab72a5-19b9-4e5a-b880-b4c1844d5535.png)

스래드의 start 라는 메서드가 실행된다

 ### thread.run 으로 실행하면 main 스레드에서 가동된다. 무조건 thread.start로 실행하자





# 예제



##### thread.sleep하면 스레드가 잠시 쉰다.



```java
public class BeepTask implements Runnable {	
    public void run() {		
        Toolkit toolkit = Toolkit.getDefaultToolkit();	
        for(int i=0; i<5; i++) {		
            toolkit.beep();
            try { Thread.sleep(500); } catch(Exception e) {}
        }
    }
}

```





# 문제상황

```java
public static void main(String[] args) {
    Toolkit toolkit = Toolkit.getDefaultToolkit();	
    for(int i=0; i<5; i++) {		
        toolkit.beep();
        try { Thread.sleep(500); } catch(Exception e) {}
    }

    for(int i=0; i<5; i++) {
        System.out.println("띵");
        try { Thread.sleep(500); } catch(Exception e) {}
    }
}
```

단일 스레드는 위에서 아래로만 실행된다.

즉 

소리를 출력하고 그 뒤에 메시지를 출력한다.



소리를 출력하는 작업과 메시지를 출력하는 작업을 동시에 실행하려면 스레드가 필요하다.





# 방법1

```java
public static void main(String[] args) {
    //how1
    Runnable beepTask = new BeepTask();
    Thread thread = new Thread(beepTask);
    
    //스레드 실행
    thread.start();

    for(int i=0; i<5; i++) {
        System.out.println("띵");
        try { Thread.sleep(500); } catch(Exception e) {}
    }
}
```

![image](https://user-images.githubusercontent.com/65274952/135188919-38eb723a-fdcf-4e9e-8c56-518d76067b2f.png)

하나의 작업을 스레드로 실행한다면? 

2개의 스레드에서 작업이 일어나서 동시에 처리된다.



# 방법2 익명 구현객체

```java
public static void main(String[] args) {
    //how2
    Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            Toolkit toolkit = Toolkit.getDefaultToolkit();	
            for(int i=0; i<5; i++) {		
                toolkit.beep();
                try { Thread.sleep(500); } catch(Exception e) {}
            }
        }
    });

    //스레드 실행
    thread.start();

    for(int i=0; i<5; i++) {
        System.out.println("띵");
        try { Thread.sleep(500); } catch(Exception e) {}
    }
}
```



명시적으로 다른 곳에서 runnable 객체를 구현하여 작성하거나

runnable 을 익명구현객체로 처리하는 방법이 있다.



2번째 방법을 자주 사용한다.



# 스레드 생성 2번째 방법

이전에는 스레드에 작업내용을 runnable을 구현하여서 넣어줬는데

![image](https://user-images.githubusercontent.com/65274952/135189285-79bcbb61-19b7-4ae1-a3f7-ad637ca9eb96.png)

이제는 스레드를 상속받은 자식객체를 만들고 그 이전의 run 메서드를 재정의 하여서 스레드를 생성한다.



## 익명 자식 객체

![image](https://user-images.githubusercontent.com/65274952/135189314-951ecef7-bd97-4ade-a95f-3fcc9ff9b78c.png)

명시적으로 자식객체를 만드는 방법도 있지만



아래의 익명 자식객체를 만드는 경우가 더 많다.

스레드가 실행할 작업내용 run을 재정의 해야한다.



# 명시적 생성과 익명 자식생성

명시적으로 스레드의 자식 스레드 객체 생성
![image](https://user-images.githubusercontent.com/65274952/135189393-ad9661d7-5c29-4ca1-811b-46cb4a0758a0.png)

익명 스레드의 자식 스레드 객체 생성
![image](https://user-images.githubusercontent.com/65274952/135189373-4fb44281-142f-4e00-9bb0-3a93e253a432.png)



## 예제 

```java
public static void main(String[] args) {
    //how1 
    //직접 자식객체 클래스 생성하여 사용하기 
    //BeepThread()는 다른 곳에 생성함
    Thread thread = new BeepThread();

    thread.start();		

    for(int i=0; i<5; i++) {
        System.out.println("띵");
        try { Thread.sleep(500); } catch(Exception e) {}
    }
}
```





```java
public static void main(String[] args) {

    //how2
    //익명 자식객체 생성하고 바로 run 재정의하기
    Thread thread = new Thread() {
        @Override
        public void run() {		
            Toolkit toolkit = Toolkit.getDefaultToolkit();	
            for(int i=0; i<5; i++) {		
                toolkit.beep();
                try { Thread.sleep(500); } catch(Exception e) {}
            }
        }
    };


    thread.start();		

    for(int i=0; i<5; i++) {
        System.out.println("띵");
        try { Thread.sleep(500); } catch(Exception e) {}
    }
}
```



# 스레드의 이름

스레드는 각자 이름을 가지는 것을 알아야한다.

![image](https://user-images.githubusercontent.com/65274952/135189709-e2aba256-3008-440e-aa54-75e3a4c6aa9f.png)

스레드는 내부적으로 자동 생성된 이름을 가진다.

![image](https://user-images.githubusercontent.com/65274952/135189957-d34f6cdc-9415-442b-9c4c-d26ca3d947ea.png)

getName 과 setName으로 이름을 얻거나이름을 직접 지정해 줄 수 있다.



# 동시성과 병렬성 

![image](https://user-images.githubusercontent.com/65274952/135190147-e9a50243-fab4-4936-b889-5dc196b6e575.png)

코드 실행의 주체는 CPU 안의 core 이다.



## 동시성

core는 한번에 하나의 스레드를 실행시킬 수 있다.



core 는 스레드를 빠르게 교체하는 방식으로 여러개의 스레드를 동시에 실행 시킬 수 있다. (하지만 한번에 1개의 core는 1개의 스레드만 실행한다.)



하지만 스레드의 교체 주기가 아주 빠르기 때문에 동시에 실행하는 것 처럼 보인다. (실제로는 동시에 2개의 스레드를 실행하는 일은 없다)



## 병렬성

코어 하나당 하나의 스레드를 실행하는데 여러개의 코어가 있어서 스레드를 각각 실행하여 병렬적으로 실행 ( 진짜로 2개의 스레드가 동시에 실행 )





# 스레드가 실행되는 순서를 지정할 수 있나요?

![image](https://user-images.githubusercontent.com/65274952/135190680-20eae77e-61bb-45d6-b4f0-bdeb7b2251ad.png)

안된다.

 웹어플리케이션에서는 요청하나와  스레드 하나가 같다.

어떤 스레드가 먼저 실행되는지는 알 수 없다.



이벤트 선착순 처리 -> 1000개의 요청 발생 -> 하지만 1000개의 스레드 중 뭐가 먼저 실행될지 못정함.



웹에서의 처리방식은 동시성이다.

WAS도 운영체제에서 실행하기 때문에 

만약 서버의 PC가 8개의 코어가 있으면

10000개의 요청 -> 동시성으로처리 -> 하지만 동시에 하면서 내가 원하는 순서대로 스레드(요청) 실행시키기 불가능

어쩌지?



![image](https://user-images.githubusercontent.com/65274952/135190933-e2cb877d-9325-44cf-bbfb-60fdc3266f48.png)



스레드의 run 메서드를 조금 실행하고 다시 가져다 놓는것을 반복해서 스케쥴링한다.



![image](https://user-images.githubusercontent.com/65274952/135191011-d7ecbdc8-3d92-448a-8860-d451bee163b6.png)

우선순위가 높으면 많이 실행 시킬 수는 있겠지만

먼저 실행되는 것은 보장 할 수 없다.

우선순위 줄 수 있지만 그걸로 우선순위 순서대로 실행시키는것은 아니다.



# 문제상황에 관련한 주제



#

![image](https://user-images.githubusercontent.com/65274952/135191149-5438626f-f1aa-4832-9ef7-6676c6f556ea.png)



![image](https://user-images.githubusercontent.com/65274952/135191250-4b9ab154-839f-4edd-bff1-3492c734acd3.png)



![image](https://user-images.githubusercontent.com/65274952/135191570-0b363528-bba7-4f24-858f-c133c7684b04.png)

```http
INFO : com.mycompany.webapp.controller.Ch01Controller.content() - 실행1 
INFO : com.mycompany.webapp.controller.Ch01Controller.content() - http-nio-8080-exec-4
INFO : com.mycompany.webapp.controller.Ch01Controller.content() - 실행1 
INFO : com.mycompany.webapp.controller.Ch01Controller.content() - http-nio-8080-exec-1
INFO : com.mycompany.webapp.controller.Ch01Controller.content() - 실행1 
INFO : com.mycompany.webapp.controller.Ch01Controller.content() - http-nio-8080-exec-2
INFO : com.mycompany.webapp.controller.Ch01Controller.content() - 실행1 
INFO : com.mycompany.webapp.controller.Ch01Controller.content() - http-nio-8080-exec-3
INFO : com.mycompany.webapp.controller.Ch01Controller.content() - 실행1 
INFO : com.mycompany.webapp.controller.Ch01Controller.content() - http-nio-8080-exec-4
INFO : com.mycompany.webapp.controller.Ch01Controller.content() - 실행1 
INFO : com.mycompany.webapp.controller.Ch01Controller.content() - http-nio-8080-exec-8
INFO : com.mycompany.webapp.controller.Ch01Controller.content() - 실행1 
INFO : com.mycompany.webapp.controller.Ch01Controller.content() - http-nio-8080-exec-7
INFO : com.mycompany.webapp.controller.Ch01Controller.content() - 실행1 
INFO : com.mycompany.webapp.controller.Ch01Controller.content() - http-nio-8080-exec-6
INFO : com.mycompany.webapp.controller.Ch01Controller.content() - 실행1 
INFO : com.mycompany.webapp.controller.Ch01Controller.content() - http-nio-8080-exec-5
INFO : com.mycompany.webapp.controller.Ch01Controller.content() - 실행1 
INFO : com.mycompany.webapp.controller.Ch01Controller.content() - http-nio-8080-exec-9
INFO : com.mycompany.webapp.controller.Ch01Controller.content() - 실행1 
INFO : com.mycompany.webapp.controller.Ch01Controller.content() - http-nio-8080-exec-10
INFO : com.mycompany.webapp.controller.Ch01Controller.content() - 실행1 
INFO : com.mycompany.webapp.controller.Ch01Controller.content() - http-nio-8080-exec-1
```



1000명이 찰나에 요청을 한다면 순차적으로 처리 되리란 법이 없다. 

수많은 요청이 발생하면 병렬적으로 실행하는 것에 한계가 있다. 따라서 동시성으로 실행하기 때문에 실행순서 보장할 수 없다.

was 를 운영하는 PC의 스레드가 먼저 생성된 스레드를 먼저 실행하고 먼저 끝내리라는 보장이 없기 때문에.



특정 스레드가 먼저 실행되지 않고 먼저 끝나게 못한다.



10000개의 요청이 있다고 해도 미세한 시간 차이는 분명 존재한다 (물리적으로 그럴 수 밖에 없음)



# lock

![image](https://user-images.githubusercontent.com/65274952/135192265-8a38b7fa-29e3-4a0a-b6d6-64f64509423c.png)

테이블은 수정이 되고 있는 행을 잠궈버린다.

1000개 중에 한개가 작업한다면

999개는 잠금이 풀릴 때 까지 기다려야한다.



이후

999개중 한개가 작업하면

998개는 잠금이 풀릴 때 까지 기다려야 한다.



요청이 서로 lock을 얻으려고 요청이 무수히 밀려있으면 DB가 부담이된다.



# 해결법?

lock 을 최소화하면서 순차적으로 실행하는 방법은?



요청이 무수히 들어오는 것은 못막음



# queue

![image](https://user-images.githubusercontent.com/65274952/135194150-2c7f9645-5d5d-40f0-b591-0d5056de052f.png)

좌측은 무수한 요청이 접수되는과정

queue 에들어가는 것 부터가 들어온 순서(시간)이 된다.

Queue에 들어온 순서대로 10개만 쿠폰을 받고 나머지는 못받는다

 

![image](https://user-images.githubusercontent.com/65274952/135194387-e6bd9d2a-df04-41af-91be-601f2c64dc12.png)



10개만 db에 접근하고 나머지는 실패 이밴트 띄우면된다.



선착순은

queue 에 들어가는 순서를 기준으로 선착순이다.

 

![image](https://user-images.githubusercontent.com/65274952/135194397-91c7d40b-3ff8-4d54-8752-ce9c5e676b10.png)

스레드여러개로 처리하는게 아니라



![image-20210929114638372](C:\Users\mwe22\AppData\Roaming\Typora\typora-user-images\image-20210929114638372.png)

하나의 스레드의 Queue로 하나의 DB에 접근하는것





![image](https://user-images.githubusercontent.com/65274952/135194639-b6d3fad3-64da-4cb6-9241-873e0d48a484.png)



스레드풀에 작업처리요청 (이부분을 무수한 요청이라고 생각하면 됨)

q 에들어오는 순서로 선착순 구현



![image](https://user-images.githubusercontent.com/65274952/135194771-a2a1fb0f-489e-4897-a110-d39ee65451f3.png)





![image](https://user-images.githubusercontent.com/65274952/135194811-6bf8cb03-c57a-48c8-b999-60aa7e27f9ce.png)



submit 메서드는 큐에 작업을 하나 넣는 형태임

runnable callable 스레드가 실제 실행해야할 코드를 가지고 있는 객체다.



## runnable callable  차이

runnable 스레드가 작업을 처리하고 끝나는것 

callable 스레드가 작업을 처리하고나서 결과를 리턴해준다.

 

웹의 특징은 요청이들어오면 응답을 제공해야한다.

![image](https://user-images.githubusercontent.com/65274952/135195242-44f728d7-2677-40d1-923c-79a279b1e487.png)

작업을 관리해주는 스레드가 끝나야 뷰 이름을 리턴해 줄 수 있다.



![image](https://user-images.githubusercontent.com/65274952/135195485-795d1d37-c6a5-43c7-9062-e461f7b02864.png)





작업결과의 성공 실패를 받고 그것에 따라서 뷰이름을 결정해서 리턴해야하면 

runnable 말고 callable 사용해야한다.



![image](https://user-images.githubusercontent.com/65274952/135195665-6b3b82ec-8858-470f-a9f1-d1cdf49b0726.png)

퓨처 객체의 get은 착업처리가 완료 될 때 까지 block 상태가 됨



T0 스레드의 큐에 있는 작업은 Callable 이 submit 으로 T1 스레드로 보내진다. T1 스레드가 작업을 마치면

그 내용을 get 으로 받아서 적절한 view 를 리턴?



![image](https://user-images.githubusercontent.com/65274952/135197184-7b264425-bb5e-47d8-a391-331914dc9e76.png)

큰거는 큐에 넣는것

작은거는 큐에서 빼와서 처리하는거

다른 스레드임





![image](https://user-images.githubusercontent.com/65274952/135197689-43ad079e-c4e4-4cb6-9592-5e33b90bb65c.png)

바깥 스레드는
get 에서 대기하다가 대기풀리면 마저 처리

![image](https://user-images.githubusercontent.com/65274952/135197719-b743db27-0fe6-4419-af89-b32f9c867e66.png)

result 값이 return 이 되면 그때서야 대기가 풀리고 결과 view 를 리턴한다.





 {% endraw %}





