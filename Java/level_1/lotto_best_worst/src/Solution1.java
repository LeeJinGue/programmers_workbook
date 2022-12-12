package src;

public class Solution1 {
    public int[] solution(int[] lottos, int[] win_nums) {
		// 로또의 순서 상관 X
		// lottos: 구매한 로또번호(지워진 번호의 값은 0)
		// win_nums: 당첨번호
		// answer[0]: 최고 순위, answer[1]: 최저순위
		// 1. lottos에서 0이 몇개가 있는지 확인한다.
		// 2. lottos와 win_nums에 동일한 숫자가 몇개가 있는지 확인한다.
		// 3. 0의 개수만큼 cnt_same에 더해주는데 6보다 크면 6으로 바꿔주고 그걸 answer[0]에 대입
		// 4. 최저는 그냥 cnt_same값이니까 answer[1]에 대입
		// 방법1. for lottos {for win_nums}
		// 방법2. 시간복잡도 줄이는 방법이 있을까? 모르겠다.
		
		int cnt_zero = 0, cnt_same = 0;
		
		// lottos를 돌면서 0을 찾으면 cnt_zeor를 +1한다.
		for(int i = 0; i<lottos.length; i++) {
			if(lottos[i]==0) {
				// 테스트용 System.out.println("0을 찾았습니다.");
				cnt_zero++;
				
			}
		}
		
		// lottos와 win_nums를 돌면서 같은 숫자를 찾으면 cnt_same을 +1한다.    
		for(int i = 0; i<win_nums.length; i++) {
			for(int j = 0; j<lottos.length; j++) {
				// lottos가 0이면 지워진 값이니까 패스
				if(lottos[j] ==0) {
					continue;
				}
				if(win_nums[i] == lottos[j]) {
					// 두 배열 값이 같을때 테스트용으로 출력
					// 테스트용 System.out.println(String.format("lottos[%d]: %d, win_nums[%d]: %d", j, lottos[j], i, win_nums[i]));
					cnt_same++;
				}
			}
		}
		// 테스트용 System.out.println("cnt_zero: " + Integer.toString(cnt_zero) + ", cnt_same: " + Integer.toString(cnt_same));
		
		// 최고로 맞춘 개수와 최저로 맞춘 개수
		int best_cnt = cnt_zero + cnt_same;
		int worst_cnt = cnt_same;
		
		int[] answer = {cnt_to_rank(best_cnt), cnt_to_rank(worst_cnt)};
		
		return answer;
	}
	public int cnt_to_rank(int cnt) {
		int rank = 0;
		if(cnt >=6) {
			rank= 1;
		}else if(cnt == 5) {
			rank= 2;
		}else if(cnt == 4) {
			rank= 3;
		}else if(cnt == 3) {
			rank= 4;
		}else if(cnt == 2) {
			rank= 5;
		}else {
			rank= 6;
		}

		return rank;
	}
}
