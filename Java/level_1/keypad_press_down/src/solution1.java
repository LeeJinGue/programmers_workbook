public class solution1 {
    // 엄지손가락은 상하좌우 4가지 방향으로만 이동 가능, 이동 한 칸당 거리 1
    // 가운데 열의 숫자일 경우 각 엄지 손가락의 거리 계산

    // numbers: 순서대로 누를 번호가 담긴 배열(각 원소는 0~9)
    // hand: 오른손잡이인지 왼손잡이인지(left/right)
    // answer: 왼손 엄지손가락을 사용한 경우는 L, 오른손 엄지손가락을 사용할 경우엔 R을 순서대로 이어붙인다.
    
    // 자판 위치 -> 정수
    // 1 2 3     0  1  2
    // 4 5 6  -> 3  4  5
    // 7 8 9  -> 6  7  8
    // * 0 #     9 10 11
    // 1. 객체 User
    // 2. User: 
    public String solution(int[] numbers, String hand) {
        String answer = "";
        User user1 = new User(hand);
        // user1.setGraph();
        answer = user1.getAnswer(numbers);
        System.out.println("정답: " + answer);
        return answer;
    }
    
    
    public class User{
        String hand;
        int leftLoc;
        int rightLoc;
        public User(String hand){
            this.hand = hand;
            this.leftLoc = 9;
            this.rightLoc = 11;
        }
        int[] keypad_to_loc(int[] numbers){
            int[] loc = new int[numbers.length];
            for(int i=0; i<numbers.length; i++){
                // 자판 1~9까지는 -1
                if(numbers[i]>0 && numbers[i] <10){
                    loc[i]=numbers[i]-1;

                // 자판 0은 10으로
                }else if(numbers[i] == 0){
                    loc[i] = 10;
                // 이상한거면 -1
                }else{
                    // System.out.println("오류");
                    loc[i] = -1;
                }
            }
            return loc;
        }
        String setLoc(String hand, int loc){
            switch(hand){
                case "right":
                    // System.out.println("오른쪽 손가락 사용, 새 위치: "+ loc);
                   this.rightLoc = loc;
                    return "R";
                case "left":
                    // System.out.println("왼쪽 손가락 사용, 새 위치: "+ loc);
                    this.leftLoc = loc;
                    return "L";
            }
            return "ERROR";

        }
        String getAnswer(int[] numbers){
            String answer ="";
            // 인풋으로 들어온 자판 -> 위치값
            int[] loc_numbers = {};
            loc_numbers=keypad_to_loc(numbers);
            // System.out.println("자판값 -> 위치값: ");
            
            for(int i : loc_numbers){
                System.out.print(Integer.toString(i)+", ");
            }
            System.out.println("");
            
            String useLeftHand = "036";
            String useRightHand = "258";
            for(int loc : loc_numbers){
                
                String loc_string = Integer.toString(loc);
                if(useLeftHand.contains(loc_string)){
                    answer += setLoc("left", loc);
                }else if(useRightHand.contains(loc_string)){
                    answer += setLoc("right", loc);
                }else{
                    // 1,4,7,10인 경우 거리계산해야함
                    
                    int leftDist = calDistance(this.leftLoc, loc);
                    int rightDist = calDistance(this.rightLoc, loc);
                    
                    // 오른쪽이 더 가까우면
                    if(leftDist > rightDist){
                        // System.out.print("오른쪽이 더 가깝습니다.");
                        answer += setLoc("right", loc);
                        
                        // 왼쪽이 더 가까우면
                    } else if(leftDist < rightDist){
                        // System.out.print("왼쪽이 더 가깝습니다.");
                        answer += setLoc("left", loc);
                        
                        // 거리가 같으면
                    }else{
                        // System.out.print("거리가 같습니다");
                        
                        // 왼손잡이일 경우
                        if(hand == "left"){
                            // System.out.print(", 왼손잡이입니다.");
                        
                            answer += setLoc("left", loc);
                            // 오른손잡이일 경우
                        }else if(hand == "right"){
                            // System.out.print(", 오른손잡이입니다.");

                            answer += setLoc("right", loc);
                        }else{
                            // System.out.println("뭔가 잘못되었다2");
                        }
                    }
                }
            }
            return answer;
        }
        
        int calDistance(int start, int dest){
            // 시작위치와 도착위치의 거리를 계산한다.
            int y_distance = Math.abs(start/3 - dest/3);
            int x_distance = Math.abs(start%3 - dest%3);
            int tot_distance = x_distance + y_distance;            
            // System.out.println("시작위치: "+start+", 도착위치: "+dest + ", 거리: "+tot_distance);
            return tot_distance;
        }
        
    }
    public static void main(String[] args) {
        solution1 sol1 = new solution1();
        int[] numbers = {7, 0, 8, 2, 8, 3, 1, 5, 7, 6, 2};
        String hand = "left";
        sol1.solution(numbers, hand);
    }
}


// public class ListGraph{
//     private ArrayList<ArrayList<Integer>> listGraph;
//     boolean[] visited;

//     public ListGraph(int initSize){
//         this.listGraph = new ArrayList<ArrayList<Integer>>();
//         visited = new boolean[12];
//         // for(boolean b : visited){
//         //     b = false;
//         // }
//         for(int i=0; i<initSize;i++){
//             listGraph.add(new ArrayList<Integer>());
//         }
//     }
//     // 그래프 return
//     public ArrayList<ArrayList<Integer>> getGraph(){
//         return this.listGraph;
//     }

//     // 그래프 특정 노드 return
//     public ArrayList<Integer> getNode(int node){
//         return this.listGraph.get(node);
//     }


//     // 양방향 edge 추가
//     public void addEdge(int x, int y){
//         listGraph.get(x).add(y);
//         listGraph.get(y).add(x);
//     }
    
//     // 그래프 출력(인접 리스트)
//     public void printGraphToAdjList(){
//         for(int i=0; i<listGraph.size(); i++){
//             System.out.print("정점 " + i + "의 인접 리스트");
//             for(int j = 0; j<listGraph.get(i).size(); j++){
//                 System.out.print(" -> " + listGraph.get(i).get(j));
//             }
//             System.out.println();
//         }
//     }
    
//     ArrayList<ArrayList<Node>> nodeGraph;
//     public int dfs_return_cost(int startV, int destV){
//         nodeGraph = new ArrayList<ArrayList<Node>>();
//         for(int i=0; i<12; i++){
//             nodeGraph.add(new ArrayList<Node>());
//         }
//         int [][] k = {{10, 11}, 
//         {2, 4},
//          {3, 5},
//           {6},
//           {5, 7},
//           {6, 8},
//           {9},
//           {8, 10},
//           {9, 0},
//           {11},
//           {0},
//           {11}};
//         for(int[] i : k){
//             for(int j: i){
//              nodeGraph.get(j).add(new Node(i[j], 1));   
//             }
//         }
        

//         int[] dist = new int[12];
//         for(int i : dist){
//             i = Integer.MAX_VALUE;
//         }
//         PriorityQueue<Node> q = new PriorityQueue<Node>((o1, o2) -> Integer.compare(o1.cost, o2.cost));
//         q.offer(new Node(startV, 0));
//         dist[startV] = 0;
//         while(!q.isEmpty()){
//             Node curNode = q.poll();
//             if(curNode.idx == destV){
//                 return dist[curNode.idx];
//             }

//             if(dist[curNode.idx] < curNode.cost){
//                 continue;
//             }

//             // for()
//         }

//         // 1. 시작 노드를 방문한다.
//         Queue<Integer> queue = new LinkedList<>();
//         visited[startV] = true;
//         queue.add(startV);
//         int v = 0;
//         while(!queue.isEmpty()){
//             v = queue.poll();
//             System.out.print(v + " ");

//             Iterator<Integer> iter = listGraph.get(v).listIterator();
//             while(iter.hasNext()){
//                 int w = iter.next();
//                 if(!visited[w]){
//                     visited[w] = true;
//                     queue.add(w);
//                 }
//             }
//         }
//     }
// }
// public class Node{
//     int idx;
//     int cost;
//     Node(int idx, int cost){
//         this.idx = idx;
//         this.cost = cost;
//     }
// }