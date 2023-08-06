public class Fruit extends Product {
    private boolean isLocal;

    public Fruit(String name, int price, int stock, boolean isLocal) {
        super(name, price, stock);
        this.isLocal = isLocal;
    }

    public boolean getIsLocal() {
        return this.isLocal;
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

    @Override
    public int checkDiscount(int quantity) {
        if (isLocal) {
            if (quantity >= 5) {
                return 30;
            } else if (quantity >= 3) {
                return 20;
            }
        } else {
            if (quantity >= 5) {
                return 20;
            } else if (quantity >= 3) {
                return 15;
            }
        }
        return 0;
    }
}
