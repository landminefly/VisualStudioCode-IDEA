package test11_12;

import java.util.Arrays;

public class FloydTest {
    public static final int INF = 65535;

    public static void main(String[] args) {
        char[] vertexes = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int[][] edges = new int[][]{{INF, 12, INF, INF, INF, 16, 14},
                                    {12, INF, 10, INF, INF, 7, INF},
                                    {INF, 10, INF, 3, 5, 6, INF},
                                    {INF, INF, 3, INF, 4, INF, INF},
                                    {INF, INF, 5, 4, INF, 2, 8},
                                    {16, 7, 6, INF, 2, INF, 9},
                                    {14, INF, INF, INF, 8, 9, INF}};
        Graph4 graph = new Graph4(vertexes, edges);
        Floyd floyd = new Floyd(graph);
        floyd.nearest();
        System.out.println("preNode数组");
        for (int[] i : floyd.preNode) {
            for (int j : i) {
                System.out.print(j + "\t");
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("distance数组");
        for (int[] i : floyd.distance) {
            for (int j : i) {
                if (j == INF) {
                    System.out.print("INF\t");
                } else {
                    System.out.print(j + "\t");
                }
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("从A到D的最短路线");
        int i = 0;
        int j = 3;
        while (j != i) {
            System.out.print(graph.translateIndexToVertex(j) + " <- ");
            j = floyd.preNode[i][j];
        }
        System.out.print(graph.translateIndexToVertex(i));
    }
}

class Floyd {
    public Graph4 graph;
    public int[][] distance;
    public int[][] preNode;

    public static final int INF = 65535;

    public Floyd(Graph4 graph) {
        this.graph = graph;
        preNode = new int[graph.VCount][graph.VCount];
        for (int[] i : preNode) {
            Arrays.fill(i, -1);
        }
        distance = new int[graph.VCount][];
        for (int i = 0; i < graph.VCount; i++) {
            distance[i] = Arrays.copyOf(graph.edgesWithWeight[i], graph.VCount);
        }
        for (int i = 0; i < graph.VCount; i++) {
            for (int j = i + 1; j < graph.VCount; j++) {
                if (graph.edgesWithWeight[i][j] != INF) {
                    preNode[i][j] = i;
                    preNode[j][i] = j;
                }
            }
        }
    }

    public void nearest() {
        int tempDis;
        for (int k = 0; k < graph.VCount; k++) {
            for (int i = 0; i < graph.VCount; i++) {
                for (int j = 0; j < graph.VCount; j++) {
                    tempDis = distance[i][k] + distance[k][j];
                    if (tempDis < distance[i][j]) {
                        distance[i][j] = tempDis;
                        preNode[i][j] = preNode[k][j];
                    }
                }
            }
        }
    }
}

class Graph4 {
    int VCount;
    int ECount;
    char[] vertexes;
    int[][] edgesWithWeight;
    public static final int INF = 65535;

    public Graph4(char[] vertexes, int[][] edgesWithWeight) {
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
