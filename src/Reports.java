
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class Reports{
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
    /**
     * Available peripherals
     * @return count
     * Will return total number of available peripherals
     */
    public static int availablePeripherals(){
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
     * @return count
     * Will return total number of assigned peripherals
     */
    public static int assignedPeripherals(){
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
     * @return count
     * Will return total number of input devices
     */
    public static int inputDevices(){
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
     * @return count
     * Will return total number of output devices
     */
    public static int outputDevices(){
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
     * @return count
     * Will return total number of input/output devices
     */
    public static int inputOutputDevices(){
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
     * @return count
     * Will return total number of assigned employees
     */
    public static int assignedEmployees() {
        int count = 1;
        try {
            String sql = "SELECT e.emp_name, d.name FROM employees e join devices d on d.assigned_to = e.emp_name";
            PreparedStatement statement = connect().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.equals(0)) {
                System.out.println("No employees found!");
            } else {
                while (resultSet.next()) {
                    if (resultSet.next()) {
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
    
    
}
