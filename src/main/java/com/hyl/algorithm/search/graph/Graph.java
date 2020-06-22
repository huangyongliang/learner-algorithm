package com.hyl.algorithm.search.graph;

/**
 * 图的遍历
 * <p>
 *
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-06-22 17:56
 */
public class Graph {

    int[][] map;
    int[] book;
    int sum;
    int start;

    public void init() {
        // 图的邻接矩阵存储法
        //        1
        //       / \
        //      2   3 - 5
        //      |
        //      4
        map = new int[6][6];
        map[0] = new int[] {0, 1, 2, 3, 4, 5};
        map[1] = new int[] {1, 0, 1, 1, 8, 1};
        map[2] = new int[] {2, 1, 0, 8, 1, 8};
        map[3] = new int[] {3, 1, 8, 0, 8, 1};
        map[4] = new int[] {4, 8, 1, 8, 0, 8};
        map[5] = new int[] {5, 1, 8, 1, 8, 0};

        book = new int[6];
        sum = 0;
        start = 1;

    }

    public void find() {
        this.findBFS();
    }

    private void findBFS() {
        int[] que = new int[6];
        int head = 0;
        int tail = 0;

        int ti = start;
        book[ti] = 1;
        sum++;
        que[tail] = ti;
        tail++;

        System.out.print(start + "->");

        while (head < tail) {

            ti = que[head];
            for (int i = 1; i <= 5; i++) {

                if (map[ti][i] == 1 && book[i] == 0) {
                    book[i] = 1;
                    que[tail] = i;
                    tail++;
                    sum++;
                    System.out.print(i + "->");
                }
            }
            head++;
        }

    }

    private void findDFS() {
        book[start] = 1;
        this.DFS(start);
    }

    private void DFS(int cur) {

        System.out.print(cur + "->");
        sum++;
        for (int i = 1; i <= 5; i++) {
            if (map[cur][i] == 1 && book[i] == 0) {
                book[i] = 1;
                DFS(i);
            }
        }
    }

    public void print() {

        System.out.println();
        for (int[] ints : map) {
            for (int i : ints) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }

        System.out.println("节点总数：" + sum);
    }

    public static void main(String[] args) {
        Graph graph = new Graph();
        graph.init();
        graph.find();
        graph.print();
    }

}
