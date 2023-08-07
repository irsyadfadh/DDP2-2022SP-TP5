import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ShyourBox {
    private List<Product> products = new ArrayList<>();
    private List<Cart> carts = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();

    public static void main(String[] args) {
        ShyourBox shyourboxApp = new ShyourBox();
        System.out.println("Welcome to ShyourBox! Yuk beli jangan shy shy!");

        // Subject to change: file address.
        String productAddress = "D:\\IRSYAD\\UI\\DDP 2\\TP 5\\input\\daftarProduk.txt";
        String customerAddress = "D:\\IRSYAD\\UI\\DDP 2\\TP 5\\input\\daftarCustomer.txt";

        shyourboxApp.addProduct(productAddress);
        shyourboxApp.addCustomer(customerAddress);

        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            System.out.println("Menu" +
                    "\n1. Beli Produk" +
                    "\n2. Cari Produk" +
                    "\n3. Print Struk" +
                    "\n0. Keluar");
            System.out.print("Pilih menu: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    shyourboxApp.buyProduct(scanner);
                    break;
                case 2:
                    System.out.print("Cari produk dengan nama: ");
                    scanner.nextLine();
                    String name = scanner.nextLine();
                    Product product = shyourboxApp.searchProduct(name);
                    if(product != null) {
                        if (product instanceof Fruit && (((Fruit) product).getIsLocal() == true)) {
                            System.out.println("Produk Ditemukan!");
                            System.out.println("[Buah Lokal]");
                            System.out.println("Nama Produk: " + product.name);
                            System.out.println("Harga: " + product.price);
                            System.out.println("Stok: " + product.stock);
                        }
                        else if (product instanceof Fruit && (((Fruit) product).getIsLocal() == false)) {
                            System.out.println("Produk Ditemukan!");
                            System.out.println("[Buah Impor]");
                            System.out.println("Nama Produk: " + product.name);
                            System.out.println("Harga: " + product.price);
                            System.out.println("Stok: " + product.stock);
                        }
                        else if (product instanceof Veggie && (((Veggie) product).getIsOrganic() == true)) {
                            System.out.println("Produk Ditemukan!");
                            System.out.println("[Sayur Organik]");
                            System.out.println("Nama Produk: " + product.name);
                            System.out.println("Harga: " + product.price);
                            System.out.println("Stok: " + product.stock);
                        }
                        else {
                            System.out.println("Produk Ditemukan!");
                            System.out.println("[Sayur Konvensional]");
                            System.out.println("Nama Produk: " + product.name);
                            System.out.println("Harga: " + product.price);
                            System.out.println("Stok: " + product.stock);
                        }
                    }
                    else {
                        System.out.println("Produk tidak ditemukan!");
                    }
                    break;
                case 3:
                    shyourboxApp.printReceipt();
                    break;
                case 0:
                    System.out.println("Yay!");
                    break;
                default:
                    System.out.println("Pilihan menu tidak valid.");
                    break;
            }
        } while (choice != 0);
        scanner.close();
    }
    /*Metode untuk memproses proses pembelian produk oleh pelanggan. */
    public void buyProduct(Scanner scanner) {
        scanner.nextLine();
        System.out.print("Masukkan nama customer: ");
        String customerName = scanner.nextLine();
        Customer customer = findCustomer(customerName);

        if (customer == null) {
            System.out.println("Mohon maaf, customer atas nama " + customerName + " tidak terdaftar!");
            return;
        }

        Cart cart = new Cart(customer);

        System.out.println("====MASUKKAN ITEM KE KERANJANG====");
        while (true) {
            System.out.print("Masukkan nama produk: ");
            String productName = scanner.nextLine();
            if (productName.equalsIgnoreCase("X")) {
                break;
            }

            Product product = searchProduct(productName);

            if (product == null) {
                System.out.println("Mohon maaf, produk tidak tersedia!");
                continue;
            }

            System.out.print("Masukkan Jumlah (kg): ");
            int quantity = scanner.nextInt();
            scanner.nextLine(); // consume newline character

            if (quantity > product.stock) {
                System.out.println("Mohon maaf, stok tidak mencukupi!");
                continue;
            }

            OrderItem orderItem = new OrderItem(product, quantity);
            cart.addOrderItem(orderItem);
            System.out.println("Produk Berhasil ditambahkan!");
        }

        carts.add(cart);
        System.out.println("Terima kasih sudah berbelanja, " + customerName + "!");
    }
    /*Method untuk mencari produk berdasarkan nama. */
    public Product searchProduct(String names) {
        for (Product product : products) {
            if (product.name.equalsIgnoreCase(names) == true) {
                return product;
            }
        }
        return null;
    }
    /*Method untuk membaca produk dari file dan menambahkannya ke dalam daftar produk. */
    public void addProduct(String fileAddress) {
        try {
            Scanner scanner = new Scanner(new File(fileAddress));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(", ");
                if (parts.length == 5) {
                    String type = parts[0].trim();
                    String productName = parts[1].trim();
                    int price = Integer.parseInt(parts[2].trim());
                    int stock = Integer.parseInt(parts[3].trim());
                    boolean isLocal = parts[4].trim().equalsIgnoreCase("Lokal");

                    if (type.equalsIgnoreCase("Fruit")) {
                        Fruit fruit = new Fruit(productName, price, stock, isLocal);
                        products.add(fruit);
                    } else if (type.equalsIgnoreCase("Veggie")) {
                        boolean isOrganic = parts[4].trim().equalsIgnoreCase("Organik");
                        Veggie veggie = new Veggie(productName, price, stock, isOrganic);
                        products.add(veggie);
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /*Method untuk membaca customer dari file dan menambahkannya ke dalam daftar pelanggan. */
    public void addCustomer(String fileAddress) {
        try {
            Scanner scanner = new Scanner(new File(fileAddress));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(", ");
                if (parts.length == 2) {
                    String customerName = parts[0].trim();
                    boolean isPremium = parts[1].trim().equalsIgnoreCase("Premium");
                    Customer customer = new Customer(customerName, isPremium);
                    customers.add(customer);
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    /*Metode untuk mencari customer berdasarkan nama. */
    public Customer findCustomer(String name) {
        for (Customer customer : customers) {
            if (customer.getName().equalsIgnoreCase(name)) {
                return customer;
            }
        }
        return null;
    }
    /*Metode untuk mencetak struk belanja ke file "Struk.txt" berdasarkan keranjang yang disimpan. */
    public void printReceipt() {
        try {
            FileWriter writer = new FileWriter("Struk.txt");
            writer.write("Berikut adalah rekap perbelanjaan hari ini:\n\n");
            for (Cart cart : carts) {
                writer.write("=============================\n");
                writer.write("Nama Customer: " + cart.getCustomer().getName() + "\n");
                writer.write("Daftar Belanja:\n");
                for (OrderItem orderItem : cart.getOrderList()) {
                    writer.write(orderItem.getProduct().name + "  " + orderItem.getQuantity() + "kg  " + orderItem.getFinalPrice() + "\n");
                }
                writer.write("\nTotal Perbelanjaan: " + cart.getTotalPrice() + "\n");
            }
            writer.write("=============================\n");
            writer.close();
            System.out.println("Struk belanja telah dicetak dalam file Struk.txt");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
