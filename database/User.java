package database;

public class User implements Comparable<User> {
    private String userName;
    private Integer score;

    public User(Integer score, String userName) {
        this.userName = userName;
        this.score = score;
    }
    @Override
    public int compareTo(User comparesTo) {
        int compareScore = ((User)comparesTo).getScore();
        return compareScore-this.score;
    }

    public String getUserName() {
        return userName;
    }

    public int getScore() {
        return score;
    }
}
