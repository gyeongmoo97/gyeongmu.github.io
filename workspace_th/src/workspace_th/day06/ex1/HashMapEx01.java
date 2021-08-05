package workspace_th.day06.ex1;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class HashMapEx01 {
	public static void main(String[] args) {
//		//Map<K, V> = map1 = new HashMap();
		Map map = new HashMap();
		
		map.put("kosa", "th2");
		map.put("bitcamp", "th129");
		map.put("Kosta", 1234);
		map.put("kosa", "th2");
		map.put(1, "th2");
		
		System.out.println(map.size());
		System.out.println(map);
		//map의 key는 중복허용하지 않으므로 4개 나올거다.
		
		Set set = map.entrySet();
		Iterator it = set.iterator();
		
//		while(it.hasNext()) {
//			System.out.println(it.next());
//		}
		
		while(it.hasNext()) {
			Map.Entry e = (Map.Entry)it.next();
			System.out.println("key : "+ e.getKey());
			System.out.println("val : "+ e.getValue());
		}
		Map<String, Integer> map1 = new HashMap();
		map1.put("김김김", 170);
		map1.put("최초최", 171);
		map1.put("박박박", 178);
		map1.put("황황황", 174);
		map1.put("수수수", 164);
		
//		Iterator it2 = map.entrySet();
		Iterator it2 = map1.entrySet().iterator();
		System.out.println("-------------");
		while(it2.hasNext()) {
			Map.Entry e = (Map.Entry)it2.next();
			System.out.println("key : "+ e.getKey());
			System.out.println("val : "+ e.getValue());
		}
		System.out.println("__________________");
		Collection values = map1.values();
		it = values.iterator();
		while(it.hasNext()) {
			int i = (int)it.next();
			System.out.println(i);
		}
		
		
		System.out.println("가장 큰 val"+Collections.max(values));
		System.out.println("가장 작은 val"+Collections.min(values));
		
		if(map1.containsKey("박박박")){
			System.out.println("1hello");
		}else {
			System.out.println("1none");
		}
		if(map1.containsKey("박박박1")){
			System.out.println("2hello");
		}else {
			System.out.println("2none");
		}
	}
}
