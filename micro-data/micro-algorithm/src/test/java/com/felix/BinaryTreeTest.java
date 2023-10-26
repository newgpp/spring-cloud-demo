package com.felix;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author felix
 * @desc some desc
 */
public class BinaryTreeTest {

    private final SortedBinaryTree bt = new SortedBinaryTree();

    @Before
    public void init() {
        bt.add(5);
        bt.add(3);
        bt.add(8);
        bt.add(2);
        bt.add(4);
        bt.add(7);
        bt.add(9);
        bt.add(1);
        bt.add(6);
        bt.add(10);
    }

    @Test
    public void contains_node_should_return_true() {
        Assert.assertTrue(bt.containsNode(1));
        Assert.assertTrue(bt.containsNode(5));
        Assert.assertTrue(bt.containsNode(10));
        Assert.assertFalse(bt.containsNode(11));
    }

    @Test
    public void delete_node_should_success() {
        Assert.assertTrue(bt.containsNode(5));
        bt.delete(5);
        Assert.assertFalse(bt.containsNode(5));
    }

    @Test
    public void travel_depths_in_order() {
        bt.traverseInOrder(bt.root);
    }

    @Test
    public void travel_depths_pre_order() {
        bt.traversePreOrder(bt.root);
    }

    @Test
    public void travel_depths_post_order() {
        bt.traversePostOrder(bt.root);
    }

    @Test
    public void travel_level_order() {
        bt.traverseLevelOrder();
    }


}