import java.util.Scanner;
import java.io.Serializable;

public class Product implements Serializable {

    static Scanner scanner = new Scanner(System.in);
    protected int product_id;
    protected String product_name;
    protected double product_price;
    protected int product_total;

    // Constructor không tham số
    public Product() {
        this.product_id = 0;
        this.product_name = "null";
        this.product_price = 0.0;
        this.product_total = 0;
    }

    // Constructor có tham số
    public Product(int product_id, String product_name, double product_price, int product_total) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_total = product_total;
    }

    // Getter and setter
    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public double getProduct_price() {
        return product_price;
    }

    public void setProduct_price(double product_price) {
        this.product_price = product_price;
    }

    public int getProduct_total() {
        return product_total;
    }

    public void setProduct_total(int product_total) {
        this.product_total = product_total;
    }

    //Phương thức toString
    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", product_name='" + product_name + '\'' +
                ", product_price=" + product_price +
                ", product_total=" + product_total +
                '}';
    }

    public void Input() {
        System.out.print("ID: ");
        product_id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Name: ");
        product_name = scanner.nextLine();
        System.out.print("Price: ");
        product_price = scanner.nextDouble();
        System.out.print("Total: ");
        product_total = scanner.nextInt();
    }
}
