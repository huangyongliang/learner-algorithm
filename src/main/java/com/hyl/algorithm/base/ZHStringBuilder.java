package com.hyl.algorithm.base;

/**
 * @author Hyl
 * @version V 0.1
 * @since 0.1 2020-05-25 21:44
 */
public abstract class ZHStringBuilder {

    private static final String[] firstName = {"赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "褚", "卫", "蒋", "沈",
        "韩", "杨", "朱", "秦", "尤", "许", "何", "吕", "施", "张"};

    private static final String[] secondName = {"人", "之", "初", "性", "本", "善", "性", "相", "近", "习", "相", "远", "苟", "不",
        "教", "性", "乃", "迁", "教", "之", "道", "贵", "以", "专"};

    public static String getZHName() {
        //随机生成姓氏
        int firstIndex = (int) (Math.random() * firstName.length);
        //随机生成名
        int secondIndex = (int) (Math.random() * secondName.length);
        return firstName[firstIndex] + secondName[secondIndex];
    }

}
