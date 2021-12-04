package mainfiles;

import database.Database;
import database.User;
import java.util.ArrayList;
import java.sql.*;

public class SaveScore {
    public static void setNewScore(int newScore) {
        if (Main.isSignedIn) {
            ArrayList<User> users = Database.getHighScores();
            int i;
            for (i = 0; i < users.size(); i++) {
                if (Main.userName.compareTo(users.get(i).getUserName()) == 0) {
                    break;
                }
            }
            if (newScore > users.get(i).getScore()) {
                Connection con = null;
                Statement update = null;
                try {
                    try { Class.forName("com.mysql.cj.jdbc.Driver"); }
                    catch (Exception e) { System.out.println(e); }

                    con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/mydb", "root", "root");
                    update = (Statement) con.createStatement();
                    String query1 = "update high_scores set score='" + newScore + "' " + "where username='" + users.get(i).getUserName() + "'";
                    update.executeUpdate(query1);
                }
                catch (SQLException e) { e.printStackTrace(); }
                catch (Exception e) { e.printStackTrace(); }
                finally {
                    try {
                        if (update != null)
                            con.close();
                    } catch (SQLException e) {
                    }
                    try {
                        if (con != null)
                            con.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
