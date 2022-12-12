import java.util.ArrayList;
import java.util.List;

public class Solution {
    // A: 어피치, C: 콘, F: 프로도, J: 제이지, M: 무지, N: 네오, R: 라이언
    // 첫 글자: 조건을 제시한 사람
    // 두번째 글자: ~
    // 세번째 글자: 상대방. 항상 첫 글자랑 다르다.
    // 네번째 글자: =, >, < 중 하나. 
    // 다섯번째 글자: 0~6 사이의 정수, 제시한 사람과 상대방 사이의 다른 사람의 수
    // answer: 모든 조건을 만족하는 경우의 수
    // ex) N~F=0 : 네오와 프로도 사이에 다른사람이 0명 있다.

    // 1. 인자로 받은 조건을 해석한다.
    // -> 스트링의 인덱스를 하나하나 뽑아서 해석한다.
    // 2. 해석한 조건에 맞는 경우의 수를 계산한다.
    // -> 프렌즈들이 일렬로 서는 모든 경우의 수를 계산하면서, 조건들에 맞을 때에만 answer++ 한다.
    // 스트링 배열 {"A", "C", "F", "J", "M", "N", "R"} 
    
    public static void main(String[] args) {
        Solution sol = new Solution();
        int n = 2;
        String[] data = new String[n];
        data[0] = "N~F=0";
        data[1] = "R~T>2";
        System.out.println("answer: "+sol.solution(n, data));
    }
    boolean[] visited = new boolean[8];
    char[] target = {'A', 'C', 'F', 'J', 'M', 'N', 'R', 'T'};
    char[] result = new char[8];
    String targetString = "ACFJMNRT";
    String resultString = "";
    String[] conditions;
    int answer = 0;
    int conditionLength = 0;
    public int solution(int n, String[] data) {
        answer = 0;
        conditionLength = n;
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
            resultString+=Character.toString(targetString.charAt(i));
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
    int getAnswer(String names, int conditionNum, String[] conditions){
        // List<String> friends_order_list = new ArrayList<>();
        int answer = 0;
        
        char[] namesChars = names.toCharArray();
        for(char c1:namesChars){
            String s1 = names.replace(Character.toString(c1), "");
            for(char c2:s1.toCharArray()){
                String s2 = s1.replace(Character.toString(c2), "");
                for(char c3:s2.toCharArray()){
                    String s3 = s2.replace(Character.toString(c3), "");
                    for(char c4:s3.toCharArray()){
                        String s4 = s3.replace(Character.toString(c4), "");
                        for(char c5:s4.toCharArray()){
                            String s5 = s4.replace(Character.toString(c5), "");
                            for(char c6:s5.toCharArray()){
                                String s6 = s5.replace(Character.toString(c6), "");
                                for(char c7:s6.toCharArray()){
                                    String s7 = s6.replace(Character.toString(c7), "");
                                    String friendsOrder = Character.toString(c1)+Character.toString(c2)+Character.toString(c3)+Character.toString(c4)+Character.toString(c5)+Character.toString(c6)+s7;
                                    // System.out.println("string: "+string);
                                    int correctCondition = 0;
                                    for(String condition: conditions){
                                        if(checkCondition(friendsOrder, condition)){
                                            correctCondition++;
                                            // System.out.println(friendsOrder+"가 "+condition+"의 조건을 만족합니다.");
                                        }
                                    }
                                    if(correctCondition==conditionNum){
                                        // friends_order_list.add(friendsOrder);
                                        answer++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        return answer;
        // ArrayList<String> clone = (ArrayList<String>) full.clone();

        // for(String a1: full){
        //     String friendsOrder = "";
        //     ArrayList<String> temp1 = (ArrayList<String>) clone.clone();
        //     temp1.remove(a1);
            
        //     for(String a2: temp1){
        //         ArrayList<String> temp2 = (ArrayList<String>) temp1.clone();
        //         temp2.remove(a2);
                
        //         for(String a3: temp2){
        //             ArrayList<String> temp3 = (ArrayList<String>) temp2.clone();
        //             temp3.remove(a3);
                    
        //             for(String a4: temp3){
        //                 ArrayList<String> temp4 = (ArrayList<String>) temp3.clone();
        //                 temp4.remove(a4);
                        
        //                 for(String a5: temp4){
        //                     ArrayList<String> temp5 = (ArrayList<String>) temp4.clone();
        //                     temp5.remove(a5);    
                            
        //                     for(String a6: temp5){
            
        //                         ArrayList<String> temp6 = (ArrayList<String>) temp5.clone();
        //                         temp6.remove(a6);
        //                         for(String a7: temp6){
        //                             ArrayList<String> temp7 = (ArrayList<String>) temp6.clone();
        //                             temp7.remove(a7);
        //                             friendsOrder = a1+a2+a3+a4+a5+a6+a7+temp7.get(0);
        //                             // 프렌즈 순서 문자열 friendsOrder 생성

        //                             int correctCondition = 0;
        //                             for(String condition: conditions){
        //                                 if(checkCondition(friendsOrder, condition)){
        //                                     correctCondition++;
        //                                     // System.out.println(friendsOrder+"가 "+condition+"의 조건을 만족합니다.");
        //                                 }
        //                             }
        //                             if(correctCondition==conditionNum){
        //                                 // friends_order_list.add(friendsOrder);
        //                                 answer++;
        //                             }
        //                         }
        //                     }
        //                 }
        //             }
        //         }
        //     }
        // }
        // return answer;
    }    
    
    // public int solution(int n, String[] data) {
    //     permunant(0);
    //     int answer = 0;
    //     // String[] names = {"A", "C", "F", "J", "M", "N", "R", "T"};
    //     // ArrayList<String> nameList = new ArrayList<>();
    //     // nameList.addAll(Arrays.asList(names));
    //     String names = "ACFJMNRT";
    //     answer = getAnswer(names, n, data);
    //     // List<String> friends_order_list = getAnswerList(nameList, n, data);
    //     // for(String friendsOrder: friends_order_list){
    //     //     // System.out.println("String: "+s);
    //     //     int correctCondition = 0;
    //     //     for(String condition: data){
    //     //         if(checkCondition(friendsOrder, condition)){
    //     //             correctCondition++;
    //     //             // System.out.println(friendsOrder+"가 "+condition+"의 조건을 만족합니다.");
    //     //         }
    //     //     }
    //     //     if(correctCondition == n){
    //     //         answer++;
    //     //     }
    //     //     // System.out.println(friendsOrder+"가 "+ correctCondition+"개 조건을 만족합니다.");
    //     // }
    //     // System.out.println("개수: "+friends_order_list.size());
    //     // System.out.println("answer: "+answer);
        
    //     return answer;
    // }
    public boolean checkCondition(String string, String condition){
        String friend1 = Character.toString(condition.charAt(0));
        String friend2 = Character.toString(condition.charAt(2));
        String compare = Character.toString(condition.charAt(3));
        int dist = Integer.parseInt(condition.substring(4));
        // 프렌즈1와 프렌즈2 사이의 간격 체크
        // ex) ACFJMNRT
        // M~C<2
        
        int friend1_index = string.indexOf(friend1);
        int friend2_index = string.indexOf(friend2);
        // 두 프렌즈 사이의 간격은 두 프렌즈의 인덱스 차이 -1이다.
        
        int friends_dist = Math.abs(friend1_index-friend2_index)-1;
        if(compare.equals("<")){
            if(friends_dist < dist){
                // System.out.println(friend1+"과 "+friend2+"사이의 거리는 "+friends_dist+"입니다.");
                return true;
            }
        }else if(compare.equals(">")){
            if(friends_dist > dist){
                // System.out.println(friend1+"과 "+friend2+"사이의 거리는 "+friends_dist+"입니다.");
                return true;
            }

        }else if(compare.equals("=")){

            if(friends_dist == dist){
                // System.out.println(friend1+"과 "+friend2+"사이의 거리는 "+friends_dist+"입니다.");
                return true;
            }
        }else{
            System.out.println("오류");
        }
        return false;     
    }
    // public static void main(String[] args) {
    //     Solution sol = new Solution();
    //     int n = 2;
    //     String[] data = new String[n];
    //     data[0] = "N~F=0";
    //     data[1] = "R~T>2";
    //     System.out.println("answer: "+sol.solution(n, data));
    // }
}
