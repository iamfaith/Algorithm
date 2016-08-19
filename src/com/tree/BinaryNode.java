package com.tree;

/**
 * Created by faith on 16/8/12.
 */
public class BinaryNode<AnyType> {

    BinaryNode(AnyType element) {
        this(element, null, null);
    }

    BinaryNode(AnyType element, BinaryNode<AnyType> left, BinaryNode<AnyType> right)  {
        this.element = element;
        this.left = left;
        this.right = right;
    }

    AnyType element; //the data in the node
    BinaryNode<AnyType> left; //left child
    BinaryNode<AnyType> right;//right child

}
