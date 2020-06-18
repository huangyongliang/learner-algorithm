package com.hyl.algorithm.search;

/**
 * 炸弹人游戏
 * <p>
 *
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-06-18 20:56
 */
public class BombermanGame {

    private char[][] map;
    private int[][] book;

    private int[][] next;

    private int tx, ty, mx, my, startX, startY, targetX, targetY, sumG;

    public void init() {

        map = new char[11][11];
        map[0] = new char[] {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'};
        map[1] = new char[] {'#', '.', 'G', 'G', '.', 'G', '.', '.', '.', '.', '#'};
        map[2] = new char[] {'#', '.', '#', '#', '#', 'G', '#', '.', '#', '.', '#'};
        map[3] = new char[] {'#', '.', '.', 'G', '.', 'G', '.', '.', '.', '.', '#'};
        map[4] = new char[] {'#', '.', '#', 'G', '#', 'G', '#', 'G', '#', '.', '#'};
        map[5] = new char[] {'#', '.', '.', '.', '.', '.', '.', '.', '.', 'G', '#'};
        map[6] = new char[] {'#', '.', '#', 'G', '#', '.', '#', '.', '#', 'G', '#'};
        map[7] = new char[] {'#', 'G', '.', '.', '.', '.', '.', '.', '.', 'G', '#'};
        map[8] = new char[] {'#', 'G', '#', 'G', '#', 'G', '#', '.', '#', 'G', '#'};
        map[9] = new char[] {'#', '.', '.', 'G', '.', 'G', '.', 'G', '.', '.', '#'};
        map[10] = new char[] {'#', '#', '#', '#', '#', '#', '#', '#', '#', '#', '#'};

        book = new int[11][11];

        mx = 11;
        my = 11;

        startX = 1;
        startY = 1;

        next = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    }

    public void find() {
        // this.findBFS();
        this.findDFS(startX,startY);
    }

    /**
     * 深度优先算法
     */
    private void findDFS(int x,int y) {

        //越界了
        if (x < 0 || x > mx || y > my || y < 0) {
            return;
        }

        //如果是怪物或者墙不能通行
        if (map[x][y] == 'G' || map[x][y] == '#') {
            return;
        }

        //可以通行且没有走过
        if (map[x][y] == '.' && book[x][y] == 0) {

            //标记走过
            book[x][y] = 1;

            int sumGT = getSumG(x, y);
            if (sumGT > sumG) {
                targetX = x;
                targetY = y;
                sumG = sumGT;
            }
            for (int[] ints : next) {
                findDFS(x + ints[0], y + ints[1]);
            }

            //标记走过
            book[x][y] = 0;
        }



    }


    /**
     * 广度优先算法
     */
    private void findBFS() {
        Node[] que = new Node[mx * my];

        int head = 0, tail = 0;

        tx = startX;
        ty = startY;
        //标记已走过
        book[tx][ty] = 1;

        targetX = tx;
        targetY = ty;
        sumG = getSumG(tx, ty);
        que[tail] = new Node();
        que[tail].x = tx;
        que[tail].y = ty;
        tail++;

        while (head < tail) {

            for (int[] ints : next) {

                tx = que[head].x + ints[0];
                ty = que[head].y + ints[1];

                //越界了
                if (tx < 0 || tx > mx || ty > my || ty < 0) {
                    continue;
                }

                //如果是怪物或者墙不能通行
                if (map[tx][ty] == 'G' || map[tx][ty] == '#') {
                    continue;
                }

                //可以通行且没有走过
                if (map[tx][ty] == '.' && book[tx][ty] == 0) {

                    //标记走过
                    book[tx][ty] = 1;
                    //加入队列标记
                    que[tail] = new Node();
                    que[tail].x = tx;
                    que[tail].y = ty;
                    tail++;

                    int sumGT = getSumG(tx, ty);

                    if (sumGT > sumG) {
                        targetX = tx;
                        targetY = ty;
                        sumG = sumGT;
                    }
                }
            }
            head++;
        }
    }

    private int getSumG(int i, int j) {
        int sumG = 0;
        int x, y;

        // 向上统计
        x = i;
        y = j;
        while (y >= 0) {
            if (map[x][y] == 'G') {
                sumG++;
            }
            if (map[x][y] == '#') {
                break;
            }
            y--;
        }

        // 向下统计
        x = i;
        y = j;
        while (y <= my) {
            if (map[x][y] == 'G') {
                sumG++;
            }
            if (map[x][y] == '#') {
                break;
            }
            y++;
        }
        // 向左统计
        x = i;
        y = j;
        while (x >= 0) {
            if (map[x][y] == 'G') {
                sumG++;
            }
            if (map[x][y] == '#') {
                break;
            }
            x--;
        }
        // 向右统计
        x = i;
        y = j;
        while (x <= mx) {
            if (map[x][y] == 'G') {
                sumG++;
            }
            if (map[x][y] == '#') {
                break;
            }
            x++;
        }

        return sumG;
    }

    public void print() {
        System.out.println("  \t0\t1\t2\t3\t4\t5\t6\t7\t8\t9\t10");
        for (int i = 0; i < map.length; i++) {
            System.out.print("" + i + ":\t");
            for (int j = 0; j < map[i].length; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println("最大消灭怪物的坐标:(" + targetX + "," + targetY + ")," + sumG);

    }

    public static void main(String[] args) {
        BombermanGame bombermanGame = new BombermanGame();
        bombermanGame.init();
        bombermanGame.find();
        bombermanGame.print();
    }

    public static class Node {
        //横坐标
        int x;
        //纵坐标
        int y;
    }
}
