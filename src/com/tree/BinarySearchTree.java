package com.tree;

import java.util.Comparator;

/**
 * Created by faith on 16/8/12.
 */
public class BinarySearchTree<AnyType> {

    private BinaryNode<AnyType> root;
    private Comparator<? super AnyType> cmp;

    public BinarySearchTree() {
        this(null);
    }

    public BinarySearchTree(Comparator<? super AnyType> cmp) {
        this.cmp = cmp;
        root = null;
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

    private void printTreeFirstOrder(BinaryNode<AnyType> t) {
        if (t != null) {
            System.out.print(t.element + " ");
            printTreeFirstOrder(t.left);
            printTreeFirstOrder(t.right);
        }
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

    private boolean contain(AnyType x, BinaryNode<AnyType> t) {
        if (t == null) {
            return false;
        }

        int compareResult = myCompare(x, t.element);

        if (compareResult < 0) {
            return contain(x, t.left);
        } else if (compareResult > 0) {
            return contain(x, t.right);
        } else {
            return true;
        }
    }


    /**
     * 递归实现
     *
     * @param t
     * @return
     */
    private BinaryNode<AnyType> findMin(BinaryNode<AnyType> t) {

        if (t == null) {
            return null;
        } else if (t.left == null) {
            return t;
        } else {
            return findMin(t.left);
        }


    }


    /**
     * 非递归的方式查找
     *
     * @param t
     * @return
     */
    private BinaryNode<AnyType> findMax(BinaryNode<AnyType> t) {

        if (t != null) {
            while (t.right != null) {
                t = t.right;
            }
        }

        return t;
    }


    private BinaryNode<AnyType> insert(AnyType x, BinaryNode<AnyType> t) {
        if (t == null) {
            return new BinaryNode<AnyType>(x, null, null);
        }

        int compareResult = myCompare(x, t.element);

        if (compareResult < 0) {
            t.left = insert(x, t.left);
        } else if (compareResult > 0) {
            t.right = insert(x, t.right);
        }
//        System.out.println(x+"---");
        return t;
    }

    private BinaryNode<AnyType> remove(AnyType x, BinaryNode<AnyType> t) {
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
        return t;
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();

        tree.root = tree.insert(5, tree.root);
        tree.root = tree.insert(2, tree.root);
        tree.root = tree.insert(2, tree.root);
        tree.root = tree.insert(8, tree.root);
        tree.root = tree.insert(1, tree.root);
        tree.root = tree.insert(11, tree.root);

        tree.printTreeFirstOrder();
        System.out.println();

        System.out.println(tree.root.hashCode());
        BinaryNode<Integer> max = tree.findMax(tree.root);
        System.out.println("max:" + max.hashCode() + "root:" + tree.root.hashCode());
        System.out.println(max.element);


        System.out.println(tree.findMin(tree.root).element);
        System.out.println(tree.root.element);


        tree.root = tree.remove(2, tree.root);
        tree.printTreeFirstOrder();
    }
}


