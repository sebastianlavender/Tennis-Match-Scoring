import spock.lang.Specification
import spock.lang.Unroll

class TennisGameSpec extends Specification {

    TennisGame tennisGame;

    def setup() {
        tennisGame = new TennisGame("Serena", "Venus")
    }

    def "a tennis game takes 2 players starting on 0 points each"() {
        expect:
        tennisGame.getScore() == "love all"
    }

    @Unroll
    def "when playerOne is on #playerOne and playerTwo is on #playerTwo: #result is returned"() {
        when:
        createScore(playerOne, playerTwo)

        then:
        tennisGame.getScore() == result

        where:
        playerOne | playerTwo | result
        1         | 0         | "fifteen love"
        0         | 1         | "love fifteen"
        1         | 1         | "fifteen all"
        2         | 1         | "thirty fifteen"
        1         | 2         | "fifteen thirty"
        2         | 2         | "thirty all"
        3         | 2         | "forty thirty"
        2         | 3         | "thirty forty"
    }

    def "A player wins when they score 4 times whilst the other player scores 3 or less"() {
        when:
        createScore(4, 2)

        then:
        tennisGame.getScore() == "Serena wins"
    }

    def "when both players are on 40 points each, deuce is retuned"() {
        when:
        createScore(3, 3)

        then:
        tennisGame.getScore() == "deuce"
    }

    @Unroll
    def "when a player scores whilst on deuce they gain advantage"() {
        when:
        createScore(serena, venus)

        then:
        tennisGame.getScore() == result

        where:
        serena | venus | result
        4      | 3     | "advantage Serena"
        3      | 4     | "advantage Venus"
        5      | 4     | "advantage Serena"
        7      | 8     | "advantage Venus"
    }

    @Unroll
    def "when a player scores on advantage they win"() {
        when:
        createScore(6, 4)

        then:
        tennisGame.getScore() == "Serena wins"

        where:
        serena | venus | result
        5      | 3     | "Serena wins"
        3      | 5     | "Venus wins"
        10     | 8     | "Serena wins"
        8      | 10    | "Venus wins"
    }

    def createScore(int playerOne, int playerTwo) {
        playerOne.times {
            tennisGame.playerOneScores()
        }

        playerTwo.times {
            tennisGame.playerTwoScores()
        }
    }
}
