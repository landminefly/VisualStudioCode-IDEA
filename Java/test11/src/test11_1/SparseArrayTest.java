package test11_1;

import java.util.Arrays;

public class SparseArrayTest {
    public static void main(String[] args) {

        //先准备一个二维数组，它的大部分元素值都是5
        int[][] arr = new int[10][12];
        for (int[] i : arr) {

            Arrays.fill(i, 5);
        }
        arr[3][5] = 10;
        arr[6][8] = 8;
        arr[9][1] = 12;

        //普通二维数组 -> 稀疏数组
        //要先知道数组的mainNum
        int mainNum = 5;
        //第一次遍历，获取不同于mainNum的元素个数numsCount
        int numsCount = 0;
        for (int[] i : arr) {
            for (int j : i) {
                if (j != mainNum) {
                    numsCount++;
                }
            }
        }

        //创建稀疏数组，并给第一行赋值
        int[][] sparseArr = new int[numsCount + 1][3];
        sparseArr[0][0] = arr.length;
        sparseArr[0][1] = arr[0].length;
        sparseArr[0][2] = mainNum;

        //第二次遍历，获取不同于mainNum的元素信息并赋值给稀疏数组
        int count = 1;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] != mainNum) {
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = arr[i][j];
                    count++;
                }
            }
        }
        //完成

        //稀疏数组 -> 普通二维数组
        //要先知道数组的mainNum
        //根据稀疏数组第一行的信息，创建一个二维数组，并先全部填充为mainNum
        int[][] newArr = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int[] i : newArr) {
            Arrays.fill(i, mainNum);
        }
        //再根据稀疏数组从第二行开始的信息，将不同于mainNum的元素复原到二维数组中
        for (int i = 1; i < sparseArr.length; i++) {
            newArr[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        //完成
    }
}

