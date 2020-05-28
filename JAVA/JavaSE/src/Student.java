public class Student extends Person {

    /*
    java 只支持单继承
    一个子类只能有一个父类
    但可多层继承
     */
    private String school;

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Override
    public void printInfo() {
        super.printInfo();
        System.out.println(this.school);
    }
    /*
    super 可以向上追溯所有的父类
     */


    Person person = new Student();  // 向上转型 upcasting 此时 person 没有Student中的变量（编译时）
                                    // 方法则调用Student中的方法 -- 动态绑定 需有重写的方法（运行时）
}
