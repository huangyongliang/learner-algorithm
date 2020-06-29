package com.hyl.algorithm.other;

import com.hyl.algorithm.search.base.SearchIntf;
import com.hyl.algorithm.search.base.SearchIntfFactory;

/**
 * Prim算法解决最小生成树
 * <p>
 *
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-06-29 07:55
 */
public class PrimMinTree implements SearchIntf {

    private ArrayGraph graph;

    private int[] dis;
    private int n;
    private int start;
    private int length;
    private int[] books;

    @Override
    public void init() {
        n = 6;
        graph = new ArrayGraph(n);
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
        books = new int[n + 1];
        start = 1;
        length = 0;
    }

    @Override
    public void find() {
        dis[start] = 0;
        books[start] = 1;
        for (int i = 1; i <= n; i++) {
            dis[i] = graph.getPath(start, i);
        }
        int min = -1, minPath = 999;

        for (int i = 1; i <= n; i++) {
            if (dis[i] < minPath) {
                minPath = dis[i];
                min = i;
            }
        }
        books[min] = 1;
        length += dis[min];
        adjustDis(min);
    }

    private void adjustDis(int index) {

        for (int i = 1; i <= n; i++) {
            if (books[i] == 1) {
                continue;
            }
            if (dis[i] > graph.getPath(index, i)) {
                dis[i] = graph.getPath(index, i);
            }
        }

        int min = -1,minPath = 999;

        for (int i = 1; i <= n; i++) {
            if (books[i] == 1){
                continue;
            }
            if (dis[i]<minPath){
                minPath = dis[i];
                min = i;
            }
        }
        if (min == -1){
            return;
        }

        books[min] = 1;
        length +=dis[min];
        adjustDis(min);
    }

    @Override
    public void print() {
        graph.print();
        System.out.println("最小路径："+length);
    }

    public static void main(String[] args) {
        SearchIntfFactory.produce(PrimMinTree.class);
    }

}
