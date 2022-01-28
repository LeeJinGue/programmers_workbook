import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BAEK_9342 {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 규칙을 지키는지 확인하는 정규식표현
        String regex = "^[A-F]{0,1}A+F+C+[ABCDEF]{0,1}$";
        Pattern ptn = Pattern.compile(regex);        
        Matcher matcher;
        
        try {
            int num = Integer.parseInt(br.readLine());
            // System.out.println(num+"");
            for(int i = 0; i<num; i++){
                String line = br.readLine();
                matcher = ptn.matcher(line);
                if(matcher.find()){
                    bw.append("Infected!" +"\n");
                }else{
                    bw.append("Good" + "\n");
                }
            }
            bw.flush();
            bw.close();
            br.close();
            
        } catch (Exception e) {
            System.out.print(e);
        }{

        }
        
    }
}
