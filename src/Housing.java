import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Housing extends Product implements Serializable {

    private String address;
    private int rooms;
    private double area;
    //Constructor khong tham so
    public Housing() {
        super();
        this.address = "";
        this.rooms = 0;
        this.area = 0;
    }

    //Constructor co tham so
    public Housing(int product_id, String product_name, double product_price, int product_total, String address, int rooms, double area) {
        super(product_id, product_name, product_price, product_total);
        this.address = address;
        this.rooms = rooms;
        this.area = area;
    }

    //Getter and setter
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

@Override
public String toString() {
    return "Housing{" +
            "Id='" + getProduct_id() + '\'' +
            ", Name='" + getProduct_name() + '\'' +
            ", Price=" + getProduct_price() +
            ", Total=" + getProduct_total() +
            ", address='" + address + '\'' +
            ", rooms=" + rooms +
            ", area=" + area +
            '}';
}


    @Override
    public void Input() {
        super.Input();
        scanner.nextLine();
        System.out.print("Address: ");
        this.address = scanner.nextLine();
        System.out.print("Rooms: ");
        this.rooms = scanner.nextInt();
        System.out.print("Area (m²): ");
        this.area = scanner.nextDouble();
    }
}
