package com.hyl.algorithm.factory;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.hyl.algorithm.base.ZHStringBuilder;
import com.hyl.algorithm.entity.Student;

/**
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-05-31 08:41
 */
@Component
public class StudentFactory {

    private String str = "孙善:72\t李乃:61\t卫以:73\t吴以:6\t冯教:69\t张不:77\t蒋贵:23\t赵相:80\t韩贵:96\t尤本:9\t尤性:56\t杨相:63\t陈初:23\t蒋不:55\t冯迁:56\t吴苟:37\t朱苟:14\t孙初:74\t赵本:3\t蒋善:58\t陈不:88\t孙苟:99\t秦性:78\t杨初:61\t陈本:17\t沈之:74\t秦乃:71\t卫教:93\t张本:33\t周远:43\t陈以:3\t朱教:63\t韩远:78\t许教:99\t赵善:25\t赵性:70\t蒋道:74\t卫教:88\t吕专:36\t郑相:0\t周迁:80\t冯贵:54\t沈道:90\t卫乃:13\t陈道:7\t冯不:58\t尤之:45\t吴性:15\t王乃:13\t赵初:33\t吴初:62\t施相:7\t尤人:7\t周本:35\t秦远:59\t蒋习:63\t尤不:14\t吴近:8\t卫教:65\t张性:97\t吕相:98\t杨苟:43\t卫贵:45\t李初:34\t朱性:99\t秦贵:81\t赵之:37\t许性:84\t卫人:50\t朱迁:71\t杨专:26\t沈以:81\t杨道:10\t何人:32\t施不:0\t吴性:26\t尤远:32\t李初:50\t许以:34\t赵迁:17\t冯教:82\t韩教:57\t李不:54\t张相:42\t王善:56\t李初:22\t李教:22\t沈习:30\t冯苟:99\t尤人:88\t尤贵:90\t钱相:45\t卫本:64\t沈教:71\t张以:69\t杨初:30\t施远:93\t褚专:30\t何相:9\t孙远:80";

    public Student[] product(int size) {

        if (!StringUtils.isEmpty(str)){
            return product(str);
        }

        Student[] students = new Student[size];
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student();
            students[i].setName(ZHStringBuilder.getZHName());
            students[i].setScore((int) (Math.random() * size));
        }

        return students;
    }

    public Student[] product(String str){

        String[] split = str.split("\t");

        Student[] students = new Student[split.length];
        for (int i = 0; i < students.length; i++) {
            students[i] = new Student();
            String[] studentStr = split[i].split(":");
            students[i].setName(studentStr[0]);
            students[i].setScore(Integer.parseInt(studentStr[1]));
        }

        return students;

    }

}
