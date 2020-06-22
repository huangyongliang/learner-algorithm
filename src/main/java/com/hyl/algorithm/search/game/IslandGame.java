package com.hyl.algorithm.search.game;

/**
 * 宝岛探险
 * <p>
 * <li> 0 代表海洋，1-9代表陆地海拔</li>
 * <li> 计算降落地的面积</li>
 *
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-06-19 14:12
 */
public class IslandGame {

    /** 地图 **/
    private int[][] map;
    /** 标记 **/
    private int[][] book;
    /** 降落点 **/
    private int startX, startY;
    /** 面积 **/
    private int area;
    /** 岛屿范围 **/
    private int mx, my;
    /** 行走方向 **/
    private int[][] next;

    public void init() {

        mx = 10;
        my = 10;
        map = new int[mx][my];
        map[0] = new int[] {6, 1, 0, 0, 0, 0, 0, 0, 0, 0};
        map[1] = new int[] {1, 1, 0, 0, 0, 0, 1, 2, 1, 0};
        map[2] = new int[] {0, 0, 0, 0, 0, 1, 2, 3, 1, 0};
        map[3] = new int[] {0, 0, 0, 1, 9, 1, 2, 2, 1, 0};
        map[4] = new int[] {0, 0, 0, 2, 1, 8, 1, 2, 1, 0};
        map[5] = new int[] {0, 0, 0, 3, 2, 5, 1, 2, 1, 0};
        map[6] = new int[] {0, 0, 5, 4, 8, 9, 0, 2, 0, 0};
        map[7] = new int[] {1, 0, 0, 0, 0, 0, 0, 3, 0, 0};
        map[8] = new int[] {2, 1, 1, 0, 6, 0, 0, 2, 0, 0};
        map[9] = new int[] {3, 2, 1, 0, 0, 6, 0, 1, 0, 0};

        book = new int[mx][my];

        next = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

        area = 0;
        startX = 5;
        startY = 6;
    }

    public void find() {
        // this.findBFS();
        // this.findDFS(startX, startY,-1);
        this.findIslandCount();
    }

    /**
     * 需要独立子图的个数<br>
     * Floodfill漫水填充法（也称种子填充法）
     */
    private void findIslandCount() {

        int num = 0;

        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (map[i][j] > 0) {
                    num--;
                    findDFS(i, j, num);
                }
            }
        }
    }

    private void findDFS(int x, int y, int color) {
        //越界判断
        if (x < 0 || y < 0 || x >= mx || y >= my) {
            return;
        }

        if (book[x][y] == 0 && 0 < map[x][y] && map[x][y] < 10) {

            book[x][y] = 1;
            map[x][y] = color;
            area++;

            for (int[] ints : next) {
                findDFS(x + ints[0], y + ints[1], color);
            }
            //防止重复统计
            // book[x][y] = 0;
        }
    }

    /**
     * 深度优先
     *
     * @param x
     * @param y
     */
    private void findDFS(int x, int y) {

        //越界判断
        if (x < 0 || y < 0 || x >= mx || y >= my) {
            return;
        }

        if (book[x][y] == 0 && 0 < map[x][y] && map[x][y] < 10) {

            book[x][y] = 1;
            area++;

            for (int[] ints : next) {
                findDFS(x + ints[0], y + ints[1]);
            }
            //防止重复统计
            // book[x][y] = 0;
        }
    }

    /**
     * 广度优先
     */
    private void findBFS() {
        Node[] que = new Node[mx * my + 1];
        int head = 0;
        int tail = 0;
        //第一点
        int tx, ty;
        tx = startX;
        ty = startY;
        book[tx][ty] = 1;
        area++;
        //入列队
        que[tail] = new Node();
        que[tail].x = tx;
        que[tail].y = ty;
        tail++;

        while (head < tail) {

            //广度扩展
            for (int[] ints : next) {
                tx = que[head].x + ints[0];
                ty = que[head].y + ints[1];

                //越界判断
                if (tx < 0 || ty < 0 || tx >= mx || ty >= my) {
                    continue;
                }
                //标记走过，同时添加判断条件
                if (book[tx][ty] == 0 && 0 < map[tx][ty] && map[tx][ty] < 10) {

                    book[tx][ty] = 1;

                    que[tail] = new Node();
                    que[tail].x = tx;
                    que[tail].y = ty;
                    tail++;
                    //累加一块地
                    area++;
                }
            }
            head++;
        }

    }

    public void print() {
        System.out.println("  \t0\t1\t2\t3\t4\t5\t6\t7\t8\t9");
        for (int i = 0; i < map.length; i++) {
            System.out.print("" + i + ":\t");
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("起点：(" + startX + "," + startY + "),面积：" + area);
    }

    public static class Node {
        int x;
        int y;
    }

    public static void main(String[] args) {
        IslandGame islandGame = new IslandGame();
        islandGame.init();
        long start = System.currentTimeMillis();
        islandGame.find();
        long end = System.currentTimeMillis();
        islandGame.print();
        System.out.println("find函数执行时间:" + (end - start) + "ms");
    }

}
