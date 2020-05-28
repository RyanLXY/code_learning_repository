public class EqualsLearn {

    public static void main(String[] args) {
        Person p1 = new Person();
        Person p2 = new Person();
        System.out.println(p1 == p2); // 判断p1与p2是否指向同一个对象

        String s1 = new String("abc");
        String s2 = new String("abc");
        System.out.println(s1 == s2); // 判断s1与s2是否指向同一个对象
        // File Data String Wrapper 类 比较内容与类型
        System.out.println(s1.equals(s2)); // 判断s1与s2的类型及内容是否相同，而不考虑是否引用同一个对象

    }
}
