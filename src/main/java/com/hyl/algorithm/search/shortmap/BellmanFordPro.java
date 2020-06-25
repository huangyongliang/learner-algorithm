package com.hyl.algorithm.search.shortmap;

import java.util.Arrays;

import com.hyl.algorithm.search.base.SearchIntf;
import com.hyl.algorithm.search.base.SearchIntfFactory;

/**
 * BellmanFord列表优化版
 * <p>
 *
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-06-26 04:37
 */
public class BellmanFordPro implements SearchIntf {

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

    int[] dis;
    int[] book;
    int start;

    @Override
    public void init() {
        n = 5;
        m = 7;

        int[][] data = new int[m + 1][2];
        data[0] = new int[] {0, 0, 0};
        data[1] = new int[] {1, 2, 2};
        data[2] = new int[] {1, 5, 10};
        data[3] = new int[] {2, 3, 3};
        data[4] = new int[] {2, 5, 7};
        data[5] = new int[] {3, 4, 4};
        data[6] = new int[] {4, 5, 5};
        data[7] = new int[] {5, 3, 6};
        // 存在复权回路 无限循环下去
        // data[1] = new int[] {1, 2, 4};
        // data[2] = new int[] {2, 3, -1};
        // data[3] = new int[] {3, 5, -1};
        // data[4] = new int[] {5, 4, -1};
        // data[5] = new int[] {4, 2, -1};
        // data[6] = new int[] {2, 6, 1};

        first = new int[n + 1];
        Arrays.fill(first, -1);
        next = new int[m + 1];
        Arrays.fill(next, -1);
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
        start = 1;
        dis = new int[n + 1];
        book = new int[n + 1];
    }

    @Override
    public void find() {

        Arrays.fill(dis, 1, dis.length, 999);
        dis[start] = 0;
        int[] que = new int[n * m + 1];
        int head = 0, tail = 0;

        que[tail] = start;
        tail++;
        book[start] = 1;

        while (head < tail) {
            // 遍历每个边
            int index = first[que[head]];
            while (index != -1) {
                // 松弛
                if (dis[v[index]] > dis[u[index]] + w[index]) {
                    dis[v[index]] = dis[u[index]] + w[index];
                    if (book[v[index]] == 0) {
                        book[v[index]] = 1;
                        que[tail] = v[index];
                        tail++;
                    }
                }
                index = next[index];
            }
            book[que[head]] = 0;
            head++;
        }

    }

    @Override
    public void print() {
        System.out.print("  ");
        for (int i = 1; i <= n; i++) {
            System.out.print("\t" + i);
        }
        System.out.println();
        for (int i : dis) {
            System.out.print(i + "\t");
        }
        System.out.println();
        printLines();
    }

    public void printLines() {
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

    public static void main(String[] args) {
        SearchIntfFactory.produce(BellmanFordPro.class);
    }

}
