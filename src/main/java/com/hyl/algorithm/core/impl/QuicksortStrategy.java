package com.hyl.algorithm.core.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.hyl.algorithm.core.intf.Strategy;
import com.hyl.algorithm.entity.Student;
import com.hyl.algorithm.service.intf.StudentService;

import lombok.extern.slf4j.Slf4j;

/**
 * 快速排序算法
 * <p>
 *
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-05-31 08:36
 */
@Component
@Slf4j
public class QuicksortStrategy implements Strategy {

    @Resource
    private StudentService studentService;

    private Student[] students;

    @Override
    public void init(int size) {
        students = studentService.product(size);
        print();
    }

    @Override
    public void strategy() {
        this.quicksort(0, students.length - 1);
    }

    private void quicksort(int left, int right) {

        if (left > right) {
            return;
        }

        int home;
        int indexLeft = left;
        int indexRight = right;

        Student first = students[left];

        while (indexLeft != indexRight) {
            //顺序很重要，要先从右往左找
            while (indexLeft < indexRight && students[indexRight].getScore() >= first.getScore()) {
                indexRight--;
            }

            //再从左往右找
            while (indexLeft < indexRight && students[indexLeft].getScore() <= first.getScore()) {
                indexLeft++;
            }

            //左右哨兵没有相遇，交换位置
            if (indexLeft < indexRight) {
                Student temp = students[indexLeft];
                students[indexLeft] = students[indexRight];
                students[indexRight] = temp;
                // {
                //     System.out.println("交换：" + indexLeft + "--->" + indexRight);
                //     print();
                // }
            }
        }
        //归位下标
        home = indexRight;
        //如果归位下标就是左侧第一位不用归位交换
        if (left != home) {
            students[left] = students[home];
            students[home] = first;
        }
        // {
        //     System.out.println("归位：" + left + "-->" + home);
        //     print();
        // }
        //左侧递归
        quicksort(left, home - 1);
        //右侧递归
        quicksort(home + 1, right);

    }

    @Override
    public void print() {
        studentService.print(students);
    }
}
