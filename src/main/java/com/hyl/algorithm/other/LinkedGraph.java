package com.hyl.algorithm.other;

import java.util.Arrays;

/**
 * 邻接表图
 * <p>
 *
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-06-30 01:30
 */
public class LinkedGraph {

    int[] u, v, w;
    int[] first, next;
    int size;
    int n;

    static final int MAX_PATH = 999;

    public LinkedGraph(int n, int m) {
        this.n = n;
        first = new int[n + 1];
        next = new int[m * 2 + 1];
        u = new int[m * 2 + 1];
        v = new int[m * 2 + 1];
        w = new int[m * 2 + 1];
        Arrays.fill(first, -1);
        Arrays.fill(next, -1);
        size = 0;
    }

    public void addUndirected(int x, int y, int path) {
        this.addDirected(x, y, path);
        this.addDirected(y, x, path);
    }

    public void addDirected(int x, int y, int path) {
        u[size] = x;
        v[size] = y;
        w[size] = path;

        next[size] = first[x];
        first[x] = size;
        size++;
    }

    public int getPath(int x, int y) {

        int index = first[x];
        while (index != -1) {

            if (v[index] == y){
                return w[index];
            }
            index = next[index];
        }
        return MAX_PATH;
    }


    public void printLines(){
        for (int i = 1; i <= n; i++) {
            System.out.print(i + ":\t");
            if (first[i] != -1) {
                System.out.print("(" + u[first[i]] + "," + v[first[i]] + "," + w[first[i]] + ")\t");
                int nextIndex = next[first[i]];
                while (nextIndex != -1) {
                    System.out.print("(" + u[nextIndex] + "," + v[nextIndex] + "," + w[nextIndex] + ")\t");
                    nextIndex = next[nextIndex];
                }
            }
            System.out.println();
        }
    }

}
