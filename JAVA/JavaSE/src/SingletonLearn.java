public class SingletonLearn {
    /*
     单例设计模式 singleton
     只有一个实例化对象
     在整个系统运行过程中类只被实例化一次
     假设构造中需要执行多行代码 占用很大资源 耗时很长
    */

    // 饿汉式 私有构造 无法使用new创建新对象
    private SingletonLearn(){}

    //私有类变量
    private static SingletonLearn singletonLearn = new SingletonLearn();

    //共有的方法
    public static SingletonLearn getInstance(){
        return singletonLearn;
    }

}
