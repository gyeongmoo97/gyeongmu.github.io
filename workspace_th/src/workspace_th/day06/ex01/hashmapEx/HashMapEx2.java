package workspace_th.day06.ex01.hashmapEx;

import java.util.*;

public class HashMapEx2 {
	public static void main(String[] args) {
		HashMap<String, Integer> map = new HashMap<String, Integer>(); 
		
		map.put("김자바", 90);
		map.put("이순신", new Integer(900));
		map.put("전두환", new Integer(190));
		map.put("박정희", new Integer(1000));
		map.put("김자바", new Integer(80));
		
		Set set = map.entrySet();
		//System.out.println(set);
		Iterator it = set.iterator();
		
		while( it.hasNext() ) {
			Map.Entry  e = (Map.Entry)it.next(); 
			System.out.println("이름 : " + e.getKey() + ", 점수 : " + e.getValue());
		} // while end
		
		
		set = map.keySet();
		System.out.println("참가자 명단 : " + set);
		
		Collection value =  map.values(); 
		System.out.println(value);
		
		it = value.iterator();
		
		int total = 0;
		
		while( it.hasNext() ) {
			Integer i = (Integer)it.next();
			total += i.intValue();
		} // while end
		
		System.out.println("총점 : " + total);
		System.out.println("평균 : " + (float)total / set.size() );
		System.out.println("최고점수 : " + Collections.max(value));
		System.out.println("최저점수 : " + Collections.min(value));
	}
}




