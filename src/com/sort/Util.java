package com.sort;

/**
 * Created by faith on 16/8/1.
 */
public class Util {

    public static final void printArray(Object[] objs) {
        for (Object o : objs) {
            System.out.print(o + " ");
        }
    }


    public static final Integer[] generateRandom(int number) {
        Integer[] a = new Integer[number];
        for (int i = 0; i < a.length; i++) {
            int random = (int) (Math.random() * 1000);
//            System.out.println(random);
            a[i] = random;
        }
        return a;
    }

    public static void main(String[] args) {
        Integer[]  a = null;
        a = Util.generateRandom(100);
        printArray(a);
    }
}
