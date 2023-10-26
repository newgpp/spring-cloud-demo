package com.felix;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author felix
 * @desc some desc
 * https://www.baeldung.com/java-binary-tree
 */
public class SortedBinaryTree {

    Node root;

    private Node addRecursive(Node current, int value) {
        if (current == null) {
            return new Node(value);
        }
        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        }
        return current;
    }

    private boolean containsNodeRecursive(Node current, int value) {
        if (current == null) {
            return false;
        }
        if (value == current.value) {
            return true;
        }
        return value < current.value ? containsNodeRecursive(current.left, value) :
                containsNodeRecursive(current.right, value);
    }

    private Node deleteRecursive(Node current, int value) {
        if (current == null) {
            return null;
        }
        if (value == current.value) {
            // a node has no children
            if (current.left == null && current.right == null) {
                return null;
            }
            // a node has exactly one child
            if (current.right == null) {
                return current.left;
            }
            if (current.left == null) {
                return current.right;
            }
            // a node has two children 1.用右子树的最小节点替换当前节点 2.删除右子树的这个最小节点
            int smallestValue = findSmallestValue(current.right);
            current.value = smallestValue;
            current.right = deleteRecursive(current.right, smallestValue);
            return current;
        } else if (value < current.value) {
            current.left = deleteRecursive(current.left, value);
            return current;
        } else {
            current.right = deleteRecursive(current.right, value);
            return current;
        }
    }

    private int findSmallestValue(Node root) {
        return root.left == null ? root.value : findSmallestValue(root.left);
    }

    /**
     * Depth-First(深度遍历) Search
     * 从小到大
     */
    public void traverseInOrder(Node node) {
        if (node != null) {
            traverseInOrder(node.left);
            System.out.print(" " + node.value);
            traverseInOrder(node.right);
        }
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

    /**
     * Depth-First(深度遍历) Search
     * 从大到小
     */
    public void traversePostOrder(Node node) {
        if (node != null) {
            traversePostOrder(node.right);
            System.out.print(" " + node.value);
            traversePostOrder(node.left);
        }
    }

    /**
     * Breadth-First(广度遍历) Search
     */
    public void traverseLevelOrder() {
        if (root == null) {
            return;
        }
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);
        while (!nodes.isEmpty()) {
            Node node = nodes.peek();
            System.out.print(" " + node.value);
            if (node.left != null) {
                nodes.add(node.left);
            }
            if (node.right != null) {
                nodes.add(node.right);
            }
            nodes.remove();
        }
    }


    public void add(int value) {
        root = addRecursive(root, value);
    }

    public boolean containsNode(int value) {
        return containsNodeRecursive(root, value);
    }

    public void delete(int value) {
        deleteRecursive(root, value);
    }


}
