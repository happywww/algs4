public class RedBlackTree {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    Node root;

    private class Node {
        int key;
        String value;
        int N;
        boolean color;
        Node left, right;

        Node(int key, String value, int N, boolean color) {
            this.key = key;
            this.value = value;
            this.N = N;
            this.color = color;
        }
    }

    private boolean isRed(Node x) {
        if(x == null) return false;
        return x.color == RED;
    }

    int size(Node n) {
        return n.N;
    }

    Node rotateLeft(Node h) {
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.N = h.N;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    public void put(int key, String value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private void flipColor(Node n) {
        n.left.color = BLACK;
        n.right.color = BLACK;
        n.color = RED;
    }

    private Node put(Node n, int k, String v) {
        if(n == null) {
            return new Node(k, v, 1, RED);
        }
        if(k < n.key) {
            n.left = put(n.left, k, v);
        } else {
            if(k == n.key) {
                n.value = v;
            } else {
                n.right = put(n.right, k, v);
            }
        }
        if(isRed(n.right) && !isRed(n.left)) {
            rotateLeft(n);
        }
        if(isRed(n.left) && isRed(n.left.left)) {
            rotateRight(n);
        }
        if(isRed(n.left) && isRed(n.right)) {
            flipColor(n);
        }

        n.N = size(n.left) + size(n.right) + 1;    //  if none of above 3 if-statement executes.
        return n;
    }
}
