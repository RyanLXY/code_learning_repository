public class JavaBeanLearn {
    /*
    JavaBean java写成的可重用组件
    标准：
        类共有
        有一个无参数公共构造器
        有属性 一般私有 有对应的get set方法
     */

    private String name;
    private int sex;
    private int age;

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }
}
