import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class changYoung_diary {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String text = scanner.nextLine();
        String[] words = text.split(" ");

        StringBuilder sb = new StringBuilder();
        for(String word : words){
            sb.append(get_origin_diary(word) + " ");
        }
        sb.deleteCharAt(sb.length()-1);
        System.out.print(sb);
        scanner.close();
    }
    public static String get_origin_diary(String code){
        String regex = "[aeiou][p][aeiou]";
        Pattern ptn = Pattern.compile(regex);
        Matcher matcher = ptn.matcher(code);
        String origin = code;
        while(matcher.find()){
            String old = matcher.group(0);
            String New =  Character.toString(matcher.group(0).charAt(0));
            if(old.charAt(0) == old.charAt(2)){
                origin = origin.replaceAll(old,New);
            }
        }
        return origin;
    }
}
