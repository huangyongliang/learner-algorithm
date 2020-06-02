package com.hyl.algorithm.example;

import org.springframework.stereotype.Component;

import com.hyl.algorithm.core.intf.Strategy;

/**
 * 括号匹配
 * <p>
 *
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-06-02 17:14
 */
@Component
public class BracketStrategy implements Strategy {

    private String brackets;

    private boolean result;

    @Override
    public void init(int size) {
        brackets = "{([](){})}";
        result = false;
    }

    @Override
    public void strategy() {

        StackTest stackTest = new StackTest(brackets.length());

        char[] chars = brackets.toCharArray();

        for (char c : chars) {
            if (c == '[' || c == '(' || c == '{') {
                stackTest.push(c);
            } else if (c == ']') {
                if (stackTest.pop() != '[') {
                    result = false;
                    return;
                }
            } else if (c == ')') {
                if (stackTest.pop() != '(') {
                    result = false;
                    return;
                }
            } else if (c == '}') {
                if (stackTest.pop() != '{') {
                    result = false;
                    return;
                }
            }
        }

        if (stackTest.getSize()!=0){
            result = false;
            return;
        }

        result =true;

    }

    public static class StackTest {
        private char[] data;

        private int top;
        private int size;

        public StackTest(int length) {
            data = new char[length];
            top = 0;
        }

        public void push(char c) {
            if (size == data.length) {
                return;
            }
            data[top++] = c;
            size++;
        }

        public char pop() {
            if (size == 0) {
                return 1;
            }
            size--;
            return data[--top];
        }

        public int getSize(){
            return size;
        }

    }

    @Override
    public void print() {

        if (result) {
            System.out.println(brackets + ": 是匹配的！");
        } else {
            System.out.println(brackets + ": 是不匹配的！");
        }

    }
}
