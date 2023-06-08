
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSetMetaData;
import java.util.LinkedHashMap;
import java.util.Map;

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
     * @return Boolean Will return true if successfully added a peripheral
     */
    public static Map<String, Object> create(String name, String peripheral, String assigned_to) {
        Map<String, Object> result = new LinkedHashMap<>();
        try {

            String sql = "INSERT INTO devices (device_code, name, peripheral, assigned_to)  VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = connect().prepareStatement(sql);

            pstmt.setString(1, getCode());
            pstmt.setString(2, name);
            pstmt.setString(3, peripheral);
            pstmt.setString(4, assigned_to);

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                result.put("success", true);
                result.put("message", "Peripheral created successfully");
            } else {
                result.put("success", false);
                result.put("message", "Failed to create peripheral");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employees.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * Show specific peripheral record
     *
     * @param device_code
     * @return array
     */
    public static Map<String, Object> fetchPeripheralRecord(String device_code) {
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            String sql = "SELECT * FROM devices WHERE device_code = ?";
            PreparedStatement pstmt = connect().prepareStatement(sql);

            pstmt.setString(1, device_code);

            ResultSet resultSet = pstmt.executeQuery();

            if (resultSet == null) {
                result.put("success", "true");
                result.put("message", "No peripherals found");
            } else {
                while (resultSet.next()) {
                    // Retrieve values from the result set
                    String name = resultSet.getString("name");
                    String peripheral = resultSet.getString("peripheral");
                    String assigned_to = resultSet.getString("assigned_to");

                    // Process the retrieved data
                    result.put("success", "true");
                    result.put("device_code", device_code);
                    result.put("name", name);
                    result.put("peripheral", peripheral);
                    result.put("assigned_to", assigned_to);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employees.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * Fetch unassigned peripherals
     *
     * @return array
     */
    public static String[][] availablePeripherals() {
        String[][] dataArray = null;
        try {
            String sql = "SELECT * FROM devices";
            PreparedStatement statement = connect().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet != null) {
                // Get the column count
                ResultSetMetaData metaData = resultSet.getMetaData();
                int columnCount = metaData.getColumnCount();

                // Creating a list to store the data
                List<String[]> data = new ArrayList<>();

                // Iterating over the result set and populating the list
                while (resultSet.next()) {
                    String assigned_to = resultSet.getString("assigned_to");
                    if (assigned_to == null) {
                        String[] row = new String[columnCount];
                        int index = 0;
                        for (int i = 1; i <= columnCount; i++) {
                            row[index++] = resultSet.getString(i);
                        }
                        data.add(row);
                    }

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

    /**
     * Fetch assigned peripherals
     *
     * @return array
     */
    public static String[][] peripherals() {
        String[][] dataArray = null;
        try {
            String sql = "SELECT * FROM devices WHERE assigned_to != 'null'";
            PreparedStatement statement = connect().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet != null) {
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

    /**
     * Fetch input devices
     *
     * @return array
     */
    public static String[][] fetchInputDevices() {
        String[][] dataArray = null;
        try {
            String sql = "SELECT * FROM devices WHERE peripheral = 'Input'";
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

    /**
     * Fetch output devices
     *
     * @return array
     */
    public static String[][] fetchOutputDevices() {
        String[][] dataArray = null;
        try {
            String sql = "SELECT * FROM devices WHERE peripheral = 'Output'";
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

    /**
     * Fetch input/output devices
     *
     * @return array
     */
    public static String[][] fetchInputOutputDevices() {
        String[][] dataArray = null;
        try {
            String sql = "SELECT * FROM devices WHERE peripheral = 'Input/Output'";
            PreparedStatement statement = connect().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet != null) {
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

    /**
     * Update Method
     *
     * @param name
     * @param peripheral
     * @param assigned_to
     * @param device_code
     *
     * Will update the current peripheral.
     * @return Boolean Will return true if successfully updated a peripheral
     */
    public static Map<String, Object> update(String device_code, String name, String peripheral, String assigned_to) {
        Map<String, Object> result = new LinkedHashMap<>();
        try {

            String sql = "UPDATE devices SET name = ?, peripheral = ? , assigned_to = ? WHERE device_code = ?";

            PreparedStatement pstmt = connect().prepareStatement(sql);

            pstmt.setString(1, name);
            pstmt.setString(2, peripheral);
            pstmt.setString(3, assigned_to);
            pstmt.setString(4, device_code);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                result.put("success", true);
                result.put("message", "Peripheral updated successfully");
            } else {
                result.put("success", false);
                result.put("message", "Failed to update peripheral");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employees.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * Delete peripheral method
     *
     * @param device_code
     * @return Boolean Will return true if successfully deleted a peripheral
     * This method will not delete assigned peripherals
     */
    public static Map<String, Object> delete(String device_code) {
        Map<String, Object> result = new LinkedHashMap<>();
        try {
            if (isPeripheralAssigned(device_code) == true) {
                result.put("success", false);
                result.put("message", "The peripheral is currently assigned to an employee. Unassign it first before deleting");
            } else {
                String sql = "DELETE FROM devices WHERE device_code = ?";

                PreparedStatement pstmt = connect().prepareStatement(sql);

                pstmt.setString(1, device_code);

                int rowsAffected = pstmt.executeUpdate();

                if (rowsAffected > 0) {
                    result.put("success", true);
                    result.put("message", "Peripheral deleted successfully");
                } else {
                    result.put("success", false);
                    result.put("message", "Failed to delete peripheral");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employees.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public static Map<String, Object> removePeripheralAssignment(String device_code) {
         Map<String, Object> result = new LinkedHashMap<>();
        try {
            String removeAssignmentQuery = "UPDATE devices SET assigned_to = NULL WHERE device_code = ?";
            PreparedStatement pstmt = connect().prepareStatement(removeAssignmentQuery);
            
            pstmt.setString(1, device_code);

            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                result.put("success", true);
                result.put("message", "Remove assignment successfull");
            } else {
                result.put("success", false);
                result.put("message", "Failed to remove assignment");
            }

            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Peripherals.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    /**
     * Generate code method
     *
     * @return code This method will generate code for the computer peripherals.
     * The first 4 digit of the code is the current year and the other is an
     * incremented value by one.
     */
    private static String getCode() {
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
     *
     * @param device_code
     * @return Boolean
     */
    private static Boolean isPeripheralAssigned(String device_code) {
        Boolean result = false;
        try {
            String sql = "SELECT assigned_to FROM devices WHERE device_code = ?";
            PreparedStatement pstmt = connect().prepareStatement(sql);

            pstmt.setString(1, device_code);
            ResultSet resultSet = pstmt.executeQuery();

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
