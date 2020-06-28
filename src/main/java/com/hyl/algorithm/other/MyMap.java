package com.hyl.algorithm.other;

/**
 * 自建图
 * <p>
 *
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-06-29 04:31
 */
public class MyMap {

    int[] n;
    Line[] lines;
    int head, tail;
    static final int DEFAULT_SIZE = 50;
    int size;

    public MyMap(int n) {
        this.n = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            this.n[i] = i;
        }
        lines = new Line[DEFAULT_SIZE];
        size = 0;
        head = 0;
        tail = 0;
    }

    public void addLine(int x, int y, int path) {
        lines[tail] = new Line(x, y, path);
        tail++;
        size++;
    }

    public void setOrderLine() {
        quickSort(0, size - 1);
    }

    private void quickSort(int left, int right) {

        if (left > right) {
            return;
        }

        int home;
        int leftIndex = left;
        int rightIndex = right;

        Line first = lines[left];

        while (leftIndex != rightIndex) {

            while (lines[rightIndex].path >= first.path && leftIndex < rightIndex) {
                rightIndex--;
            }

            while (lines[leftIndex].path <= first.path && leftIndex < rightIndex) {
                leftIndex++;
            }

            if (leftIndex < rightIndex) {
                Line temp = lines[rightIndex];
                lines[rightIndex] = lines[leftIndex];
                lines[leftIndex] = temp;
            }

        }
        home = leftIndex;

        Line temp = lines[home];
        lines[home] = lines[left];
        lines[left] = temp;

        quickSort(left, home - 1);
        quickSort(home + 1, right);
    }

    public void printLine() {

        for (int i = head; i < tail; i++) {
            System.out.println(lines[i]);
        }

    }

    public boolean isConnected(int x, int y) {
        return getBoss(x) == getBoss(y);
    }

    private int getBoss(int i) {
        if (n[i] == i) {
            return i;
        }
        return getBoss(n[i]);
    }

    public boolean merge(int x,int y){
        int bossX = getBoss(x);
        int boosY = getBoss(y);
        if (boosY!=bossX){
            n[boosY] = bossX;
            return true;
        }else {
            return false;
        }
    }

    public static class Line {
        int x;
        int y;
        int path;

        public Line(int x, int y, int path) {
            this.x = x;
            this.y = y;
            this.path = path;
        }

        @Override
        public String toString() {
            return "Line{" + "x=" + x + ", y=" + y + ", path=" + path + '}';
        }
    }
}
