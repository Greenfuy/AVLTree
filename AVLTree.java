import java.util.List;

public class AVLTree {

    private Node root;

    public long counter;

    public AVLTree() {
        counter = 0;
    }

    private static class Node {
        int value;
        int height;
        Node left;
        Node right;


        public Node(int value) {
            this.value = value;
        }
    }

    public void updateHeight(Node n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    public int height(Node n) {
        return n == null ? -1 : n.height;
    }

    public int getBalance(Node n) {
        return (n == null) ? 0 : height(n.right) - height(n.left);
    }

    public Node rotateRight(Node y) {
        Node x = y.left;
        Node z = x.right;
        x.right = y;
        y.left = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    public Node rotateLeft(Node y) {
        Node x = y.right;
        Node z = x.left;
        x.left = y;
        y.right = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    public Node rebalance(Node z) {
        updateHeight(z);
        int balance = getBalance(z);
        if (balance > 1) {
            if (height(z.right.right) > height(z.right.left)) {
                z = rotateLeft(z);
            } else {
                z.right = rotateRight(z.right);
                z = rotateLeft(z);
            }
        } else if (balance < -1) {
            if (height(z.left.left) > height(z.left.right))
                z = rotateRight(z);
            else {
                z.left = rotateLeft(z.left);
                z = rotateRight(z);
            }
        }

        return z;
    }

    private Node insert(Node node, int value) {
        counter++;
        if (node == null) {
            root = new Node(value);
            return root;
        } else if (node.value > value) {
            node.left = insert(node.left, value);
        } else if (node.value < value) {
            node.right = insert(node.right, value);
        }
        return rebalance(node);
    }

    public boolean add(int value) {
        root = insert(root, value);
        return true;
    }

    private Node delete(Node node, int value) {
        counter++;
        if (node == null) {
            return null;
        } else if (node.value > value) {
            node.left = delete(node.left, value);
        } else if (node.value < value) {
            node.right = delete(node.right, value);
        } else {
            if (node.left == null || node.right == null) {
                node = (node.left == null) ? node.right : node.left;
            } else {
                Node mostLeftChild = mostLeftChild(node.right);
                node.value = mostLeftChild.value;
                node.right = delete(node.right, node.value);
            }
        }
        if (node != null) {
            node = rebalance(node);
        }
        return node;
    }

    public Node remove(int value) {
        return delete(root, value);
    }

    public Node find(int value) {
        return get(root, value);
    }

    public Node get(Node node, int value) {
        //counter++;
        if (node == null) return null;
        if (node.value == value) return node;
        else if (node.value > value) return get(node.left, value);
        else return get(node.right, value);
    }

    private Node mostLeftChild(Node node) {
        if (node.left == null) return node;
        return mostLeftChild(node.left);
    }

    public long getIterations() {
        long save = counter;
        counter = 0;
        return save;
    }

    public long[][] addElem(List<Integer> list) {
        long[][] data = new long[10000][2];
        for (int i = 0; i < list.size(); i++) {
            long startTime = (long) System.nanoTime();
            add(list.get(i));
            long endTime = (long) System.nanoTime();
            data[i][0] = getIterations();
            data[i][1] = endTime - startTime;
        }
        return data;
    }

    public long[][] findElem(List<Integer> list) {
        long[][] data = new long[100][2];
        for (int i = 0; i < list.size(); i++) {
            long startTime = System.nanoTime();
            find(list.get(i));
            long endTime = System.nanoTime();
            data[i][0] = getIterations();
            data[i][1] = endTime - startTime;
        }
        return data;
    }

    public long[][] removeElem(List<Integer> list) {
        long[][] data = new long[1000][2];
        for (int i = 0; i < list.size(); i++) {
            long startTime = System.nanoTime();
            remove(list.get(i));
            long endTime = System.nanoTime();
            data[i][0] = getIterations();
            data[i][1] = endTime - startTime;
        }
        return data;
    }
}
