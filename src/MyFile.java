import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MyFile {
    private static final String FILE_NAME = "Housing.bin";

    // Lưu danh sách nhà vào tệp tin nhị phân
    public void saveHousings(List<Housing> housingList) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(housingList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Tải danh sách nhà từ tệp tin nhị phân
    public List<Housing> loadHousings() {
        List<Housing> housingList = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            housingList = (List<Housing>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return housingList;
    }
}
