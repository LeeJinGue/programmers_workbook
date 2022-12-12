import java.util.HashMap;

class solution {
    // 참가자 명단 participant에 있으면서 완주자 명단 completion에 없는,
    // 완주하지 못한 선수의 이름을 return하도록.
    // 참가자 중에 동명이인이 있을 수 있습니다. 
    public String solution1(String[] participant, String[] completion) {
        String answer = "";
        // 이름, 참가수
        // 
        HashMap<String, Integer> map1 = new HashMap<>();
        // 참가자 조사하면서 key에 추가
        for(String s: participant){
            if(map1.containsKey(s)){
                map1.replace(s, map1.get(s)+1);
            }else{
                map1.put(s, 1);
            }
        }
        for(String s: completion){
            if(map1.containsKey(s)){
                if(map1.get(s) == 1){
                    map1.remove(s);
                }else{
                    map1.replace(s, map1.get(s) - 1);
                }
            }
        }
        for(String s: map1.keySet()){
            for(int i=0; i<map1.get(s); i++){
                answer = answer+" " + s;
            }
        }
        return answer;
    }
    public static void main(String[] args) {
        solution sol = new solution();
        String[] participant = {"leo", "kiki", "eden"};
        String[] completion = {"eden", "kiki"};
        System.out.println("return:"+sol.solution1(participant, completion));
    }
}