package test11_3;

public class EightQueenTest {
    public static void main(String[] args) {
        int[] position = new int[8];
        Queen queen = new Queen(position);
        queen.startSolve();
        System.out.println("解法共有:"+queen.count+"种");
    }
}

class Queen {
    //棋盘大小为 maxSize*maxSize，棋子个数为 maxSize
    int maxSize;
    //棋盘，因为每行只能放一个棋子，所以可以用一维数组来表示棋盘
    //第n个元素的值m就表示第n行的棋子放在第m列
    int[] position;
    //解法的数量
    int count = 0;

    public Queen(int[] position) {
        this.position = position;
        this.maxSize = position.length;
    }

    //要想开始求解，调用此方法即可开始递归
    public void startSolve() {
        attempt(0);
    }

    //主要思想就集中在该方法中
    //row表示当前行数
    public void attempt(int row) {
        //如果row == maxSize，那么此时行数已经超出棋盘，表示一种解法已找到
        //那么也就无需也不能循环了，直接返回即可
        if (row == maxSize) {
            printChessBoard();
            count++;
            return;
        }
        //循环，在当前行的每列依次尝试放上棋子，并检查是否与之前 row - 1 行的棋子冲突
        //如果冲突，则进入下一列；如果不冲突，则就假定把棋子放在这里，并进入下一行，开始下一行的循环
        //循环完成后，该方法就执行完毕，会返回到上一行，继续上一行的循环
        for (int col = 0; col < maxSize; col++) {
            position[row] = col;
            if (check(row)) {
                attempt(row + 1);
            }
        }
    }

    //判断该棋子的位置是否与之前 row - 1 行的棋子位置冲突
    public boolean check(int row) {
        for (int i = 0; i < row; i++) {
            //只需判断棋子是否在同一列或同一斜线即可，因为一维数组的表示方式已经确保了棋子不会在同一行
            if (position[i] == position[row] || Math.abs(row - i) == Math.abs(position[row] - position[i])) {
                return false;
            }
        }
        return true;
    }

    //打印棋盘
    public void printChessBoard() {
        for (int i = 0; i < maxSize; i++) {
            for (int j = 0; j < maxSize; j++) {
                if (position[i] == j) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }
}
