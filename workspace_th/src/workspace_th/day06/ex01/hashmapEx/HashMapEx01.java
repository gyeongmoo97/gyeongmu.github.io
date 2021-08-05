package workspace_th.day06.ex01.hashmapEx;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public class HashMapEx01 {
	public static void main(String[] args) {
		Map map = new HashMap();
		
		map.put("kosa", "th2");
		map.put("bitcamp", "129");
		map.put("kosta", 1234);
		map.put("kbs", "th2");
		
		System.out.println("요소 개수: " + map.size());
		System.out.println(map);
		
//		Set set = map.entrySet();
//		Iterator it = set.iterator();
		Iterator it = map.entrySet().iterator();
		System.out.println("=========================");
		while( it.hasNext() ) {
			//System.out.println(it.next());  // key=value
			Map.Entry e =   (Map.Entry)it.next();
			System.out.println("회사명 : " + e.getKey() );
			System.out.println("기수 : " + e.getValue());
		}
		
		System.out.println("=========================");
		Map<String, Integer> m = new HashMap();
		m.put("김연아", 170);
		m.put("홍길동", 165);
		m.put("박태환", 177);
		m.put("전두환", 183);
		m.put("박정희", 155);
		
		it= m.entrySet().iterator();
		System.out.println("시험응시자 수 : " + m.size() + "명");
		System.out.print("참가자 이름 : ");
		while( it.hasNext() ) {
			Map.Entry e =   (Map.Entry)it.next();
			System.out.print( e.getKey() + "\t");
		}
		
		System.out.print("\n\n참가자 평균 키 : ");
		it= m.entrySet().iterator();
		Integer sum = 0;
		while( it.hasNext() ) {
			Map.Entry e =   (Map.Entry)it.next();
			 sum += (Integer)e.getValue();
		}
		System.out.print( sum / m.size() );
		System.out.println();
		
		Collection values = m.values();
		it = values.iterator();
		while( it.hasNext() ) {
			//Integer i = (Integer)it.next();
			System.out.println(it.next());
		}

		System.out.println("가장 큰 사람 : " +  Collections.max(values));
		System.out.println("가장 작은 사람 : " + Collections.min(values));
		
		System.out.println("******************************");
		if( m.containsKey("김연아")) System.out.println("연아 학생 있음");
		else System.out.println("연아 학생 없음");
	}

}


















