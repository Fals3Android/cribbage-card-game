import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    @Test
    public void shouldDealCardsForATwoPlayerGame() {
        Deck defaultDeck = new Deck();
        Game instance = new Game(defaultDeck);
        instance.deal();
        assertEquals(6, instance.playerOneCards.size());
        assertEquals(6, instance.playerTwoCards.size());
    }

    @Test
    public void shouldCreateAValidCrib() {
        Deck defaultDeck = new Deck();
        Game instance = new Game(defaultDeck);
        instance.deal();
        instance.createCrib();
        assertEquals(4, instance.crib.size());
        assertEquals(4, instance.playerOneCards.size());
        assertEquals(4, instance.playerTwoCards.size());
    }

    @Test
    public void shouldCutDeckAndRevealTopCard() {
        Deck defaultDeck = new Deck(Arrays.asList("ace", "two", "three"), Arrays.asList("clubs"));
        Game instance = new Game(defaultDeck);

        instance.cutDeck();
        Object[] cardKeys = instance.cards.keySet().toArray();

        assertEquals("clubs:two", cardKeys[0]);
        assertEquals("clubs:ace", cardKeys[1]);
        assertEquals("clubs:three", cardKeys[2]);
    }
}