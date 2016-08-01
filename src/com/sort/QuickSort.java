package com.sort;

/**
 * 快速排序的优化方法
 * 1\采用截取范围,若小于截取范围退化成插入排序
 * 2\枢轴采用三数中值法
 *
 * 1.复杂度：最好 O(nlog2n)，最坏 O(n2)
 2.快速排序的最坏情况基于每次划分对主元的选择。基本的快速排序选取第一个元素作为主元。这样在数组已经有序的情况下，每次划分将得到最坏的结果。一种比较常见的优化方法是随机化算法，即随机选取一个元素作为主元。这种情况下虽然最坏情况仍然是O(n^2)，但最坏情况不再依赖于输入数据，而是由于随机函数取值不佳。
 3.快速排序是一种不稳定的排序算法
 * Created by faith on 16/8/1.
 */
public class QuickSort {

    //截止范围
    private static final int CUTOFF = 10;

    private static <AnyTye extends Comparable<? super AnyTye>> AnyTye median3(AnyTye[] a, int left, int right) {
        int center = (left + right) / 2;

        if (a[center].compareTo(a[left]) < 0) {
            SwapReference(a, left, center);
        }

        if (a[right].compareTo(a[left]) < 0) {
            SwapReference(a, left, right);
        }

        if (a[right].compareTo(a[center]) < 0) {
            SwapReference(a, center, right);
        }

        //place pivot at position right - 1
        SwapReference(a, center, right - 1);

        //返回基准值
        return a[right - 1];
    }

    private static final <AnyTye extends Comparable<? super AnyTye>> void SwapReference(AnyTye[] a, int left, int right) {
        AnyTye temp = a[left];
        a[left] = a[right];
        a[right] = temp;

    }

    private static <AnyTye extends Comparable<? super AnyTye>> void quicksort(AnyTye[] a, int left, int right) {
        if (left + CUTOFF <= right) {
            AnyTye pivot = median3(a, left, right);
//            System.out.println(pivot);
            int i = left + 1;
            int j = right - 2;
//            AnyTye tmp = a[j];
            while (i < j) {
                while (i < j && a[i].compareTo(pivot) <= 0) {
                    i++;
                }
//                a[j] = a[i];

                while (i < j && a[j].compareTo(pivot) >= 0) {
                    j--;
                }
//                a[i] = a[j];
                if (i < j) {
                    SwapReference(a, i, j);
                }
            }
//            a[j] = tmp;
            //交换基准值和i指向的值
            SwapReference(a, i, right - 1);

            quicksort(a, left, i - 1);//sort small elements
            quicksort(a, i + 1, right); //sort large elements
        } else {
            InsertSort.insertionSort(a, left, right);
        }

    }

    public static <AnyTye extends Comparable<? super AnyTye>> void quicksort(AnyTye[] a) {
        quicksort(a, 0, a.length - 1);
    }


    public static void main(String[] args) {
        Integer[] a = null;
        a = Util.generateRandom(100 * 10000 * 5);
//        Util.printArray(a);
        long start = System.currentTimeMillis();
        System.out.println("\nbrefore:" + start);
        QuickSort.quicksort(a);
//        Util.printArray(a);
        long end = System.currentTimeMillis();
        System.out.println("\nafter:" + end);
        System.out.println("sum:" + (end - start));
    }
}
