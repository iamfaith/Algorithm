package com.avltree;

import java.util.Comparator;

/**
 * Created by faith on 16/8/17.
 */
public class AvlTree<AnyType> {

    private AvlNode<AnyType> root;
    private Comparator<? super AnyType> cmp;
    private static final int ALLOWED_IMBALANCE = 1;


    public AvlTree() {
        this(null);
    }

    public AvlTree(Comparator<? super AnyType> cmp) {
        this.cmp = cmp;
    }

    private int height(AvlNode<AnyType> t) {
        return t == null ? -1 : t.height;
    }

    /**
     * @param lhs
     * @param rhs
     * @return int compare result
     */
    private int myCompare(AnyType lhs, AnyType rhs) {
        if (cmp != null) {
            return cmp.compare(lhs, rhs);
        } else {
            return ((Comparable) lhs).compareTo(rhs);
        }

    }

    private AvlNode<AnyType> insert(AnyType x, AvlNode<AnyType> t) {
        if (t == null) {
            return new AvlNode<AnyType>(x, null, null);

        }

        int compareResult = myCompare(x, t.element);

        if (compareResult < 0) {
            t.left = insert(x, t.left);
        } else if (compareResult > 0) {
            t.right = insert(x, t.right);
        } else {

        }

        return balance(t);

    }


    /**
     * only for debug
     * @param x
     * @param t
     * @return
     */
    private AvlNode<AnyType> balance(AnyType x, AvlNode<AnyType> t) {
        System.out.println("call balance---" + x);
        return balance(t);
    }

    private AvlNode<AnyType> balance(AvlNode<AnyType> t) {

        if (t == null) {
            return t;
        }

        /**
         *   左边失衡
         */
        if (height(t.left) - height(t.right) > ALLOWED_IMBALANCE) {
            /**
             *     t
             *   A                  A
             *  B         ==>    B     t
             */
            if (height(t.left.left) >= height(t.left.right)) {
                t = rotateWithLeftChild(t);
            } else {
                /**
                 *     t              B
                 *  A       ==>    A     t
                 *     B
                 */
                t = doubleWithLeftChild(t);
            }
        }

        if (height(t.right) - height(t.left) > ALLOWED_IMBALANCE) {
            /**
             *    t                     A
             *       A      ==>      t     B
             *          B
             */
            if (height(t.right.right) >= height(t.right.left)) {
                t = rotateWithRightChild(t);
            } else {
                /**
                 *     t              B
                 *        A  ==>   t     A
                 *     B
                 */
                t = doubleWithRightChild(t);
            }

        }
        t.height = Math.max(height(t.left), height(t.right)) + 1;
        return t;
    }


    /**
     *       k2             k1
     *    k1    Z   ==>   X    k2
     *  X   Y                Y    Z
     * @param k2
     * @return
     */
    private AvlNode<AnyType> rotateWithLeftChild(AvlNode<AnyType> k2) {
        AvlNode<AnyType> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;

        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;

        return k1;
    }


    /**
     *      k2                    k1
     *    Z     k1  ===>      K2      Y
     *         X   Y        Z    X
     * @param k2
     * @return
     */
    private AvlNode<AnyType> rotateWithRightChild(AvlNode<AnyType> k2) {
        AvlNode<AnyType>  k1 = k2.right;
        k2.right = k1.left;
        k1.left = k2;

        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(k2.height, height(k1.right)) + 1;
        return k1;
    }

    /**
     *         k3                             k2
     *     k1          Z     ==>          k1      k3
     *   A      k2                     A     B   C    Z
     *        B    C
     * @param k3
     * @return
     */
    private AvlNode<AnyType> doubleWithLeftChild(AvlNode<AnyType> k3) {

        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    /**
     *         k3                            K2
     *     Z       K1         ==>         k3       k1
     *           K2     A              Z     B   C    A
     *         B    C
     * @param k3
     * @return
     */
    private AvlNode<AnyType> doubleWithRightChild(AvlNode<AnyType> k3) {

        k3.right = rotateWithLeftChild(k3.right);
        return rotateWithRightChild(k3);
    }


    private AvlNode<AnyType> findMin(AvlNode<AnyType> t) {
        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        } else {
            return findMin(t.left);
        }
    }

    private AvlNode<AnyType> remove(AnyType x, AvlNode<AnyType> t) {
        if (t == null) {
            return t;
        }

        int compareResult = myCompare(x, t.element);

        if (compareResult < 0) {
            t.left = remove(x, t.left);
        } else if (compareResult > 0) {
            t.right = remove(x, t.right);
        } else if (t.left != null && t.right != null) {
            t.element = findMin(t.right).element;
            t.right = remove(t.element, t.right);

        } else {
            t = (t.left != null) ? t.left : t.right;
        }
        return balance(t);
    }

    /**
     * 先序遍历tree
     */
    public void printTreeFirstOrder() {
        if (root == null) {
            System.out.println("empty tree");
        } else {
            printTreeFirstOrder(root);
        }

    }

    private void printTreeFirstOrder(AvlNode<AnyType> t) {
        if (t != null) {
            System.out.print(t.element + " ");
            printTreeFirstOrder(t.left);
            printTreeFirstOrder(t.right);
        }
    }


    private AvlNode<AnyType> findMax(AvlNode<AnyType> t) {
        if (t != null) {
            while(t.right != null)
                t = t.right;
        }
        return t;
    }

    public static void main(String[] args) {
        AvlTree<Integer> tree = new AvlTree<Integer>();

//        tree.root = tree.insert(5, tree.root);
//        tree.root = tree.insert(2, tree.root);
//        tree.root = tree.insert(2, tree.root);
//        tree.root = tree.insert(3, tree.root);
//        tree.root = tree.insert(2, tree.root);
//        tree.root = tree.insert(1, tree.root);

        for (int i = 0; i < 1024 * 1024 * 100; i++) {
            tree.root = tree.insert(i, tree.root);
        }


//        tree.printTreeFirstOrder();
        System.out.println();


//        tree.root = tree.remove(2, tree.root);
//
//        System.out.println("max is " + tree.findMax(tree.root).element);
//        tree.printTreeFirstOrder();
    }
}
