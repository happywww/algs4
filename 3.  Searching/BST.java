public class BST {
    public Node root;

    private class Node {
        int key;
        String value;
        int N;
        Node left, right;

        public Node(int k, String v, int n) {
            key = k;
            value = v;
            N = n;
        }
    }

    public int size() {
        return size(root);
    }

    private int size(Node n) {
        if(n == null) {
            return 0;
        } else {
            return n.N;
        }
    }

    public String get(int key) {
        return get(root, key);
    }

    private String get(Node n, int k) {
        if(n == null) {
            return null;
        }
        if(k == n.key) {
            return n.value;
        } else {
            if(k < n.key) {
                return get(n.left, k);
            } else {
                return get(n.right, k);
            }
        }
    }

    public void put(int key, String value) {
        root = put(root, key, value);
    }

    private Node put(Node n, int k, String v) {
        if(n == null) {
            return new Node(k, v, 1);
        }
        if(n.key == k) {
            n.value = v;
        } else {
            if(k < n.key) {
                n.left = put(n.left, k, v);
            } else {
                n.right = put(n.right, k, v);
            }
        }
        n.N = size(n.left) + size(n.right);
        return n;
    }

    public Node min() {
        Node n = root;
        while(n.left != null) {
            n = n.left;
        }
        return n;
    }

    public int floor(int key) {
        return floor(root, key).key;
    }

    private Node floor(Node n, int k) {
        if(n == null) return null;
        if(n.key == k) {
            return n;
        } else {
            if(k < n.key) {
                return floor(n.left, k);
            } else {
                Node t = floor(n.right, k);
                return t == null ? n : t;
            }
        }
    }
    
    public int select(int ranking) {
        return select(root, ranking).key;
    }
    
    private Node select(Node n, int r) {
        if (n == null) {
            return null;
        }
        int t = size(n.left);
        if(t == r) {
            return n;
        } else {
            if(r < t) {
                return select(n.left, r);
            } else {
                return select(n.right, r-t-1);
            }
        }
    }
    
    public int rank(int key) {
        return rank(root, key);
    }
    
    private int rank(Node n, int k) {
        if(n == null) {
            return 0;
        }
        if(k == n.key) {
            return size(n.left);
        } else {
            if(k < n.key) {
                return rank(n.left, k);
            } else {
                return size(n.left) + 1 + rank(n.right, k);
            }
        }
    }
    
    public static void main(String[] args) {
        BST r = new BST();
        r.put(2, "root");
        r.put(1, "left");
        r.put(0, "right");

        System.out.println(r.get(2));
        System.out.println(r.get(1));
        System.out.println(r.get(3));
        System.out.println(r.min().value);
    }
}