package com.hyl.algorithm.search.graph;

/**
 * 城市地图
 *
 * <p>
 *
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-06-23 00:32
 */
public class CityMapGame {

    int[][] map;
    int[] book;

    int start;
    int end;
    int path;

    public void init() {
        // 图的邻接矩阵存储法
        map = new int[6][6];
        // 有向图
        // map[0] = new int[] {0, 1, 2, 3, 4, 5};
        // map[1] = new int[] {1, 0, 2, 0, 0, 10};
        // map[2] = new int[] {2, 0, 0, 3, 0, 7};
        // map[3] = new int[] {3, 4, 0, 0, 4, 0};
        // map[4] = new int[] {4, 0, 0, 0, 0, 5};
        // map[5] = new int[] {5, 0, 0, 3, 0, 0};
        // 无向图
        map[0] = new int[] {0, 1, 2, 3, 4, 5};
        map[1] = new int[] {1, 0, 2, 4, 0, 10};
        map[2] = new int[] {2, 2, 0, 3, 0, 7};
        map[3] = new int[] {3, 4, 3, 0, 4, 3};
        map[4] = new int[] {4, 0, 0, 4, 0, 5};
        map[5] = new int[] {5, 10, 7, 3, 5, 0};

        book = new int[6];

        start = 1;
        end = 5;
        path = Integer.MAX_VALUE;
    }

    public void find() {
        findBFS();
    }

    private void findBFS() {
        MapPath[] que = new MapPath[5 * 5 + 1];
        // 累加是的路径，标记是路径
        int[][] books = new int[6][6];

        int head = 0;
        int tail = 0;

        que[tail] = new MapPath();
        que[tail].x = start;
        que[tail].y = start;
        que[tail].path = 0;
        tail++;
        books[start][start] = 1;

        while (head < tail) {

            MapPath mapPath = que[head];
            int cur = mapPath.y;
            if (cur == end) {
                if (mapPath.path < path) {
                    path = mapPath.path;
                    this.printPath(mapPath);
                    System.out.println("\t总路径:"+mapPath.path);
                }
            }

            for (int i = 1; i <= 5; i++) {
                if (map[cur][i] > 0 && books[cur][i] == 0) {
                    books[cur][i] = 1;
                    que[tail] = new MapPath();
                    que[tail].x = cur;
                    que[tail].y = i;
                    que[tail].path = mapPath.path + map[cur][i];
                    que[tail].p = mapPath;
                    tail++;
                }

            }

            head++;
        }

    }

    private void printPath(MapPath mapPath) {
        if (mapPath.p == null) {
            return;
        }
        printPath(mapPath.p);
        System.out.print(mapPath);
    }

    private void findDFS() {

        book[start] = 1;
        findDFS(start, 0);
    }

    private void findDFS(int cur, int totalPath) {

        if (cur == end) {
            if (totalPath < path) {
                path = totalPath;
                return;
            }
        }

        for (int i = 1; i <= 5; i++) {
            if (map[cur][i] > 0 && book[i] == 0) {
                book[i] = 1;
                this.findDFS(i, totalPath + map[cur][i]);
                book[i] = 0;
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
        System.out.println("最短路途：" + path);
    }

    public static class MapPath {
        // 出发城市
        int x;
        // 到达城市
        int y;
        // 累加路径
        int path;
        // 上个路径
        MapPath p;

        @Override
        public String toString() {
            return x + "->" + y + "\t";
        }
    }

    public static void main(String[] args) {
        CityMapGame cityMapGame = new CityMapGame();
        cityMapGame.init();
        cityMapGame.find();
        cityMapGame.print();
    }

}
