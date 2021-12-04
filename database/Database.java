package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;

public class Database {
    public static ArrayList<User> getHighScores() {
        ArrayList<User> users = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("select * from high_scores");
                    while(rs.next()) { users.add(0, new User(rs.getInt(1), rs.getString(2), rs.getString(4))); }
                    con.close();

            Collections.sort(users);
        } catch(Exception e) {
            System.out.println(e);
        }
        return users;
    }
}
