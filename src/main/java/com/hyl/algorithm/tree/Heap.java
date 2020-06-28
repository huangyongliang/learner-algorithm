package com.hyl.algorithm.tree;

import java.util.Arrays;

/**
 * 堆（数组结构实现的完全二叉树）
 * <p>
 *
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-06-28 06:09
 */
public class Heap {

    // 数据
    private int[] h;
    // 数据长度
    private int size;
    // 默认初始化最大长度
    static final int DEFAULT_SIZE = 100;
    // 最大长度
    private int maxSize;

    public Heap() {
        this(DEFAULT_SIZE);
    }

    public Heap(int size) {
        if (size < 0) {
            throw new RuntimeException("size :" + size + " illegal!");
        }
        h = new int[size + 1];
        this.size = 0;
        maxSize = size;
    }

    public Heap(int[] a, boolean up) {
        this();
        if (up) {
            // 一个一个的添加
            for (int i : a) {
                add(i);
            }
        } else {
            if (a.length >= maxSize) {
                resize();
            }
            // 初始化一个完全二叉树
            System.arraycopy(a, 0, h, 1, a.length);
            size = a.length;
            // 从一个非叶结点开始向下调整
            for (int i = size / 2; i >= 1; i--) {
                shiftDown(i);
            }
        }

    }

    private void resize() {
        // 扩展最大size
        maxSize = maxSize * 2 + 1;
        int[] oldH = h;
        int[] newH = new int[maxSize];
        // 拷贝原始数组到新数组
        System.arraycopy(oldH, 0, newH, 0, oldH.length);
        h = newH;
    }

    public void add(int value) {
        if (size + 1 > maxSize)
            resize();
        size++;
        h[size] = value;
        shiftUp(size);
    }

    public void replaceRoot(int value) {
        if (size == 0) {
            throw new RuntimeException("no root! size : " + size);
        }
        h[1] = value;
        shiftDown(1);
    }

    public int getRoot() {
        if (size == 0) {
            throw new RuntimeException("no root! size : " + size);
        }
        return h[1];
    }

    public int deleteFromRoot() {

        if (size == 0) {
            throw new RuntimeException("can not delete! size : " + size);
        }

        int result = h[1];
        h[1] = h[size];
        size--;
        shiftDown(1);
        return result;
    }

    private void shiftUp(int i) {
        if (i <= 1) {
            return;
        }

        // 用于判定不需要调整的标记
        int flag = 0;
        while (i != 1 && flag == 0) {
            // 判定父结点
            if (h[i / 2] > h[i]) {
                // 调整
                swap(i / 2, i);
            } else {
                // 不再需要调整
                flag = 1;
            }
            i = i / 2;
        }
    }

    private void shiftDown(int i) {
        if (2 * i > size) {
            return;
        }

        // 用于判定不需要调整的标记
        int flag = 0;
        while (2 * i <= size && flag == 0) {
            int k;
            // 判定右儿子是否存在
            if (2 * i + 1 <= size) {
                // 判定左右儿子哪个小
                k = h[2 * i] < h[2 * i + 1] ? 2 * i : 2 * i + 1;
            } else {
                // 不存在直接适用左儿子
                k = 2 * i;
            }

            if (h[k] < h[i]) {
                // 调整
                swap(k, i);
            } else {
                // 不需要调整标记
                flag = 1;
            }
            i = k;
        }
    }

    private void swap(int x, int y) {
        int temp = h[x];
        h[x] = h[y];
        h[y] = temp;
    }

    @Override
    public String toString() {
        return "Heap{" + "h=" + Arrays.toString(h) + '}';
    }

    public void printTree() {
        print(" ", 1);
    }

    private void print(String space, int index) {

        if (index * 2 <= size) {
            print(space + "\t\t", index * 2);
            System.out.println(space + "\t/");
        }
        System.out.println(space + h[index]);

        if (index * 2 + 1 <= size) {
            System.out.println(space + "\t\\");
            print(space + "\t\t", index * 2 + 1);
        }
    }
}
