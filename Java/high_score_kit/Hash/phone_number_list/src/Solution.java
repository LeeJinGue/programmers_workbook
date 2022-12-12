import java.util.Arrays;

class Solution {
    // phone_book: 전화번호부에 적힌 전화번호를 담은 배열
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        Arrays.sort(phone_book);
        for(int i=0; i<phone_book.length-1;i++){
            if(isPrefix(phone_book[i], phone_book[i+1])){
                answer = false;
                System.out.println(phone_book[i+1]+"가 "+phone_book[i]+"로 시작합니다." );
                break;
            }
        }
        // System.out.println(Arrays.toString(phone_book));
        return answer;
    }
    public static void main(String[] args) {
        Solution sol = new Solution();
        String[] phone_book = {"119", "97674223", "1195524421"};
        System.out.println(sol.solution(phone_book));

    }
    public boolean isPrefix(String s1, String s2){
        if(s2.startsWith(s1)){
            return true;
        }
        return false;
    }
}