package Java;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Topic_05_User {

	public static void main(String[] args) throws ParseException {
		Topic_04 topic04;
		topic04 = new Topic_04();
		System.out.println(topic04.getFullName());
		topic04.setName("Nguyen Thi Hoai Linh");
		System.out.print(topic04.getFullName());
		
		String date = "20/08/2020";
		SimpleDateFormat input = new SimpleDateFormat("dd/MM/yyyy");
		Date datetime = input.parse(date);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		System.out.println(format.format(datetime));
		
	}

}
