package it.unibo.deathnote;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Test;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import it.unibo.deathnote.api.DeathNote;
import it.unibo.deathnote.impl.DeathNoteImpl;

class TestDeathNote {
    private static final int TESTRULEMIN = 0;
    private static final int TESTRULEMAX = 0;
    private static final long WAIT = 100;
    private static final long WAIT2 = 6100;

    @Test
    void testGetRule() {
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

    @SuppressFBWarnings(value = "DCN", justification = "Catching NPE intentionally for test coverage")
    @Test
    void testWriteName() {
        final DeathNote deathTest = new DeathNoteImpl();
        try {
            deathTest.writeName(null);
        } catch (final NullPointerException e) { // NOPMD
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
    }

    @Test
    void testWriteDeathCause() {
        final DeathNote deathTest = new DeathNoteImpl();
        try {
            deathTest.writeDeathCause("Not working");
        } catch (final IllegalStateException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
        deathTest.writeName("Marco");
        try {
            deathTest.writeDeathCause(null);
        } catch (final IllegalStateException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
    }

    @Test
    void testWriteDetails() {
        final DeathNote deathTest = new DeathNoteImpl();
        try {
            deathTest.writeDetails("Not working");
        } catch (final IllegalStateException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
        deathTest.writeName("Marco");
        try {
            deathTest.writeDetails(null);
        } catch (final IllegalStateException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
    }

    @Test
    void testGetDeathCause() {
        final DeathNote deathTest = new DeathNoteImpl();
        deathTest.writeName("Luigi");
        deathTest.writeName("Piero");
        try {
            deathTest.getDeathCause("Daniele");
        } catch (final IllegalArgumentException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
    }

    @Test
    void testGetDeathDetails() {
        final DeathNote deathTest = new DeathNoteImpl();
        deathTest.writeName("Luigi");
        deathTest.writeName("Piero");
        try {
            deathTest.getDeathDetails("Daniele");
        } catch (final IllegalArgumentException e) {
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
    }

    @Test
    void testDeathNoteRules() {
        for (final String rulesIndex : DeathNote.RULES) {
            assertNotNull(rulesIndex);
            assertFalse(rulesIndex.isBlank());
        }
    }

    @Test
    void testDeathNoteNames() {
        final DeathNote deathTest = new DeathNoteImpl();
        deathTest.writeName("Enzo");
        deathTest.writeName("Luca");
        final String savedName = "Giancarlo";
        assertFalse(deathTest.isNameWritten(savedName));
        deathTest.writeName(savedName);
        assertTrue(deathTest.isNameWritten(savedName));
        assertFalse(deathTest.isNameWritten("Gianpiero"));
        deathTest.writeName("");
        assertFalse(deathTest.isNameWritten(""));
    }

    @Test
    void testCauseOfDeathTime() throws InterruptedException {
        final DeathNote deathTest = new DeathNoteImpl();
        try {
            deathTest.writeDeathCause("no name");
            fail("IllegalStateException not thrown");
        } catch (final IllegalStateException e) {
            final String testName = "Giovanni";
            final String testCause = "karting accident";
            deathTest.writeName("Gigi");
            assertEquals("heart attack", deathTest.getDeathCause("Gigi"));
            deathTest.writeName(testName);
            assertTrue(deathTest.writeDeathCause(testCause));
            assertEquals(testCause, deathTest.getDeathCause(testName));
            Thread.sleep(WAIT);
            assertFalse(deathTest.writeDeathCause("car accident"));
            assertEquals(testCause, deathTest.getDeathCause(testName));
        }
    }

    @Test
    void testDetailsOfDeathTime() throws InterruptedException {
        final DeathNote deathTest = new DeathNoteImpl();
        try {
            deathTest.writeDeathCause("no name");
            fail("IllegalStateException not thrown");
        } catch (final IllegalStateException e) {
            final String testName = "Giovanna";
            final String testName1 = "Andrea";
            final String testdetails = "ran for too long";
            deathTest.writeName(testName);
            assertTrue(deathTest.getDeathDetails(testName).isEmpty());
            assertTrue(deathTest.writeDetails(testdetails));
            assertEquals(testdetails, deathTest.getDeathDetails(testName));
            deathTest.writeName(testName1);
            Thread.sleep(WAIT2);
            assertFalse(deathTest.writeDetails("car accident"));
            assertTrue(deathTest.getDeathDetails(testName1).isEmpty());
        }
    }
}
