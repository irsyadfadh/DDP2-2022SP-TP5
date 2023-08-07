/*subkelas dari Product yang merepresentasikan produk sayuran. */
public class Veggie extends Product {

    private boolean isOrganic;

    public Veggie(String name, int price, int stock, boolean isOrganic) {
        super(name, price, stock);
        this.isOrganic = isOrganic;
    }

    public boolean getIsOrganic() {
        return isOrganic;
    }

    public String getName() {
        return this.name;
    }

    public int getPrice() {
        return this.price;
    }

    public int getStock() {
        return this.stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }
    /*Menghitung diskon berdasarkan kuantitas pembelian sayuran. */
    //diskon berbeda untuk sayuran organik dan konvensional
    @Override
    public int checkDiscount(int quantity) {
        if (isOrganic) {
            if (quantity >= 5) {
                return 20;
            } else if (quantity >= 3) {
                return 10;
            }
        } else {
            if (quantity >= 5) {
                return 25;
            } else if (quantity >= 3) {
                return 20;
            }
        }
        return 0;
    }
}
