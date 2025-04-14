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

    public void delete(T x) {
        root = delete(root, x);
    }

    private Node delete(Node current, T x) {
        if (current == null) {
            return null;
        }
        int cmp = x.compareTo(current.data);
        if (cmp < 0) {
            current.left = delete(current.left, x);
        } else if (cmp > 0) {
            current.right = delete(current.right, x);
        } else {
            if (current.left == null) {
                return current.right;
            }
            if (current.right == null) {
                return current.left;
            }
            Node smallest = getMin(current.right);
            current.data = smallest.data;
            current.right = delete(current.right, smallest.data);
        }
        return current;
    }

    private Node getMin(Node current) {
        while (current.left != null) {
            current = current.left;
        }
        return current;
    }

    public boolean contains(T x) {
        return contains(root, x);
    }

    private boolean contains(Node current, T x) {
        if (current == null) {
            return false;
        }
        int cmp = x.compareTo(current.data);
        if (cmp == 0) {
            return true;
        } else if (cmp < 0) {
            return contains(current.left, x);
        } else {
            return contains(current.right, x);
        }
    }
}