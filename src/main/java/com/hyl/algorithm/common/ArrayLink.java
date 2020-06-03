package com.hyl.algorithm.common;

/**
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-06-03 14:17
 */
public class ArrayLink {

    private int[] data;

    private int[] right;

    private int size;

    public ArrayLink() {
        data = new int[101];
        right = new int[101];
        right[0]=-1;
        size = 0;
    }

    public void add(int i) {

        data[size] = i;

        int index = 0;
        while (right[index] != -1) {
            index++;
        }
        right[index] = size;
        right[index+1] = -1;
        size++;
    }

    public void print(){
        int index = 0;
        while (right[index] != -1) {
            System.out.print(data[right[index]]+"\t");
            index++;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        ArrayLink arrayLink = new ArrayLink();

        arrayLink.add(3);
        arrayLink.add(2);
        arrayLink.add(4);
        arrayLink.add(1);
        arrayLink.print();
    }

}
