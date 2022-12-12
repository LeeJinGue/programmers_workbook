import java.util.ArrayList;
import java.util.Arrays;

public class Solution1 {
    // board: n차원 배열
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        crane crn = new crane(board);
        for(int i: moves){
            crn.drawing_doll(i);
        }
        
        answer = crn.answer;
        return answer;
    }
    public class crane{
        int n;
        int[][] board;
        ArrayList<Integer> basket;
        int answer;
        public crane(int[][] board){
            // 칸 크기 초기화
            this.n = board.length;
            System.out.println("칸크기: "+ this.n);
            // 보드 초기화
            this.board = new int[this.n][this.n];
            this.board = board;
            for(int i = 0; i<5; i++){
                System.out.println(i+"번째 칸: " + Arrays.toString(this.board[i]));
            }

            // 바구니 초기화
            basket = new ArrayList<Integer>();

            // 점수 초기화
            this.answer = 0;
        }
        public void drawing_doll(int location){
            int num = location-1;
            System.out.println("크레인 위치: "+num);
            for(int i = 0; i<this.n; i++){
                // 칸이 비어있다면
                int drawingDoll = this.board[i][num];
                if(drawingDoll == 0){
                    continue;
                }else{
                    
                    // 바구니가 비어있다면
                    if(this.basket.size() == 0){
                        System.out.print("바구니가 비었습니다. 인형을 추가합니다.");
                        this.basket.add(drawingDoll);
                        this.board[i][num] = 0;
                        System.out.println("바구니: "+ this.basket.toString());
                        break;
                    }else{

                    int lastDoll=this.basket.get(this.basket.size()-1);
                    // 바구니의 마지막 인형과 새로 들어갈 인형의 크기가 같다면
                    if(drawingDoll == lastDoll){
                        System.out.print("인형이 연속됩니다.");
                        // 바구니 마지막 칸을 지우고
                        this.basket.remove(this.basket.size()-1);
                        // 정답에 +2 해준다.
                        this.answer+=2;
                        this.board[i][num] = 0;
                        System.out.println("바구니: "+ this.basket.toString());
                        break;
                    
                    // 아니면 그냥 인형만 추가해준다.
                    }else{
                        this.basket.add(drawingDoll);
                        this.board[i][num] = 0;
                        System.out.print("인형을 꺼냅니다.");
                        System.out.println("바구니: "+ this.basket.toString());
                        break;
                    }
                }
                
            }
            
        }
        
    }
    
}
    public static void main(String[] args) {
        // int[][] board = new int[5][5];
        int[][] board = {{0,0,0,0,0},{0,0,1,0,3},{0,2,5,0,1},{4,2,4,4,2},{3,5,1,3,1}};
        for(int i=0; i<5; i++){
            System.out.println(i+"번째 줄: "+Arrays.toString(board[i]));
        }
        int[] moves = {1,5,3,5,1,2,1,4};
        Solution1 sol = new Solution1();
        System.out.println("터트린 인형 개수: "+sol.solution(board, moves));
    }
}
