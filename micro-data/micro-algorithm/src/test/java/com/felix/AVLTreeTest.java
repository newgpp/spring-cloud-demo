package com.felix;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author felix
 * @desc some desc
 */
public class AVLTreeTest {

    private final AVLTree tree = new AVLTree();

    @Before
    public void init() {
        Node current = null;
        for (int i = 1; i < 11; i++) {
            current = tree.insert(current, i);
        }
        tree.root = current;
    }

    @Test
    public void travel_tree() {
        tree.traversePreOrder(tree.root);
    }

    @Test
    public void avl_tree_should_auto_balanced() {
        int balance = tree.getBalance(tree.root);
        Assert.assertTrue(Math.abs(balance) <= 1);
    }
}
