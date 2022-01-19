public class Solution1 {
    // 신규 유저가 규칙에 맞지 않는 아이디를 입력했을 때,
    // 입력된 아이디와 유사하면서 규칙에 맞는 아이디를 추천해주는 프로그램

    // 규칙
    // 1. 아이디의 길이는 3자~15자
    // 2. 아이디는 알파벳, 소문자, 숫자, -, _, .만 사용 가능.
    // 3. .는 처음과 끝에 사용X, 연속으로 사용X

    // 문제에서 제시한 처리과정
    // 1. new_id의 모든 대문자를 대응되는 소문자로 치환한다.
    // 2. new_id에서 알파벳 소문자, 숫자, -, _, .를 제외한 모든 문자를 제거한다.
    // 3. new_id에서 .가 2번 이상 연속된 부분을 하나의 .로 치환한다.
    // 4. new_id에서 .가 처음이나 끝에 위치한다면 제거한다.
    // 5. new_id가 빈 문자열이라면 new_id에 "a"를 대입한다.
    // 6. new_id의 길이가 15자 이상이면 new_id의 첫 15개의 문자만 남겨둔다. 제거 후 new_id의 앞/끝에 .가 있다면 .를 제거한다.
    // 7. new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙인다. 

    // new_id: 신규 유저가 입력한 아이디
    // new_id의 길이는 1~1000
    // new_id는 알파벳 대문자, 소문자, 숫자, 특수문자로 구성
    // new_id에 나타날 수 있는 특수문자는 -_.~!@#$%^&*()=+[{]}:?,<>/로 한정된다.
    // answer: 처리과정을 거친 후의 추천 아이디
    public String solution(String new_id) {
        System.out.println("처음 입력한 아이디: " + new_id);

        String answer = "";
        // 제거해야되는 특수문자들
        String no_permit_special_characters = "~!@#$%^&*()=+[{]}:?,<>/";
        // 허용되는 특수문자들
        String permit_special_characters = "-_.";

        //1. 대문자->소문자 치환
        answer = new_id.toLowerCase();
        System.out.println("1단계 치환: " + answer);

        //2. 제거해야되는 특수문자들 제거
        char[] char_array = answer.toCharArray();
        String change = "";
        for(char c : char_array){
            // 허용안되는 특수문자라면 무시
            if(no_permit_special_characters.contains(Character.toString(c))){
            }else{
                // 허용되는 거라면 추가해주기
                change+= Character.toString(c);
            }
        }
        answer = change;
        System.out.println("2단계 치환: " + answer);

        // 3. .이 2번이상인 부분을 .으로 바꿔준다. -> .이 반복되는가? + 몇번 반복되는가?
        // .인가? -> 전에도 .인가?
        boolean isDot = false;
        char_array = answer.toCharArray();
        change = "";
        for(char c : char_array){
            if(Character.toString(c).equals(".")){
                if(isDot){
                    // 전에도 .이라면 생략한다.
                }else{
                    change+=Character.toString(c);
                }
                isDot = true;
            }else{
                isDot = false;
                change+= Character.toString(c);
            }
        }
        answer = change;
        System.out.println("3단계 치환: " + answer);
        // 4. .이 처음/끝에 위치하면 제거한다.
        char_array = answer.toCharArray();
        if(answer.endsWith(".")){
            answer=answer.substring(0, answer.length()-1);
        //    System.out.println("끝이 .라면 바꾼다.");
        }
        if(answer.startsWith(".")){
            answer=answer.substring(1);
        //    System.out.println("시작이 .라서 바꾼다.");
        }
        System.out.println("4단계 치환: " + answer);

        // 5. 빈 문자열이라면 new_id에 "a"를 대입한다.
        if(answer.equals("")){
            answer = "a";
        }
        System.out.println("5단계 치환: " + answer);

        // 6. 길이가 16이상이면 첫 15개의 문자만 남겨둔다.
        if(answer.length() > 15){
            System.out.println("길이: " + answer.length());
            answer = answer.substring(0, 15);
            if(answer.endsWith(".")){
                answer=answer.substring(0, answer.length()-1);
            }
        }
        System.out.println("6단계 치환: " + answer);

        // 7. 길이가 2자 이하라면 길이가 3이 될 때까지 마지막 문자를 반복해서 붙여준다.
        change = answer;
        if(change.length()<=2){
            System.out.println("x");
            char last = answer.charAt(answer.length()-1);
            while(change.length() != 3){
                System.out.println("x");
                change+=last;
            }
        }
        answer=change;
        System.out.println("7단계 치환: " + answer);

        return answer;
    }
}
