public static class Edge implements Comparable<Edge> {
    private final int v;
    private final int w;
    private final double weight;

    public Edge(int v, int w, double weight) {
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double Weight() {
        return weight;
    }

    public int either() {
        return v;
    }

    public int other(int vertex) {
        if(vertex == v) {
            return w;
        } else {
            if(vertex == w) {
                return v;
            } else {
                throw new RuntimeException("Inconsistent edge");
            }
        }
    }

    @Override
    public int compareTo(Edge o) {
        if(this.weight > o.weight) {
            return 1;
        } else {
            if(this.weight == weight) {
                return 0;
            } else {
                return -1;
            }
        }
    }
}

public static class EdgeWeightedGraph {
    private final int V;
    private int E;
    private LinkedList<Edge>[] adj;

    public EdgeWeightedGraph(int V) {
        this.V = V;
        this.E = 0;
        adj = (LinkedList<Edge>[]) new LinkedList[V];
        for(int i = 0; i < V; i++) {
            adj[i] = new LinkedList<Edge>();
        }
    }

    public int V() {
        return V;
    }

    public int E() {
        return E;
    }

    public void addEdge(Edge e) {
        int v = e.either();
        int w = e.other(v);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public LinkedList<Edge> adj(int v) {
        return adj[v];
    }

    public LinkedList<Edge> edges() {
        LinkedList<Edge> result = new LinkedList<>();
        for(int v = 0; v < V; v++) {
            for(Edge edge : adj[v]) {
                if(v < edge.other(v)) {
                    result.add(edge);
                }
            }
        }
        return result;
    }
}

public static class LazyPrimMST {
    private boolean[] marked;
    private PriorityQueue<Edge> pq;
    private LinkedList<Edge> mst;

    public LazyPrimMST(EdgeWeightedGraph g) {
        marked = new boolean[g.V()];
        pq = new PriorityQueue<>();
        mst = new LinkedList<>();

        visit(g, 0);
        while(!pq.isEmpty()) {
            Edge e = pq.poll();
            int v = e.either();
            int w = e.other(v);
            if(!(marked[v] && marked[w])) {
                mst.add(e);
                if(!marked[v]) {
                    visit(g, v);
                };
                if(!marked[w]) {
                    visit(g, w);
                }
            }
        }
    }

    private void visit(EdgeWeightedGraph g, int v) {
        marked[v] = true;
        for(Edge e : g.adj(v)) {
            int w = e.other(v);
            if(!marked[w]) {
                pq.add(e);
            }
        }
    }
}

public static class PrimMST {
    private int[] edgeTo;
    private double[] distTo;
    private boolean[] marked;
    private PriorityQueue<NearstV> pq;

    public PrimMST(EdgeWeightedGraph g) {
        edgeTo = new int[g.V()];
        distTo = new double[g.V()];
        marked = new boolean[g.V()];
		pq = new PriorityQueue<>();
		
        for(int i = 1; i < g.V(); i++) {
            distTo[i] = Double.MAX_VALUE;
        }
		
		edgeTo[0] = 0.0;
        pq.add(new NearstV(0, 0));
        while (!pq.isEmpty()) {
            visit(g, pq.poll().v);
        }
    }

    public void visit(EdgeWeightedGraph g, int v) {
        marked[v] = true;
        for (Edge e : g.adj(v)) {
            int w = e.other(v);
            if (!marked[w]) {
                if (e.weight < distTo[w]) {
                    edgeTo[w] = v;
                    distTo[w] = e.weight;
                    boolean isExisted = false;
                    for(NearstV nv : pq) {
                        if(nv.v == w) {
                            nv.d = distTo[w];
                            isExisted = true;
                            break;
                        }
                    }
                    if (!isExisted) {
                        pq.add(new NearstV(w, distTo[w]));
                    }
                }
            }
        }
    }


    private class NearstV implements Comparable<NearstV> {
        public int v;
        public double d;

        public NearstV(int v, double d) {
            this.v = v;
            this.d = d;
        }

        @Override
        public int compareTo(NearstV o) {
            if(d < o.d) {
                return -1;
            } else {
                if(d == o.d) {
                    return 0;
                } else {
                    return 1;
                }
            }
        }

        @Override
        public boolean equals(Object obj) {
            if (obj instanceof NearstV) {
                return v == ((NearstV) obj).v;
            } else {
                return super.equals(obj);
            }

        }
    }
}

public static class KruskalMST{
    // pass
}



