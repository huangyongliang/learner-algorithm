package com.hyl.algorithm.core.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hyl.algorithm.core.intf.Strategy;
import com.hyl.algorithm.entity.Student;
import com.hyl.algorithm.service.intf.StudentService;

/**
 * <h>冒泡排序</h>
 * <li>时间复杂度：O(n*n)</li>
 *
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-05-25 16:57
 */
@Component
public class BubbleStrategy implements Strategy {

    @Autowired
    private StudentService studentService;

    private Student[] students;

    @Override
    public void init(int size) {
        students = studentService.product(size);
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
        studentService.print(students);
    }
}
