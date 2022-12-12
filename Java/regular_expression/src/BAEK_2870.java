import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BAEK_2870 {
    // 첫 라인은 줄의 수
    // 숫자, 알파벳 소문자 N줄
    // 숫자를 모두 찾고 숫자를 비내림차순으로 정리
    // 숫자의 앞에 0이 있을 때는 정리하면서 생략 가능
    // -> 0으로 시작되는 숫자는 0 생략
    // 글자를 살펴보다가 숫자가 나오는 경우에는 가능한 가장 큰 숫자를 찾아야 한다.
    // -> 문자 숫자 문자 이런식으로 or 줄의 시작/끝
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        List<Integer> numArray = new ArrayList<Integer>();
        List<String> strArray = new ArrayList<String>();

        try {
            // 입력받은 줄 수
            int num = Integer.parseInt(br.readLine());

            // 숫자가 반복되는 경우를 찾기 위한 정규표현식
            String regex = "\\d+";
            Pattern ptn = Pattern.compile(regex);

            for(int i=0; i<num; i++){
                String line = br.readLine();
                Matcher matcher = ptn.matcher(line);

                // 반복되는 숫자들을 찾을 때마다 numArray에 추가한다.
                while(matcher.find()){
                    numArray.add(Integer.parseInt(matcher.group(0)));
                    String s = matcher.group(0);
                    // 앞자리 0 생략
                    s = s.replace("^0+", "");
                    strArray.add(matcher.group(0));
                }
            }
            // 다 찾은 후 오름차순으로 정렬해준다.
            Collections.sort(numArray);

            // 결과 출력
            for(int i: numArray){
                bw.write(i + "\n");
            }
            bw.flush();
            bw.close();
            br.close();
            
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}
