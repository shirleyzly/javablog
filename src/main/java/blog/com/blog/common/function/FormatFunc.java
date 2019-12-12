package blog.com.blog.common.function;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FormatFunc {

    /**
     * 获取日期所在周几
     * @return
     */
    public static String getWeekofDate(Date date) {
        SimpleDateFormat dateFm = new SimpleDateFormat("EEEE");
        return dateFm.format(date);
    }

    /**
     * 获取当前日期的年月日周几
     * @return
     */
    public static String getDateOfYmdAndWeek() {
        SimpleDateFormat simdf = new SimpleDateFormat("yyyy年MM月dd日");
        Calendar cal = Calendar.getInstance();
        String ymd = simdf.format(cal.getTime());
        String week = getWeekofDate(new Date());
        return ymd+week;
    }
}
