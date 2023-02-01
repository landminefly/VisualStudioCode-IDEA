package test11_11;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class GraphTest {
    public static void main(String[] args) {
        Graph graph = new Graph(new String[]{"D", "Q", "Y"});
        graph.addVertex("G");
        graph.addVertex("W");
        graph.addEdge("D", "Y");
        graph.addEdge("G", "W");
        graph.showEdges();
        graph.DFS();
        graph.BFS();
    }
}

//无向图
class Graph {
    //顶点集
    public ArrayList<String> vertexes;
    //邻接矩阵，顶点集下标代表的顶点和邻接矩阵下标代表的顶点是一一对应的
    public int[][] edges;
    //遍历时判断顶点是否已被访问
    public boolean[] isVisited;

    //创建图对象时，先创建一些顶点
    public Graph(String[] strings) {
        vertexes = new ArrayList<>();
        edges = new int[strings.length][strings.length];
        vertexes.addAll(Arrays.asList(strings));
    }

    //添加一个顶点，同时也要更新邻接矩阵
    public void addVertex(String s) {
        vertexes.add(s);
        for (int i = 0; i < edges.length; i++) {
            edges[i] = Arrays.copyOf(edges[i], vertexes.size());
        }
        edges = Arrays.copyOf(edges, vertexes.size());
        edges[edges.length - 1] = new int[vertexes.size()];
    }

    //通过顶点下标的方式来添加边
    //因为是无向图，所以若i!=j，那么edges[i][j]、edges[j][i]都要+1
    public void addEdge(int i, int j) {
        if (i < edges.length && i >= 0 &&
            j < edges.length && j >= 0) {
            edges[i][j]++;
            if (i != j) {
                edges[j][i]++;
            }
        } else {
            System.out.println("下标错误，添加失败");
        }
    }

    //通过顶点值的方式来添加边
    //因为是无向图，所以若i!=j，那么edges[i][j]、edges[j][i]都要+1
    public void addEdge(String s1, String s2) {
        int i = translateVertexToIndex(s1);
        int j = translateVertexToIndex(s2);
        if (i < edges.length && i >= 0 &&
            j < edges.length && j >= 0) {
            edges[i][j]++;
            if (i != j) {
                edges[j][i]++;
            }
        } else {
            System.out.println("顶点不存在，添加失败");
        }
    }

    //将顶点值转换为顶点下标
    public int translateVertexToIndex(String s) {
        return vertexes.indexOf(s);
    }

    //将顶点下标转换为顶点值
    public String translateIndexToVertex(int index) {
        if (index < edges.length && index >= 0) {
            return vertexes.get(index);
        } else {
            System.out.println("下标错误，转换失败");
            return null;
        }
    }

    //打印邻接矩阵
    public void showEdges() {
        for (int[] i : edges) {
            System.out.println(Arrays.toString(i));
        }
    }

    //深度优先遍历，要用到递归
    //考虑到非连通图，因此要把所有顶点分别作为遍历起点执行一次
    public void DFS() {
        isVisited = new boolean[edges.length];
        System.out.println("DFS开始输出");
        for (int i = 0; i < edges.length; i++) {
            if (!isVisited[i]) {
                DFS(i);
            }
        }
        System.out.println("输出完毕");
    }

    private void DFS(int index) {
        isVisited[index] = true;
        System.out.print(translateIndexToVertex(index) + " ");
        for (int i = 0; i < edges.length; i++) {
            if (edges[index][i] >= 1 && !isVisited[i]) {
                DFS(i);
            }
        }
    }

    //广度优先遍历，无需递归，要用到队列
    //考虑到非连通图，因此要把所有顶点分别作为遍历起点执行一次
    public void BFS() {
        isVisited = new boolean[edges.length];
        System.out.println("BFS开始输出");
        for (int i = 0; i < edges.length; i++) {
            if (!isVisited[i]) {
                BFS(i);
            }
        }
        System.out.println("输出完毕");
    }

    public void BFS(int index) {
        Queue<Integer> queue = new LinkedList<>();
        isVisited[index] = true;
        System.out.print(translateIndexToVertex(index) + " ");
        queue.add(index);

        while (!queue.isEmpty()) {
            int tempIndex = queue.remove();
            for (int i = 0; i < edges.length; i++) {
                if (edges[tempIndex][i] >= 1 && !isVisited[i]) {
                    isVisited[i] = true;
                    System.out.print(translateIndexToVertex(i) + " ");
                    queue.add(i);
                }
            }
        }
    }
}
