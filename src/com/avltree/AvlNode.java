package com.avltree;

/**
 * Created by faith on 16/8/17.
 */
public class AvlNode<AnyType> {

    AvlNode(AnyType element) {

    }

    AvlNode(AnyType element, AvlNode<AnyType> lt, AvlNode<AnyType> rt) {
        this.element = element;
        this.left = lt;
        this.right = rt;
        height = 0;
    }

    AnyType element;
    AvlNode<AnyType> left;
    AvlNode<AnyType> right;

    int height;

}
