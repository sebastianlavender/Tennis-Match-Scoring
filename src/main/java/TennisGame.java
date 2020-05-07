public class TennisGame {
    private final String playerOne;
    private final String playerTwo;
    private int playerOneScore = 0;
    private int playerTwoScore = 0;


    public TennisGame(String playerOne, String playerTwo) {
        this.playerOne = playerOne;
        this.playerTwo = playerTwo;
    }

    public String getScore() throws IllegalArgumentException {
        if (playerOneScore == playerTwoScore) {
            return playersOnSameScore();
        }

        if (playerOneHasWon()) {
            return playerOne + " wins";
        }

        if (playerTwoHasWon()) {
            return playerTwo + " wins";
        }

        if (playerOneScore >= 3 && playerTwoScore >= 3) {
            return playerWithAdvantage();
        }

        return playerScore(playerOneScore) + " " + playerScore(playerTwoScore);
    }

    public void playerOneScores() {
        playerOneScore += 1;
    }

    public void playerTwoScores() {
        playerTwoScore += 1;
    }

    private String playerWithAdvantage() {
        return playerOneScore > playerTwoScore ? "advantage " + playerOne : "advantage " + playerTwo;
    }

    private String playersOnSameScore() {
        if (playerTwoScore >= 3) {
            return "deuce";
        }

        return playerScore(playerOneScore) + " all";
    }

    private boolean playerOneHasWon() {
        return playerOneScore > 3 && playerOneScore - playerTwoScore >= 2;
    }

    private boolean playerTwoHasWon() {
        return playerTwoScore > 3 && playerTwoScore - playerOneScore >= 2;
    }

    private String playerScore(int playerScore) throws IllegalArgumentException {
        switch (playerScore) {
            case 0:
                return "love";
            case 1:
                return "fifteen";
            case 2:
                return "thirty";
            case 3:
                return "forty";
            default:
                throw new IllegalArgumentException("invalid score passed through");
        }
    }
}
