import java.util.*;

//Gameplay
//    Shuffle()
//    Deal()
//    Create_Crib()
//    Round_Score()
// Result -> player score by player: player one 105, player two 98, player one wins!

public class Game {
    Deck defaultDeck;
    public Map<String, Integer> cards = new LinkedHashMap<>();
    public Map<String, Integer> playerOneCards = new LinkedHashMap<>();
    public Map<String, Integer> playerTwoCards = new LinkedHashMap<>();
    public Map<String, Integer> crib = new LinkedHashMap<>();

    Game(Deck deck) {
        defaultDeck = deck;
        cards.putAll(deck.getCards());
    }

    public void deal() {
        int counter = 0;
        int maxCardsPerDeal = (6 * 2);

        while(counter != maxCardsPerDeal) {
            String p1Key = (String) cards.keySet().toArray()[counter];
            int p1Value =  cards.get(p1Key);
            playerOneCards.put(p1Key, p1Value);
            cards.remove(p1Key);
            counter += 1;

            String p2Key = (String) cards.keySet().toArray()[counter];
            int p2Value =  cards.get(p2Key);
            playerTwoCards.put(p2Key, p2Value);
            cards.remove(p2Key);
            counter += 1;
        }
    }

    /**
     * Ideally crib creation should include the choice to remove the lowest scoring cards, from a players hand.
     */
    public void createCrib() {
        int counter = 0;

        while(counter < 2) {
            String p1Key = (String) playerOneCards.keySet().toArray()[counter];
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
}
