package de.ifheroes.core.profile.levelstructur.advanced;

import de.ifheroes.core.profile.types.HeroProfileLanguage;

/**
 * The AdvancedData interface defines the contract for managing advanced-level 
 * data within a hero's profile
 */
public interface AdvancedData {

    /**
     * Retrieves the language setting associated with the hero's profile.
     *
     * @return The HeroProfileLanguage representing the current language setting.
     */
    public HeroProfileLanguage getLanguage();

    /**
     * Sets the language for the hero's profile.
     *
     * @param language The HeroProfileLanguage to be set as the current language.
     */
    public void setLanguage(HeroProfileLanguage language);
}
