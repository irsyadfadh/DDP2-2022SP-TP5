import java.util.ArrayList;
import java.util.List;
/*Keranjang Belanja */
public class Cart {
    private Customer customer;
    private List<OrderItem> orderList;

    public Cart(Customer customer) {
        this.customer = customer;
        this.orderList = new ArrayList<>();
    }
    /*Getter objek customer */
    public Customer getCustomer() {
        return customer;
    }
    /*Return daftar OrderItem dalam keranjang. */
    public List<OrderItem> getOrderList() {
        return orderList;
    }
    /* Menghitung total harga belanja dalam keranjang dengan memperhitungkan potensi diskon premium.  */
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
    /*Menambahkan objek OrderItem ke dalam daftar orderList. */
    public void addOrderItem(OrderItem orderItem) {
        orderList.add(orderItem);
    }
}
