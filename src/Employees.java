
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

    public void create(String emp_name, String contact_num, String gender) {
        try {
            String sql = "INSERT INTO employees (emp_name, contact_num, gender)  VALUES ('" + emp_name + "', '" + contact_num + "', '" + gender + "')";
            Statement statement = connect().createStatement();

            int rowsAffected = statement.executeUpdate(sql);
            if (rowsAffected > 0) {
                System.out.println("Employee added successfully.");
            } else {
                System.out.println("Failed to add employee.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employees.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void read() {
        try {
            String sql = "SELECT * FROM employees";
            PreparedStatement statement = connect().prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.equals(0)) {
                while (resultSet.next()) {
                    // Retrieve values from the result set
                    int emp_id = resultSet.getInt("emp_id");
                    String emp_name = resultSet.getString("emp_name");
                    String contact_num = resultSet.getString("contact_num");
                    String gender = resultSet.getString("gender");

                    // Process the retrieved data
                    System.out.println("ID: " + emp_id);
                    System.out.println("Name: " + emp_name);
                    System.out.println("Contact no: " + contact_num);
                    System.out.println("Gender: " + gender);
                    System.out.println("---------------------------");
                }
            }else{
                System.out.println("No employees found!");
            }

        } catch (SQLException e) {
             e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employees.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void update(int emp_id, String emp_name, String contact_num, String gender) {
        try {
            String sql = "UPDATE employees SET emp_name = '" + emp_name + "', contact_num = '" + contact_num + "', gender = '" + gender + "' WHERE emp_id = '" + emp_id + "'";
            Statement statement = connect().createStatement();

            int rowsAffected = statement.executeUpdate(sql);

            if (rowsAffected > 0) {
                System.out.println("Employee updated successfully.");
            } else {
                System.out.println("Failed to update employee.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employees.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(int emp_id) {
        try {
            String sql = "DELETE FROM employees WHERE emp_id = '" + emp_id + "'";
            Statement statement = connect().createStatement();

            int rowsAffected = statement.executeUpdate(sql);

            if (rowsAffected > 0) {
                System.out.println("Employee deleted successfully.");
            } else {
                System.out.println("Failed to delete employee.");
            }
        } catch (SQLException e) {
           e.printStackTrace();;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Employees.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
