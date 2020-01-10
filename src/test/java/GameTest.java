import org.junit.jupiter.api.Test;

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
}