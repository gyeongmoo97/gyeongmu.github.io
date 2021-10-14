---
title: Day
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



```java
public static void main(String[] args) throws NumberFormatException, IOException {
    int n  = Integer.parseInt(br.readLine());
    StringTokenizer st;

    //점수 받아와서 클래스 리스트에 넣기
    ArrayList<Score> list = new ArrayList();
    for (int i = 0; i < n; i++) {
        st = new StringTokenizer(br.readLine());
        String name=st.nextToken();
        int kor = Integer.parseInt(st.nextToken());
        int eng = Integer.parseInt(st.nextToken());
        int math = Integer.parseInt(st.nextToken());
        list.add(new Score(name, kor, eng, math));
    }




    //출력
    StringBuilder sb = new StringBuilder();
    for(Score s : list) {
        sb.append(s.name).append("\n");
    }
    System.out.println(sb);

}
```



```java
class Score{
    String name;
    int kor;
    int eng;
    int math;

    Score(){

    }
```











```java
public static void main(String[] args) throws NumberFormatException, IOException {
    int n  = Integer.parseInt(br.readLine());
    StringTokenizer st;

    ArrayList<Score> list = new ArrayList();
    for (int i = 0; i < n; i++) {
        st = new StringTokenizer(br.readLine());
        String name=st.nextToken();
        int kor = Integer.parseInt(st.nextToken());
        int eng = Integer.parseInt(st.nextToken());
        int math = Integer.parseInt(st.nextToken());
        list.add(new Score(name, kor, eng, math));
    }

    Collections.sort(list, (s1,s2)->{
        if(s1.kor==s2.kor) {
            if(s1.eng==s2.eng) {
                if(s1.math==s2.math) {
                    return s1.name.compareTo(s2.name);
                }
                return s2.math-s1.math;
            }else {
                return s1.eng-s2.eng;
            }
        }else {
            return s2.kor-s1.kor;
        }
    });


    StringBuilder sb = new StringBuilder();
    for(Score s : list) {
        sb.append(s.name).append("\n");
    }
    System.out.println(sb);

}
```







 {% endraw %}