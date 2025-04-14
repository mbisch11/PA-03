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
            return null;  // should not happen because of pre-check in delete(x)
        }
        int cmp = x.compareTo(current.data);
        if (cmp < 0) {
            current.left = delete(current.left, x);
        } else if (cmp > 0) {
            current.right = delete(current.right, x);
        } else {
            // Node with only one child or no child:
            if (current.left == null) {
                return current.right;
            }
            if (current.right == null) {
                return current.left;
            }
            // Node with two children: replace the data with the smallest from the right subtree.
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

    public String toString() {
        int height = getHeight(root);
        // For an empty tree, height will be 0.
        if (height == 0) {
            return "N";
        }
        // In a complete binary tree of height h, the total number of nodes is 2^h - 1.
        int size = (int) Math.pow(2, height) - 1;
        String[] arr = new String[size];
        fillArray(root, 0, arr, size);
        return String.join(", ", arr);
    }

    private void fillArray(Node node, int index, String[] arr, int size) {
        if (index >= size) {
            return;
        }
        if (node == null) {
            arr[index] = "N";
            return;
        }
        arr[index] = node.data.toString();
        fillArray(node.left, 2 * index + 1, arr, size);
        fillArray(node.right, 2 * index + 2, arr, size);
    }

    private int getHeight(Node node) {
        if (node == null) {
            return 0;
        }
        int leftH = getHeight(node.left);
        int rightH = getHeight(node.right);
        return 1 + Math.max(leftH, rightH);
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