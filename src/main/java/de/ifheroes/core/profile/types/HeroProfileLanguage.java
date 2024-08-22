package de.ifheroes.core.profile.types;


/**
 * The HeroProfileLanguage enum represents the available languages for a hero's profile.
 * Each language is associated with its full name as a string.
 */
public enum HeroProfileLanguage {

    DE("Deutsch"), 
    EN("English");

    private String fullName;

    /**
     * Constructor for the HeroProfileLanguage enum.
     * Associates the enum constant with its full name.
     * 
     * @param fullName The full name of the language.
     */
    private HeroProfileLanguage(String fullName) {
        this.fullName = fullName;
    }

    /**
     * Retrieves the full name of the language.
     * 
     * @return The full name of the language as a String.
     */
    public String getFullName() {
        return this.fullName;
    }
}