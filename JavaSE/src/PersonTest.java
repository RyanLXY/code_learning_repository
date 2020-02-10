public class PersonTest {
    public static void main(String[] args){
        Person p1 = new Person();
        String[] s1 = new String[] {"John","23"};
        p1.printInfo(s1);
        String[] s2 = new String[] {"Beijing","16","Zhang"};
        p1.printInfo(s2);
        p1.printInfo(null);

        p1.printInfo2("Li", "19", "male");
        String[] s3 =  new String[] {"Beijing","16","Zhang"};
        p1.printInfo2(s3);
        p1.printInfo2();
    }
}
