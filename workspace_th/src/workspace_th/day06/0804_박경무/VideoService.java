package workspace_th.day06.quiz1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class VideoService {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	// 비디오 관련 모든 내용 출력
	// key는 비디오 이름
	// value는 비디오에 관한 모든 값 장르 대여여부 대여자 대여일자

	static Map<String, ArrayList> map = new HashMap();


	// 비디오 추가
	public void addVod(Map map) throws IOException {
		System.out.println("추가할 비디오 이름을 입력해 주세요");
		String title = br.readLine();
		if (map.containsKey(title)) {
			System.out.println("이미 같은 이름의 비디오가 존재합니다");
			// 그 비디오 정보 출력
			return;
		} else {
			ArrayList list = new ArrayList();
			System.out.println("장르를 입력해주세요");
			String category = br.readLine();
			list.add(category);
			Boolean lend = false;
			list.add(lend);
			String lendName = "대여자 없음";
			list.add(lendName);
			map.put(title, list);
		}
		
	}

	// 비디오 삭제
	public void removeVod(Map map) throws IOException {
		System.out.println("목록에서 삭제할 비디오 이름을 고른뒤 삭제할 비디오 이름을 입력해 주세요");
		showVodList(map);
		String title = br.readLine();
		if (!map.containsKey(title)) {
			System.out.println("존재하지 않는 비디오 입니다.");
			// 그 비디오 정보 출력
			return;
		} else {
			map.remove(title);
			System.out.println("삭제 완료");
		}
		System.out.println("---------------------");
	}

	// 비디오 수정
	public void rewriteVod(Map map) throws IOException {
		System.out.println("목록에서 수정할 비디오 이름을 고른뒤 수정할 비디오 이름을 입력해 주세요");
		showVodList(map);
		String title = br.readLine();
		if (!map.containsKey(title)) {
			System.out.println("존재하지 않는 비디오 입니다.");
			// 그 비디오 정보 출력
			return;
		} else {
			ArrayList list = new ArrayList();
			System.out.println(title+"의 새로운 장르를 입력해주세요");
			String category = br.readLine();
			list.add(category);

			System.out.println(title+"은(는) 대여중인가요? y/n");
			String yOrN = br.readLine().trim();
			if (yOrN.equals("n") || yOrN.equals("N")) {
				Boolean lend = false;
				list.add(lend);
				String lendName = "대여자 없음";
				list.add(lendName);
				map.put(title, list);
			} else {
				Boolean lend = true;
				list.add(lend);
				System.out.println("대여자 이름을 입력해주세요 ");
				String lendName = br.readLine();
				list.add(lendName);
				map.put(title, list);
			}
		}
	
		System.out.println("---------------------");
	}

	// 비디오 리스트 출력
	public void showVodList(Map map) {
		if(map.size()==0) {
			System.err.println("입력받은 비디오 없음");
			return;
		}
		Set set = map.entrySet();
		Iterator it = set.iterator();
		System.out.println("--------------전체 비디오 리스트 출력------------");
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			// 명시적 형변환 필수
			System.out.println("title : " + e.getKey());
		}
		System.out.println("---------------------");
	}

	// 비디오 하나의 정보 모두 출력
	public void showVodAll(Map map) {
		if(map.size()==0) {
			System.err.println("입력받은 비디오 없음");
			return;
		}
		Set set = map.entrySet();
		Iterator it = set.iterator();
		while (it.hasNext()) {
			Map.Entry e = (Map.Entry) it.next();
			// 명시적 형변환 필수
			String key = (String)e.getKey();
			System.out.println("--------------전체 비디오 정보 출력------------");
			System.out.println("비디오 제목 : " + key);
//			System.out.println("value : " + map.get(key).);
			ArrayList list =  (ArrayList)map.get(key);
			System.out.println("비디오 장르 : "+ list.get(0));
			System.out.println("비디오 대여 여부: " +list.get(1));
			System.out.println("비디오 대여자 : " +list.get(2));
			System.out.println("---------------------");
		}
	}

}
