public class StringCreateLearn {

    String s1 = "abc";
    String s2 = "abc";
    /*
    常量池中添加"abc"对象 返回引用地址给s1
    通过equals方法判断常量池中已有"abc"对象 返回相同的引用
    省内存
     */
    boolean eq1 = (s1 == s2); // true 地址相同
    String s3 = new String("def");
    String s4 = new String("def");
    /*
    常量池中添加"def"对象 在堆中创建值为"def"的对象s3 返回指向堆中s3的引用
    在堆中创建值为"def"的对象s4 返回指向堆中s4的引用
     */
    boolean eq2 = (s3 == s4); // false 地址不相同

    String s5 = "x" + "y"; //经过jvm优化 直接添加"xy"到常量池

    String s6 = new String("1") + new String("1") +new String("2");
    /*
    通过StringBuilder实现， 在常量池中添加1 2 堆中创建"112"的对象
     */
    /*
    p1.name = "a"
    p2.name = "a"
    p1.name == p2.name true 字面量赋值 都引用字符串常量池
    p1.name == "a" true
     */

}

