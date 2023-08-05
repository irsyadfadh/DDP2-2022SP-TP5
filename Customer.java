public class Customer {
    private String name;
    private boolean isPremium;

    public Customer(String name, boolean isPremium) {
        this.name = name;
        this.isPremium = isPremium;
    }

    public String getName() {
        return name;
    }

    public boolean isPremium() {
        return isPremium;
    }
}
