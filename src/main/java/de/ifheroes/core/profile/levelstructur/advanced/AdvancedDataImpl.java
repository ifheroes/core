package de.ifheroes.core.profile.levelstructur.advanced;

import de.ifheroes.core.profile.types.HeroProfileLanguage;

/**
 * The AdvancedDataImpl class is a concrete implementation of the AdvancedData interface.
 * It manages advanced-level data for a HeroProfile.
 */
public class AdvancedDataImpl implements AdvancedData {

    private HeroProfileLanguage language;

    /**
     * Default constructor.
     * Initializes the language to English (EN) by default.
     */
    public AdvancedDataImpl() {
        setLanguage(HeroProfileLanguage.EN);
    }

    /**
     * Parameterized constructor.
     * Initializes the language to the specified value.
     *
     * @param language The HeroProfileLanguage to be set for the profile.
     */
    public AdvancedDataImpl(HeroProfileLanguage language) {
        setLanguage(language);
    }
    
    /**
     * Retrieves the language setting associated with the hero's profile.
     *
     * @return The current HeroProfileLanguage.
     */
    @Override
    public HeroProfileLanguage getLanguage() {
        return language;
    }

    /**
     * Sets the language for the hero's profile.
     *
     * @param language The HeroProfileLanguage to be set as the current language.
     */
    @Override
    public void setLanguage(HeroProfileLanguage language) {
        this.language = language;
    }
}
