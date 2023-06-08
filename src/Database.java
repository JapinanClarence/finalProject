/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author japin
 */
import java.sql.*;

public class Database {

    private static Connection connect() throws SQLException {
        //url
        String url = "dorsu_inventory_system";

        //Database connection
        Connection conn = DriverManager.getConnection(url);
        return conn;
    }

}
