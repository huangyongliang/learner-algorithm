package com.hyl.algorithm.search.shortmap;

import java.util.Arrays;

import com.hyl.algorithm.search.base.SearchIntf;
import com.hyl.algorithm.search.base.SearchIntfFactory;

/**
 * BellmanFord算法的最短路径
 * <p>
 *
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-06-25 07:00
 */
public class BellmanFordAdjacencyList implements SearchIntf {

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
        n = 6;
        m = 9;

        int[][] data = new int[m + 1][2];
        data[0] = new int[] {0, 0, 0};
        data[1] = new int[] {1, 2, 1};
        data[2] = new int[] {1, 3, 12};
        data[3] = new int[] {2, 3, 9};
        data[4] = new int[] {2, 4, 3};
        data[5] = new int[] {3, 5, 5};
        data[6] = new int[] {4, 3, 4};
        data[7] = new int[] {4, 5, 13};
        data[8] = new int[] {4, 6, 15};
        data[9] = new int[] {5, 6, 4};
        // 存在复权回路
        // data[1] = new int[] {1, 2, 4};
        // data[2] = new int[] {2, 3, -1};
        // data[3] = new int[] {3, 5, -1};
        // data[4] = new int[] {5, 4, -1};
        // data[5] = new int[] {4, 2, -1};
        // data[6] = new int[] {2, 6, 1};
        //
        // data[1] = new int[] {2, 3, 2};
        // data[2] = new int[] {1, 2, -3};
        // data[3] = new int[] {1, 5, 5};
        // data[4] = new int[] {4, 5, 2};
        // data[5] = new int[] {3, 4, 3};

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

        Arrays.fill(dis, 2, dis.length, 999);
        // 用于判断不在发生松弛，既可以退出
        boolean isBreak;
        // 核心算法
        // 进行n-1次松弛，得到经过n-1个点（除了第一个点）后的所有点的松弛后的最小路径图
        for (int k = 1; k <= n - 1; k++) {
            isBreak = true;
            for (int i = 1; i <= m; i++) {
                if (dis[v[i]] > dis[u[i]] + w[i]) {
                    dis[v[i]] = dis[u[i]] + w[i];
                    isBreak = false;
                }
            }
            if (isBreak){
                break;
            }
        }

    }

    @Override
    public void print() {
        System.out.println("  \t1\t2\t3\t4\t5\t6");
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
        SearchIntfFactory.produce(BellmanFordAdjacencyList.class);
    }
}
