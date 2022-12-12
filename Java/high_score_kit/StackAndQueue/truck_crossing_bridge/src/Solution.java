import java.util.LinkedList;
import java.util.Queue;

public class Solution {
    public static void main(String[] args) throws Exception {
        Solution sol = new Solution();
        int bridge_length = 2;
        int weight = 10;
        int[] truck_weights = {7, 4, 5, 6};
        System.out.println("총 걸린 시간: "+sol.solution(bridge_length, weight, truck_weights) +"초");
    }
    int total_weight = 0;
    int truck_index = 0;

    public int solution(int bridge_length, int weight, int[] truck_weights) {
        // 1. 시간을 +1해서 완료되는 트럭은 건너게 만들고 다리가 비었는지 확인한다.
        // 2
        // 2. bridge_length로 트럭의 개수를 검사하고
        // 3. weight로 다리에 올라간 트럭의 총 무게를 검사합니다. 
        // 4. 2,3의 조건을 만족하면 트럭을 하나 더 올립니다.
        // 5. 2,3의 조건을 만족하지 않으면 시간을 ++합니다.
        // 가정1. 
        int total_time = 0;
        Queue<CrossingTruck> street = new LinkedList<>();
        boolean isEnd = false;
        int end_truck = 0;
        while(!isEnd){
            // 시간을 +1합니다.
            total_time++;
            //System.out.print("지금은 "+total_time+"초 입니다.   ");
            if(!street.isEmpty()){
                // 다리에 있는 트럭들의 시간을 +1합니다.
                for(CrossingTruck truck: street){
                truck.cross_time+=1;            
                }
                // 트럭이 건너게 되면 다리에서 제거합니다.
                if(street.peek().cross_time > (bridge_length)){
                //System.out.print("트럭이 다리를 건넜습니다. ");
                end_truck++;
                total_weight -= street.poll().truck_weight;
                if(end_truck == truck_weights.length){
                    break;
                };
            };
            }
            // 다음 트럭이 있는지 확인합니다.
            if(truck_index == truck_weights.length){
                //System.out.println();
                continue;
            }
            if(street.isEmpty()){
                //System.out.print("다리가 비어있습니다.    ");
                putOnTheStreet(street, truck_weights[truck_index]);
            }else{
                if( street.size()+1 <= bridge_length){
                //System.out.print("개수 통과 ");
                //System.out.print(total_weight + " + "+truck_weights[truck_index] + " <= "+weight+"  ");
                if(total_weight + truck_weights[truck_index] <= weight){
                    putOnTheStreet(street, truck_weights[truck_index]);
    
                }
            }
            
        
        }
        //System.out.println();
        }

        return total_time;
    }
    public void putOnTheStreet(Queue<CrossingTruck> street, int truck_weight){
        //System.out.print(truck_index+"번쨰의 "+truck_weight+"kg의 트럭을 다리에 올립니다.");
        CrossingTruck newTruck = new CrossingTruck(truck_weight);
        // 전체 무게를 증가시킵니다.
        total_weight+=newTruck.truck_weight;
        street.add(newTruck);
        // 다음 트럭으로 인덱스를 +1합니다.
        truck_index++;
    }
    public class CrossingTruck{ // 건너고 있는 트럭 개체
        int cross_time; // 건너고 있는 시간(1에서 시작해서 다리의 길이가 되면 큐를 빠져나간다.)
        int truck_weight; // 트럭의 무게
        public CrossingTruck(int truck_weight){
            this.truck_weight = truck_weight;
            this.cross_time = 1;
        }
    }
}

// 아니면 Street 클래스를 만들고 Queue를 관리해보면?
// Truck 클래스를 만든다.

// 시나리오
// 0. 시간을 증가시키는데, 맨앞 트럭의 시간이 길이-1이라면 건너게 합니다.
// 1. 첫 트럭은 무조건 올린다.
// 2. 첫 트럭이 아닐 경우 다리에 트럭이 차있는지 확인합니다.
// 2-1. 여유가 있다면 다음 트럭을 올릴 때 다리가 버틸 수 있는 무게인지 확인합니다.
// 2-2. 무게도 맞다면 트럭을 올립니다.

// 트럭을 올린다.
// 1. 트럭 인덱스에 맞는 트럭의 무게를 갖고 새 트럭 객체를 만듭니다.
// 2. 