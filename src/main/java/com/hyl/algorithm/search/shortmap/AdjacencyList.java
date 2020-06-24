package com.hyl.algorithm.search.shortmap;

import com.hyl.algorithm.search.base.SearchIntf;
import com.hyl.algorithm.search.base.SearchIntfFactory;

/**
 * 邻接表实现
 * <p>
 * <li> 时间空间复杂度是：O(M),(M边的数量)
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-06-25 05:02
 */
public class AdjacencyList implements SearchIntf {
    // 顶点的第一条线的下标
    int[] first;
    // 顶点线的下一哥
    int[] next;
    int[] u;
    int[] v;
    int[] w;
    //顶点说
    int n;
    //线条数
    int m;

    @Override
    public void init() {
        n = 5;
        m = 5;

        int[][] data = new int[m + 1][2];
        data[0] = new int[] {0, 0, 0};
        data[1] = new int[] {1, 4, 9};
        data[2] = new int[] {4, 3, 8};
        data[3] = new int[] {1, 2, 5};
        data[4] = new int[] {2, 4, 6};
        data[5] = new int[] {1, 3, 7};

        first = new int[n + 1];
        next = new int[m + 1];
        u = new int[m + 1];
        v = new int[m + 1];
        w = new int[m + 1];

        for (int i = 1; i <= m; i++) {
            u[i] = data[i][0];
            v[i] = data[i][1];
            w[i] = data[i][2];

            next[i] = first[u[i]];
            first[u[i]] = i;

        }

    }

    @Override
    public void find() {

    }

    @Override
    public void print() {
        for (int i = 1; i <= n; i++) {
            System.out.print(i + ":\t");
            if (first[i] != 0) {
                System.out.print("(" + u[first[i]] + "," + v[first[i]] + "," + w[first[i]] + ")\t");
                int nextIndex = next[first[i]];
                while (nextIndex != 0) {
                    System.out.print("(" + u[nextIndex] + "," + v[nextIndex] + "," + w[nextIndex] + ")\t");
                    nextIndex = next[nextIndex];
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        SearchIntfFactory.produce(AdjacencyList.class);
    }

}
