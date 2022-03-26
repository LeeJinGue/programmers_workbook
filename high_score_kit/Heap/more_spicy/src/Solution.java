import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class Solution {
    public void mix(ArrayList<Integer> list){
        System.out.println("변화 전: "+list.toString());
        int low1 = list.get(0);
        int low2 = list.get(1);
        list.remove(0);
        list.set(0, low1 + (low2 *2));
        System.out.println("변화 후: "+list.toString());
    }
    public int solution(int[] scoville, int K) {
        // 배열에서 가장 낮은 것 2개를 빼낸다.
        ArrayList<Integer> list = new ArrayList<>(Arrays.stream(scoville).boxed().collect(Collectors.toList()));
        // 정렬부터 한다.
        list.sort(Comparator.naturalOrder());

        int mixNum = 0;
        while(list.size()>1){
            mixNum++;
            mix(list);
            if(list.get(0) >= K) break;
        }
        if(list.get(0) >= K){
            return mixNum;
        }else{
            return -1;
        }

        // 두개를 섞어서 하나를 다시 배열에 넣는다.
    }
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
        Solution sol = new Solution();
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int K = 7;
        System.out.println("return"+sol.solution(scoville, K));
    }
    
}
