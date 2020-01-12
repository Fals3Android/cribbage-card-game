import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

//Gameplay
//    Shuffle()
//    Deal()
//    Create_Crib()
//    Round_Score()
// Result -> player score by player: player one 105, player two 98, player one wins!

public class Game {
    Deck defaultDeck;
    public int playerOneScore;
    public int playerTwoScore;
    public Map<String, Integer> cards = new LinkedHashMap<>();
    public Map<String, Integer> playerOneCards = new LinkedHashMap<>();
    public Map<String, Integer> playerTwoCards = new LinkedHashMap<>();
    public Map<String, Integer> crib = new LinkedHashMap<>();
    // Pegging Phase
    public int runningPeggingTotal;
    public Map<String, Integer> runningPeggingOrder = new LinkedHashMap<>();

    Game(Deck deck) {
        defaultDeck = deck;
        cards.putAll(deck.getCards());
    }

    public void deal() {
        int counter = 0;
        int maxCardsPerDeal = (6 * 2);

        while(counter != maxCardsPerDeal) {
            String p1Key = (String) cards.keySet().toArray()[0];
            int p1Value =  cards.get(p1Key);
            playerOneCards.put(p1Key, p1Value);
            cards.remove(p1Key);

            String p2Key = (String) cards.keySet().toArray()[0];
            int p2Value =  cards.get(p2Key);
            playerTwoCards.put(p2Key, p2Value);
            cards.remove(p2Key);
            counter += 2;
        }
    }

    /**
     * Ideally crib creation should include the choice to remove the lowest scoring cards, from a players hand.
     */
    public void createCrib() {
        int counter = 0;

        while(counter < 2) {
            String p1Key = (String) playerOneCards.keySet().toArray()[0];
            int p1Value =  playerOneCards.get(p1Key);
            playerOneCards.remove(p1Key);
            crib.put(p1Key, p1Value);
            counter++;
        }

        counter = 0;

        while(counter < 2) {
            String p2Key = (String) playerTwoCards.keySet().toArray()[0];
            int p2Value =  playerTwoCards.get(p2Key);
            playerTwoCards.remove(p2Key);
            crib.put(p2Key, p2Value);
            counter++;
        }
    }

    public void cutDeck() {
        String middleKey = (String) cards.keySet().toArray()[(int) Math.ceil(cards.size() / 2)];
        int middleValue =  cards.get(middleKey);
        cards.remove(middleKey);
        Map<String, Integer> newMap = new LinkedHashMap<>();
        newMap.put(middleKey, middleValue);
        newMap.putAll(cards);
        cards = newMap;
    }

    /**
     * Ways to score:
     * 15 or 31 = 2 points
     * Pairs (Ranks) = 2 pairs (2 points), 3 pairs (6 points), 4 pairs (12 points)
     * Run = Amount of cards in run (3 cards = 3 points, 4 cards = 4 points, etc...)
     * Go = 1 point
     * Last Card = 1 point
     * Alternate playing cards starting with non dealer
     */
    public void peggingPlay(String suit, String rank, int value, int player) {
        runningPeggingTotal += value;
        boolean equalsFifteen = runningPeggingTotal == 15;
        boolean isPlayerOne = player == 0;

        // find rank pairs
        AtomicBoolean rankPresent = new AtomicBoolean(false);
        runningPeggingOrder.forEach((k,v) -> {
            if(k.contains(rank)) {
                rankPresent.set(true);
            }
        });

        if(rankPresent.get()) {
            if(isPlayerOne) {
                playerOneScore += 2;
            } else {
                playerTwoScore += 2;
            }
        }

        // place played card in map
        runningPeggingOrder.put(suit.concat(":" + rank), value);

        // equals fifteen?
        if(isPlayerOne && equalsFifteen) {
            playerOneScore += 2;
        }

        if(!isPlayerOne && equalsFifteen) {
            playerTwoScore += 2;
        }
    }
}
