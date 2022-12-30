package test4_2;

import org.junit.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Set;

public class CalendarTest
{
    @Test
    public void Calendar()
    {
        //实例化
        Calendar calendar = Calendar.getInstance();
        Calendar c = new GregorianCalendar();

        //get()
        int days1 = calendar.get(Calendar.DAY_OF_MONTH);
        int days2 = calendar.get(Calendar.DAY_OF_YEAR);
        int days3 = calendar.get(Calendar.DAY_OF_WEEK);

        //set()
        calendar.set(Calendar.DAY_OF_YEAR,2);//改成1月2号

        //add()
        calendar.add(Calendar.DAY_OF_YEAR,-1);//1月2号减1天，改成1月1号

        //getTime(): Calendar->Date
        Date date1 = calendar.getTime();

        //setTime: Date->Calendar
        Date date2 = new Date();
        calendar.setTime(date2);
    }
}
