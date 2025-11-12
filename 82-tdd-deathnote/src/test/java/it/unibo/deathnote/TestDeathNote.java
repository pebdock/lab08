package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {
    private static final int TESTRULEMIN = 0;
    private static final int TESTRULEMAX = 0;

    @Test
    void testGetRuleExceptions() {
        final DeathNote deathTest = new DeathNoteImpl();
        try {
            deathTest.getRule(TESTRULEMIN);
        } catch (final IllegalArgumentException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
        try {
            deathTest.getRule(TESTRULEMAX);
        } catch (final IllegalArgumentException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
    }

    
}
