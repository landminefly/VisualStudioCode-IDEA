package test2_1;

public class test2_1_1
{
    // public static void main(String[] args)
    // {
        // System.out.println("hello world!");

        // String[] Asoul = new String[]{"Diana","Ava"};//��̬��ʼ��
        // int[] nums = new int[5];//��̬��ʼ��
        // //P.S. ��������ֻ�ܶ�ѡһ������д������ new int[5]{1,2,3,4,5};

        // int[] nums = new int[5];
        // for(int i = 0; i<5; i++)
        // {
        //     nums[i] = i;
        // }

        // int[] nums = new int[5];
        // System.out.println( nums.length );//5

        // String[] Asoul = new String[2];
        // Asoul[0] = "Diana";
        // Asoul[1] = "Ava";
        // Asoul = new String[3];

        // int i = 20;
        // int[] nums = new int[i];
        // //��C���Բ�ͬ��ȷ�������Сʱ����ʹ�ñ���

        //��̬��ʼ��
        // int[][] arr1 = new int[][]{{1,2,3},{4,5},{6,7,8,9}};

        // //��̬��ʼ��1
        // float[][] arr2 = new float[3][4];
        // //��̬��ʼ��2 & ��ֵ
        // String[][]arr3 = new String[3][];
        // arr3[0] = new String[]{"hello","world"};
        //
        // int[][] arr1 = new int[][]{{1,2,3},{4,5},{6,7,8,9}};
        // //��ά��������鳤��
        // System.out.println(arr1.length);//3
        // System.out.println(arr1[2].length);//4

        // int[][] arr = new int[4][];
        // arr[1] = new int[]{1,2,3};
        // arr[2] = new int[4];
        // arr[2][1] = 30;


        // int[][] arr1 = new int[3][4];
        // System.out.println(arr1);// [[I@119d7047
        //                          // [[ ��˼�Ƕ�ά���飬 I ��˼��int���ͣ�@ ��߸��ŵ��ǵ�ַ
        // System.out.println(arr1[0]);// [I@776ec8df

        // int[][] arr2 = new int[3][];
        // System.out.println(arr2);// [[I@4eec7777
        // System.out.println(arr2[0]);// null

        // int[][] arr2 = arr1;

        // int[] arr = new int[9];
        // int sum = 0;
        // for(int i = 0; i<arr.length; i++)
        // {
        //     arr[i] = (int)(Math.random()*100);
        //     sum+= arr[i];
        // }
        // System.out.println(sum);
        // System.out.println(sum/(double)arr.length);

        // Scanner scan = new Scanner(System.in);
        // int row = scan.nextInt();
        //
        // int[][] arr = new int[row][];
        // for(int i = 0; i<arr.length; i++)
        // {
        //     arr[i] = new int[i+1];
        //     for(int j = 0; j<arr[i].length; j++)
        //     {
        //         if(j == arr[i].length-1 || j == 0)
        //         {
        //             arr[i][j] = 1;
        //         }
        //         else
        //         {
        //             arr[i][j] = arr[i-1][j-1] + arr[i-1][j];
        //         }
        //     }
        // }
        //
        // for(int i = 0; i<arr.length; i++)
        // {
        //     for(int m = i; m<arr.length; m++)
        //     {
        //         System.out.print("  ");
        //     }
        //     for(int j = 0; j<arr[i].length; j++)
        //     {
        //         System.out.print(arr[i][j]+"   ");
        //     }
        //     System.out.println();
        // }
        //
        // int[] arr = new int[10];
        // for(int i = 0; i<arr.length; i++)
        // {
        //     arr[i] = (int)(Math.random() * 100);
        // }
        // //bubble sort
        // for(int i = 0; i<arr.length-1; i++)
        // {
        //     for(int j = 0; j<arr.length-1-i; j++)
        //     {
        //         if(arr[j] > arr[j+1])
        //         {
        //             int temp = arr[j];
        //             arr[j] = arr[j+1];
        //             arr[j+1] = temp;
        //         }
        //     }
        // }
        //
        // for (int j : arr)
        // {
        //     System.out.print(j + "\t");
        // }
        //

        // int[] arr1 = new int[]{1,2,3,4,5};
        // int[] arr2 = copyOf(arr1,5);

    // }

    public static void main(String[] args)
    {
        // //������Ķ���
        // Person p1 = new Person();
        //
        // //���ö�������Ժͷ���
        // p1.name = "Nobody";
        // p1.isMale = true;
        // p1.age = 18;
        // System.out.print("������Ϊ" + p1.name + "��" + p1.age + "�꣬");
        // if(p1.isMale)
        //     System.out.println("�С�");
        // else
        //     System.out.println("Ů��");
        //
        // p1.eat();
        // p1.sleep();
        // p1.talk("����");

        // Person p1 = new Person();
        // p1.name = "Nobody";
        // p1.isMale = true;
        // Person p2 = new Person();
        // Person p3 = p1;
        // p3.age = 10;

        // new Person().sleep();//��������

        PersonDaily pd = new PersonDaily();
        pd.toDo(new Person());
    }

}

class PersonDaily
{
    public void toDo(Person p)
    {
        p.sleep();
        p.talk("����");
    }
}

class Person
{
    //����
    String name;
    int age;
    boolean isMale;

    //����
    public void eat(boolean isFull)
    {
        if(isFull)
        {
            System.out.println("�Բ�����");
        }
        else
        {
            System.out.println("������������������������");
        }
    }
    public void sleep()
    {
        System.out.println("�����ʱ��̫�࣬����ɾ��������ˣ�");
    }
    public void talk(String language)
    {
        System.out.println("��������" + language);
    }

    // public void getSum(String s, int i)
    // {}
    //
    // public void getSum(int i, String s)
    // {}

    // public int getSum(int ... i)
    // {
    //     int sum = 0;
    //     for(int j = 0; j<i.length; j++)
    //     {
    //         sum+= i[j];
    //     }
    //     return sum;
    // }

}
