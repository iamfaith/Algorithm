package com.sort;

/**
 * Created by faith on 16/8/1.
 */
public class ChoiceSort {

    private static final <AnyTye extends Comparable<? super AnyTye>> void SwapReference(AnyTye[] a, int left, int right) {
        AnyTye temp = a[left];
        a[left] = a[right];
        a[right] = temp;

    }


    public static <AnyTye extends Comparable<? super AnyTye>> void choiceSort(AnyTye[] a) {
        if (a == null || a.length < 0)
            return;

        int j;
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (j = i + 1; j < a.length; j++) {
                if (a[min].compareTo(a[j]) > 0) {
                    min = j;
                }

                if (i != min) {
                    SwapReference(a, i, min);
                }
            }

        }
    }

    public static void main(String[] args) {
        Integer[] a = null;
        a = Util.generateRandom(100);
//        Util.printArray(a);
        long start = System.currentTimeMillis();
        System.out.println("\nbrefore:" + start);
        ChoiceSort.choiceSort(a);
//        Util.printArray(a);
        long end = System.currentTimeMillis();
        System.out.println("\nafter:" + end);
        System.out.println("sum:" + (end - start));
    }
}
