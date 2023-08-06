import java.util.ArrayList;
import java.util.List;

public class Cart {
    private Customer customer;
    private List<OrderItem> orderList;

    public Cart(Customer customer) {
        this.customer = customer;
        this.orderList = new ArrayList<>();
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<OrderItem> getOrderList() {
        return orderList;
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
