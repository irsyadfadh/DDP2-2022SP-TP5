import java.util.ArrayList;

public class Cart {
    private Customer customer;
    private ArrayList<OrderItem> orderList;

    public Cart(Customer customer) {
        this.customer = customer;
        this.orderList = new ArrayList<>();
    }

    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderList) {
            totalPrice += orderItem.getFinalPrice();
        }
        if (customer.isPremium() && totalPrice >= 300000) {
            totalPrice -= totalPrice * 0.1;
        }
        return totalPrice;
    }

    public void addOrderItem(OrderItem orderItem) {
        orderList.add(orderItem);
    }
}
