import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) throws Exception {
        //System.out.println("Hello, World!");
        
        int[] priorities = {1, 1, 9, 1, 1, 1};
        // priorities[0]의 중요도 : 2, 순서: 0
        // priorities를 하나하나 보면서 중요도를 체크한다. 
        
        int location = 0;
        System.out.println("return: " +solution(priorities, location));
    }
    public static int solution(int[] priorities, int location) {
        Queue<Integer> priorityQ = new LinkedList<>();
        Queue<Integer> indexQ = new LinkedList<>();
        int index = 0;
        int printNum = 0; // 출력횟수.
        for(int i:priorities){
            priorityQ.add(i);
            indexQ.add(index++);
        }
        //System.out.println("우선순위 큐: "+priorityQ.toString());
        //System.out.println("인덱스 큐: "+indexQ.toString());
        
        // 0. 대기목록 리스트를 Queue로 만든다.

        while(printNum < priorities.length){
            // 맨 앞을 꺼낸다.
            int nowPriority = priorityQ.poll();
            int nowIndex = indexQ.poll();
            //System.out.println("현재 인덱스: "+nowIndex+", 현재 우선순위: "+nowPriority);
            boolean isBack = false;
            for(int tempPriority:priorityQ){
                // 나머지 큐랑 비교하면서 더 큰 우선순위가 있는지 찾는다.
                if(nowPriority < tempPriority){
                    // 더 큰 인덱스가 있으면 일단 뒤로 보낸다.
                    priorityQ.add(nowPriority);
                    indexQ.add(nowIndex);
                    isBack = true;
                    //System.out.println("뒤로 보냅니다.");
                    break;
                }
            }
            // 뒤로 보내지 않았다면 현재꺼를 출력한다.
            if(!isBack){
                //System.out.println("출력합니다. 인덱스: "+nowIndex+", 우선순위: "+nowPriority);
                printNum++;
                // 만약 출력하는 애의 인덱스가 내가 찾고싶은 인덱스(로케이션)이라면 
                if(nowIndex == location){
                    break;      
                }
            }
        }
        return printNum;
    }
}

// 문서     A B C D
// 중요도   2 1 3 2
// 내가 알고싶은 문서의 위치 - 
// 1. A를 꺼냈는데 우선순위가 높은 C가 있어서 A를 뒤로 보낸다.  BCDA
// 2. B를 꺼냈는데 우선순위가 높은 B가 있어서 B를 뒤로 보낸다.  CDAB
// 3. C를 꺼냈는데 우선순위가 높은 문서가 없어서 C를 출력한다.  DAB
// 4. D를 꺼냈는데 

// 우선순위이면서 대기목록 큐 priorityQ
// 현재위치 now_loc
// 1) q1의 맨 앞 요소를 꺼내서 뒤의 우선순위들과 비교한다. 뒤의 우선순위들?
// if 더 큰 우선순위가 있다면 맨 뒤로 보낸다.
// -> 그럼 중간에 있는 것들 다 보내야되지않나?

// 우선순위 큐와 인덱스 큐를 만들어서 
// 인쇄할 때 인덱스 큐가 location과 같으면 멈추고 answer에 저장한다.