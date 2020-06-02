package com.hyl.algorithm.example;

import org.springframework.stereotype.Component;

import com.hyl.algorithm.core.intf.Strategy;

/**
 * 扑克游戏-小猫钓鱼
 * <p>
 *
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-06-03 02:35
 */
@Component
public class CardGameStrategy implements Strategy {

    private QueueTest aBoy;
    private QueueTest bBoy;
    private StackTest desk;

    private String winName;

    @Override
    public void init(int size) {

        aBoy = new QueueTest(100);
        bBoy = new QueueTest(100);
        desk = new StackTest(10);

        int[] tempA = new int[] {2, 4, 1, 2, 5, 6};
        int[] tempB = new int[] {3, 1, 3, 5, 6, 4};
        for (int i : tempA) {
            aBoy.add(i);
        }
        for (int i : tempB) {
            bBoy.add(i);
        }

        System.out.println("aBoy cards :");
        for (int i : tempA) {
            System.out.print(i + "\t");
        }
        System.out.println();
        System.out.println("bBoy cards :");
        for (int i : tempB) {
            System.out.print(i + "\t");
        }
        System.out.println();
    }

    @Override
    public void strategy() {
        this.book();
    }

    /**
     * 标记法
     */
    private void book() {
        int[] books = new int[10];

        while (true) {
            int aCard = aBoy.pull();
            books[aCard]++;
            if (books[aCard] == 1) {
                desk.push(aCard);
                if (aBoy.getSize() == 0) {
                    winName = "bBoy";
                    return;
                }
            } else if (books[aCard] == 2) {
                books[aCard]--;
                aBoy.add(aCard);
                int popA;
                do {
                    popA = desk.pop();
                    books[popA]--;
                    aBoy.add(popA);
                } while (popA != aCard);
            }

            int bCard = bBoy.pull();
            books[bCard]++;
            if (books[bCard] == 1) {
                desk.push(bCard);
                if (bBoy.getSize() == 0) {
                    winName = "aBoy";
                    return;
                }
            } else if (books[bCard] == 2) {
                books[bCard]--;
                bBoy.add(bCard);
                int popB;
                do {
                    popB = desk.pop();
                    books[popB]--;
                    bBoy.add(popB);
                } while (popB != bCard);
                books[bCard] = 0;
            }
        }

    }

    /**
     * 枚举法
     */
    private void enumeration() {
        while (true) {

            int aCard = aBoy.pull();
            int timesA = desk.contain(aCard);
            if (timesA == -1) {
                desk.push(aCard);
                if (aBoy.getSize() == 0) {
                    winName = "bBoy";
                    return;
                }
            } else {
                aBoy.add(aCard);
                for (int i = 0; i < timesA; i++) {
                    aBoy.add(desk.pop());
                }
            }

            int bCard = bBoy.pull();
            int timesB = desk.contain(bCard);
            if (timesB == -1) {
                desk.push(bCard);
                if (bBoy.getSize() == 0) {
                    winName = "aBoy";
                    return;
                }
            } else {
                bBoy.add(bCard);
                for (int i = 0; i < timesB; i++) {
                    bBoy.add(desk.pop());
                }
            }
        }
    }

    @Override
    public void print() {

        System.out.println("winName:" + winName);
        System.out.println("aBoy cards :");
        while (aBoy.getSize() != 0) {
            System.out.print(aBoy.pull() + "\t");
        }
        System.out.println();
        System.out.println("bBoy cards :");
        while (bBoy.getSize() != 0) {
            System.out.print(bBoy.pull() + "\t");
        }
        System.out.println();
        System.out.println("desk cards :");
        while (desk.getSize() != 0) {
            System.out.print(desk.pop() + "\t");
        }
        System.out.println();
    }

    public static class QueueTest {

        private int[] data;
        private int top;
        private int tail;
        private int size;

        public QueueTest(int size) {

            data = new int[size];
            top = 0;
            tail = 0;
            this.size = 0;
        }

        public void add(int i) {
            if (size == data.length) {
                return;
            }
            data[top++] = i;
            size++;
        }

        public int pull() {
            if (size == 0) {
                return -1;
            }

            size--;
            int temp = data[tail++];
            data[tail - 1] = 0;
            return temp;
        }

        public int getSize() {
            return size;
        }
    }

    public static class StackTest {
        private int[] data;

        private int top;
        private int size;

        public StackTest(int length) {
            data = new int[length];
            top = 0;
        }

        public void push(int c) {
            if (size == data.length) {
                return;
            }
            data[top++] = c;
            size++;
        }

        public int pop() {
            if (size == 0) {
                return 1;
            }
            size--;
            int temp = data[--top];
            data[top] = 0;
            return temp;
        }

        public int getSize() {
            return size;
        }

        public int contain(int i) {
            int times = 0;

            for (int index = top - 1; index >= 0; index--) {
                times++;
                if (data[index] == i) {
                    return times;
                }
            }
            return -1;
        }

    }

}
