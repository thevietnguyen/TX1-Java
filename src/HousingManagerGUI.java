import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class HousingManagerGUI extends JFrame {
    private HousingManagerImpl manager;
    private JTable table;
    private DefaultTableModel tableModel;

    public HousingManagerGUI() {
        manager = new HousingManagerImpl();

        setTitle("Housing Manager");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        // Create components
        JPanel panel = new JPanel(new BorderLayout());
        tableModel = new DefaultTableModel(new Object[]{"ID", "Name", "Price", "Total", "Address", "Rooms", "Area"}, 0);
        table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);

        JPanel formPanel = new JPanel(new GridLayout(7, 2));
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField priceField = new JTextField();
        JTextField totalField = new JTextField();
        JTextField addressField = new JTextField();
        JTextField roomsField = new JTextField();
        JTextField areaField = new JTextField();

        ImageIcon icon = new ImageIcon("icon.png"); // Thay "new_icon.png" bằng đường dẫn tới file ảnh của bạn
        setIconImage(icon.getImage());

        formPanel.add(new JLabel("ID:"));
        formPanel.add(idField);
        formPanel.add(new JLabel("Name:"));
        formPanel.add(nameField);
        formPanel.add(new JLabel("Price:"));
        formPanel.add(priceField);
        formPanel.add(new JLabel("Total:"));
        formPanel.add(totalField);
        formPanel.add(new JLabel("Address:"));
        formPanel.add(addressField);
        formPanel.add(new JLabel("Rooms:"));
        formPanel.add(roomsField);
        formPanel.add(new JLabel("Area:"));
        formPanel.add(areaField);

        JPanel buttonPanel = new JPanel(new GridLayout(1, 5));
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton deleteButton = new JButton("Delete");
        JButton searchButton = new JButton("Search");
        JButton sortButton = new JButton("Sort");

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(sortButton);

        panel.add(tableScrollPane, BorderLayout.CENTER);
        panel.add(formPanel, BorderLayout.NORTH);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        add(panel);

        // Add action listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                double price = Double.parseDouble(priceField.getText());
                int total = Integer.parseInt(totalField.getText());
                String address = addressField.getText();
                int rooms = Integer.parseInt(roomsField.getText());
                double area = Double.parseDouble(areaField.getText());

                Housing housing = new Housing(id, name, price, total, address, rooms, area);
                manager.addHousing(housing);
                updateTable();
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                String name = nameField.getText();
                double price = Double.parseDouble(priceField.getText());
                int total = Integer.parseInt(totalField.getText());
                String address = addressField.getText();
                int rooms = Integer.parseInt(roomsField.getText());
                double area = Double.parseDouble(areaField.getText());

                Housing housing = new Housing(id, name, price, total, address, rooms, area);
                manager.editHousing(housing);
                updateTable();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int id = Integer.parseInt(idField.getText());
                Housing housing = new Housing();
                housing.setProduct_id(id);
                manager.delHousing(housing);
                updateTable();
            }
        });

        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchHousing();
            }
        });

        sortButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sortHousing();
            }
        });

        updateTable();
    }

    private void searchHousing() {
        String[] options = {"1. Name", "2. Price", "3. Address", "4. Rooms", "5. Area"};
        String input = (String) JOptionPane.showInputDialog(
                this, "Choose search option:", "Search Housing",
                JOptionPane.QUESTION_MESSAGE, null, options, options[0]);

        if (input != null) {
            String searchOption = input.split("\\.")[0];
            List<Housing> foundHousings = manager.searchHousing(searchOption);
            updateTable(foundHousings);
        }
    }

    private void sortHousing() {
        String[] options = {"Ascending", "Descending"};
        int choice = JOptionPane.showOptionDialog(
                this, "Sort by price:", "Sort Housing",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE,
                null, options, options[0]);

        double sortOption = (choice == 0) ? 1 : 2;
        List<Housing> sortedHousings = manager.sortedHousing(sortOption);
        updateTable(sortedHousings);
    }

    private void updateTable() {
        tableModel.setRowCount(0); // Clear existing data
        for (Housing housing : manager.housingList) {
            tableModel.addRow(new Object[]{
                    housing.getProduct_id(),
                    housing.getProduct_name(),
                    housing.getProduct_price(),
                    housing.getProduct_total(),
                    housing.getAddress(),
                    housing.getRooms(),
                    housing.getArea()
            });
        }
    }

    private void updateTable(List<Housing> housings) {
        tableModel.setRowCount(0); // Clear existing data
        for (Housing housing : housings) {
            tableModel.addRow(new Object[]{
                    housing.getProduct_id(),
                    housing.getProduct_name(),
                    housing.getProduct_price(),
                    housing.getProduct_total(),
                    housing.getAddress(),
                    housing.getRooms(),
                    housing.getArea()
            });
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HousingManagerGUI().setVisible(true);
            }
        });
    }
}
