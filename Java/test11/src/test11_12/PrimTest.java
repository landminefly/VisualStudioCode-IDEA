package test11_12;

import java.util.Arrays;

public class PrimTest {
    public static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) {
        // char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        // int[][] edges = new int[][]{{INF, 12, INF, INF, INF, 16, 14},
        //                             {12, INF, 10, INF, INF, 7, INF},
        //                             {INF, 10, INF, 3, 5, 6, INF},
        //                             {INF, INF, 3, INF, 4, INF, INF},
        //                             {INF, INF, 5, 4, INF, 2, 8},
        //                             {16, 7, 6, INF, 2, INF, 9},
        //                             {14, INF, INF, INF, 8, 9, INF}};
        char[] vertexes = {'0', '1', '2', '3', '4', '5', '6', '7'};
        int[][] edges = new int[][]{{INF, 2, 3, INF, INF, INF, INF, INF},
                                    {2, INF, INF, 2, INF, INF, INF, INF},
                                    {3, INF, INF, 1, INF, INF, INF, INF},
                                    {INF, 2, 1, INF, 2, 4, INF, INF},
                                    {INF, INF, INF, 2, INF, 1, 2, INF},
                                    {INF, INF, INF, 4, 1, INF, 2, 1},
                                    {INF, INF, INF, INF, 2, 2, INF, 1},
                                    {INF, INF, INF, INF, INF, 1, 1, INF}};
        Graph1 graph = new Graph1(vertexes, edges);
        // int[][] edgesOfTree = Prim1.createMinSpanningTree(graph, 'B');
        int[][] edgesOfTree = Prim1.createMinSpanningTree(graph, '0');
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
     * @param g             图对象
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
        for (int i = 0; i < VCount; i++) {
            for (int j = i + 1; j < VCount; j++) {
                if (edgesWithWeight[i][j] != INF) {
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

class Prim1 {
    public static final int INF = Integer.MAX_VALUE;

    /**
     * @param g             图对象
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
        //记录各个顶点到当前生成树的最小权重，以及所对应的生成树中的关联顶点
        //如果顶点本身已经在生成树中，则记权重为0
        closeEdge[] lowestWeightToMST = new closeEdge[g.VCount];
        //初始化lowestWeightToMST
        for (int i = 0; i < lowestWeightToMST.length; i++) {
            if (i != index) {
                //除了vertexToStart以外，各个顶点的最小权重就是各个顶点到vertexToStart的权重
                lowestWeightToMST[i] = new closeEdge(index, g.edgesWithWeight[index][i]);
            } else {
                //置vertexToStart对应顶点于生成树中，并将最小权值置为0
                lowestWeightToMST[i] = new closeEdge(index, 0);
            }
        }
        int minWeight;
        int v1 = -1;
        int v2 = -1;
        //还需顶点数-1次循环，才能将所有顶点都放入生成树
        for (int e = 0; e < g.VCount - 1; e++) {
            //如果不是第一次循环，就要更新lowestWeightToMST
            //因为上一次循环中新放入生成树的节点，与未放入生成树的节点的权重可能比当前记录的最小权重更小
            //此时v2就是上一次循环中新放入生成树的节点
            if (v2 != -1) {
                for (int j = 0; j < g.VCount; j++) {
                    //当且仅当该节点不在生成树中，且v2到该节点的权重比当前记录的最小权重更小时，才有必要更新
                    if (lowestWeightToMST[j].weight != 0 && g.edgesWithWeight[j][v2] < lowestWeightToMST[j].weight) {
                        lowestWeightToMST[j].weight = g.edgesWithWeight[j][v2];
                        lowestWeightToMST[j].indexInMST = v2;
                    }
                }
            }
            //重置minWeight、v1、v2的值
            minWeight = INF;
            v1 = -1;
            v2 = -1;
            //找到lowestWeightToMST中，未放入生成树的所有节点中，最小权重最小的节点
            //该最小权重所对应的边，就是此次要放入生成树的边
            for (int j = 0; j < g.VCount; j++) {
                if (lowestWeightToMST[j].weight != 0 && lowestWeightToMST[j].weight < minWeight) {
                    minWeight = lowestWeightToMST[j].weight;
                    //v1更新为最小权重对应的生成树中的节点
                    v1 = lowestWeightToMST[j].indexInMST;
                    //v2更新为生成树外的节点
                    v2 = j;
                }
            }
            edgesOfTree[v1][v2] = minWeight;
            edgesOfTree[v2][v1] = minWeight;
            lowestWeightToMST[v2].weight = 0;
            System.out.println("添加边：" + g.translateIndexToVertex(v1) + " - " + g.translateIndexToVertex(v2));
        }
        return edgesOfTree;
    }
}

class closeEdge {
    public int indexInMST;
    public int weight;

    public closeEdge(int indexInMST, int weight) {
        this.indexInMST = indexInMST;
        this.weight = weight;
    }
}
