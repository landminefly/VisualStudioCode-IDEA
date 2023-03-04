package test11_12;

import java.util.Arrays;

public class PrimTest {
    public static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] edges = new int[][]{{INF, 12, INF, INF, INF, 16, 14},
                                    {12, INF, 10, INF, INF, 7, INF},
                                    {INF, 10, INF, 3, 5, 6, INF},
                                    {INF, INF, 3, INF, 4, INF, INF},
                                    {INF, INF, 5, 4, INF, 2, 8},
                                    {16, 7, 6, INF, 2, INF, 9},
                                    {14, INF, INF, INF, 8, 9, INF}};
        Graph1 graph = new Graph1(vertexes, edges);
        int[][] edgesOfTree = Prim.createMinSpanningTree(graph, 'B');
        for (int[] i : edgesOfTree) {
            for (int j : i) {
                if (j == INF) {
                    System.out.print("INF\t");
                } else {
                    System.out.print(j + "\t");
                }
            }
            System.out.println();
        }
    }
}

class Prim {
    public static final int INF = Integer.MAX_VALUE;

    /**
     * @param g 图对象
     * @param vertexToStart 循环开始的顶点
     * @return 最小生成树的邻接矩阵
     */
    public static int[][] createMinSpanningTree(Graph1 g, char vertexToStart) {
        //记录最小生成树的邻接矩阵
        int[][] edgesOfTree = new int[g.VCount][g.VCount];
        for (int[] i : edgesOfTree) {
            Arrays.fill(i, INF);
        }
        int index = g.translateVertexToIndex(vertexToStart);
        boolean[] visited = new boolean[g.VCount];
        visited[index] = true;
        //记录每次循环中，符合条件的边的权及其关联顶点
        int minWeight;
        int v1 = -1;
        int v2 = -1;
        for (int e = 0; e < g.VCount - 1; e++) {
            //每次循环开始前，先重置minWeight的值
            minWeight = INF;
            for (int i = 0; i < g.VCount; i++) {
                for (int j = i + 1; j < g.VCount; j++) {
                    if (((visited[i] && !visited[j]) || (!visited[i] && visited[j])) && g.edgesWithWeight[i][j] < minWeight) {
                        v1 = i;
                        v2 = j;
                        minWeight = g.edgesWithWeight[i][j];
                    }
                }
            }
            if (visited[v1]) {
                visited[v2] = true;
            } else {
                visited[v1] = true;
            }
            edgesOfTree[v1][v2] = minWeight;
            edgesOfTree[v2][v1] = minWeight;
            System.out.println("添加边：" + g.translateIndexToVertex(v1) + " - " + g.translateIndexToVertex(v2));
        }
        return edgesOfTree;
    }
}

class Graph1 {
    int VCount;
    int ECount;
    char[] vertexes;
    int[][] edgesWithWeight;
    public static final int INF = Integer.MAX_VALUE;

    public Graph1(char[] vertexes, int[][] edgesWithWeight) {
        this.vertexes = vertexes;
        this.edgesWithWeight = edgesWithWeight;
        this.VCount = vertexes.length;
        for(int i =0; i < VCount; i++) {
            for(int j = i+1; j < VCount; j++) {
                if(edgesWithWeight[i][j] != INF) {
                    ECount++;
                }
            }
        }
    }

    //将顶点值转换为顶点下标
    public int translateVertexToIndex(char c) {
        for (int i = 0; i < vertexes.length; i++) {
            if (c == vertexes[i]) {
                return i;
            }
        }
        return -1;
    }

    //将顶点下标转换为顶点值
    public char translateIndexToVertex(int index) {
        if (index < VCount && index >= 0) {
            return vertexes[index];
        } else {
            throw new RuntimeException("下标错误，转换失败");
        }
    }
}
