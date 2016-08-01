package com.sort;

/**
 *
 * 典型的快速排序,效率没有QuickSort高
 * Created by faith on 16/8/1.
 */
public class QuickSort2 {

    public static <AnyTye extends Comparable<? super AnyTye>> void quicksort(AnyTye[] a) {
        quicksort(a, 0, a.length - 1);
    }

    private static <AnyTye extends Comparable<? super AnyTye>> void quicksort(AnyTye[] a, int left, int right) {
        if (left < right) {
            int mid = getMiddle(a, left, right);
//            System.out.println(mid);
            quicksort(a, left, mid - 1); //对左部分
            quicksort(a, mid + 1, right);//对右部分
        }

    }

    private static <AnyTye extends Comparable<? super AnyTye>> int getMiddle(AnyTye[] a, int low, int high) {
        AnyTye tmp = a[low];
        while (low < high) {
            while (low < high && a[high].compareTo(tmp) >= 0) {
                high--;
            }
            a[low] = a[high];
            while (low < high && a[low].compareTo(tmp) <= 0) {
                low++;
            }
            a[high] = a[low];
//            System.out.println(high + "[" + a[high] + "]" + "--" + low + "[" + a[high] + "]" + "--" + tmp);
        }
        //设置基准值
        a[low] = tmp;
        return low;
    }

    public static void main(String[] args) {
        Integer[] a = null;
        a = Util.generateRandom(100 * 10000 * 10);
//        Util.printArray(a);
        long start = System.currentTimeMillis();
        System.out.println("\nbrefore:" + start);
        QuickSort2.quicksort(a);
//        Util.printArray(a);
        long end = System.currentTimeMillis();
        System.out.println("\nafter:" + end);
        System.out.println("sum:" + (end - start));
    }
}
