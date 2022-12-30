package test4_2;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.TemporalAccessor;
import java.util.Date;

/**
 * jdk 8������ʱ��API�Ĳ���
 *
 * @author shkstart
 * @create 2019 ���� 2:44
 */
public class JDK8DateTimeTest {

    @Test
    public void testDate(){
        //ƫ����
        Date date1 = new Date(2020 - 1900,9 - 1,8);
        System.out.println(date1);//Tue Sep 08 00:00:00 GMT+08:00 2020
    }

    /*
    LocalDate��LocalTime��LocalDateTime ��ʹ��
    ˵����
        1.LocalDateTime�����LocalDate��LocalTime��ʹ��Ƶ��Ҫ��
        2.������Calendar
     */
    @Test
    public void test1(){
        //now():��ȡ��ǰ�����ڡ�ʱ�䡢����+ʱ��
        LocalDate localDate = LocalDate.now();
        LocalTime localTime = LocalTime.now();
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime6 = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));

        System.out.println(localDate);
        System.out.println(localTime);
        System.out.println(localDateTime);

        //of():����ָ�����ꡢ�¡��ա�ʱ���֡��롣û��ƫ����
        LocalDateTime localDateTime1 = LocalDateTime.of(2020, 10, 6, 13, 23, 43);
        System.out.println(localDateTime1);


        //getXxx()����ȡ��ص�����
        System.out.println(localDateTime.getDayOfMonth());
        System.out.println(localDateTime.getDayOfWeek());
        System.out.println(localDateTime.getMonth());
        System.out.println(localDateTime.getMonthValue());
        System.out.println(localDateTime.getMinute());

        //���ֲ��ɱ���
        //withXxx():������ص�����
        LocalDate localDate1 = localDate.withDayOfMonth(22);
        System.out.println(localDate);
        System.out.println(localDate1);


        LocalDateTime localDateTime2 = localDateTime.withHour(4);
        System.out.println(localDateTime);
        System.out.println(localDateTime2);

        //���ɱ���
        LocalDateTime localDateTime3 = localDateTime.plusMonths(3);
        System.out.println(localDateTime);
        System.out.println(localDateTime3);

        LocalDateTime localDateTime4 = localDateTime.minusDays(6);
        System.out.println(localDateTime);
        System.out.println(localDateTime4);
    }

    /*
    Instant��ʹ��
    ������ java.util.Date��

     */
    @Test
    public void test2(){
        //now():��ȡ���������߶�Ӧ�ı�׼ʱ��
        Instant instant = Instant.now();
        System.out.println(instant);//2019-02-18T07:29:41.719Z

        //���ʱ���ƫ����
        OffsetDateTime offsetDateTime = instant.atOffset(ZoneOffset.ofHours(8));
        System.out.println(offsetDateTime);//2019-02-18T15:32:50.611+08:00

        //toEpochMilli():��ȡ��1970��1��1��0ʱ0��0�루UTC����ʼ�ĺ�����  ---> Date���getTime()
        long milli = instant.toEpochMilli();
        System.out.println(milli);

        //ofEpochMilli():ͨ�������ĺ���������ȡInstantʵ��  -->Date(long millis)
        Instant instant1 = Instant.ofEpochMilli(1550475314878L);
        System.out.println(instant1);
    }

    /*
    DateTimeFormatter:��ʽ����������ڡ�ʱ��
    ������SimpleDateFormat

     */

    @Test
    public void test3(){
//        ��ʽһ��Ԥ����ı�׼��ʽ���磺ISO_LOCAL_DATE_TIME;ISO_LOCAL_DATE;ISO_LOCAL_TIME
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        //��ʽ��:����-->�ַ���
        LocalDateTime localDateTime = LocalDateTime.now();
        String str1 = formatter.format(localDateTime);
        System.out.println(localDateTime);
        System.out.println(str1);//2019-02-18T15:42:18.797

        //�������ַ��� -->����
        TemporalAccessor parse = formatter.parse("2019-02-18T15:42:18.797");
        System.out.println(parse);

//        ��ʽ����
//        ���ػ���صĸ�ʽ���磺ofLocalizedDateTime()
//        FormatStyle.LONG / FormatStyle.MEDIUM / FormatStyle.SHORT :������LocalDateTime
        DateTimeFormatter formatter1 = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);
        //��ʽ��
        String str2 = formatter1.format(localDateTime);
        System.out.println(str2);//2019��2��18�� ����03ʱ47��16��


//      ���ػ���صĸ�ʽ���磺ofLocalizedDate()
//      FormatStyle.FULL / FormatStyle.LONG / FormatStyle.MEDIUM / FormatStyle.SHORT : ������LocalDate
        DateTimeFormatter formatter2 = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
        //��ʽ��
        String str3 = formatter2.format(LocalDate.now());
        System.out.println(str3);//2019-2-18


//       �ص㣺 ��ʽ�����Զ���ĸ�ʽ���磺ofPattern(��yyyy-MM-dd hh:mm:ss��)
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
        //��ʽ��
        String str4 = formatter3.format(LocalDateTime.now());
        System.out.println(str4);//2019-02-18 03:52:09

        //����
        TemporalAccessor accessor = formatter3.parse("2019-02-18 03:52:09");
        System.out.println(accessor);

    }

}
