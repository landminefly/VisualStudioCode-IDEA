package test12_1;

import java.util.Arrays;
import java.util.HashMap;

public class SeatingChart {
    private final int[][] chartArr;
    private final int colCount;
    private final int stuCount;
    private final HashMap<Integer, String> stuMap;
    private final HashMap<Integer, Integer> genderMap;
    private final int[] endOfGroups;

    public SeatingChart(int colCount, int stuCount, HashMap<Integer, String> stuMap, HashMap<Integer, Integer> genderMap, int[] endOfGroups) {
        if (genderMap.size() != stuMap.size()) {
            throw new RuntimeException("学生数量不匹配！");
        }
        Arrays.sort(endOfGroups);
        if (endOfGroups[endOfGroups.length - 1] != colCount - 1) {
            throw new RuntimeException("组数有误！");
        }
        this.chartArr = new int[(stuCount + colCount - 1) / colCount][colCount];
        this.stuCount = stuCount;
        this.colCount = colCount;
        this.stuMap = stuMap;
        this.genderMap = genderMap;
        this.endOfGroups = endOfGroups;
    }

    public int randomNum() {
        return (int) (Math.random() * stuCount + 1);
    }

    private boolean ifExist(int i, int j, int num) {
        for (int row = 0; row <= i; row++) {
            for (int col = 0; col < colCount; col++) {
                if (row == i && col == j) {
                    break;
                }
                if (chartArr[row][col] == num) {
                    return true;
                }
            }
        }
        return false;
    }

    private int getPreviousEndOfGroup(int index) {
        for (int i = 0; i < endOfGroups.length; i++) {
            if (index <= endOfGroups[i]) {
                if (i == 0) {
                    return -1;
                }
                return endOfGroups[i - 1];
            }
        }
        throw new RuntimeException("index参数错误");
    }

    private boolean ifGenderDiff(int i, int j, int num) {
        int left = getPreviousEndOfGroup(j) + 1;
        if (j == left) {
            return false;
        }
        return !(genderMap.get(chartArr[i][j - 1]).intValue() == genderMap.get(num).intValue());
    }

    public void startArrangement() {
        for (int i = 0; i < chartArr.length; i++) {
            for (int j = 0; j < colCount; j++) {
                if (i == chartArr.length - 1 && j >= stuCount % colCount) {
                    return;
                }
                while (true) {
                    int ran = randomNum();
                    if (!ifExist(i, j, ran) && !ifGenderDiff(i, j, ran)) {
                        chartArr[i][j] = ran;
                        break;
                    }
                }
            }
            System.out.print("");
        }
    }

    public void showChart() {
        for (int[] i : chartArr) {
            for (int j = 0; j < i.length; j++) {
                if (stuMap.get(i[j]) == null) {
                    break;
                }
                System.out.print(stuMap.get(i[j]) + "\t");
                if (Arrays.binarySearch(endOfGroups, j) >= 0) {
                    System.out.print("\t");
                }
            }
            System.out.println();
        }
    }
}
