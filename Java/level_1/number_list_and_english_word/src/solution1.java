public class solution1 {
    public int solution(String s) {
        int answer = 0;
        String result = s;
        String[] numEng = {"zero","one", "two", "three", "four", "five", "six", "seven", "eight", "nine"};
        String[] numInt = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
        int i = 0;
        for(String eng : numEng){
            result = result.replaceAll(eng, numInt[i]);
            i++;
        }
        answer = Integer.parseInt(result);
        return answer;
    }
}
