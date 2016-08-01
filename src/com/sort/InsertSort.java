package com.sort;

/**
 * Created by faith on 16/8/1.
 */
public class InsertSort {

    public static <AnyType extends Comparable<? super AnyType>> void insertionSort(AnyType[] a) {

        insertionSort(a, 0, a.length -1);
    }

    public static <AnyType extends Comparable<? super AnyType>> void insertionSort(AnyType[] a, int left, int right) {
        int j;

        for (int i = left + 1; i <= right; i++) {
            AnyType temp = a[i];
            for (j = i; j > 0 && temp.compareTo(a[j - 1]) < 0; j--) {
                a[j] = a[j - 1];
            }
            a[j] = temp;

        }

    }


    public static void main(String[] args) {

        Integer[] a = null;
        a = Util.generateRandom(100*10000);
//        Util.printArray(a);
        long start = System.currentTimeMillis();
        System.out.println("\nbrefore:" + start);
        InsertSort.insertionSort(a);
//        Util.printArray(a);
        long end = System.currentTimeMillis();
        System.out.println("\nafter:" + end);
        System.out.println("sum:" + (end - start));
    }
}
