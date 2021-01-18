package Models;

public class AnswerWrapper {
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isGuessedRight() {
        return guessedRight;
    }

    public void setGuessedRight(boolean guessedRight) {
        this.guessedRight = guessedRight;
    }

    String username;
    boolean guessedRight = true;
}
