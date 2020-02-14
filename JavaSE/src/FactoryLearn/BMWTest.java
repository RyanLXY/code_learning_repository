package FactoryLearn;

public class BMWTest {

    /**
     * 通过工厂吧new对象隔离 使用接口接受不同产品实现类 实例类名的更改不影响其他合作开发者的编程
     */
    public static void main(String[] args){

        BMW b3 = new BMW3Factory().productBMW();
        b3.showInfo();

        BMW b5 = new BMW5Factory().productBMW();
        b5.showInfo();

        BMW b7 = new BMW7Factory().productBMW();
        b7.showInfo();
    }
}
