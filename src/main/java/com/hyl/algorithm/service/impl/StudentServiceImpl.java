package com.hyl.algorithm.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.hyl.algorithm.entity.Student;
import com.hyl.algorithm.factory.StudentFactory;
import com.hyl.algorithm.service.intf.StudentService;

/**
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-05-31 08:50
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Resource
    private StudentFactory studentFactory;

    @Override
    public Student[] product(int size) {
        return studentFactory.product(size);
    }

    @Override
    public void print(Student[] students) {
        System.out.println("总数：" + students.length);

        for (Student student : students) {
            System.out.print(student.getName() + ":" + student.getScore() + "\t");
        }
        System.out.println();
    }
}
