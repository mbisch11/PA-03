public class BinarySearchTree <T extends Comparable<T>> {
    public class Node {
        public T data;
        public Node left;
        public Node right;

        public Node(T data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    protected Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    public void insert(T x) {
        root = insert(root, x);
    }

    private Node insert(Node current, T x) {
        if (current == null) {
            return new Node(x);
        }
        if (x.compareTo(current.data) < 0) {
            current.left = insert(current.left, x);
        } else if (x.compareTo(current.data) > 0) {
            current.right = insert(current.right, x);
        }
        return current;
    }
}