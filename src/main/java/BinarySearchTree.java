import java.util.ArrayList;

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
        if (!contains(x)) {
            throw new IllegalArgumentException("Data not found: " + x);
        }
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

    public boolean contains(T x) {
        return contains(root, x);
    }

    private Node getMin(Node current) {
        while (current.left != null) {
            current = current.left;
        }
        return current;
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

    public ArrayList<T> partition(T x) {
        ArrayList<T> partitioned = new ArrayList<>();
        partition(root, x, partitioned);
        return partitioned;
    }

    private void partition(Node current, T x, ArrayList<T> partitioned) {
        if (current == null) {
            return;
        }
        if (current.data.compareTo(x) >= 0) {
            partitioned.add(current.data);
        }
        partition(current.left, x, partitioned);
        partition(current.right, x, partitioned);
    }

    public BinarySearchTree<T> rebalance() {
        ArrayList<T> sorted = new ArrayList<>();
        inOrder(root, sorted);
        BinarySearchTree<T> newTree = new BinarySearchTree<>();
        newTree.root = buildBalancedTree(sorted, 0, sorted.size() - 1);
        return newTree;
    }

    private void inOrder(Node node, ArrayList<T> sorted) {
        if (node == null) {
            return;
        }
        inOrder(node.left, sorted);
        sorted.add(node.data);
        inOrder(node.right, sorted);
    }

    private Node buildBalancedTree(ArrayList<T> sorted, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = start + (end - start) / 2;
        Node node = new Node(sorted.get(mid));
        node.left = buildBalancedTree(sorted, start, mid - 1);
        node.right = buildBalancedTree(sorted, mid + 1, end);
        return node;
    }
}