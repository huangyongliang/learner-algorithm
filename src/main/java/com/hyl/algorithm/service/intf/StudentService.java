package com.hyl.algorithm.service.intf;

import com.hyl.algorithm.entity.Student;

/**
 * 学生服务
 * <p>
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-05-31 08:48
 */
public interface StudentService {

    /**
     * 生产学生对象数组
     * <p>
     * @param size 数量
     * @return 学生数组
     */
    Student[] product(int size);

    /**
     * 打印学生对象数组
     * <p>
     * @param students 学生数组
     */
    void print(Student[] students);
}
