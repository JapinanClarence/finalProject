
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedHashMap;
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
public class Reports {

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

    public static Map<String, Object> report() {
        Map<String, Object> result = new LinkedHashMap<>();

        result.put("Available Peripherals", availablePeripherals());
        result.put("Assigned Peripherals", assignedPeripherals());
        result.put("Input Devices", inputDevices());
        result.put("Output Devices", outputDevices());
        result.put("Input/Output Devices", inputOutputDevices());
        result.put("Assigned Employee", assignedEmployees());

        return result;
    }

    /**
     * Available peripherals
     *
     * @return count Will return total number of available peripherals
     */
    private static int availablePeripherals() {
        int count = 0;
        try {
            String sql = "SELECT * FROM devices";
            PreparedStatement statement = connect().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.equals(0)) {
                System.out.println("No peripherals found!");
            } else {
                while (resultSet.next()) {
                    String assigned_to = resultSet.getString("assigned_to");
                    if (assigned_to == null) {
                        count++;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employees.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    /**
     * Assigned peripherals
     *
     * @return count Will return total number of assigned peripherals
     */
    private static int assignedPeripherals() {
        int count = 0;
        try {
            String sql = "SELECT * FROM devices WHERE assigned_to != 'null'";
            PreparedStatement statement = connect().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.equals(0)) {
                System.out.println("No peripherals found!");
            } else {
                while (resultSet.next()) {
                    count++;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employees.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    /**
     * Input devices
     *
     * @return count Will return total number of input devices
     */
    private static int inputDevices() {
        int count = 0;
        try {
            String sql = "SELECT * FROM devices WHERE peripheral = 'Input'";
            PreparedStatement statement = connect().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.equals(0)) {
                System.out.println("No peripherals found!");
            } else {
                while (resultSet.next()) {
                    count++;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employees.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    /**
     * Output devices
     *
     * @return count Will return total number of output devices
     */
    private static int outputDevices() {
        int count = 0;
        try {
            String sql = "SELECT * FROM devices WHERE peripheral = 'Output'";
            PreparedStatement statement = connect().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.equals(0)) {
                System.out.println("No peripherals found!");
            } else {
                while (resultSet.next()) {
                    count++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employees.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    /**
     * Input Output devices
     *
     * @return count Will return total number of input/output devices
     */
    private static int inputOutputDevices() {
        int count = 0;
        try {
            String sql = "SELECT * FROM devices WHERE peripheral = 'Input/Output'";
            PreparedStatement statement = connect().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.equals(0)) {
                System.out.println("No peripherals found!");
            } else {
                while (resultSet.next()) {
                    count++;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employees.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    /**
     * Assigned employees
     *
     * @return count Will return total number of assigned employees
     */
    private static int assignedEmployees() {
        int count = 0;
        try {
            String sql = "SELECT COUNT(*) AS count FROM (SELECT DISTINCT assigned_to FROM devices) AS d WHERE d.assigned_to != 'NULL'";
            PreparedStatement statement = connect().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.equals(0)) {
                System.out.println("No employees found!");
            } else {
                while (resultSet.next()) {
                    count = resultSet.getInt("count");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employees.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

}
