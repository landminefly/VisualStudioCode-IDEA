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

class Graph {
    public ArrayList<String> vertexes;
    public int[][] edges;
    public boolean[] isVisited;

    public Graph(String[] strings) {
        vertexes = new ArrayList<>();
        edges = new int[strings.length][strings.length];
        vertexes.addAll(Arrays.asList(strings));
    }

    public void addVertex(String s) {
        vertexes.add(s);
        for (int i = 0; i < edges.length; i++) {
            edges[i] = Arrays.copyOf(edges[i], vertexes.size());
        }
        edges = Arrays.copyOf(edges, vertexes.size());
        edges[edges.length - 1] = new int[vertexes.size()];
    }

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

    public int translateVertexToIndex(String s) {
        return vertexes.indexOf(s);
    }

    public String translateIndexToVertex(int index) {
        if (index < edges.length && index >= 0) {
            return vertexes.get(index);
        } else {
            System.out.println("下标错误，转换失败");
            return null;
        }
    }

    public void showEdges() {
        for (int[] i : edges) {
            System.out.println(Arrays.toString(i));
        }
    }

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
