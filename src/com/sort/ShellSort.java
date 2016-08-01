package com.sort;

/**
 *
 * 1.希尔排序是直接插入的一种改进，在效率上较直接插入排序有较大的改进。
 直接插入排序每次只能将Record移动一个位置，即增量increment为1，而希尔排序开始时增量较大，分组较多，每组Record数目少，故各组内直接插入较快；increment递减之后分组逐渐减少，Record增多，但由于已经在increment较大时进行过排序，表更接近于有序状态，新一趟的排序也较快。
 2.我在实验中子表的排序是通过定义一个新表，然后调用直接插入函数。但这种方法效率并不高，而且浪费空间；直接对子表进行直接插入的排序是一种更好的方法。
 3.希尔排序复杂度为：O(nlog2n)   d =1希尔与直接插入排序基本一致
 4.希尔排序是不稳定的排序算法，即相等元素的顺序可能改变
 * Created by faith on 16/8/1.
 */
public class ShellSort {

    public static <AnyType extends Comparable<? super AnyType>> void shellSort(AnyType[] a) {
        int j;

        for (int gap = a.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < a.length; i++) {
                AnyType tmp = a[i];
                for (j = i; j >= gap && tmp.compareTo(a[j - gap]) < 0; j -= gap) {
                    a[j] = a[j - gap];
                }
                a[j] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        Integer[] a = null;
        a = Util.generateRandom(100 * 10000 * 10);
//        Util.printArray(a);
        long start = System.currentTimeMillis();
        System.out.println("\nbrefore:" + start);
        ShellSort.shellSort(a);
//        Util.printArray(a);
        long end = System.currentTimeMillis();
        System.out.println("\nafter:" + end);
        System.out.println("sum:" + (end - start));
    }
}
