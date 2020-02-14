package FactoryLearn;

/**
 * 宝马车的产品接口
 */
public interface BMW {
    // 产品信息 车的发动方式 ...
    void showInfo();
}


/**
 * 具体的车的类
 */
class BMW3i implements BMW{
    @Override
    public void showInfo() {
        System.out.println("BMW3i");
    }
}

class BMW5 implements BMW{
    @Override
    public void showInfo() {
        System.out.println("BMW5");
    }
}

class BMW7 implements BMW{
    @Override
    public void showInfo() {
        System.out.println("BMW7");
    }
}