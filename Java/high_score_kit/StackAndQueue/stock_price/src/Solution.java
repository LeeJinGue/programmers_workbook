import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) throws Exception {
        Solution sol = new Solution();
        int[] prices = {1, 1, 3, 2 ,3};
        System.out.println("정답: "+Arrays.toString(sol.solution(prices)));
    }
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];
        Queue<Integer> q1 = new LinkedList<>();
        for(int i:prices) q1.add(i);
        int dist = 1;
        for(int t=0; t<prices.length; t++){
            int now = q1.poll();
            int time=0;
            System.out.println(t+"번째 주가가 떨어지지 않은 시간을 계산합니다.");
            for(int q:q1){
                if(now <= q){
                    System.out.print("주가가 떨어지지 않았습니다. 1초 늘어났습니다. ");
                    time++;
                }else{
                    System.out.print("주가가 떨어졌습니다. ");
                    time++;
                    dist = time;
                    break;
                }
            }
            System.out.println();
            answer[t] = time;    
            

        }
        return answer;
    }
}

// 2 4 4 2 3 1 
// 1 1 0 1 0