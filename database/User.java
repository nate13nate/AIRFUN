package database;

public class User implements Comparable<User> {
    private String userName;
    private Integer score;
    private String password;

    public User(Integer score, String userName, String password) {
        this.userName = userName;
        this.score = score;
        this.password = password;
    }
    @Override
    public int compareTo(User comparesTo) {
        int compareScore = ((User)comparesTo).getScore();
        return compareScore-this.score;
    }

    public String getUserName() { return userName; }
    public int getScore() { return score; }
    public String getPass() { return password; }
}
