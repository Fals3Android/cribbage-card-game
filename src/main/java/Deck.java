import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Deck {
    private List<String> defaultRanks = Arrays.asList("ace", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "joker", "king", "queen");
    private ArrayList<String> ranks = new ArrayList<String>(defaultRanks);
    private Map<String, Integer> cards = new LinkedHashMap<String, Integer>();

    Deck() {
        setCards();
    }

    public Map<String, Integer> getCards() {
        return cards;
    }

    private void setCards() {
        String Suits[] = {"clubs:", "diamonds:", "hearts:", "spades:"};

        for(String suit : Suits) {
            AtomicInteger currentValue = new AtomicInteger(1);

            ranks.forEach((rank) -> {
                if(currentValue.get() == 10) {
                    cards.put(suit.concat(rank), currentValue.get());
                } else {
                    cards.put(suit.concat(rank), currentValue.getAndIncrement());
                }
            });

            currentValue.set(1);
        }
    }

    public ArrayList<String> getRanks() {
        return ranks;
    }

    public void setRanks(List<String> defaultRanks) {
        ranks = new ArrayList<String>(defaultRanks);
    }

    public void shuffle() {
        Map<String, Integer> shuffledCards = new LinkedHashMap<String, Integer>();

        while(cards.size() != 0) {
            int index = (int) (cards.size() * Math.random());
            String currentKey = (String) cards.keySet().toArray()[index];
            int currentValue =  (int) cards.get(currentKey);
            cards.remove(currentKey);
            shuffledCards.put(currentKey, currentValue);
        }
        cards = shuffledCards;
    }
}
