package com.hyl.algorithm.search.graph;

/**
 * 最少转机航班
 * <p>
 *
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-06-23 03:11
 */
public class FlightGame {

    int[][] map;
    int[] book;

    int start;
    int end;
    int count;

    public void init() {

        // 图的邻接矩阵存储法
        map = new int[6][6];
        // 无向图
        map[0] = new int[] {0, 1, 2, 3, 4, 5};
        map[1] = new int[] {1, 0, 1, 1, 0, 0};
        map[2] = new int[] {2, 1, 0, 1, 1, 0};
        map[3] = new int[] {3, 1, 1, 0, 1, 1};
        map[4] = new int[] {4, 0, 1, 1, 0, 1};
        map[5] = new int[] {5, 0, 0, 1, 1, 0};

        book = new int[6];

        start = 1;
        end = 5;
        count = Integer.MAX_VALUE;
    }

    public void find() {
        findDFS();
    }

    private void findDFS() {
        book[start] = 1;

        this.findDFS(start, 0);
    }

    private void findDFS(int cur, int countTemp) {

        if (cur == end) {
            if (countTemp < count) {
                count = countTemp;
            }
            return;
        }

        for (int i = 1; i <= 5; i++) {
            if (map[cur][i] == 1 && book[i] == 0) {
                book[i] = 1;
                this.findDFS(i,countTemp+1);
                book[i] = 0;
            }
        }

    }

    private void findBFS() {

        FlightNode[] que = new FlightNode[6];
        int head = 0;
        int tail = 0;

        que[tail] = new FlightNode();
        que[tail].i = start;
        que[tail].count = 0;
        tail++;
        book[start] = 1;

        while (head < tail) {

            FlightNode flightNode = que[head];
            int cur = flightNode.i;
            if (cur == end) {
                count = flightNode.count;
                this.printFlight(flightNode);
                break;
            }
            for (int i = 1; i <= 5; i++) {
                if (map[cur][i] == 1 && book[i] == 0) {
                    book[i] = 1;
                    que[tail] = new FlightNode();
                    que[tail].i = i;
                    que[tail].count = flightNode.count + 1;
                    que[tail].p = flightNode;
                    tail++;
                }
            }
            head++;
        }
    }

    private void printFlight(FlightNode flightNode) {

        if (flightNode == null) {
            return;
        }
        printFlight(flightNode.p);
        System.out.print(flightNode.i + "\t");

    }

    public void print() {
        System.out.println();
        for (int[] ints : map) {
            for (int i : ints) {
                System.out.print(i + "\t");
            }
            System.out.println();
        }
        System.out.println("最少转机次数：" + count);
    }

    public static class FlightNode {
        int i;
        int count;
        FlightNode p;
    }

    public static void main(String[] args) {
        FlightGame flightGame = new FlightGame();
        flightGame.init();
        flightGame.find();
        flightGame.print();
    }
}
