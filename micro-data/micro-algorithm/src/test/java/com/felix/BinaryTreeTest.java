package com.felix;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.Stream;

/**
 * @author felix
 * @desc some desc
 */
public class BinaryTreeTest {

    private BinaryTree bt = new BinaryTree();

    @Before
    public void init(){
        Stream.iterate(1, x -> x + 1).limit(10).forEach(x -> bt.add(x));
    }

    @Test
    public void contains_node_should_return_true(){
        bt.traverseInOrder(bt.root);
        Assert.assertTrue(bt.containsNode(1));
        Assert.assertTrue(bt.containsNode(5));
        Assert.assertTrue(bt.containsNode(10));
        Assert.assertFalse(bt.containsNode(11));
    }

    @Test
    public void delete_node_should_success(){
        bt.traverseInOrder(bt.root);
        Assert.assertTrue(bt.containsNode(5));
        bt.delete(5);
        System.out.println("");
        System.out.println("---------------------");
        bt.traverseInOrder(bt.root);
    }



}