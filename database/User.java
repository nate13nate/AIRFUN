package database;

public class User {
    private String userName;
    private Integer score;

    public User(Integer score, String userName) {
        this.userName = userName;
        this.score = score;
    }

    public String getUserName() {
        return userName;
    }

    public int getScore() {
        return score;
    }
}
