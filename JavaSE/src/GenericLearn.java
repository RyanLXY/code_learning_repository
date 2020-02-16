import java.util.ArrayList;
import java.util.List;

public class GenericLearn{

    public static void main(String[] args) {
        AAA<String> aaa1= new AAA<>();
        aaa1.setKey("xxxx");
        String s = aaa1.getKey();

        AAA<Integer> aaa2 = new AAA<>();
        aaa2.setKey(1);
        int i = aaa2.getKey();

        AAA<Object> aaa3 = new AAA<>(); // Object 类型
        aaa3.setKey(new Object());
        // 同类 不同数据类型的泛型 不能相互赋值

        BBB1<Object> bbb1 = new BBB1<>(); //需添加泛型类型
        BBB2 bbb2 = new BBB2(); //无需指定泛型

        CCC<Object> ccc= new CCC<>();
        ccc.test("xxx");
        ccc.test(123);
        Integer integer = ccc.test1(123);
        Boolean b = ccc.test1(true);

        DDD ddd = new DDD();
        List<String> l1 = new ArrayList<>();
        ddd.test(l1);
        List<Integer> l2 = new ArrayList<>();
        ddd.test(l2);
    }
}

// 泛型类
class AAA<T>{
    private T key;

    public void setKey(T key) {
        this.key = key;
    }

    public T getKey() {
        return key;
    }
}

//泛型接口
interface BBB<T>{
    T test(T t);
}

// 未传入泛型实参时，类与泛型类定义相同 需在声明类时声明泛型
class BBB1<T> implements BBB<T>{
    @Override
    public T test(T t) {
        return t;
    }
}


class BBB2 implements BBB<String>{
    @Override
    public String test(String s) {
        return s;
    }
}

class CCC <E>{
    private E e;
    //泛型方法
    public <T> void test(T t){
        T t1 = t;

    }
    public <T> T test1(T t){
        return t;
    }
    public <T> void test2(T... strs){
        for (T s :strs){
            System.out.println(s);
        }
    }
    //在静态方法中不能使用类定义的泛型 如要使用泛型 只能使用静态方法自己定义的泛型
    public static  <T> void test3(T t){

//        System.out.println(e);
        System.out.println(t);
    }
}

//通配符 ?
class DDD{
    public void test(List<?> list){ //test方法需要一个list集合 不确定list中的数据类型
    }
    public void test1(List<? extends C1> list){ // C1 D1｜  C1及其子类
    }
    public void test2(List<? super C1> list){ // A1 B1 C1｜ C1及其父类
    }
    public void test3(List<? extends IA> list){ // IAImp1｜ IA的实现类
    }
}

/*
 * <? extends Person> 泛型为Person及其子类
 * <? super Person> 泛型为Person及其父类
 * <? extends Comparable> 只允许泛型为实现了Comparable 接口实现类的引用调用
 */

class A1{}
class B1 extends A1{}
class C1 extends B1{}
class D1 extends C1{}

interface IA{}
class IAImp1 implements IA{}