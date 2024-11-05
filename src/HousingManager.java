import java.util.List;
public interface HousingManager {
    // Thêm một thông tin bất động sản vào hệ thống
    public boolean addHousing(Housing h);

    // Sửa thông tin bất động sản đã lưu
    public boolean editHousing(Housing h);

    // Xóa thông tin bất động sản đã lưu
    public boolean delHousing(Housing h);

    // Tìm kiếm bất động sản theo tên

    public List<Housing> searchHousing(String name);

    // Sắp xếp bất động sản theo giá và diện tích
    public List<Housing> sortedHousing(double price);
}
