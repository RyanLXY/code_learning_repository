public abstract class Order {
    // 抽象类 不能被实例化 抽象类可以继承抽象类
    int orderId;
    String orderName;

    public Order( int orderId, String orderName){
        this.orderId = orderId;
        this.orderName = orderName;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    @Override
    public boolean equals(Object obj) {
        boolean flag = false;
        if (obj instanceof Order) {
            Order order = (Order) obj;
            if(this.orderId == order.orderId && this.orderName.equals(order.orderName)){
                flag = true;
            }
        }
        return flag;

    }

    //抽象方法
    public abstract void orderMethod();
}
