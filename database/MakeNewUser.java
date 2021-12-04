package database;

import java.sql.*;
import java.lang.Math;
import java.util.ArrayList;

public class MakeNewUser {
    public static void makeUser(String name, String pass, ArrayList<User> users) {
        double rand = Math.random() * (9999 - 999) + 1000;
        int id = (int)rand;
        Connection con = null;
        Statement insert = null;
        try {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
            } catch (Exception e) {
                System.out.println(e);
            }
            con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "root");
            System.out.println("Connection is created successfully:");
            insert = (Statement) con.createStatement();
            String query1 = "INSERT INTO high_scores " + "VALUES (0, \"" + name + "\", \"" + id + "\", \"" + pass + "\")";
            insert.executeUpdate(query1);
            System.out.println("Record is inserted in the table successfully..................");
            users.add(new User(0, name, pass));
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (insert != null)
                    con.close();
            } catch (SQLException e) {}
            try {
                if (con != null)
                    con.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        System.out.println("Please check it in the MySQL Table......... ……..");
    }
}
