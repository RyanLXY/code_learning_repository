public class PersonTest {
    public static void main(String[] args){
        Person p1 = new Person();
        String[] ss1 = new String[] {"John","23"};
        p1.printInfo1(ss1);
        String[] ss2 = new String[] {"Beijing","16","Zhang"};
        p1.printInfo1(ss2);
        p1.printInfo1(null);

        p1.printInfo2("Li", "19", "male");
        String[] ss3 =  new String[] {"Beijing","16","Zhang"};
        p1.printInfo2(ss3);
        p1.printInfo2();

        Student s1 = new Student();
        System.out.println(s1 instanceof Person); // 判断s1是否为Person类的对象
    }
}
