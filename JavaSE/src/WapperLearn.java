public class WapperLearn {

    //装箱 拆箱
    int i = 500;
    Integer integer = new Integer(i);
    int ii = integer.intValue();

    Integer i1 = 500;
    int i11 = i1;   // 自动拆装箱

    Float f = new Float("4.65");
    float ff = f.floatValue();

    int is = Integer.parseInt("123"); // 字符串转基本数据类型

    String istr = String.valueOf(i); // 基本数据类型转字符串



}
