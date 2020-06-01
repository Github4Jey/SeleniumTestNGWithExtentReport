package libFunctions;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;

public class SeleniumGenericReusableFns {
	
	public static void main(String[] args) throws Exception {
		addorsubtracthrswithdate();
		timediffinmillisecs();
		getcurrentday();
		// TODO Auto-generated method stub
		
		Date date = new Date();
		Timestamp timestamp1 = new Timestamp(date.getTime());
		//int hrs = timestamp1.getHours();
		//int mins = timestamp1.getMinutes();
		//int secs = timestamp1.getSeconds();

		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(timestamp1.getTime());

		// add a bunch of seconds to the calendar
		cal.add(Calendar.SECOND, 98765);

		// create a second time stamp
		Timestamp timestamp2 = new Timestamp(cal.getTime().getTime());

		long milliseconds = timestamp2.getTime() - timestamp1.getTime();
		int seconds = (int) milliseconds / 1000;

		int hours = seconds / 3600;
		int minutes = (seconds % 3600) / 60;
		seconds = (seconds % 3600) % 60;

		System.out.println("timestamp1: " + timestamp1);
		System.out.println("timestamp2: " + timestamp2);

		System.out.println("Difference: ");
		System.out.println(" Hours: " + hours);
		System.out.println(" Minutes: " + minutes);
		System.out.println(" Seconds: " + seconds);
	

	}
	
	public static void timediffinmillisecs() throws Exception {
		String dateStart = "01/14/2012 09:29:58";
		String dateStop = "01/15/2012 10:31:48";

		//HH converts hour in 24 hours format (0-23), day calculation
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

		Date d1 = null;
		Date d2 = null;

		
			d1 = format.parse(dateStart);
			d2 = format.parse(dateStop);

			//in milliseconds
			long diff = d2.getTime() - d1.getTime();

			long diffSeconds = diff / 1000 % 60;
			long diffMinutes = diff / (60 * 1000) % 60;
			long diffHours = diff / (60 * 60 * 1000) % 24;
			long diffDays = diff / (24 * 60 * 60 * 1000);

			System.out.print(diffDays + " days, ");
			System.out.print(diffHours + " hours, ");
			System.out.print(diffMinutes + " minutes, ");
			System.out.print(diffSeconds + " seconds.");
	}
	
	public static void addorsubtracthrswithdate() {
		Date date = new Date();
		
		System.out.println(date);
		Date newDate = DateUtils.addHours(date, 3);
		System.out.println(newDate);
		Date annewdate = DateUtils.addHours(newDate, -2);
		System.out.println(annewdate);
		Date newDatec = DateUtils.addMinutes(annewdate, 65);
		System.out.println(newDatec);
			
	}
	
	public void addhrminsswithDate() {
		Calendar calendar = Calendar.getInstance();
        System.out.println("Original = " + calendar.getTime());
 
        // Substract 2 hour from the current time
        calendar.add(Calendar.HOUR, -2);
 
        // Add 30 minutes to the calendar time
        calendar.add(Calendar.MINUTE, 30);
 
        // Add 300 seconds to the calendar time
        calendar.add(Calendar.SECOND, 300);
        System.out.println("Updated  = " + calendar.getTime());
        
        //another way using calendar
        
        Date d1 = new Date();
        Calendar cl = Calendar. getInstance();
        cl.setTime(d1);
        System.out.println("today is " + d1.toString());
        cl. add(Calendar.MONTH, 1);
        System.out.println("date after a month will be " + cl.getTime().toString() );
        cl. add(Calendar.HOUR, 70);
        System.out.println("date after 7 hrs will be " + cl.getTime().toString() );
        cl. add(Calendar.YEAR, 3);
        System.out.println("date after 3 years will be " + cl.getTime().toString() );
	}
	
	public static void getcurrentday() {
		  Date now = new Date();
		  
	        SimpleDateFormat simpleDateformat = new SimpleDateFormat("E"); // the day of the week abbreviated
	        System.out.println(simpleDateformat.format(now));
	 
	        simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
	        System.out.println(simpleDateformat.format(now));
	 
	        Calendar calendar = Calendar.getInstance();
	        calendar.setTime(now);
	        System.out.println(calendar.get(Calendar.DAY_OF_WEEK)); 
	}


}
