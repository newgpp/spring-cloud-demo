package com.felix;

/**
 * @author felix
 * @desc 平衡二叉树
 * https://www.baeldung.com/java-avl-trees
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

}
