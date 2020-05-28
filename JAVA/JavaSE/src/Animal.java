public class Animal {

    /*
    new Animal 执行时：
    1、类的属性默认初始化和显示初始化
    2、执行代码块代码
        2.1、多个同级别代码块 从上到下执行
        2.2、先静态后非静态
    3、执行构造器代码
     */
    String name;
    static int id;
    public Animal(){
        this.name = "cat";
        System.out.println("构造方法");
    }

    // 非静态代码块 每次new对象都执行
    {
        System.out.println("非静态代码块1");
    }
    {
        System.out.println("非静态代码块2");
    }
    {
        System.out.println("非静态代码块3");
    }

    // 静态代码块中只能放静态属性和方法 只被执行一次
    static{
        //name = ""; X
        id = 1;
        showId();
        System.out.println("静态代码块");
    }

    public static void showId(){
        System.out.println(id);
    }
    public void test(){
        System.out.println("Animal's test");
    }

}
