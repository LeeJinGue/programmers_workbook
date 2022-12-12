class Solution1 {
    public int solution(int[] absolutes, boolean[] signs) {
        int answer = 0;
        for(int i =0; i<absolutes.length; i++){
            if(signs[i]){
                answer+= absolutes[i];
            }else{
                answer-= absolutes[i];
            }
        }
        return answer;
    }
    public static void main(String[] args) {

        Solution1 sol = new Solution1();
        int[] absolutes = {4, 7, 12};
        boolean[] signs = {true, false, true};
        sol.solution(absolutes, signs);
    }
}