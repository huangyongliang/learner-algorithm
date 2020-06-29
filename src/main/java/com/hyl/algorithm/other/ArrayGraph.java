package com.hyl.algorithm.other;

import java.util.Arrays;

/**
 * 邻接矩阵图
 * <p>
 *
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-06-29 08:10
 */
public class ArrayGraph {
    private int[][] data;
    private int n;
    private static final int MAX_PATH = 999;

    public ArrayGraph(int n) {
        this.n = n;
        data = new int[n + 1][n + 1];
        for (int[] ints : data) {
            Arrays.fill(ints, MAX_PATH);
        }
        // 打印坐标用的
        for (int i = 0; i <= n; i++) {
            data[i][i] = 0;
            data[i][0] = i;
            data[0][i] = i;
        }
    }

    public void addUndirected(int x, int y, int path) {
        data[x][y] = path;
        data[y][x] = path;
    }

    public int getPath(int x,int y){
        return data[x][y];
    }

    public void print() {
        System.out.println("邻接矩阵图:");
        for (int[] ints : data) {
            for (int i : ints) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }
    }

}
