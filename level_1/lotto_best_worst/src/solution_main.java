package src;


import java.util.Arrays;
public class solution_main {
    public static void main(String[] args) throws Exception {
        Solution1 sol = new Solution1();
		int[] lottos = {19, 1, 45, 31, 10, 6};
		int[] win_nums = {31, 10, 45, 1, 6, 19};
		int[] answer = sol.solution(lottos, win_nums);
		System.out.println(Arrays.toString(answer));
    }
}
