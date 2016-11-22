package xmltoXls;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDT {

	public static void main(String[] args) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MMMM/yyyy");
		Date date = new Date(System.currentTimeMillis());
		String s = sdf.format(date);
		System.out.println(DateFormat.getInstance().parse(s));
	}

}
