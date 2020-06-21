package com.hyl.algorithm.search;

import java.util.ArrayList;
import java.util.List;

/**
 * 管道游戏
 * <p>
 *
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-06-22 02:19
 */
public class PipelineGame {

    int[][] map;
    Pipe[][] pipes;

    int[][] book;

    int mx, my;

    int startX, startY;
    Direction startD;
    int endX, endY;
    Direction endD;

    public PipelineGame() {
        //出入口设置
        startX = 0;
        startY = 0;
        startD = Direction.LEFT;
        endX = 4;
        endY = 3;
        endD = Direction.RIGHT;
        //地图初始化
        mx = 5;
        my = 4;
        map = new int[5][4];
        map[0] = new int[] {0, 1, 0, 1};
        map[1] = new int[] {1, 0, 1, 2};
        map[2] = new int[] {1, 1, 0, 1};
        map[3] = new int[] {0, 1, 1, 0};
        map[4] = new int[] {1, 0, 0, 1};
        book = new int[mx][my];

        pipes = new Pipe[map.length][];

        for (int i = 0; i < map.length; i++) {
            pipes[i] = new Pipe[map[i].length];
            for (int j = 0; j < map[i].length; j++) {
                pipes[i][j] = new Pipe();
                pipes[i][j].x = i;
                pipes[i][j].y = j;
                switch (map[i][j]) {
                    case 0:
                        pipes[i][j].type = PipeType.STRAIGHT;
                        break;
                    case 1:
                        pipes[i][j].type = PipeType.CURVE;
                        break;
                    default:
                        pipes[i][j].type = PipeType.TREE;
                        break;
                }
            }
        }
    }

    public void find() {

        // findDFS(startX, startY, startD, null);
        findBFS();
    }

    /**
     * 广度优先算法
     */
    private void findBFS() {

        Pipe[] que = new Pipe[mx * my + 1];

        int head = 0;
        int tail = 0;

        int tx = startX;
        int ty = startY;

        // 入口点入队列
        que[tail] = pipes[tx][ty];
        que[tail].entrance = startD;
        book[tx][ty] = 1;
        tail++;

        while (head < tail) {
            Pipe pipe = que[head];

            if (this.isEnd(pipe)){
                break;
            }
            List<Move> pipeMove = this.getPipeMove(pipe);

            for (Move move : pipeMove) {
                tx = pipe.x + move.x;
                ty = pipe.y + move.y;

                // 越界判断
                if (tx < 0 || ty < 0 || tx >= mx || ty >= my) {
                    continue;
                }
                // 走过
                if (book[tx][ty] == 1) {
                    continue;
                }

                if (pipes[tx][ty].type == PipeType.TREE) {
                    continue;
                }
                book[tx][ty] = 1;
                que[tail] = pipes[tx][ty];
                que[tail].parent = pipe;
                switch (move){
                    case TO_UP:
                        que[tail].entrance = Direction.DOWN;
                        break;
                    case TO_DOWN:
                        que[tail].entrance = Direction.UP;
                        break;
                    case TO_LEFT:
                        que[tail].entrance = Direction.RIGHT;
                        break;
                    case TO_RIGHT:
                        que[tail].entrance = Direction.LEFT;
                        break;
                }


                tail++;

            }

            head++;
        }

    }

    /**
     * 判断当前节点可以走的方向
     * <p>
     * @param pipe 节点管道
     * @return 方向List
     */
    private List<Move> getPipeMove(Pipe pipe) {

        List<Move> moveList = new ArrayList<>();

        switch (pipe.entrance) {
            case DOWN:
                if (pipe.type == PipeType.STRAIGHT) {
                    moveList.add(Move.TO_UP);
                } else if (pipe.type == PipeType.CURVE) {
                    moveList.add(Move.TO_LEFT);
                    moveList.add(Move.TO_RIGHT);
                }
                break;
            case UP:
                if (pipe.type == PipeType.STRAIGHT) {
                    moveList.add(Move.TO_DOWN);
                } else if (pipe.type == PipeType.CURVE) {
                    moveList.add(Move.TO_LEFT);
                    moveList.add(Move.TO_RIGHT);
                }
                break;
            case RIGHT:
                if (pipe.type == PipeType.STRAIGHT) {
                    moveList.add(Move.TO_LEFT);
                } else if (pipe.type == PipeType.CURVE) {
                    moveList.add(Move.TO_DOWN);
                    moveList.add(Move.TO_UP);
                }
                break;
            case LEFT:
                if (pipe.type == PipeType.STRAIGHT) {
                    moveList.add(Move.TO_RIGHT);
                } else if (pipe.type == PipeType.CURVE) {
                    moveList.add(Move.TO_DOWN);
                    moveList.add(Move.TO_UP);
                }
                break;
        }
        return moveList;
    }

    /**
     * 判断是否是终点
     * <p>
     * @param pipe 管道节点
     * @return true是，false不是
     */
    private boolean isEnd(Pipe pipe){

        boolean result = false;

        if (pipe.x == endX && pipe.y == endY) {
            switch (pipe.entrance) {
                case LEFT:
                    if (pipe.type == PipeType.CURVE && (endD == Direction.DOWN || endD == Direction.UP)) {
                        printPath(pipe);
                        result = true;
                    } else if (pipe.type == PipeType.STRAIGHT && endD == Direction.RIGHT) {
                        printPath(pipe);
                        result = true;
                    }
                    break;
                case RIGHT:
                    if (pipe.type == PipeType.CURVE && (endD == Direction.DOWN || endD == Direction.UP)) {
                        printPath(pipe);
                        result = true;
                    } else if (pipe.type == PipeType.STRAIGHT && endD == Direction.LEFT) {
                        printPath(pipe);
                        result = true;
                    }
                    break;
                case UP:
                    if (pipe.type == PipeType.CURVE && (endD == Direction.RIGHT || endD == Direction.LEFT)) {
                        printPath(pipe);
                        result = true;
                    } else if (pipe.type == PipeType.STRAIGHT && endD == Direction.DOWN) {
                        printPath(pipe);
                        result = true;
                    }
                    break;
                case DOWN:
                    if (pipe.type == PipeType.CURVE && (endD == Direction.RIGHT || endD == Direction.LEFT)) {
                        printPath(pipe);
                        result = true;
                    } else if (pipe.type == PipeType.STRAIGHT && endD == Direction.UP) {
                        printPath(pipe);
                        result = true;
                    }
                    break;
            }
        }
        return result ;
    }

    /**
     * 深度优先算法
     *
     * @param x 横坐标
     * @param y 纵坐标
     * @param entrance 入口方向
     * @param parent 上次管道节点
     */
    private void findDFS(int x, int y, Direction entrance, Pipe parent) {

        // 越界判断
        if (x < 0 || y < 0 || x >= mx || y >= my) {
            return;
        }
        // 走过
        if (book[x][y] == 1) {
            return;
        }

        Pipe pipe = pipes[x][y];
        pipe.parent = parent;

        // 是树就返回
        if (pipe.type == PipeType.TREE) {
            return;
        }

        // 终点判断
        if (x == endX && y == endY) {
            switch (entrance) {
                case LEFT:
                    if (pipe.type == PipeType.CURVE && (endD == Direction.DOWN || endD == Direction.UP)) {
                        printPath(pipe);
                    } else if (pipe.type == PipeType.STRAIGHT && endD == Direction.RIGHT) {
                        printPath(pipe);
                    }
                    break;
                case RIGHT:
                    if (pipe.type == PipeType.CURVE && (endD == Direction.DOWN || endD == Direction.UP)) {
                        printPath(pipe);
                    } else if (pipe.type == PipeType.STRAIGHT && endD == Direction.LEFT) {
                        printPath(pipe);
                    }
                    break;
                case UP:
                    if (pipe.type == PipeType.CURVE && (endD == Direction.RIGHT || endD == Direction.LEFT)) {
                        printPath(pipe);
                    } else if (pipe.type == PipeType.STRAIGHT && endD == Direction.DOWN) {
                        printPath(pipe);
                    }
                    break;
                case DOWN:
                    if (pipe.type == PipeType.CURVE && (endD == Direction.RIGHT || endD == Direction.LEFT)) {
                        printPath(pipe);
                    } else if (pipe.type == PipeType.STRAIGHT && endD == Direction.UP) {
                        printPath(pipe);
                    }
                    break;
            }
            return;
        }
        // 下一步
        // 标记
        book[x][y] = 1;
        switch (entrance) {
            case DOWN:
                if (pipe.type == PipeType.STRAIGHT) {
                    findDFS(x + Move.TO_UP.x, y + Move.TO_UP.y, Direction.DOWN, pipe);
                } else if (pipe.type == PipeType.CURVE) {
                    findDFS(x + Move.TO_RIGHT.x, y + Move.TO_RIGHT.y, Direction.LEFT, pipe);
                    findDFS(x + Move.TO_LEFT.x, y + Move.TO_LEFT.y, Direction.RIGHT, pipe);
                }
                break;
            case UP:
                if (pipe.type == PipeType.STRAIGHT) {
                    findDFS(x + Move.TO_DOWN.x, y + Move.TO_DOWN.y, Direction.UP, pipe);
                } else if (pipe.type == PipeType.CURVE) {
                    findDFS(x + Move.TO_RIGHT.x, y + Move.TO_RIGHT.y, Direction.LEFT, pipe);
                    findDFS(x + Move.TO_LEFT.x, y + Move.TO_LEFT.y, Direction.RIGHT, pipe);
                }
                break;
            case RIGHT:
                if (pipe.type == PipeType.STRAIGHT) {
                    findDFS(x + Move.TO_LEFT.x, y + Move.TO_LEFT.y, Direction.RIGHT, pipe);
                } else if (pipe.type == PipeType.CURVE) {
                    findDFS(x + Move.TO_UP.x, y + Move.TO_UP.y, Direction.DOWN, pipe);
                    findDFS(x + Move.TO_DOWN.x, y + Move.TO_DOWN.y, Direction.UP, pipe);
                }
                break;
            case LEFT:
                if (pipe.type == PipeType.STRAIGHT) {
                    findDFS(x + Move.TO_RIGHT.x, y + Move.TO_RIGHT.y, Direction.LEFT, pipe);
                } else if (pipe.type == PipeType.CURVE) {
                    findDFS(x + Move.TO_UP.x, y + Move.TO_UP.y, Direction.DOWN, pipe);
                    findDFS(x + Move.TO_DOWN.x, y + Move.TO_DOWN.y, Direction.UP, pipe);
                }
                break;
        }
        book[x][y] = 0;

    }


    private void printPath(Pipe pipe) {
        if (pipe.parent == null) {
            System.out.print(pipe.print());
            return;
        }
        printPath(pipe.parent);
        System.out.print("->" + pipe.print());
    }

    public void print() {

        System.out.println("\n  \t0\t1\t2\t3");
        for (int i = 0; i < pipes.length; i++) {
            System.out.print("" + i + ":\t");
            for (Pipe value : pipes[i]) {
                System.out.print(value.type.code + "\t");
            }
            System.out.println();
        }

    }

    public static void main(String[] args) {
        PipelineGame pipelineGame = new PipelineGame();
        pipelineGame.find();
        pipelineGame.print();

    }

    public static class Pipe {
        // 横坐标
        int x;
        // 纵坐标
        int y;
        // 管道类型
        PipeType type;
        // 父管道
        Pipe parent;
        // 入口
        Direction entrance;

        @Override
        public String toString() {
            return type.toString();
        }

        public String print() {
            return "(" + x + "," + y + ")";
        }
    }

    public enum PipeType {
        /** 直管 **/
        STRAIGHT("0"),
        /** 弯管 **/
        CURVE("1"),
        /** 树 **/
        TREE("2");

        PipeType(String code) {
            this.code = code;
        }

        private final String code;

    }

    public enum Direction {
        UP,
        DOWN,
        RIGHT,
        LEFT
    }

    public enum Move {
        TO_UP(-1, 0),
        TO_DOWN(1, 0),
        TO_RIGHT(0, 1),
        TO_LEFT(0, -1);

        Move(int x, int y) {
            this.x = x;
            this.y = y;
        }

        private final int x;
        private final int y;

    }
}
