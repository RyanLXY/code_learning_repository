package FactoryLearn;

/**
 * 汽车生产工厂接口
 */
public interface BMWFactory {
    BMW productBMW();
}

/**
 * 具体生产车的类
 */
class BMW3Factory implements BMWFactory{

    @Override
    public BMW productBMW() {
        System.out.println("生产BMW3");
        System.out.println("改造重新命名为BMW3i");
        return new BMW3i();
    }
}

class BMW5Factory implements BMWFactory{

    @Override
    public BMW productBMW() {
        System.out.println("生产BMW5");
        return new BMW5();
    }
}

class BMW7Factory implements BMWFactory{

    @Override
    public BMW productBMW() {
        System.out.println("生产BMW7");
        return new BMW7();
    }
}
