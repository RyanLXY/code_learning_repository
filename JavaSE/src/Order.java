public class Order {
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
        boolean flag = false
        if (obj instanceof Order) {
            Order order = (Order) obj;
            if(this.orderId == order.orderId && this.orderName.equals(order.orderName)){
                flag = true;
            }
        }
        return flag

    }
}
