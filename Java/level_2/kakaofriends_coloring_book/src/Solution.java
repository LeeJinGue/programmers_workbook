import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Arrays;

class Solution {
    int numberOfArea = 0;
    int maxSizeOfOneArea = 0;
    List<Integer> areaSizeList = new ArrayList<>();
    public int[] solution2(int m, int n, int[][] picture){
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    public void dfs(int x, int y, int [][] maps, int depth){
        
    }
    public int[] solution(int m, int n, int[][] picture) {
        // m, n: 그림의 크기를 나타낸다. 가로, 세로
        // picture: m x n크기, 값이 0인 경우는 색칠X,
        // numberOfArea: 영역의 개수
        // maxSizeOfOneArea: 가장 큰 영역의 크기(칸 수)
        // 영역은 위 아래가 연결되어 있어야 한다.
        // 
        // 1. 같은 원소를 갖고있는 칸의 위치를 좌표로 표시하고 맵에 저장한다.
        // 2. 세로를 검사한다.
        for(int[] k:picture){
            for(int l: k){
                System.out.print(l+" ");
            }
            System.out.println();
        }
        System.out.println(picture[5][3]);
        // 컬러색, 좌표 맵
        HashMap<Integer, ArrayList<point>> ElementpointMap = new HashMap<>();
        // 영역 크기 리스트


        for(int j = 0; j<m; j++){
            for(int i=0; i<n; i++){
                int element = picture[j][i];
                // 색이 0이면 넘어간다. 0이 아닐때만 추가
                if(element!=0){
                    // 해쉬맵에 해당 컬러색 좌표 정보가 없으면
                    if(!ElementpointMap.containsKey(element)){
                        System.out.print("새로운 색: ");
                        point newpoint = new point(j, i, element);
                        ArrayList<point> newpointList = new ArrayList<>();
                        newpointList.add(newpoint);
                        ElementpointMap.put(element, newpointList);
                        System.out.println("("+i + ", "+j+") 위치의 "+element+"색 좌표 추가");
                    }else{
                        // 해쉬맵에 해당 컬러색 좌표정보가 이미 있다면 있는거에 추가
                        point newpoint = new point(j, i, element);
                        ElementpointMap.get(element).add(newpoint);
                    }
                }else{
                    continue;
                }
            }
        }
        // 좌표가 잘 들어갔는지 체크
        for(ArrayList<point> l :ElementpointMap.values()){
            System.out.println("요소 "+l.get(0).element+"의 좌표 리스트: ");
            for(point v : l){
                System.out.println("좌표: ("+v.x+", "+v.y+"),");
            }
        }
        // 맵을 돌면서 거리가 1 이하면 연결되어있으므로 영역의 크기 +1, 1보다 크면 다음 점이므로 초기화.
        for(ArrayList<point> l :ElementpointMap.values()){
            System.out.println("List: "+l.toString());
            getLinked(l);
        }
        System.out.print("영역의 크기 리스트: [");
        for(int i:areaSizeList){
            System.out.print(i+", ");
            if(i>maxSizeOfOneArea){
                maxSizeOfOneArea = i;
            }
        }
        System.out.println("]");
        
        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    class point{
        point(int x, int y, int element){
            this.x = x;
            this.y = y;
            this.element = element;
        }
        public String toString(){
            return "("+this.x+", "+this.y+")";
        }
        int x;
        int y;
        int element;
    }
    // 같은 숫자들의 좌표들만 모아둔 List l
    // 1. 첫번째 인덱스는 그냥넘김
    // 2. 두번째 인덱스부터 전이랑 연결되어있는지 확인한다.
    // 2-1. 연결되어있다면 영역의 크기를 +1 시킨다. 마지막 인덱스라면 영역을 추가시킨다. 아니면 그냥 넘김
    // 2.2  연결되어있지 않다면 영역을 추가시킨다. 마지막 인덱스가 아니라면 검사한 곳까지 자른다.
    public void getLinked(List<point> l){
        int areaSize = 1;
        
        for(int i = 1; i<l.size(); i++){          
            point nowP = l.get(i);
            // ArrayList<point> subList2 = (ArrayList<Solution.point>) l.clone();
            // subList2.remove(i);
            // System.out.println("subList2: "+subList2.toString());
            List<point> subList = l.subList(0, i);                
            
            System.out.println("subList: "+subList.toString());
            
            // 연결되어 있다면
            if(isConnected(nowP, subList)){
                System.out.println("연결되어 있으므로 영역의크기 +1");
                areaSize+=1;
                if(i==l.size()-1){
                    System.out.println("마지막 인덱스이므로 영역 추가");
                    // 영역의 개수 추가
                    numberOfArea++;
                    // 영역의 크기 리스트에 현재까지의 영역의 크기값 추가
                    areaSizeList.add(areaSize);
                    // 영역의 크기 1로 다시 초기화
                    areaSize = 1;
                }
            }else{
                System.out.println(nowP.toString()+"이 연결되어 있지 않습니다." + " & areaSize: "+areaSize);
                // 다음 점과의 사이가 1보다 크다면, 다음 점부터는 새로운 영역임
                // 영역의 개수 추가
                numberOfArea++;
                // 영역의 크기 리스트에 현재까지의 영역의 크기값 추가
                areaSizeList.add(areaSize);
                // 영역의 크기 1로 다시 초기화
                areaSize = 1;
                if(i!=l.size()-1){
                    System.out.println(l.subList(i, l.size()).toString());
                    getLinked(l.subList(i, l.size()));
                    
                    System.out.println("test끝");
                    break;
                }
            }
        }
        if(l.size() == 1){
            // 영역의 개수 추가
            numberOfArea++;
            // 영역의 크기 리스트에 현재까지의 영역의 크기값 추가
            areaSizeList.add(areaSize);
            // 영역의 크기 1로 다시 초기화
            areaSize = 1;
        }
    }

    // 두 점 사이의 거리 리턴
    public int dist_btw_points(point a, point b){
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
    public boolean isConnected(point a, List<point> subList){
        for(int i=0; i<subList.size(); i++){
            point p = subList.get(i);
            // 하나라도 거리가 1이하인 게 있으면 연결된 것이다.
            if(dist_btw_points(a, p) <= 1){
                System.out.print(a.toString()+"과 "+p.toString()+"의 거리가");
                System.out.println(" 1보다 작으므로 연결되어 있습니다.");
                return true;
            }
            
        }

        System.out.println(a.toString()+"과 나머지의 거리가"+" 1보다 크므로 연결되어 있지 않습니다.");
        // 아니면 연결 안된거임
        return false;
    }
    public static void main(String[] args) {
        int[][] picture = {{1, 1, 1, 0}, {1, 2, 2, 0}, {1, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 3}, {0, 0, 0, 3}};
        // 1 1 1 0
        // 1 2 2 0
        // 1 0 0 1
        // 0 0 0 1
        // 0 0 0 3
        // 0 0 0 3
        int m = 6;
        int n = 4;

        int[][] picture2 = {
            {0 ,0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
            {0 ,0, 0, 0, 0, 0, 1, 1, 1, 1, 0, 0, 0, 0, 0, 0},
            {0 ,0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},
            {0 ,0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0 ,0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0},
            {0 ,1, 1, 1, 1, 2, 1, 1, 1, 1, 2, 1, 1, 1, 1, 0},
            {0 ,1, 1, 1, 2, 1, 2, 1, 1, 2, 1, 2, 1, 1, 1, 0},
            {0 ,1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0},
            {0 ,1, 3, 3, 3, 1, 1, 1, 1, 1, 1, 3, 3, 3, 1, 0},
            {0 ,1, 1, 1, 1, 1, 2, 1, 1, 2, 1, 1, 1, 1, 1, 0},
            {0 ,0, 1, 1, 1, 1, 1, 2, 2, 1, 1, 1, 1, 1, 0, 0},
            {0 ,0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0},
            {0 ,0, 0, 0, 1, 1, 1, 1, 1, 1, 1, 1, 0, 0, 0, 0},

    };
        int m2 = 13;
        int n2 = 16;
        // 1 1 1 0
        // 1 2 1 1
        // 1 0 0 0
        // 0 0 0 1
        // 0 0 0 1
        // 0 0 0 3

        Solution sol = new Solution();
        System.out.print(Arrays.toString(sol.solution(m, n, picture)));
    }
}