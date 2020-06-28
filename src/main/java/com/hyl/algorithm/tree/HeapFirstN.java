package com.hyl.algorithm.tree;

import java.util.Arrays;

/**
 * 第n大的数
 * <p>
 *
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-06-28 10:12
 */
public class HeapFirstN {

    public static void main(String[] args) {
        int[] a = {99, 5, 36, 7, 22, 17, 46, 12, 2, 19, 25, 28, 1, 92};
        Heap heap = new Heap();
        int n = 3;
        for (int i = 0; i < n; i++) {
            heap.add(a[i]);
        }

        for (int i = n; i < a.length; i++) {
            if (a[i] > heap.getRoot()) {
                heap.replaceRoot(a[i]);
            }
        }

        System.out.println(Arrays.toString(a));
        System.out.println("第" + n + "大的数：" + heap.getRoot());

    }

}
