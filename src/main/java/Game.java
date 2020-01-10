import java.util.*;

//Gameplay
//    Shuffle()
//    Deal()
//    Create_Crib()
//    Round_Score()
// Result -> player score by player: player one 105, player two 98, player one wins!

public class Game {
    Deck defaultDeck;
    public Map<String, Integer> cards = new LinkedHashMap<String, Integer>();
    public Map<String, Integer> playerOneCards = new LinkedHashMap<String, Integer>();
    public Map<String, Integer> playerTwoCards = new LinkedHashMap<String, Integer>();
    public Map<String, Integer> crib = new LinkedHashMap<String, Integer>();

    Game(Deck deck) {
        defaultDeck = deck;
        cards.putAll(deck.getCards());
    }

    public void deal() {
        int counter = 0;
        int maxCardsPerDeal = (6 * 2);

        while(counter != maxCardsPerDeal) {
            String p1Key = (String) cards.keySet().toArray()[counter];
            int p1Value =  (int) cards.get(p1Key);
            this.playerOneCards.put(p1Key, p1Value);
            cards.remove(counter);
            counter += 1;

            String p2Key = (String) cards.keySet().toArray()[counter];
            int p2Value =  (int) cards.get(p2Key);
            playerTwoCards.put(p2Key, p2Value);
            cards.remove(counter);
            counter += 1;
        }
    }

    public void createCrib() {
        int counter = 0;

        while(counter < 2) {
            String p1Key = (String) playerOneCards.keySet().toArray()[0];
            int p1Value =  (int) playerOneCards.get(p1Key);
            playerOneCards.remove(0);
            crib.put(p1Key, p1Value);
            counter++;
        }

        counter = 0;

        while(counter < 2) {
            String p2Key = (String) playerTwoCards.keySet().toArray()[0];
            int p2Value =  (int) playerTwoCards.get(p2Key);
            playerTwoCards.remove(0);
            crib.put(p2Key, p2Value);
            counter++;
        }
    }
}
