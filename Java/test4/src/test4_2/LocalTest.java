package test4_2;

import org.junit.Test;

import java.math.BigInteger;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;

public class LocalTest
{
    @Test
    public void Local()
    {
        //now()
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime1 = LocalDateTime.now();
        LocalDateTime localDateTime2 = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));

        //of()
        LocalDateTime localDateTime3 = LocalDateTime.of(2022,2,12,15,57,00);

        //getXxx()
        int num1 = localDateTime1.getDayOfMonth();
        Month m = localDateTime1.getMonth();//返回Month枚举值，对应月份的英文
        int num3 = localDateTime1.getMonthValue();//返回int值，几月就是数字几
        DayOfWeek d = localDateTime1.getDayOfWeek();//返回DayOfWeek枚举值，对应星期的英文
        int num4 = localDateTime1.getHour();

        //withXxx()
        //由于其不可变性，会返回一个新的对象，原来的对象不变
        LocalDateTime localDateTime4 = localDateTime1.withMonth(1);//在新对象中改成1月

        //plusXxx()
        LocalDateTime localDateTime5 = localDateTime1.plusDays(3);//在新对象中增加3天
        //minusXxx()
        LocalDateTime localDateTime6 = localDateTime1.minusHours(5);//在新对象中减少5小时
    }

    @Test
    public void Instant()
    {
        Instant instant1 = Instant.now();

        Instant instant2 = Instant.ofEpochMilli(9876543210L);

        OffsetDateTime offsetDateTime2 = instant1.atOffset(ZoneOffset.ofHours(8));//较UTC快8小时

        long l = instant1.toEpochMilli();
    }

    @Test
    public void DateTimeFormatterTest()
    {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDateTime localDateTime = LocalDateTime.now();
        //格式化
        String str1 = dateTimeFormatter.format(localDateTime);
        //解析
        TemporalAccessor localDateTime1 = dateTimeFormatter.parse(str1);


        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
                                                                //注意不要用错方法，若像操作LocalDate则用ofLocalizedDate
        LocalDateTime localDateTime2 = LocalDateTime.now();
        //格式化
        String str2 = dateTimeFormatter1.format(localDateTime2);
        //解析
        TemporalAccessor localDateTime3 = dateTimeFormatter1.parse(str2);

        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime4 = LocalDateTime.now();
        //格式化
        String str3 = dateTimeFormatter2.format(localDateTime4);
        //解析
        TemporalAccessor localDateTime5 = dateTimeFormatter2.parse("2022-02-12 14:20:59");

    }
}
