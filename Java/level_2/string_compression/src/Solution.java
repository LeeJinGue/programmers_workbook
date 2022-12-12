import java.util.Stack;

class Solution{
    // 같은 값이 반복되는 경우 문자의 개수와 반복되는 값으로 표현한다.
    // 1개 단위로 자를건지 2개 단위로 자를건지
    // 단위는 1~(s의 길이/2)까지만. 
    public String str_comp(String s, int k){
        String compString = "";
        Stack<String> stack = new Stack<>();
        //System.out.println("문자열을 "+k+"개 단위로 쪼갭니다.");
        for(int i=k; i<=s.length(); i+=k){
            String split = s.substring(i-k, i);
            //System.out.print("쪼갠 문자: "+split);
            // 스택이 비어있지 않을 때
            if(!stack.isEmpty()){
                // 스택에 있는걸 꺼내서 현재 스트링과 같은지 확인한다.
                String stack_top = stack.peek();
                //System.out.print(", 스택에 있는 문자: "+stack_top);
                // 첫글자가 숫자일 때
                if(Character.isDigit(stack_top.charAt(0))){
                    //System.out.print(", 첫 글자가 숫자입니다");
                    String top_string;
                    top_string=stack_top.substring(1);

                    // 숫자 제외한 글자들이 split과 같은지 확인
                    if(top_string.equals(split)){
                        // 같다면 맨 앞 숫자를 ++ 해주고 
                        int repNum = Character.getNumericValue(stack_top.charAt(0));
                        String result = Integer.toString(++repNum) + top_string;
                        stack.pop();
                        stack.push(result);
                        //System.out.print(", 또 중복된 글자이므로 "+result+"를 푸쉬");

                    }else{
                        // 아니라면 split만 push
                        stack.push(split);
                        //System.out.print(", 중복이 아니므로 "+split+"를 푸쉬");
                    }
                    // 첫글자가 숫자가 아니라면
                }else{
                    //System.out.print(", 첫 글자가 숫자가 아닙니다");
                    // 현재 스트링과 같다면(숫자가 아니므로 이때 첫 중복)
                    if(split.equals(stack_top)){
                        split = Integer.toString(2)+split;
                        stack.pop();
                        stack.push(split);
                        //System.out.print(", 첫 중복이므로 "+split+"를 푸쉬");
                    }else{
                        // 현재 스트링과 같지 않다면 중복이 아니므로 그냥 split만 넣는다.
                        stack.push(split);
                        //System.out.print(", 중복이 아니므로 "+split+"를 푸쉬");
                    }
                }
                
                
            }else{
                // 스택이 비어있을 때 그냥 쪼갠 문자를 넣는다.
                stack.push(split);
                //System.out.print(", 스택이 비어있으므로 "+split+" 푸쉬");
            }
            //System.out.println();
        }
        for(String t : stack){
            compString += t;
        }
        
        if(s.length()%k != 0){
            String lastString = s.substring( (s.length()/k) *k);
            compString+=lastString;
            //System.out.println("마지막 남은 문자: "+lastString);
        }
        //System.out.println("최종 문자열: '"+compString+"'" + ", 최종 길이: '"+compString.length()+"'");
        return compString;

    }
    public int solution_fail(String s){
        int answer = s.length();
        String answerString = "";
        int n = 1;
        for(int k=1; k<=(s.length()/2)+1; k++){
            String result = str_comp(s, k);
            if(result.length() <= answer){
                answer = result.length();
                answerString = result;
                n = k;
            }
        }
        // //System.out.println("\n가장 짧은 것: "+answerString+", 길이: "+answer+", 나눈 단위: "+n+"개");
        //System.out.println("테스트: "+"안녕하세요".substring(1, 2));
        return answer;
    }

    public int solution(String s) {
        int answer = 0;
        // k: 쪼갤 단위
        // 정답으로 return할 길이의 최소값 초기화
        int min = s.length();
        int min_split = 0;
        for(int k=1; k<=(s.length()/2)+1;k++){
            // 결과를 담을 스트링 변수(k개로 쪼갰을 때의 결과)
            String resultString = "";
            // k개로 쪼갠다.
            String last=s.substring(0, k);
            
            // 중복된 횟수를 체크하기 위한 변수    
            int dup_cnt=1;
            // i: 인덱스
            for(int i=k;i<=s.length();i+=k){
                // 스트링의 마지막이라면
                if(s.length() < i+k){
                    //System.out.println("마지막이에요.");
                    // 중복이 안되었다면
                    if(dup_cnt == 1){
                        resultString = resultString + s.substring(i-k);
                        //System.out.println("마지막인데 중복이 안되어있어요."+dup_cnt+last);
                    }else{
                        resultString = resultString + dup_cnt+s.substring(i-k);
                        //System.out.println("마지막인데 중복이되어있어요 : "+ dup_cnt+last);
                    }
                    //System.out.println("result: "+resultString);
                    break;
                }
                // 비교를 위한 스트링 변수(다음 쪼갠 스트링)
                String compare = s.substring(i, i+k);
                //System.out.println("쪼갠 다음 스트링: " + compare);
                // 쪼갠 다음 스트링이 같다면
                if(compare.equals(last)){
                    // 중복이므로 횟수 +1
                    dup_cnt++;
                    // 중복된 값이므로 last값을 초기화하지 않는다.

                // 쪼갠 다음 스트링이 같지 않다면
                }else{
                    if(dup_cnt == 1){
                        // cnt값이 1이면 중복이 안되었던 것이므로 기존의 결과스트링에 last만 추가
                        resultString = resultString + last;
                    }else{
                        // 이전에 여러번 중복되었으면 cnt를 포함해서 결과에 추가한다.
                        resultString = resultString + Integer.toString(dup_cnt) +last;
                    }
                    // cnt값 1로 초기화
                    dup_cnt = 1;
                    // last에 현재 스트링을 대입
                    last = compare;
                }
            }
            // 가장 작은 length값
            if(resultString.length() != 0 && resultString.length()<min){
                min=resultString.length();
                min_split=k;
            }
        }
        //System.out.println("가장 작을 때의 길이: "+ min + ", 쪼갠 단위: "+ min_split);
        answer = min;
        return answer;
    }
    public static void main(String[] args) {
        Solution sol = new Solution();
        sol.solution("aaaaababcderer");
    }
}