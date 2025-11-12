package it.unibo.deathnote.impl;

import java.util.NavigableMap;
import java.util.Objects;
import java.util.TreeMap;

import it.unibo.deathnote.api.DeathNote;

/**
 * A DeathNote is a special book intended to be used by Shinigamis (Death gods) to kill humans,
 * thereby extending their own lives. Each DeathNote has a set of rules that must be followed
 * in order to use it properly.
 */
public class DeathNoteImpl implements DeathNote {

    private static final double MAXMILLS = 40.0;
    private static final double MAXMILLS2 = 6040.0;
    // CHECKSTYLE:OFF
    // Using an hashmap to save names and death(cause and details)
    private final NavigableMap<String, Death> names = new TreeMap<>();
    // CHECKSTYLE:0N
    private double timePassed;

    /**
     * Returns the rule with the given number.
     *
     * @param ruleNumber the number of the rule to return. The first rule has number one
     * @return the rule with the given number
     * @throws IllegalArgumentException if the given rule number is smaller than 1 or larger
     *     than the number of rules
     */
    @Override
    public String getRule(final int ruleNumber) {
        try {
            return RULES.get(ruleNumber);
        } catch (final IndexOutOfBoundsException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * The human whose name is written in this DeathNote will die.
     *
     * @param name the name of the human to kill
     * @throws NullPointerException if the given name is null.
     */
    @Override
    public void writeName(final String name) {
        Objects.requireNonNull(name, "The name is not valid");
        names.put(name, new Death());
        timePassed = System.currentTimeMillis();
    }

    /**
     * If the cause of death is written within the next 40 milliseconds of writing the person's
     * name, it will happen.
     *
     * @param cause the cause of the human's death
     * @return true if the cause was written within 40 milliseconds, false otherwise
     * @throws IllegalStateException if there is no name written in this DeathNote,
     *     or the cause is null
     */
    @Override
    public boolean writeDeathCause(final String cause) {
        if (cause == null) {
            throw new IllegalStateException("The details are null");
        } else if (names.isEmpty()) {
            throw new IllegalStateException("Death Note is empty");
        } else {
            names.get(names.lastKey()).setDetails(cause);
            if ((System.currentTimeMillis() - timePassed) < MAXMILLS) {
                timePassed = System.currentTimeMillis();
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     * After writing the cause of death, details of the death should be written in the next
     * 6 seconds and 40 milliseconds.
     *
     * @param details the details of the human's death
     * @return true if the details were written within 6 seconds and 40 milliseconds, false otherwise
     * @throws IllegalStateException if there is no name written in this DeathNote,
     *     or the details are null
     */
    @Override
    public boolean writeDetails(final String details) {
        if (details == null) {
            throw new IllegalStateException("The details are null");
        } else if (names.isEmpty()) {
            throw new IllegalStateException("Death Note is empty");
        } else {
            names.get(names.lastKey()).setDetails(details);
            return (System.currentTimeMillis() - timePassed) < MAXMILLS2;
        }
    }

    /**
     * Provides the cause of death of the person with the given name.
     *
     * @param name the name of the person whose death cause to return
     * @return the death cause of the person with the given name.
     *     If the cause of death is not specified, the method will return "heart attack".
     * @throws IllegalArgumentException if the provider name is not written in this DeathNote
     */
    @Override
    public String getDeathCause(final String name) {
        if (!names.containsKey(name)) {
            throw new IllegalArgumentException("Name not found");
        } else if (names.get(name) == null || names.get(name).getCause().isEmpty()) {
            return "heart attack";
        } else {
            return names.get(name).getCause();
        }
    }

    /**
     * Provides the details of the death of the person with the given name.
     *
     * @param name the name of the person whose death cause to return
     * @return the death details of the person with the given name,
     *     or an empty string if no details have been provided.
     * @throws IllegalArgumentException if the provider name is not written in this DeathNote.
     */
    @Override
    public String getDeathDetails(final String name) {
        if (isNameWritten(name)) {
            return names.get(name).getDetails();
        } else {
            throw new IllegalArgumentException("The name is not written in the death note");
        }
    }

    /**
     * Checks if the given name is written in this DeathNote.
     *
     * @param name the name of the person
     * @return true if the given name is written in this DeathNote, false otherwise
     */
    @Override
    public boolean isNameWritten(final String name) {
        return names.containsKey(name);
    }

    static class Death {
        private String cause;
        private String details;

        public void setCause(final String cause) {
            this.cause = cause;
        }

        public void setDetails(final String details) {
            this.details = details;
        }

        public String getCause() {
            return cause;
        }

        public String getDetails() {
            return details;
        }
    }
}
