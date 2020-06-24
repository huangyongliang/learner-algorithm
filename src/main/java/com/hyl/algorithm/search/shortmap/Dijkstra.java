package com.hyl.algorithm.search.shortmap;

import com.hyl.algorithm.search.base.SearchIntf;
import com.hyl.algorithm.search.base.SearchIntfFactory;

/**
 * Dijkstra
 * <p>
 *
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-06-24 02:18
 */
public class Dijkstra implements SearchIntf {

    int[][] map;
    int[] book;
    int[] dis;
    int start;
    int count;

    @Override
    public void init() {
        map = new int[7][7];
        map[0] = new int[] {0, 1, 2, 3, 4, 5, 6};
        map[1] = new int[] {1, 0, 1, 12, 999, 999, 999};
        map[2] = new int[] {2, 999, 0, 9, 3, 999, 999};
        map[3] = new int[] {3, 999, 999, 0, 999, 5, 999};
        map[4] = new int[] {4, 999, 999, 4, 0, 13, 15};
        map[5] = new int[] {5, 999, 999, 999, 999, 0, 4};
        map[6] = new int[] {6, 999, 999, 999, 999, 999, 0};

        book = new int[7];
        dis = new int[7];
        start = 1;
        count = 0;
    }

    @Override
    public void find() {
        //除去0位置标记
        book[0] = 1;

        // 初始化dis
        System.arraycopy(map[start], 1, dis, 1, 6);
        // 第一个顶点就是确定的点
        book[start] = 1;
        count++;
        // findD(start);
        findFor();
    }



    private void findFor(){
        for (int i = 1;i<=6-1;i++){
            // 确定新的点
            int min = 7;
            int minMap = 9999;
            for (int j = 1; j <= 6; j++) {
                if (book[j] == 1) {
                    continue;
                }
                if (minMap > dis[j]) {
                    min = j;
                    minMap = dis[j];
                }
            }
            book[min] = 1;

            // 松弛
            for (int k = 1; k <= 6; k++) {
                if (book[k] == 1) {
                    continue;
                }
                if (dis[k] > dis[min] + map[min][k]) {
                    dis[k] = dis[min] + map[min][k];
                }
            }
        }
    }

    private void findD(int cur) {
        if (count == 6) {
            return;
        }

        // 松弛
        for (int i = 1; i <= 6; i++) {
            if (book[i] == 1) {
                continue;
            }
            if (dis[i] > dis[cur] + map[cur][i]) {
                dis[i] = dis[cur] + map[cur][i];
            }
        }
        // 确定新的点
        int min = 7;
        int minMap = 9999;
        for (int i = 1; i <= 6; i++) {
            if (book[i] == 1) {
                continue;
            }
            if (minMap > dis[i]) {
                min = i;
                minMap = dis[i];
            }
        }
        book[min] = 1;
        count++;
        //继续需要寻找
        findD(min);

    }

    @Override
    public void print() {
        System.out.println();
        for (int[] ints : map) {
            for (int i : ints) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }

        System.out.println("距离" + start + "最近的数组:");
        System.out.println("  \t1\t2\t3\t4\t5\t6");
        for (int i : dis) {
            System.out.print(i + "\t");
        }
        System.out.println();
        for (int i : book) {
            System.out.print(i + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        SearchIntfFactory.produce(Dijkstra.class);
    }
}
