package com.hyl.algorithm.tree;

/**
 * 堆排序
 * <p>
 *
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-06-28 09:54
 */
public class HeapSort {
    public static void main(String[] args) {
        int[] a = {99, 5, 36, 7, 22, 17, 46, 12, 2, 19, 25, 28, 1, 92};
        Heap heap = new Heap(a, true);
        for (int i = 0; i < a.length; i++) {
            System.out.print(heap.deleteFromRoot() + "\t");
        }
        System.out.println();
    }
}
