package workspace_th.day06.quiz1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainEntry {
	static Map<String, ArrayList> map = new HashMap();
	public static void main(String[] args) throws IOException {
		
		VideoService vs = new VideoService();
		//비디오 관련 모든 내용 출력
		vs.showVodAll(map);
		//비디오 추가
		vs.addVod(map);
		//비디오 관련 모든 내용 출력
		vs.showVodAll(map);
		//비디오 추가
		vs.addVod(map);
		//비디오 추가
		vs.addVod(map);
						
				
		//비디오 삭제
		vs.removeVod(map);
		//비디오 수정
		vs.rewriteVod(map);;
		
		//비디오 리스트 출력
		vs.showVodList(map);
		
		System.out.println("------------");
		//비디오 관련 모든 내용 출력
		vs.showVodAll(map);

	}

}
