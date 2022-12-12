package src;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Pattern;
public class solution_main {
    public static void main(String[] args) throws Exception {
        // Solution1 sol = new Solution1();
		// int[] lottos = {19, 1, 45, 31, 10, 6};
		// int[] win_nums = {31, 10, 45, 1, 6, 19};
		// int[] answer = sol.solution(lottos, win_nums);
		// System.out.println(Arrays.toString(answer));

		String time = "2022-05-31 13:00:00";
		// System.out.println(getYearFromDateString(time)+"년");
		// System.out.println(getMonthFromDateString(time)+"월");
		// System.out.println(getDayFromDateString(time)+"일");
		// System.out.println(getHourFromDateString(time)+"시");
		// System.out.println(getMinuteFromDateString(time)+"분");
		// System.out.println(getDateFromDateString(time));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
		DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		Date date = null;
		date = format.parse(time);
		
        System.out.println(sdf.format(date));
        System.out.println(getNextDate(time));
		LocalDate nowDate = LocalDate.now();
		LocalDateTime nowDateTime = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String dateString = nowDateTime.format(formatter);
		System.out.println("ofPattern: "+dateString);
		String numString = "1";
		System.out.println(numString + " is "+ (isInteger(numString) == true ? "integer" : "not integer"));
		String ten = "010";
		int tenInt = Integer.parseInt(ten);
		int tenInt2 = Integer.valueOf(ten);
		System.out.println("tenint: "+String.valueOf(tenInt)+", tenInt2: "+String.valueOf(tenInt2));
		String stuffList = "지갑,핸드폰,버즈라이브,테스트1,테스트 2";
		String[] stuffs = stuffList.split(",");
		ArrayList<String> stuffArrayList = new ArrayList<String>(Arrays.asList(stuffs));
		stuffArrayList.add("안녕");
		stuffArrayList.remove(0);
		System.out.println("stuffArrayList: "+stuffArrayList.toString());

		String list = "[asdf,zxcv]";
		// String pattern = "(\[)(\])";
	
		System.out.println(list.replace("[", "").replace("]",""));
    }
	Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
	public static boolean isInteger(String strNum){
		if(strNum == null){
			return false;
		}
		try{
			int i = Integer.parseInt(strNum);
		}catch(NumberFormatException nfe){
			return false;
		}
		return true;
	}
	public static Date getConvertStringToDate(String dateString){
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try{
			date = format.parse(dateString);
		}catch (ParseException e){
			e.printStackTrace();
		}
		return date;
	}
	public static String getNextDate(String currentDate){
		Calendar cal = Calendar.getInstance();
		cal.setTime(getConvertStringToDate(currentDate));
		cal.add(cal.DATE, +1);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(cal.getTime());
	}
	public static String getYearFromDateString(String time){
		return time.substring(0,4);
	}
	public static String getMonthFromDateString(String time){
		return time.substring(5,7);
	}
	public static String getDayFromDateString(String time){
		return time.substring(8,10);
	}
	public static String getHourFromDateString(String time){
		return time.substring(11, 13);
	}
	public static String getMinuteFromDateString(String time){
		return time.substring(14, 16);
	}
	public static String getDateFromDateString(String time){
        return time.substring(0,10);
    }
}
