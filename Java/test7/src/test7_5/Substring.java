package test7_5;

public class Substring
{
    public static int subString(String str,String subString)
    {
        int i = 0;
        int count = 0;
        while(i < str.length())
        {
            if(str.indexOf(subString,i) != -1)
            {
                i = str.indexOf(subString,i) + subString.length();
                count++;
            }else
            {
                break;
            }
        }
        return count;
    }
    public static void main(String[] args)
    {
        String s1 = "aaabcagababccbabc";
        String s2 = "abc";
        System.out.println(Substring.subString(s1, s2));
    }
}
