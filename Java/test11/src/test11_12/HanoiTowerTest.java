package test11_12;

public class HanoiTowerTest {
    public static void main(String[] args) {
        HanoiTower.solve(3, 'A', 'B', 'C');
    }
}

class HanoiTower {
    /**
     * 将a柱上面的num个盘子移到c柱，需借助b柱
     * 思路：先将a柱上面的num-1个盘子递归地移到b柱，然后将a柱的第num个盘子移到c柱，
     * 最后再将先前移到b柱的num-1个盘子递归地移到c柱
     * @param num a柱需移动的盘子个数
     *            注意：a柱、b柱、c柱都是变量，它们所具体代表的柱子还得看传入的形参
     */
    public static void solve(int num, char a, char b, char c) {
        if (num == 1) {
            //如果只需移动1个盘子，那就直接将其从a柱移动到c柱
            System.out.println("盘1: " + a + " -> " + c);
        } else {
            //先将a柱上面的num-1个盘子递归地移到b柱
            //此处的递归调用，实际上在反复调换第三个和第四个参数
            solve(num - 1, a, c, b);
            //然后将a柱的第num个盘子移到c柱
            System.out.println("盘" + num + ": " + a + " -> " + c);
            //最后再将先前移到b柱的num-1个盘子递归地移到c柱
            //此处的递归调用，实际上在反复调换第二个和第三个参数
            solve(num - 1, b, a, c);
        }
    }
}
