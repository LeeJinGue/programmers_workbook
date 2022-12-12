import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Solution {
    // record: 채팅방 입장/퇴장 기록 + 닉네임 변경 기록
    // Enter [유저 아이디] [닉네임]
    // or Leave [유저 아이디]
    // or Change [유저 아이디] [닉네임]
    // 셋 중 하나씩 담긴다.
    // 각 단어는 공백으로 구분되어 있다.
    // 유저 아이디와 닉네임은 대소문자를 구분한다.
    // 아이디와 닉네임의 길이는 1~10 이하다.
    
    // answer: 관리자가 보게 되는 메세지
    // 닉네임 변경이 된 최종 결과를 담는다. 
    // -> 닉변은 들어온 유저가 닉네임을 변경한 경우 or 나갔던 유저가 다른 닉네임으로 들어온 경우 변경된다.
    
    // 문제풀이
    // 1. record를 쭉 돌면서 User를 추가한다.
    // 2. record를 다시 쭉 돌면서 유저 아이디를 검사하면서 최종 닉네임과 입장/퇴장기록에 대해
    // answer에 추가해준다. 

    public String[] solution(String[] record) {
        
        List<String> answerList = new ArrayList<String>();

        HashMap<String, User> userMap = new HashMap<>();
        // User를 추가해주고 닉네임 변경시 유저 객체의 이름을 계속 바꿔준다.

        int listLength = 0;
        for(String now:record){
            // record 한 줄을 단어별로 나눠준다.
            
            String[] recordSplit = now.split(" ");
            // 첫 글자가 Enter냐, Leave냐, Change냐
            // Enter id name
            String id = recordSplit[1];
            if(recordSplit[0].startsWith("E")){
                listLength++;
                String name = recordSplit[2];
                // 첫 로그인이 아닌 경우
                if(userMap.containsKey(id)){
                    //System.out.println("재접속");
                    // 닉네임이 바뀌었는지 확인한다.
                    User forCheck = userMap.get(id);
                    if(forCheck.uName.equals(name)){                        
                        //System.out.println("이전과 닉네임이 같습니다, 전: "+forCheck.uName+", 후: "+name);
                    }else{
                        // 바뀐 닉네임으로 Enter했다면 닉변해준다.
                        
                        forCheck.changeName(name);
                        userMap.replace(id, forCheck);
                    }
                    
                // 첫 로그인인 경우
                }else{
                    User newUser = new User(id, name);
                    userMap.put(id, newUser);
                }
            }else if(recordSplit[0].startsWith("L")){
                listLength++;
                // 채팅방을 나갈 경우 닉네임을 변경할 수 없으므로 넘어간다.
            }else if(recordSplit[0].startsWith("C")){
                String newName = recordSplit[2];
                // 닉변시 그냥 바로 User객체를 받아와서 닉변해준다.
                User forChange = userMap.get(id);
                forChange.changeName(newName);
                userMap.replace(id, forChange);
            }else{
                //System.out.println("Error 0");
            }
        }
        String[] ans = new String[listLength];
        int i=0;
        for(String now:record){
            // record 한 줄을 단어별로 나눠준다.
            String[] recordSplit2 = now.split(" ");
            String nowId = recordSplit2[1];
            if(recordSplit2[0].startsWith("E")){
                // Enter 아이디 닉네임
                answerList.add(userMap.get(nowId).uName + "님이 들어왔습니다.");
                ans[i]=userMap.get(nowId).uName + "님이 들어왔습니다.";
                i++;
    

            }else if(recordSplit2[0].startsWith("C")){
                // 아이디 변경시 메시지에 추가되지 않으므로 넘어간다.
            }else if(recordSplit2[0].startsWith("L")){
                // Leave 아이디
                answerList.add(userMap.get(nowId).uName + "님이 나갔습니다.");
                ans[i]=userMap.get(nowId).uName + "님이 나갔습니다.";
                i++;
            }else{
                //System.out.println("Error 1");
            }

        }
        return ans;
        

    }

    public class User{
        private String uId;
        private String uName;
        public User(String uId, String uName){
            this.uId = uId;
            this.uName = uName;
        }
        public void changeName(String newName){
            //System.out.print("닉네임이 변경되었습니다. old: "+ this.uName);
            this.uName = newName;
            //System.out.println(", new: "+this.uName);
        }
        public boolean isNameEqual(String name){
            // 같으면 false, 다르면 true 리턴
            if(this.uName.equals(name)){
                return false;
            }else{
                return true;
            }
        }
    }
        public static void main(String[] args) {
            Solution sol = new Solution();
            String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo","Leave uid1234","Enter uid1234 Prodo","Change uid4567 Ryan"};
            String[] answer = sol.solution(record);
            for(String ans:answer){
                //System.out.println(ans);
            }
        }
}
