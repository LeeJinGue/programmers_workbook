public class sol2 {
    boolean[] visited = {false, false, false, false, false, false, false, false};
    char[] target ={'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    char[] result = new char[8];
    String[] conditions;
    int answer = 0;
    int conditionLength = 0;
    public int solution(int n, String[] data) {
        answer = 0;
        target[0]='A';
        
        
        conditionLength = n;
        conditions = new String[n];
        conditions = data;
        permunant(0);
        return answer;
    }
    void permunant(int cnt){
        if(cnt == 7){
            // System.out.println("result: "+resultString);
            // resultString = "";
            int satisfied = 0;
            for(String condition:conditions){
                if(checkConditionChar(result, condition)){
                    satisfied++;
                }
            }
            if(satisfied == conditionLength){
                // System.out.println(Arrays.toString(result) +"가 "+Arrays.toString(conditions) +"를 만족했습니다.");
                answer++;
            }
            return;
        }
        for(int i=0; i<8; i++){
            if(visited[i]){
                continue;
            }
            visited[i] = true;
            result[cnt] = target[i];
            // resultString+=Character.toString(targetString.charAt(i));
            permunant(cnt+1);
            visited[i] = false;

        }
    }
    boolean checkConditionChar(char[] charArray, String condition){
        char friend1 = condition.charAt(0);
        char friend2 = condition.charAt(2);
        char compare = condition.charAt(3);
        int dist = Integer.parseInt(condition.substring(4));
        // 프렌즈1와 프렌즈2 사이의 간격 체크
        // ex) ACFJMNRT
        // M~C<2

        int friend1_index = -1;
        int friend2_index = -1;
        for(int i=0;i<charArray.length;i++){
            if(charArray[i] == friend1){
                friend1_index = i;
            }else if(charArray[i] == friend2){
                friend2_index = i;
            }
        }
        // 두 프렌즈 사이의 간격은 두 프렌즈의 인덱스 차이 -1이다.
        
        int friends_dist = Math.abs(friend1_index-friend2_index)-1;
        if(compare == '<'){
            if(friends_dist < dist){
                // System.out.println(friend1+"과 "+friend2+"사이의 거리는 "+friends_dist+"입니다.");
                return true;
            }
        }else if(compare == '>'){
            if(friends_dist > dist){
                // System.out.println(friend1+"과 "+friend2+"사이의 거리는 "+friends_dist+"입니다.");
                return true;
            }

        }else if(compare == '='){

            if(friends_dist == dist){
                // System.out.println(friend1+"과 "+friend2+"사이의 거리는 "+friends_dist+"입니다.");
                return true;
            }
        }else{
            System.out.println("오류");
        }
        return false;     
    }
}
