package com.hyl.algorithm.core.impl;

import org.springframework.stereotype.Component;

import com.hyl.algorithm.base.ZHStringBuilder;
import com.hyl.algorithm.core.intf.Strategy;

import lombok.Data;

/**
 * <h>冒泡排序</h>
 *
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-05-25 16:57
 */
@Component
public class BubbleStrategy implements Strategy {

    private Student[] students;

    @Override
    public void init(int size) {
        students = new Student[size];
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student();
            students[i].setName(ZHStringBuilder.getZHName());
            students[i].setScore((int) (Math.random() * size));
        }
        print();
    }

    @Override
    public void strategy() {
        Student temp;
        for (int i = 0; i < students.length; i++) {
            for (int j = 0; j < students.length - i - 1; j++) {
                if (students[j].getScore() > students[j + 1].getScore()) {
                    temp = students[j];
                    students[j] = students[j + 1];
                    students[j + 1] = temp;
                }
            }
        }
    }

    @Override
    public void print() {
        System.out.println("总数：" + students.length);

        for (Student student : students) {
            System.out.print(student.getName() + ":" + student.getScore() + "\t");
        }
        System.out.println();

    }

    @Data
    static class Student {
        private String name;
        private int score;
    }
}
