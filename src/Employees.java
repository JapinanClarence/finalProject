
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author japin
 */
public class Employees {

    private static Connection connect() throws SQLException, ClassNotFoundException {
        //url
        String url = "jdbc:mysql://localhost:3306/dorsu_inventory_system";
        String username = "root";
        String password = "";
        Class.forName("com.mysql.cj.jdbc.Driver");

        //Database connection
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }

    public static Map<String, Object> create(String emp_name, String contact_num, String gender) {
        Map<String, Object> result = new LinkedHashMap<>();

        try {
            String sql = "INSERT INTO employees (emp_name, contact_num, gender)  VALUES (?, ?, ?)";
            PreparedStatement pstmt = connect().prepareStatement(sql);

            pstmt.setString(1, emp_name);
            pstmt.setString(2, contact_num);
            pstmt.setString(3, gender);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                result.put("success", true);
                result.put("message", "Employee added successfully");
            } else {
                result.put("success", false);
                result.put("message", "Failed to add employee");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employees.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static String[][] read() {
        String[][] dataArray = null;

        try {
            String sql = "SELECT * FROM employees";
            PreparedStatement statement = connect().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.equals(0)) {
                // Get the column count
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                // Creating a list to store the data
                List<String[]> data = new ArrayList<>();

                // Iterating over the result set and populating the list
                while (resultSet.next()) {

                    String[] row = new String[columnCount];
                    int index = 0;
                    for (int i = 1; i <= columnCount; i++) {
                        row[index++] = resultSet.getString(i);
                    }
                    data.add(row);
                }
                // Converting the list to a 2D array
                dataArray = new String[data.size()][columnCount];
                for (int i = 0; i < data.size(); i++) {
                    dataArray[i] = data.get(i);
                }
            } 

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employees.class.getName()).log(Level.SEVERE, null, ex);
        }
        return dataArray;
    }

    public static Map<String, Object> update(int emp_id, String emp_name, String contact_num, String gender) {
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            String sql = "UPDATE employees SET emp_name = ?, contact_num = ?, gender = ? WHERE emp_id = ?";
            PreparedStatement pstmt = connect().prepareStatement(sql);

            pstmt.setString(1, emp_name);
            pstmt.setString(2, contact_num);
            pstmt.setString(3, gender);
            pstmt.setInt(4, emp_id);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                result.put("success", true);
                result.put("message", "Employee updated successfully");
            } else {
                result.put("success", false);
                result.put("message", "Failed to update employee");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employees.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static Map<String, Object> delete(int emp_id) {
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            String sql = "DELETE FROM employees WHERE emp_id = ?";

            PreparedStatement pstmt = connect().prepareStatement(sql);

            pstmt.setInt(1, emp_id);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                result.put("success", true);
                result.put("message", "Employee removed successfully");
            } else {
                result.put("success", false);
                result.put("message", "Failed to remove employee");
            }
        } catch (SQLException e) {
            e.printStackTrace();;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employees.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
