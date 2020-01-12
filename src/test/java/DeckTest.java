import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

class DeckTest {
    private List<String> mockDefaultDeckOrder = Arrays.asList(
            "clubs:ace",
            "clubs:two",
            "clubs:three",
            "clubs:four",
            "clubs:five",
            "clubs:six",
            "clubs:seven",
            "clubs:eight",
            "clubs:nine",
            "clubs:ten",
            "clubs:joker",
            "clubs:king",
            "clubs:queen",
            "diamonds:ace",
            "diamonds:two",
            "diamonds:three",
            "diamonds:four",
            "diamonds:five",
            "diamonds:six",
            "diamonds:seven",
            "diamonds:eight",
            "diamonds:nine",
            "diamonds:ten",
            "diamonds:joker",
            "diamonds:king",
            "diamonds:queen",
            "hearts:ace",
            "hearts:two",
            "hearts:three",
            "hearts:four",
            "hearts:five",
            "hearts:six",
            "hearts:seven",
            "hearts:eight",
            "hearts:nine",
            "hearts:ten",
            "hearts:joker",
            "hearts:king",
            "hearts:queen",
            "spades:ace",
            "spades:two",
            "spades:three",
            "spades:four",
            "spades:five",
            "spades:six",
            "spades:seven",
            "spades:eight",
            "spades:nine",
            "spades:ten",
            "spades:joker",
            "spades:king",
            "spades:queen"
    );

    @Test
    public void shouldContainFiftyTwoCardsByDefault() {
        Deck instance = new Deck();
        assertEquals(52, instance.getCards().size());
    }

    @Test
    public void shouldContainThirteenRanksByDefault() {
        Deck instance = new Deck();
        assertEquals(13, instance.getRanks().size());
    }

    @Test
    public void shouldContainAnyNumberRanksTheUserChooses() {
        Deck instance = new Deck();
        List<String> stubRanks = Arrays.asList("ace");
        instance.setRanks(stubRanks);
        assertEquals(1, instance.getRanks().size());
    }

    @Test
    public void shouldContainAnyNumberSuitsTheUserChooses() {
        Deck instance = new Deck();
        List<String> stubSuits = Arrays.asList("clubs");
        instance.setSuits(stubSuits);
        assertEquals("clubs", instance.getSuits().get(0));
    }

    @Test
    public void shouldCreateDeckWithCardsUserSpecifies() {
        Deck instance = new Deck(Arrays.asList("two"), Arrays.asList("clubs"));
        assertEquals(1, instance.getCards().size());
        assertEquals(1, instance.getCards().get("clubs:two"));
    }

    @Test
    public void shouldScoreEachCardInTheTraditionalManner() {
        Deck instance = new Deck();
        Map stubDeck = instance.getCards();
        AtomicInteger total = new AtomicInteger();
        stubDeck.forEach((k,v) -> total.addAndGet((Integer) v));
        assertEquals(340, total.intValue());
    }

    @Test
    public void shouldReturnASortedDefaultDeck() {
        Deck instance = new Deck();
        Map<String, Integer> stubDeck = instance.getCards();

        boolean doesEqualExactly = true;
        int counter = 0;

        for(String it : stubDeck.keySet()) {
            if(!it.equals(mockDefaultDeckOrder.get(counter))) {
                doesEqualExactly = false;
                break;
            }
            counter++;
        }

        assertTrue(doesEqualExactly);
    }

    @Test
    public void shouldReturnAShuffledDeck() {
        Deck instance = new Deck();
        instance.shuffle();
        Map<String, Integer> stubDeck = instance.getCards();

        int matches = 0;
        int counter = 0;

        for(String it : stubDeck.keySet()) {
            if(it.equals(mockDefaultDeckOrder.get(counter))) {
                matches++;
            }
            counter++;
        }

        boolean result = matches < 52;
        assertTrue(result);
    }
}