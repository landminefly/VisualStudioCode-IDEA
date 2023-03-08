package test11_12;

import java.util.ArrayList;

public class HorseChessTest {
    public static void main(String[] args) {
        int[][] chess = new int[8][8];
        HorseChess horseChess = new HorseChess(chess);
        //从[0,0]位置开始，step从1算起
        horseChess.traversal(0, 0, 1);
        System.out.println(horseChess.isFinished);
        for (int[] i : horseChess.chessBoard) {
            for (int j : i) {
                System.out.print(j + "\t");
            }
            System.out.println();
        }
    }
}

class HorseChess {
    public int[][] chessBoard;
    public int rowCount;
    public int colCount;
    public boolean[] visited;
    public boolean isFinished;

    public HorseChess(int[][] chessBoard) {
        this.chessBoard = chessBoard;
        this.rowCount = chessBoard.length;
        this.colCount = chessBoard[0].length;
        this.visited = new boolean[rowCount * colCount];
    }

    //step表示现在走的是第几步，在这里也可以表示递归的层数，从1开始
    public void traversal(int row, int col, int step) {
        chessBoard[row][col] = step;
        visited[row * colCount + col] = true;
        ArrayList<Point> canVisitNext = canVisitNext(row, col);
        //对canVisitNext进行从小到大的排序
        //依据是如果这一步走了该元素所代表的坐标，那么下一步可以继续走的坐标个数，记为count
        //count越少，则尝试和回溯所需的时间成本更少，应该优先选择，以提高效率
        canVisitNext.sort((p1, p2) -> {
            int count1 = canVisitNext(p1.row, p1.col).size();
            int count2 = canVisitNext(p2.row, p2.col).size();
            return count1 - count2;
        });
        while (!canVisitNext.isEmpty()) {
            Point p = canVisitNext.remove(0);
            if (!visited[p.row * colCount + p.col]) {
                //递归
                traversal(p.row, p.col, step + 1);
            }
        }
        //如果递归层数比 colCount * rowCount 少就要回溯
        //说明这里已经走不通了，回溯时自身的chessBoard和visited置回0和false
        if (step < colCount * rowCount && !isFinished) {
            chessBoard[row][col] = 0;
            visited[row * colCount + col] = false;
        }
        //如果递归了 colCount * rowCount 次，那就说明整个棋盘都被遍历了
        //isFinished 置为 true 后一直回溯即可，直到整个递归结束
        else {
            isFinished = true;
        }
    }

    public ArrayList<Point> canVisitNext(int row, int col) {
        ArrayList<Point> canVisitNext = new ArrayList<>();
        //位置5
        if ((col - 2) >= 0 && (row - 1) >= 0) {
            canVisitNext.add(new Point(row - 1, col - 2));
        }
        //位置6
        if ((col - 1) >= 0 && (row - 2) >= 0) {
            canVisitNext.add(new Point(row - 2, col - 1));
        }
        //位置7
        if ((col + 1) < colCount && (row - 2) >= 0) {
            canVisitNext.add(new Point(row - 2, col + 1));
        }
        //位置0
        if ((col + 2) < colCount && (row - 1) >= 0) {
            canVisitNext.add(new Point(row - 1, col + 2));
        }
        //位置1
        if ((col + 2) < colCount && (row + 1) < rowCount) {
            canVisitNext.add(new Point(row + 1, col + 2));
        }
        //位置2
        if ((col + 1) < colCount && (row + 2) < rowCount) {
            canVisitNext.add(new Point(row + 2, col + 1));
        }
        //位置3
        if ((col - 1) >= 0 && (row + 2) < rowCount) {
            canVisitNext.add(new Point(row + 2, col - 1));
        }
        //位置4
        if ((col - 2) >= 0 && (row + 1) < rowCount) {
            canVisitNext.add(new Point(row + 1, col - 2));
        }
        return canVisitNext;
    }
}

class Point {
    public int row;
    public int col;

    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }
}