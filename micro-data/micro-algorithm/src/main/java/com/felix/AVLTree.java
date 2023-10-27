package com.felix;

/**
 * @author felix
 * @desc 平衡二叉树
 * https://www.baeldung.com/java-avl-trees
 * https://www.cnblogs.com/ZhaoxiCheung/p/6012783.html
 */
public class AVLTree {

    Node root;

    void updateHeight(Node n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    int height(Node n) {
        return n == null ? -1 : n.height;
    }

    int getBalance(Node n) {
        return n == null ? 0 : height(n.right) - height(n.left);
    }

    Node rotateRight(Node y){
        Node x = y.left;
        Node z = x.right;
        x.right = y;
        y.left = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    Node rotateLeft(Node y){
        Node x = y.right;
        Node z = x.left;
        x.left = y;
        y.right = z;
        updateHeight(y);
        updateHeight(x);
        return x;
    }

    Node reBalance(Node z){
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

    Node insert(Node node, int value) {
        if (node == null) {
            return new Node(value);
        } else if (node.value > value) {
            node.left = insert(node.left, value);
        } else if (node.value < value) {
            node.right = insert(node.right, value);
        } else {
            throw new RuntimeException("duplicate Key!");
        }
        return reBalance(node);
    }

    Node delete(Node node, int value) {
        if (node == null) {
            return node;
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
            node = reBalance(node);
        }
        return node;
    }

    private Node mostLeftChild(Node node) {
        return node.left == null ? node : mostLeftChild(node.left);
    }

    Node find(int value) {
        Node current = root;
        while (current != null) {
            if (current.value == value) {
                break;
            }
            current = current.value < value ? current.right : current.left;
        }
        return current;
    }

    /**
     * Depth-First(深度遍历) Search
     * 先根节点 然后左半边树 然后右半边树
     */
    public void traversePreOrder(Node node) {
        if (node != null) {
            System.out.print(" " + node.value);
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }

}
