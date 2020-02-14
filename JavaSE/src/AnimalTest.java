public class AnimalTest {
    public static void main(String[] args) {
        Animal animal = new Animal();
        new Animal();

        /*
        1
        静态代码块      只执行了一次
        非静态代码块1
        非静态代码块2
        非静态代码块3
        构造方法
        非静态代码块1
        非静态代码块2
        非静态代码块3
        构造方法
         */

        // 匿名内部类 构建了一个没有类名的Animal的子类
        // 没有构造器 如需初始化属性 需要使用代码块做初始化工作
        Animal animal1 = new Animal(){
            // 更改name 不动Animal代码
            {
                super.name = "dog";
            }
            @Override
            public void test() {
                System.out.println("0.0");
            }
        };

        System.out.println(animal1.name);
    }
}

