package com.hyl.algorithm.tree;

/**
 * 堆的测试类
 * <p>
 *
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-06-28 08:34
 */
public class HeapTest {
    public static void main(String[] args) {
        int[] a = {99, 5, 36, 7, 22, 17, 46, 12, 2, 19, 25, 28, 1, 92};
        Heap heap = new Heap(a, false);
        System.out.println(heap);
        heap.printTree();
        System.out.println("---------------------------------------------------------");
        heap.add(29);
        heap.printTree();
        System.out.println("---------------------------------------------------------");
        heap.add(11);
        heap.printTree();
        System.out.println("---------------------------------------------------------");
    }
}
