
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Year;
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
public class Peripherals {

    private static Connection connect() throws SQLException, ClassNotFoundException {
        // url
        String url = "jdbc:mysql://localhost:3306/dorsu_inventory_system";
        String username = "root";
        String password = "";
        Class.forName("com.mysql.cj.jdbc.Driver");

        // Database connection
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }

    /**
     * Create method
     *
     * @param name
     * @param peripheral
     * @param assigned_to
     *
     * Will add new peripheral
     */
    public void create(String name, String peripheral, String assigned_to) {
        try {

            String sql = "INSERT INTO devices (device_code, name, peripheral, assigned_to)  VALUES ('" + getCode()
                    + "', '" + name + "', '" + peripheral + "', '" + assigned_to + "')";
            Statement statement = connect().createStatement();

            int rowsAffected = statement.executeUpdate(sql);
            if (rowsAffected > 0) {
                System.out.println("Peripheral added successfully.");
            } else {
                System.out.println("Failed to add peripheral.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employees.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Read Method
     */
    public void read() {
        try {
            String sql = "SELECT * FROM devices";
            PreparedStatement statement = connect().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.equals(0)) {
                System.out.println("No peripherals found!");
            } else {
                while (resultSet.next()) {
                    // Retrieve values from the result set
                    String device_code = resultSet.getString("device_code");
                    String name = resultSet.getString("name");
                    String peripheral = resultSet.getString("peripheral");
                    String assigned_to = resultSet.getString("assigned_to");

                    // Process the retrieved data
                    System.out.println("Code: " + device_code);
                    System.out.println("Name: " + name);
                    System.out.println("Peripheral: " + peripheral);
                    System.out.println("Assigned to: " + assigned_to);
                    System.out.println("---------------------------");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employees.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Update Method
     *
     * @param name
     * @param peripheral
     * @param assigned_to
     * @param device_code
     *
     * Will update the current peripheral.
     */
    public void update(String device_code, String name, String peripheral, String assigned_to) {
        try {
            String sql = "UPDATE devices SET name = '" + name + "', peripheral = '" + peripheral + "', assigned_to = '" + assigned_to + "' WHERE device_code = '" + device_code + "'";
            Statement statement = connect().createStatement();

            int rowsAffected = statement.executeUpdate(sql);

            if (rowsAffected > 0) {
                System.out.println("Peripheral updated successfully.");
            } else {
                System.out.println("Failed to update peripheral.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employees.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(String device_code) {
        
        try {
            String sql = "DELETE FROM devices WHERE device_code = '" + device_code + "'";
            Statement statement = connect().createStatement();

            int rowsAffected = statement.executeUpdate(sql);

            if (rowsAffected > 0) {
                System.out.println("Peripheral deleted successfully.");
            } else {
                System.out.println("Failed to delete peripheral.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employees.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Generate code method
     *
     * @return code This method will generate code for the computer peripherals.
     * The first 4 digit of the code is the current year and the other is an
     * incremented value by one.
     */
    private String getCode() {
        String code = null;
        // will get the current year
        int currentYear = Year.now().getValue();
        String yearPrefix = String.format("%04d", currentYear);

        try {
            //will fetch the latest device code 
            String sql = "SELECT MAX(device_code) FROM devices";
            PreparedStatement statement = connect().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {

                String latestDeviceCode = resultSet.getString(1);
                //check if there are devices
                if (latestDeviceCode != null) {
                    //Extract the second part of the code
                    String numericPart = latestDeviceCode.substring(latestDeviceCode.indexOf("-") + 1);
                    //convert to integer and increment by one
                    int value = Integer.parseInt(numericPart) + 1;
                    //convert to string format
                    String incrementedValue = String.format("%04d", value);

                    //Construct the new device code
                    code = yearPrefix + "-" + incrementedValue;
                } else {
                    String id = "0001";
                    code = yearPrefix + "-" + id;
                }

            }

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Peripherals.class.getName()).log(Level.SEVERE, null, ex);
        }
        return code;
    }
    /**
     * Verify id the device/peripheral is assigned
     * @param device_code
     * @return Boolean
     */
    public Boolean isAssigned(String device_code) {
        Boolean result = false;
        try {
            String sql = "SELECT assigned_to FROM devices WHERE device_code = '" + device_code + "'";
            PreparedStatement statement = connect().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String assigned_to = resultSet.getString("assigned_to");
                
                if (assigned_to != null) {
                    result = true;
                } 
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Peripherals.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
