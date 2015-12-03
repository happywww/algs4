import java.awt.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;


public static class Graph {
    private final int V;
    private int E;
    private LinkedList<Integer>[] adj;

    public Graph(int V) {
        this.V = V;
        this.E = 0;
        adj = (LinkedList<Integer>[]) new LinkedList[V];
        for(int i = 0; i < V; ++i) {
            adj[i] = new LinkedList<Integer>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(int v, int w) {
        adj[v].add(w);
        adj[w].add(v);
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }
}

public static class DepthFirstSearch {
    private boolean[] marked;
    public int count;

    public void DepthFirstSearch(Graph g, int v) {
        marked = new boolean[g.V()];
        dfs(g, v);
    }

    private void dfs(Graph g, int v) {
        count++;
        marked[v] = true;
        for(int w : g.adj(v)) {
            if(!marked[w]) {
                dfs(g, w);
            }
        }
    }
}

public static class DepthFirstPaths {
    boolean[] marked;
    int[] edgeTo;

    public DepthFirstPaths(Graph g, int start, int end) {
        marked = new boolean[g.V()];
        pathTo(g, start, end);
    }

    public void pathTo(Graph g, int start, int end) {
        dfs(g, start);
        LinkedList<Integer> list = new LinkedList<>();
        if(marked[end]) {
            while(edgeTo[end] != start) {
                list.add(edgeTo[end]);
                end = edgeTo[end];
            }
            list.add(start);
        }
        System.out.print(list.toArray());
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        for(int w : g.adj(v)) {
            if(!marked[w]) {
                edgeTo[w] = v;
                dfs(g, w);
            }
        }
    }

    public void bfs(Graph g, int v) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(v);
        marked[v] = true;
        while(!list.isEmpty()) {
            int t = list.pop();
            for(int w : g.adj(t)) {
                if(!marked[w]) {
                    list.add(w);
                    marked[w] = true;
                    edgeTo[w] = t;
                }
            }
        }
    }
}


//    Connected Component starts
public static class CC {
    boolean[] marked;
    int[] id;
    int count;

    public int[] connectedComponent(Graph g) {
        boolean[] marked = new boolean[g.V()];
        int[] id = new int [g.V()];
        for(int v = 0; v < g.V(); v++) {
            if(!marked[v]) {
                dfs(g, v);
                count++;
            }
        }
        return id;
    }

    private void dfs(Graph g, int v) {
        marked[v] = true;
        id[v] = count;
        for(int w : g.adj(v)) {
            if(!marked[w]) {
                marked[w] = true;
                dfs(g, w);
            }
        }
    }
}
// Connected Component ends