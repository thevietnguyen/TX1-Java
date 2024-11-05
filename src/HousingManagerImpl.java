import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class HousingManagerImpl implements HousingManager {
    public List<Housing> housingList;
    private MyFile myfile;

    public HousingManagerImpl() {
        this.housingList = new ArrayList<>();
        this.myfile = new MyFile();
        // Tải dữ liệu từ tệp khi khởi tạo
        this.housingList = myfile.loadHousings();
    }

    @Override
    public boolean addHousing(Housing h) {
        boolean added = housingList.add(h);
        if (added) {
            myfile.saveHousings(housingList); // Lưu dữ liệu sau khi thêm
        }
        return added;

    }

    @Override
    public boolean editHousing(Housing h) {
        for (int i = 0; i < housingList.size(); i++) {
            if (housingList.get(i).getProduct_id() == (h.getProduct_id())) {
                housingList.set(i, h);
                myfile.saveHousings(housingList);// Lưu dữ liệu sau khi sửa
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delHousing(Housing h) {
        boolean removed = housingList.removeIf(existingHousing -> existingHousing.getProduct_id() == h.getProduct_id());
        if (removed) {
            myfile.saveHousings(housingList); // Lưu dữ liệu sau khi xóa
        }
        return removed;
    }

    @Override
    public List<Housing> searchHousing(String name) {
        Scanner scanner = new Scanner(System.in);
        List<Housing> foundHousings = new ArrayList<>();
        switch (name) {
            case "1":
                System.out.print("Nhập tên: ");
                String houseName = scanner.nextLine();
                foundHousings = (ArrayList<Housing>) housingList.stream().filter(x -> x.getProduct_name().equals(houseName))
                        .collect(Collectors.toList());
                break;
            case "2":
                System.out.print("Nhập giá: ");
                double housePrice = scanner.nextDouble();
                foundHousings = (ArrayList<Housing>) housingList.stream().filter(x -> x.getProduct_price() == (housePrice))
                        .collect(Collectors.toList());
                break;
            case "3":
                System.out.print("Nhập địa chỉ: ");
                String houseAddress = scanner.nextLine();
                foundHousings = (ArrayList<Housing>) housingList.stream().filter(x -> x.getAddress().equals(houseAddress))
                        .collect(Collectors.toList());
                break;
            case "4":
                System.out.print("Nhập số phòng: ");
                int houseRooms = scanner.nextInt();
                foundHousings = (ArrayList<Housing>) housingList.stream().filter(x -> x.getRooms() == (houseRooms))
                        .collect(Collectors.toList());
                break;
            case "5":
                System.out.print("Nhập diện tích: ");
                double houseArea = scanner.nextDouble();
                foundHousings = (ArrayList<Housing>) housingList.stream().filter(x -> x.getArea() == (houseArea))
                        .collect(Collectors.toList());
                break;
          
            default:
                System.out.println("Không hợp lệ");
                break;
            }
            return foundHousings;
        }


    @Override
    public List<Housing> sortedHousing(double price) {
        List<Housing> sortedList = new ArrayList<>(housingList);
        sortedList.sort(Comparator.comparingDouble(Housing::getProduct_price));
        if (price == 2) {
            Collections.reverse(sortedList);
        }
        myfile.saveHousings(housingList);
        return sortedList;
    }

//    public void printHousings() {
//        List<Housing> housingList = ();
//        for (Housing h : housingList) {
//            System.out.println(h);
//        }
//    }










        public static void main (String [] args){
            HousingManagerImpl manager = new HousingManagerImpl();
            Scanner scanner = new Scanner(System.in);
            boolean exit = false;

            while (!exit) {
                System.out.println("Choose an option:");
                System.out.println("1. Thêm thông tin nhà");
                System.out.println("2. Sửa thông tin nhà");
                System.out.println("3. Xóa thông tin nhà");
                System.out.println("4. Tìm nhà theo");
                System.out.println("5. Sắp xếp nhà theo giá");
                System.out.println("6. Thoát");

                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline left-over

                switch (choice) {
                    case 1:
                        Housing newHousing = new Housing();
                        newHousing.Input();
                        manager.addHousing(newHousing);
                        break;

                    case 2:
                        System.out.print("Nhập ID nhà cần sửa: ");
                        int editId = scanner.nextInt();
                        Housing editHousing = null;
                        for (Housing h : manager.housingList) {
                            if (h.getProduct_id() == (editId)) {
                                editHousing = h;
                                break;
                            }
                        }
                        if (editHousing != null) {
                            editHousing.Input();
                            manager.editHousing(editHousing);
                        } else {
                            System.out.println("Không tìm thấy nhà.");
                        }
                        break;

                    case 3:
                        System.out.print("Nhập ID nhà cần xóa: ");
                        int delId = scanner.nextInt();
                        Housing delHousing = null;
                        for (Housing h : manager.housingList) {
                            if (h.getProduct_id() == (delId)) {
                                delHousing = h;
                                break;
                            }
                        }
                        if (delHousing != null) {
                            manager.delHousing(delHousing);
                            System.out.println("Đã xóa");
                        } else {
                            System.out.println("Không tìm thấy nhà.");
                        }
                        break;

                    case 4:
                        System.out.println("Chọn thuộc tính để tìm kiếm:");
                        System.out.println("1. Tên sản phẩm");
                        System.out.println("2. Giá sản phẩm");
                        System.out.println("3. Địa chỉ");
                        System.out.println("4. Số phòng");
                        System.out.println("5. Diện tích");

                        int searchOption = scanner.nextInt();
                        scanner.nextLine();


                        List<Housing> foundHousings = manager.searchHousing(String.valueOf(searchOption));
                        for (Housing h : foundHousings) {
                            System.out.println(h);
                        }
                        break;
                    case 5:
                        System.out.println("1. Sắp xếp tăng dần");
                        System.out.println("2. Sắp xếp giảm dần");
                        int sortOption = scanner.nextInt();
                        List<Housing> sortedByPrice = manager.sortedHousing(sortOption);
                        if (sortOption != 1 && sortOption != 2) {
                            System.out.println("Lựa chọn không hợp lệ");
                        } else {
                            for (Housing h: sortedByPrice
                                 ) {
                                System.out.println(h);
                            }
                        }
                        break;
                    case 6:
                        exit = true;
                        break;

                    default:
                        System.out.println("Chọn lại.");
                }
            }
            scanner.close();
        }
    }
