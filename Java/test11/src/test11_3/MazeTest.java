package test11_3;

public class MazeTest {
    public static void main(String[] args) {
        int[][] map = new int[10][10];
        //放置墙壁
        for (int i = 0; i < 10; i++) {
            map[i][0] = 1;
            map[i][9] = 1;
        }
        for (int i = 0; i < 10; i++) {
            map[0][i] = 1;
            map[9][i] = 1;
        }
        map[0][5] = 1;
        map[1][5] = 1;
        map[2][5] = 1;
        map[3][5] = 1;
        map[4][5] = 1;
        map[5][5] = 1;
        map[6][5] = 1;

        map[9][7] = 1;
        map[8][7] = 1;
        map[7][7] = 1;
        map[6][7] = 1;
        map[5][7] = 1;
        map[4][7] = 1;
        map[3][7] = 1;

        //新建迷宫对象，并计算其通路
        Maze maze = new Maze(map, 1, 1, 8, 8);
        maze.printMap();
        boolean flag = maze.startSolve();
        if (flag) {
            System.out.println();
            System.out.println("寻路成功！");
            maze.printMap();
        } else {
            System.out.println("寻路失败！");
        }
    }
}

class Maze {
    int[][] map;
    //迷宫的行数
    int rowMax;
    //迷宫的列数
    int colMax;
    //迷宫的起点行坐标
    int rowStart;
    //迷宫的起点列坐标
    int colStart;
    //迷宫的终点行坐标
    int rowEnd;
    //迷宫的终点列坐标
    int colEnd;

    public Maze(int[][] map, int rowStart, int colStart, int rowEnd, int colEnd) {
        //先判断起点、终点坐标是否合法。如果起点终点坐标是墙壁，也视为非法坐标
        if ((rowStart < 0 || rowStart >= map.length || colStart < 0 || colStart >= map[0].length) ||
            (rowEnd < 0 || rowEnd >= map.length || colEnd < 0 || colEnd >= map[0].length) ||
            (map[rowStart][colStart] == 1 || map[rowEnd][colEnd] == 1)) {
            throw new RuntimeException("传入数据有误！");
        }
        this.map = map;
        this.rowStart = rowStart;
        this.colStart = colStart;
        this.rowEnd = rowEnd;
        this.colEnd = colEnd;
        this.rowMax = map.length;
        this.colMax = map[0].length;
    }

    //打印地图
    public void printMap() {
        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    //要想解出通路，调用此方法即可开始递归。若没有通路，会返回false
    public boolean startSolve() {
        //从起点开始走，因此传入的是起点的坐标
        return solveRecursion(rowStart, colStart);
    }

    //主要思想就集中在该方法中
    //row表示当前行坐标，col表示当前列坐标
    public boolean solveRecursion(int row, int col) {
        //如果终点坐标为2，就说明已经到达终点，通路已找到，返回true
        if (map[rowEnd][colEnd] == 2) {
            return true;
        } else {
            if (map[row][col] == 0) {
                //对于没走过的坐标，先假设可以通过它走到终点
                map[row][col] = 2;
                //然后根据约定，按照右下左上的顺序寻找通路，如果最终在某方向上找到通路，则返回true
                if (solveRecursion(row, col + 1)) {
                    return true;
                } else if (solveRecursion(row + 1, col)) {
                    return true;
                } else if (solveRecursion(row, col - 1)) {
                    return true;
                } else if (solveRecursion(row - 1, col)) {
                    return true;
                } else {
                    //如果四周都走不通，那么就认为不能通过它走到终点，返回false
                    map[row][col] = 3;
                    return false;
                }
            } else {
                //如果不是没走过的坐标，那就是墙、已走过的可能通路、已走过的死路
                //这些坐标都不能走，一律返回false
                return false;
            }
        }
    }
}
