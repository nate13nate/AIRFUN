package database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Comparator;
import database.User;

public class Database {
    public static ArrayList<User> getHighScores() {
        ArrayList<User> users = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "root");
                    Statement stmt = con.createStatement();
                    ResultSet rs = stmt.executeQuery("select * from high_scores");
                    while(rs.next()) {
                        System.out.println(rs.getInt(1) + " " + rs.getString(2));
                        users.add(new User(rs.getInt(1), rs.getString(2)));
                    }

                    con.close();

            users.sort((o1, o2)
                    -> String.valueOf(o2.getScore()).compareTo(
                    String.valueOf(o1.getScore())));
        } catch(Exception e) {
            System.out.println(e);
        }
        return users;
    }

    public static void main(String[] args) {
        getHighScores();
    }
}
