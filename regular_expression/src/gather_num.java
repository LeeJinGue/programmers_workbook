import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class gather_num {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        while(!text.equals("#")){
            System.out.println(get_gather_num(text));
            text = scanner.nextLine();
        }
    }
    public static int get_gather_num(String text){
        int gather_num = 0;
        String regex = "[aeiouAEIOU]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);
        while(matcher.find()){
            gather_num++;
            // System.out.println("모음을 찾았습니다: "+matcher.group(0));
        }
        return gather_num;
    }
}
