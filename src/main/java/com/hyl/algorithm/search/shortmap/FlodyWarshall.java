package com.hyl.algorithm.search.shortmap;

import com.hyl.algorithm.search.base.SearchIntf;

/**
 * FlodyWarshall
 * <p>
 *
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-06-24 00:52
 */
public class FlodyWarshall implements SearchIntf {

    int[][] map;

    int[][] newMap;

    @Override
    public void init() {
        map = new int[5][5];
        map[0] = new int[] {0, 1, 2, 3, 4};
        map[1] = new int[] {1, 0, 2, 6, 4};
        map[2] = new int[] {2, 999, 0, 3, 999};
        map[3] = new int[] {3, 7, 999, 0, 1};
        map[4] = new int[] {4, 5, 999, 12, 0};

        newMap = new int[5][5];

        for (int i = 0; i < map.length; i++) {
            System.arraycopy(map[i], 0, newMap[i], 0, map[i].length);
        }

    }

    @Override
    public void find() {
        for (int k = 1; k <= 4; k++) {
            for (int i = 1; i <= 4; i++) {
                for (int j = 1; j <= 4; j++) {
                    if (newMap[i][k] + newMap[k][j] < newMap[i][j]) {
                        newMap[i][j] = newMap[i][k] + newMap[k][j];
                    }
                }
            }

        }

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
        System.out.println("newMap:");
        for (int[] ints : newMap) {
            for (int i : ints) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        SearchIntf searchIntf = new FlodyWarshall();
        searchIntf.init();
        long start = System.currentTimeMillis();
        searchIntf.find();
        long end = System.currentTimeMillis();
        System.out.println("执行时间:" + (start - end) + "ms");
        searchIntf.print();

    }

}
