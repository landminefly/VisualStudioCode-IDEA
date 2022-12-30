package test2_1;

public class test2_1_1
{
    // public static void main(String[] args)
    // {
        // System.out.println("hello world!");

        // String[] Asoul = new String[]{"Diana","Ava"};//静态初始化
        // int[] nums = new int[5];//动态初始化
        // //P.S. 上述方法只能二选一，不能写成类似 new int[5]{1,2,3,4,5};

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
        // //与C语言不同，确定数组大小时可以使用变量

        //静态初始化
        // int[][] arr1 = new int[][]{{1,2,3},{4,5},{6,7,8,9}};

        // //动态初始化1
        // float[][] arr2 = new float[3][4];
        // //动态初始化2 & 赋值
        // String[][]arr3 = new String[3][];
        // arr3[0] = new String[]{"hello","world"};
        //
        // int[][] arr1 = new int[][]{{1,2,3},{4,5},{6,7,8,9}};
        // //二维数组的数组长度
        // System.out.println(arr1.length);//3
        // System.out.println(arr1[2].length);//4

        // int[][] arr = new int[4][];
        // arr[1] = new int[]{1,2,3};
        // arr[2] = new int[4];
        // arr[2][1] = 30;


        // int[][] arr1 = new int[3][4];
        // System.out.println(arr1);// [[I@119d7047
        //                          // [[ 意思是二维数组， I 意思是int类型，@ 后边跟着的是地址
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
        // //创建类的对象
        // Person p1 = new Person();
        //
        // //调用对象的属性和方法
        // p1.name = "Nobody";
        // p1.isMale = true;
        // p1.age = 18;
        // System.out.print("此人名为" + p1.name + "，" + p1.age + "岁，");
        // if(p1.isMale)
        //     System.out.println("男。");
        // else
        //     System.out.println("女。");
        //
        // p1.eat();
        // p1.sleep();
        // p1.talk("中文");

        // Person p1 = new Person();
        // p1.name = "Nobody";
        // p1.isMale = true;
        // Person p2 = new Person();
        // Person p3 = p1;
        // p3.age = 10;

        // new Person().sleep();//匿名对象

        PersonDaily pd = new PersonDaily();
        pd.toDo(new Person());
    }

}

class PersonDaily
{
    public void toDo(Person p)
    {
        p.sleep();
        p.talk("中文");
    }
}

class Person
{
    //属性
    String name;
    int age;
    boolean isMale;

    //方法
    public void eat(boolean isFull)
    {
        if(isFull)
        {
            System.out.println("吃不下了");
        }
        else
        {
            System.out.println("啊哈哈哈哈哈哈鸡汤来咯！");
        }
    }
    public void sleep()
    {
        System.out.println("耽误的时间太多，事情可就做不完了！");
    }
    public void talk(String language)
    {
        System.out.println("大佐级的" + language);
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
