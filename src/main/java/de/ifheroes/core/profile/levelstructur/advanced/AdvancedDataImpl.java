package de.ifheroes.core.profile.levelstructur.advanced;

import java.util.UUID;

import de.ifheroes.core.profile.events.EventBound;
import de.ifheroes.core.profile.types.HeroProfileLanguage;
import de.ifheroes.core.warehouse.Section;

/**
 * The AdvancedDataImpl class is a concrete implementation of the AdvancedData interface.
 * It manages advanced-level data for a HeroProfile.
 */
public class AdvancedDataImpl extends EventBound implements AdvancedData {

    private HeroProfileLanguage language;
    private UUID uuid;
    
    /**
     * Default constructor.
     * Initializes the language to English (EN) by default.
     */
    public AdvancedDataImpl(UUID uuid) {
        language = HeroProfileLanguage.EN;
        this.uuid = uuid;
    }

    /**
     * Parameterized constructor.
     * Initializes the language to the specified value.
     *
     * @param language The HeroProfileLanguage to be set for the profile.
     */
    public AdvancedDataImpl(HeroProfileLanguage language) {
    	this.language = language;
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
        callEvent(uuid, Section.ADVANCEDDATA, "language", language.toString());
    }
    
    public void setUUID(UUID uuid) {
    	this.uuid = uuid;
    }
}
