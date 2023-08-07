/*Representasi item yang dibeli dalam suatu pesanan */
public class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
    /*return objek Product yang terkait dengan item ini. */
    public Product getProduct() {
        return product;
    }
    /*return kuantitas item */
    public int getQuantity() {
        return quantity;
    }
    /*Menghitung harga akhir item setelah memperhitungkan diskon berdasarkan produk dan kuantitas. */
    public int getFinalPrice() {
        int discountPercentage = product.checkDiscount(quantity);
        int itemTotalPrice = product.price * quantity;
        return itemTotalPrice - (itemTotalPrice * discountPercentage / 100);
    }
}
