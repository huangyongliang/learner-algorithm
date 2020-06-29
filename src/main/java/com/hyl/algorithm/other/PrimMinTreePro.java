package com.hyl.algorithm.other;

import com.hyl.algorithm.search.base.SearchIntf;
import com.hyl.algorithm.search.base.SearchIntfFactory;

/**
 * Prim算法解决最小生成树邻接表、堆优化
 * <p>
 *
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-06-30 01:50
 */
public class PrimMinTreePro implements SearchIntf {

    private LinkedGraph graph;

    private int[] dis, h, pos;
    private int size;
    private int length;
    private static final int MAX_PATH = 999;
    private int start;
    private int[] book;
    private int n, m;

    @Override
    public void init() {
        n = 6;
        m = 9;
        graph = new LinkedGraph(n, m);
        graph.addUndirected(1, 2, 1);
        graph.addUndirected(2, 3, 6);
        graph.addUndirected(1, 3, 2);
        graph.addUndirected(2, 4, 11);
        graph.addUndirected(3, 4, 9);
        graph.addUndirected(3, 5, 13);
        graph.addUndirected(4, 5, 7);
        graph.addUndirected(4, 6, 3);
        graph.addUndirected(5, 6, 4);

        dis = new int[n + 1];
        h = new int[n + 1];
        pos = new int[n + 1];
        book = new int[n + 1];
        start = 4;
        length = 0;
    }

    @Override
    public void find() {

        book[start] = 1;
        //初始化dis
        for (int i = 1; i <= n; i++) {
            if (book[i] == 0) {
                dis[i] = graph.getPath(start, i);
            }
        }

        // 初始化堆
        for (int i = 1; i <= n; i++) {
            h[i] = i;
            pos[i] = i;
        }
        size = n;
        // 向下调整初始化最小堆，最后一个内部结点开始
        for (int i = n / 2; i >= 1; i--) {
            shiftDown(i);
        }

        pop();

        while (size > 0) {
            int index = pop();
            book[index] = 1;
            length += dis[index];
            // 松弛
            for (int i = 1; i <= n; i++) {
                if (book[i] == 0) {
                    int path = graph.getPath(index, i);
                    if (dis[i] > path) {
                        dis[i] = path;
                        shiftUp(pos[i]);
                    }
                }
            }
        }

    }

    private void shiftUp(int i) {
        // 不存在父结点
        if (i == 1) {
            return;
        }

        if (dis[h[i / 2]] > dis[h[i]]) {
            swap(i, i / 2);
            shiftUp(i / 2);
        }
    }

    private void shiftDown(int i) {
        // 不存在子结点
        if (2 * i > size) {
            return;
        }
        int k;
        // 存在右儿子同时右儿子比左儿子小
        if (2 * i + 1 <= size && dis[h[2 * i + 1]] < dis[h[2 * i]]) {
            k = 2 * i + 1;
        } else {
            k = 2 * i;
        }

        if (dis[h[k]] < dis[h[i]]) {
            swap(k, i);
            shiftDown(k);
        }

    }

    private void swap(int x, int y) {
        int temp = h[x];
        h[x] = h[y];
        h[y] = temp;

        temp = pos[h[x]];
        pos[h[x]] = pos[h[y]];
        pos[h[y]] = temp;
    }

    private int pop() {
        int t = h[1];
        h[1] = h[size];
        size--;
        shiftDown(1);
        return t;
    }

    @Override
    public void print() {
        graph.printLines();
        System.out.println("最短路径：" + length);
    }

    public static void main(String[] args) {
        SearchIntfFactory.produce(PrimMinTreePro.class);
    }
}
