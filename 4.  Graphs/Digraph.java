public static class Digraph {
    private final int V;
    private int E;
    private LinkedList<Integer>[] adj;

    public Digraph(int V) {
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
        E++;
    }

    public Iterable<Integer> adj(int v) {
        return adj[v];
    }

    public Digraph reverse() {
        Digraph reversed = new Digraph(V);
        for(int i = 0; i < adj.length; i++) {
            for(int v : adj[i]) {
                reversed.addEdge(v, i);
            }
        }
        return reversed;
    }
}

public static class DirectedCycle {
    private boolean[] marked;
    private boolean[] onStack;
    private LinkedList<Integer> cycle= new LinkedList<>();
    private int[] edgeTo;


    public DirectedCycle(Digraph g) {
        marked = new boolean[g.V()];
        onStack = new boolean[g.V()];
        edgeTo = new int[g.V()];
        for(int i = 0; i < g.V(); i++) {
            if(!marked[i]) {
                dfs(g, i);
            }
        }
    }

    private void dfs(Digraph g, int v) {
        marked[v] = true;
        onStack[v] = true;
        for(int w : g.adj(v)) {
            if(hasCycle()) {
                return;
            } else {
                if(!marked[w]) {
                    edgeTo[w] = v;    // Don't forget assign v to edgeTo[w];
                    dfs(g, w);
                } else {
                    if(onStack[w]) {
                        for(int i = v; i != w; i = edgeTo[i]) {
                            cycle.push(i);
                        }
                        cycle.push(w);
                        cycle.push(v);
                    }
                }
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle() {
        return !cycle.isEmpty();
    }
}

public static class DepthFirstOrder {
    private boolean[] marked;
    private ArrayList<Integer> pre;
    private ArrayList<Integer> post;
    private LinkedList<Integer> reversePost;

    public DepthFirstOrder(Digraph g) {
        marked = new boolean[g.V()];
        for(int i = 0; i < g.V(); i++) {
            if(!marked[i]) {
                dfs(g, i);
            }
        }
    }

    private void dfs(Digraph g, int v) {
        marked[v] = true;
        pre.add(v);

        for(int w : g.adj(v)) {
            if(!marked[w]) {
                dfs(g, w);
            }
        }

        post.add(v);
        reversePost.push(v);
    }

    public ArrayList<Integer> pre() {
        return pre;
    }

    public ArrayList<Integer> post() {
        return post;
    }

    public LinkedList<Integer> reversePost() {
        return reversePost;
    }
}

public static class Topological {
    private LinkedList<Integer> order = new LinkedList<>();

    public Topological(Digraph g) {
        DirectedCycle dg = new DirectedCycle(g);
        if(!dg.hasCycle()) {
            DepthFirstOrder dfo = new DepthFirstOrder(g);
            order = dfo.reversePost();
        }
    }

    public LinkedList<Integer> order() {
        return order;
    }

    // directed acyclic graph
    public boolean isDAG() {
        return order != null;
    }
}

public static class KosarajuSCC {
    private boolean[] marked;
    private int[] id;
    int count;

    public KosarajuSCC(Digraph g) {
        marked = new boolean[g.V()];
        id = new int[g.V()];
        DepthFirstOrder dfo = new DepthFirstOrder(g.reverse());
        LinkedList<Integer> order = dfo.reversePost();
        for(int v : order) {
            if(!marked[v]) {
                dfs(g, v);
                count++;
            }
            //  count++;    WRONG! `count++` Should NOT be here!
        }
    }

    private void dfs(Digraph g, int v) {
        marked[v] = true;
        id[v] = count;
        for(int w : g.adj(v)) {
            if(!marked[w]) {
                dfs(g, w);
            }
        }
    }
}