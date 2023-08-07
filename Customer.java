/*Class informasi customer atau pelanggan */
public class Customer {
    private String name;
    private boolean isPremium;

    public Customer(String name, boolean isPremium) {
        this.name = name;
        this.isPremium = isPremium;
    }
    /*Return nama customer */
    public String getName() {
        return name;
    }
    /*return status premium customer */
    public boolean isPremium() {
        return isPremium;
    }
}
