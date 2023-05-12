package test11_12;

import java.util.Arrays;

public class DijkstraTest {
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
        Graph3 graph = new Graph3(vertexes, edges);
        Dijkstra dijkstra = new Dijkstra(graph);
        dijkstra.nearest(0);
        System.out.println(Arrays.toString(dijkstra.distance));
        for (int i = 0; i < graph.VCount; i++) {
            int index = i;
            while (index != -1) {
                if (index == 0) {
                    //如果到达起始节点了，就无需再打印右箭头了
                    System.out.print(graph.translateIndexToVertex(index));
                } else {
                    System.out.print(graph.translateIndexToVertex(index) + " <- ");
                }
                index = dijkstra.preNode[index];
            }
            System.out.println();
        }
    }
}

class Dijkstra {
    public Graph3 graph;
    public boolean[] visited;
    public int[] preNode;
    public int[] distance;

    public static final int INF = 65535;

    public Dijkstra(Graph3 graph) {
        this.graph = graph;
        visited = new boolean[graph.VCount];
        preNode = new int[graph.VCount];
        Arrays.fill(preNode, -1);
        distance = new int[graph.VCount];
        Arrays.fill(distance, INF);
    }

    public void nearest(int index) {
        visited[index] = true;
        distance[index] = 0;
        update(index);
        for (int i = 0; i < graph.VCount - 1; i++) {
            update(visit());
        }
    }

    public void update(int index) {
        int tempDis;
        for (int i = 0; i < graph.VCount; i++) {
            tempDis = distance[index] + graph.edgesWithWeight[index][i];
            if (!visited[i] && tempDis < distance[i]) {
                distance[i] = tempDis;
                preNode[i] = index;
            }
        }
    }

    public int visit() {
        int minDis = INF;
        int minNode = -1;
        for (int i = 0; i < graph.VCount; i++) {
            if (!visited[i] && distance[i] < minDis) {
                minDis = distance[i];
                minNode = i;
            }
        }
        visited[minNode] = true;
        return minNode;
    }
}

class Graph3 {
    int VCount;
    int ECount;
    char[] vertexes;
    int[][] edgesWithWeight;
    public static final int INF = 65535;

    public Graph3(char[] vertexes, int[][] edgesWithWeight) {
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

