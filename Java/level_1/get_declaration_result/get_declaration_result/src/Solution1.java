package get_declaration_result.src;

import java.util.ArrayList;
import java.util.HashMap;

public class Solution1 {

	// 한 유저를 여러 번 신고할 수 있지만 중복신고의 경우는 1번으로 친다.
	
	// 주어진 변수 설명
	// id_list: 모든 이용자의 ID가 담긴 String[] -> ID는 알파벳 소문자
	// 자기자신 신고 X -> 예외처리해야할듯
	// report: 각 이용자가 신고한 이용자의 ID 정보가 담긴 문자열 배열
	// -> 각 원소는 "이용자id 신고한id"
	// k: 정지 되는 신고 횟수.
	// answer: 각 유저별로 처리 결과 메일을 받은 횟수가 담긴 int[]
	// result: 각 유저가 받은 결과 메일 수
	
	// 각 배열의 길이제한
	// 1 <= id_list.length <= 1000, 
	// 1 <= id_list[n].length <= 10,
	// 1 <= report.length <= 200,000
	// 3 <= report[n].length <= 21
	// 1 <=k <= 200
	
	// 순서
	// 1. report에 담긴 각 원소를 검사해서 신고자ID와 신고받을 자 ID를 분리한다.
	// -> 신고자ID: reporter, 신고받을자ID: reportee
	// 	1) 중복된 신고인지 검사
	// 	2) 아니라면 Map1에서 reportee에 대해 신고당한 횟수 +1
	// 	3) Map2에서 reportee에 대해 reporter를 원소로 추가
	// 2. idAndumReport를 돌면서 각 아이디의 신고당한 횟수가 k이상이면 정지시킨다.
	// 	1) 정지당했다는 것을 출력한다.
	// 	2) 정지당한 사람은 정지메일을 받는다.
	// 	3) 정지당한 사람을 신고한 사람도 해당 유저의 정지 사실을 메일로 받는다.
	
	// idAndnumReport<ID, 신고당한횟수> - Key: 신고당한 사람
	// idAndreporterMap<ID, 자기를 신고한 ID ArrayList> - Key: 신고당한 사람
	// idAndMailNumMap<ID, 메일횟수>
 
	public int[] solution(String[] id_list, String[] report, int k) {
		int[] answer = new int[id_list.length];

		HashMap<String, Integer> idAndnumReport = new HashMap<>();
		HashMap<String, ArrayList<String>> idAndreporterMap = new HashMap<>();
		HashMap<String, Integer> idAndMailNumMap = new HashMap<>();
		// 사용자ID, 신고당한횟수 Map 초기화
		// + 사용자ID, 본인을 신고한 ID리스트 Map 초기화
		for(String id : id_list) {
			idAndnumReport.put(id,0);
			idAndreporterMap.put(id, new ArrayList<String>());
			idAndMailNumMap.put(id, 0);
		}
		
		mapTest1(idAndnumReport);
		mapTest2(idAndreporterMap);
		
		// report에 담긴 각 원소를 검사해서 reporter와 reportee를 분리한다.
		for(String s : report) {
			String[] split = s.split(" ");
			String reporter = split[0];// 신고한 사람
			String reportee = split[1]; // 신고당한 사람
			// 잘 나눠졌는지 테스트
			System.out.println("신고한 사람: " + split[0] + ", 신고당한 사람: "+split[1]);

			// 중복된 신고라면 패스한다.
			if(idAndreporterMap.get(reportee).contains(reporter)) {
			}else {
				idAndnumReport.replace(reportee, idAndnumReport.get(reportee)+1);
				idAndreporterMap.get(reportee).add(reporter);
			}
		}
		mapTest1(idAndnumReport);
		mapTest2(idAndreporterMap);
		mapTest1(idAndMailNumMap);
		
		for(String s : idAndnumReport.keySet()) {
			int numReport = idAndnumReport.get(s);
			if(numReport >= k) {
				System.out.println(s +"가 정지되었습니다.");
				// 정지된 아이디를 신고한 사람들의 메일횟수 +1
				for( String reporter : idAndreporterMap.get(s)) {
					idAndMailNumMap.replace(reporter, idAndMailNumMap.get(reporter)+1);
					System.out.println("reporter: " + reporter);
				}
			}
		}
		mapTest1(idAndMailNumMap);
		// 정지된 아이디를 신고한 사람들의 메일횟수 +1
		int i = 0;
		for(String s : id_list) {
			answer[i] = idAndMailNumMap.get(s);
			i++;
		}
        return answer;
    }
	public void mapTest1(HashMap<String, Integer> map1) {
		for(String s : map1.keySet()) {
			System.out.println("Key: " + s + ", Value: " + map1.get(s));
		}
	}
	public void mapTest2(HashMap<String, ArrayList<String>> map1) {
		for(String s : map1.keySet()) {
			System.out.println("Key: " + s + ", Value: " + map1.get(s));
		}
	}
	
}
