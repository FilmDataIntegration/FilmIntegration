package czw.util;

import java.sql.Date;
import java.util.Calendar;

public class DateUtil {
	public static int getGap(Date checkin) {  
        Calendar cal = Calendar.getInstance();  
  
        if (cal.after(checkin)) {  
            throw new IllegalArgumentException("Check-in Time should be in future!");  
        }  
        long now = cal.getTimeInMillis();
        cal.setTime(checkin);
        long future = cal.getTimeInMillis();
        return (int) ((future - now) / 86400000);  
    }
}
