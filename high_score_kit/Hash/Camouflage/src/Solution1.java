import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Solution1 {
    public int solution(String[][] clothes) {
        int answer = 0;
        // [의상의 이름, 의상의 종류]
        // 키: 의상 종류, 값: 의상 이름
        HashMap<String, List<String>> clothNameAndCategory = new HashMap<>();
        for(String[] s: clothes){
            // System.out.println("카테고리: "+s[0]);
            String name = s[0];
            String category = s[1];
            // 키가 있다: 기존 리스트에 추가
            if(clothNameAndCategory.containsKey(category)){
                clothNameAndCategory.get(category).add(name);
            }
            // 키가 없다: 리스트도 새롭게 추가
            else{
                List<String> names = new ArrayList<>();
                names.add(name);
                clothNameAndCategory.put(category, names);
            }
        }

        // for(String s: clothNameAndCategory.keySet()){
        //     System.out.println(s+"의 의상 이름 리스트: "+clothNameAndCategory.get(s).toString());
        // }
        categories = new String[clothNameAndCategory.keySet().size()];
        int[] categories_length = new int[clothNameAndCategory.keySet().size()];
        int n=0;

        // categories: 카테고리 이름들이 들어있는 배열
        // categories_length: 위 배열의 인덱스에 따라 각 카테고리들의 길이(개수)가 들어있는 배열
        // dice: 인덱스 조합이 들어있는 배열
        // 
        for(String s: clothNameAndCategory.keySet()){
            categories[n] = s;
            categories_length[n] = clothNameAndCategory.get(s).size();
            n++;
        }
        // for(int i=0; i < categories.length; i++){
        //     System.out.println(categories[i]+"의 개수: "+ categories_length[i]);
        // }
        for(int i=1; i<=categories.length;i++){
            dice = new int[i];
            totalCnt = 0;
            combination(0, 0, i, categories_length);
            answer+=totalCnt;
        }
        return answer;
    }
    String[] categories;
    int totalCnt = 0;
    int[] dice;
    public void combination(int cnt, int start, int N, int[] categories_length){
        if(cnt == N){
            int gyuong = 1;
            for(int t:dice){
                gyuong*=categories_length[t];
                
            }
            System.out.println(Arrays.toString(dice)+"의 경우의 수는 " +gyuong+"입니다.");

            totalCnt+=gyuong;
            return;
        }
        for(int i= start;i<categories_length.length;i++){
            dice[cnt] = i;
            combination(cnt+1, i+1, N, categories_length);
        }
    }
    public static void main(String[] args) {
        Solution1 sol = new Solution1();
        String[][] clothes = {{"yellowhat", "headgear"}, {"bluesunglasses", "eyewear"}, {"green_turban", "headgear"}};
        System.out.println("return: "+ sol.solution(clothes));
    }
}
