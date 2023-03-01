package test11_12;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class KruskalTest {
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
        Graph2 graph = new Graph2(vertexes, edges);
        int[][] edgesOfTree = Kruskal.createMinSpanningTree(graph);
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

class Kruskal {
    public static final int INF = Integer.MAX_VALUE;

    public static int[][] createMinSpanningTree(Graph2 g) {
        int[] mark = new int[g.VCount];
        Collections.sort(g.edgesWithWeight);
        int[][] edgesOfTree = new int[g.VCount][g.VCount];
        for (int[] i : edgesOfTree) {
            Arrays.fill(i, INF);
        }
        for (int i = 0; i < g.ECount; i++) {
            Edge e = g.edgesWithWeight.get(i);
            int mark1 = getMark(mark, e.v1);
            int mark2 = getMark(mark, e.v2);
            if (mark1 != mark2) {
                mark[mark1] = mark2;
                edgesOfTree[e.v1][e.v2] = e.weight;
                edgesOfTree[e.v2][e.v1] = e.weight;
                System.out.println("添加边：" + g.translateIndexToVertex(e.v1) + " - " + g.translateIndexToVertex(e.v2));
            }
        }
        return edgesOfTree;
    }

    public static int getMark(int[] mark, int i) {
        while (mark[i] != 0) {
            i = mark[i];
        }
        return i;
    }
}

class Graph2 {
    int VCount;
    int ECount;
    char[] vertexes;
    ArrayList<Edge> edgesWithWeight;
    public static final int INF = Integer.MAX_VALUE;

    public Graph2(char[] vertexes, int[][] edgesWithWeight) {
        this.vertexes = vertexes;
        this.VCount = vertexes.length;
        this.edgesWithWeight = new ArrayList<>();
        for (int i = 0; i < VCount; i++) {
            for (int j = i + 1; j < VCount; j++) {
                if (edgesWithWeight[i][j] != INF) {
                    this.edgesWithWeight.add(new Edge(i, j, edgesWithWeight[i][j]));
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

class Edge implements Comparable<Edge> {
    int v1;
    int v2;
    int weight;

    public Edge(int v1, int v2, int weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge e) {
        return this.weight - e.weight;
    }
}
