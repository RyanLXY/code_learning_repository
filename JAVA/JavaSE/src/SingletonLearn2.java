public class SingletonLearn2 {

    // 懒汉式 最开始对象是null 直到第一次调用后 new一个对象 之后所以调用都是同一个对象

    //私有构造
    private SingletonLearn2(){}

    //私有类变量
    private static SingletonLearn2 singletonLearn2 = null;

    //共有的方法
    public static SingletonLearn2 getInstance(){
        if (singletonLearn2 == null){
            singletonLearn2 = new SingletonLearn2();
        }

        return  singletonLearn2;
    }

}
