/*class abstract representasi product */
public abstract class Product {
    protected String name;
    protected int price;
    protected int stock;

    public Product(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }
    /*Method abstrak yang didefinisikan ulang oleh subkelas untuk menghitung diskon berdasarkan kuantitas pembelian. */
    public abstract int checkDiscount(int quantity);
}
