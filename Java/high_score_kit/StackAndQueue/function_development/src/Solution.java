import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    // progress: 배포되어야 하는 순서대로 작업의 진도가 적힌 정수 배열
    // speeds: 각 작업의 개발 속도가 적힌 정수 배열
    // answer: 각 배포마다 몇 개의 기능이 배포되는지 적힌 정수 배열
    // 배포 - 뒤에 있는 기능이 먼저 개발된다면 앞에 있는 기능이 배포될 때 함께 배포된다.
    // 배포는 하루에 한번 하루가 끝날 때 이루어진다. 
    public int[] solution(int[] progresses, int[] speeds) {
        // 각 인덱스가 작업이라고 할 수 있다. 
        int[] copy = progresses;
        List<Integer> answer = new ArrayList<Integer>();
        boolean[] isEnd = new boolean[progresses.length];
        for(boolean e :isEnd){
            e = false;
        }
        System.out.println("isEnd: "+Arrays.toString(isEnd));

        int endIndex = 0;
        int day = 0;
        while(endIndex <= progresses.length-1){
            day++;
            int num = 0;
            for(int i = 0; i<copy.length;i++){
                // 속도만큼 작업 진행
                copy[i] += speeds[i];

                // 작업 끝난 인덱스에는 true
                if(copy[i] >= 100){
                    isEnd[i] = true;
                }
            }
            System.out.println(day+"일차 작업: "+Arrays.toString(copy));
            // 처음 작업끝난 인덱스는 없으니까 0부터 쭉 돈다.
            for(int i = endIndex; i<isEnd.length; i++){
                // 작업이 끝났다면 배포개수 ++
                if(isEnd[i]){
                    num++;
                    endIndex++;
                }else{
                    // 작업이 안끝난 인덱스로 오면 바로 아웃
                    break;
                }
            }
            // 배포개수가 있다면
            if(num != 0){
                answer.add(num);        
            }
        }
        // 큐.(FIFO)
        // 큐에 progress를 하나씩 넣어둔다. 하루마다 
        return answer.stream().mapToInt(i->i).toArray();
    }
    public static void main(String[] args) throws Exception {
        Solution sol = new Solution();
        int[] progresses = {93, 30, 55};
        int[] speeds = {1, 30, 5};
        System.out.println("결과: "+Arrays.toString(sol.solution(progresses, speeds)));
    }
}
