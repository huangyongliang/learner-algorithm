package com.hyl.algorithm.search;

/**
 * <h>迷宫游戏(深度优先算法例子)</h>
 * <li>迷宫组成：10*10 表格</li>
 * <li>迷宫中"0"代表可以通行"1"代表有障碍</li>
 * <li>目标位置：坐标(p,q),起点位置：坐标(0,0)</li>
 * <li>结果：在不经过障碍的情况下，寻找到达【目标位置】的最短路径</li>
 *
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-06-08 14:50
 */
public class MazeGame {
    /** 迷宫地图 **/
    private int[][] map = new int[10][10];
    /** 标记不走重复路 **/
    private int[][] books = new int[10][10];
    /** 目标坐标 **/
    private int targetP, targetQ;
    /** 最小步 **/
    private int minStep;
    /** 总共成功的类型 **/
    private int total;

    public void init() {
        map[0] = new int[] {0, 0, 1, 0, 1, 0, 0, 1, 0, 0};
        map[1] = new int[] {0, 0, 1, 0, 1, 0, 0, 1, 0, 0};
        map[2] = new int[] {0, 0, 1, 0, 1, 0, 0, 1, 0, 0};
        map[3] = new int[] {1, 0, 1, 0, 0, 0, 0, 1, 0, 0};
        map[4] = new int[] {0, 0, 0, 0, 1, 0, 0, 1, 0, 0};
        map[5] = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        map[6] = new int[] {1, 1, 1, 1, 1, 0, 0, 1, 0, 0};
        map[7] = new int[] {0, 0, 0, 0, 0, 0, 0, 1, 0, 0};
        map[8] = new int[] {0, 1, 1, 1, 1, 0, 1, 1, 0, 0};
        map[9] = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

        for (int i = 0; i < books.length; i++) {
            books[i] = new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        }

        targetP = 3;
        targetQ = 2;
        minStep = -1;
        total = 0;
    }

    public void find() {
        this.setStep(0, 0, 0);
    }

    private void setStep(int left, int right, int step) {

        // 到达目标判断
        if (left == targetP && right == targetQ) {

            System.out.println("成功到达步数：" + step);
            total++;
            if (step < minStep || minStep == -1) {
                minStep = step;
            }

            return;
        }

        // 越界判断
        if (left > 9 || right > 9 || left < 0 || right < 0) {
            return;
        }

        // 障碍判断
        if (map[left][right] == 1) {
            return;
        }

        // 通路向右向左前行
        if (map[left][right] == 0) {

            if (books[left][right] == 0) {

                books[left][right] = 1;
                // 向右
                setStep(left, right + 1, step + 1);
                // 向下
                setStep(left + 1, right, step + 1);
                // 向左
                setStep(left, right - 1, step + 1);
                // 向上
                setStep(left - 1, right, step + 1);

                books[left][right] = 0;
            }

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
        System.out.println("总共成功的步数：" + total);
        System.out.println("需要的最小步数：" + minStep);
    }

    public static void main(String[] args) {
        MazeGame mazeGame = new MazeGame();
        mazeGame.init();
        mazeGame.find();
        mazeGame.print();
    }

}
