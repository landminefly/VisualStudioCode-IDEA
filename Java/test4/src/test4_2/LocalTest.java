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
        Month m = localDateTime1.getMonth();//����Monthö��ֵ����Ӧ�·ݵ�Ӣ��
        int num3 = localDateTime1.getMonthValue();//����intֵ�����¾������ּ�
        DayOfWeek d = localDateTime1.getDayOfWeek();//����DayOfWeekö��ֵ����Ӧ���ڵ�Ӣ��
        int num4 = localDateTime1.getHour();

        //withXxx()
        //�����䲻�ɱ��ԣ��᷵��һ���µĶ���ԭ���Ķ��󲻱�
        LocalDateTime localDateTime4 = localDateTime1.withMonth(1);//���¶����иĳ�1��

        //plusXxx()
        LocalDateTime localDateTime5 = localDateTime1.plusDays(3);//���¶���������3��
        //minusXxx()
        LocalDateTime localDateTime6 = localDateTime1.minusHours(5);//���¶����м���5Сʱ
    }

    @Test
    public void Instant()
    {
        Instant instant1 = Instant.now();

        Instant instant2 = Instant.ofEpochMilli(9876543210L);

        OffsetDateTime offsetDateTime2 = instant1.atOffset(ZoneOffset.ofHours(8));//��UTC��8Сʱ

        long l = instant1.toEpochMilli();
    }

    @Test
    public void DateTimeFormatterTest()
    {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ISO_LOCAL_DATE;
        LocalDateTime localDateTime = LocalDateTime.now();
        //��ʽ��
        String str1 = dateTimeFormatter.format(localDateTime);
        //����
        TemporalAccessor localDateTime1 = dateTimeFormatter.parse(str1);


        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);
                                                                //ע�ⲻҪ�ô������������LocalDate����ofLocalizedDate
        LocalDateTime localDateTime2 = LocalDateTime.now();
        //��ʽ��
        String str2 = dateTimeFormatter1.format(localDateTime2);
        //����
        TemporalAccessor localDateTime3 = dateTimeFormatter1.parse(str2);

        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime localDateTime4 = LocalDateTime.now();
        //��ʽ��
        String str3 = dateTimeFormatter2.format(localDateTime4);
        //����
        TemporalAccessor localDateTime5 = dateTimeFormatter2.parse("2022-02-12 14:20:59");

    }
}
