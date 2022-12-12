import java.util.Arrays;

public class Solution1 {
    public int solution(int[] numbers) {
        int answer = 0;
        String check=Arrays.toString(numbers);
        System.out.println("check: "+check);
        for(int i=0;i<10; i++){
            if(!check.contains(Integer.toString(i))){
                // System.out.println(i+"가 없습니다.");
                answer+=i;
            }
        }
        // System.out.println("정답: " + answer);
        return answer;
    }
    public static void main(String[] args) {
        Solution1 sol = new Solution1();
        int[] numbers = {1,2,3,4,6,7,8,0};
        sol.solution(numbers);
    }
}
