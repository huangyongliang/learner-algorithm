package com.hyl.algorithm.search;

/**
 * 广度优先
 * <p>
 * <li>时间复杂度 O(n)</li>
 * <li>空间复杂度 O(n)</li>
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-06-17 21:29
 */
public class BreadthFirstSearch {

    Note que[] = new Note[2501];

    int a[][] = new int[51][51];
    int book[][] = new int[51][51];

    //方向
    int next[][] = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};

    int head, tail;
    int k, n, m, startX, startY, p, q, tx, ty, flag;

    public void init() {
        //起始位置
        startX = 1;
        startY = 1;
        //目标位置
        p = 3;
        q = 3;
        //地图大小
        n = 10;
        m = 10;

    }

    public void find() {

        //队列初始化
        head = 1;
        tail = 1;
        //往队列插入迷宫入口坐标

        que[tail] = new Note();
        que[tail].x = startX;
        que[tail].y = startY;
        que[tail].f = 0;
        que[tail].s = 0;
        tail++;
        book[startX][startY] = 1;

        //用来标记是否到达目标点，0表示没有达到，1表示达到
        flag = 0;

        while (head < tail) {

            for (k = 0; k <= 3; k++) {

                tx = que[head].x + next[k][0];
                ty = que[head].y + next[k][1];

                //越界判断
                if (tx < 1 || tx > n || ty < 1 || ty > m) {
                    continue;
                }
                //障碍物，是否在路径中判断
                if (a[tx][ty] == 0 && book[tx][ty] == 0) {
                    //已经走过
                    book[tx][ty] = 1;
                    //插入新的点到队列中
                    que[tail] = new Note();
                    que[tail].x = tx;
                    que[tail].y = ty;
                    que[tail].f = head;
                    que[tail].s = que[head].s + 1;
                    tail++;
                }
                //如果到目标点了，停止扩展，任务结束，退出循环
                if (tx == p && ty == q) {
                    flag = 1;
                    break;
                }
            }
            if (flag == 1) {
                break;
            }
            //注意这地方千万不要忘记，当一个点扩展结束后，head++才能对后面的点进行扩展
            head++;
        }

    }

    public void print() {

        System.out.println(que[tail - 1].s);
    }

    public static void main(String[] args) {
        BreadthFirstSearch breadthFirstSearch = new BreadthFirstSearch();
        breadthFirstSearch.init();
        breadthFirstSearch.find();
        breadthFirstSearch.print();

    }

    public static class Note {
        //横坐标
        int x;
        //纵坐标
        int y;
        //父亲在队列中的编号
        int f;
        //步数
        int s;
    }
}
