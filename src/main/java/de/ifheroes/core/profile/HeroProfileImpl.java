package de.ifheroes.core.profile;

import java.util.UUID;

import de.ifheroes.core.profile.levelstructur.advanced.AdvancedData;
import de.ifheroes.core.profile.levelstructur.advanced.AdvancedDataImpl;
import de.ifheroes.core.profile.levelstructur.basic.BasicData;
import de.ifheroes.core.profile.levelstructur.basic.BasicDataImpl;
import de.ifheroes.core.profile.levelstructur.plugin.PluginData;
import de.ifheroes.core.profile.levelstructur.plugin.PluginDataImpl;
import de.ifheroes.core.profile.types.HeroProfileLanguage;

/**
 * The HeroProfileImpl class provides a concrete implementation of the HeroProfile interface.
 * It encapsulates and manages the different levels of data associated with a player's profile:
 * - Basic data (e.g., UUID, name)
 * - Advanced data (e.g., language settings)
 * - Plugin-specific data
 */

public class HeroProfileImpl implements HeroProfile{
	
	 /*
     * Data Level
     * 
     * These fields represent the various levels of data managed by this class.
     */
	private BasicDataImpl basicData;
	private AdvancedDataImpl advancedData;
	private PluginDataImpl pluginData;
	
	 /**
     * Constructs a HeroProfileImpl with the specified basic data.
     * Initializes the advanced data and plugin data with default implementations.
     *
     * @param basicData The basic data associated with the player's profile.
     */
	public HeroProfileImpl(BasicDataImpl basicData) {
		setBasicData(basicData);
		setAdvancedData(new AdvancedDataImpl());
		setPluginData(new PluginDataImpl());
	}
	
	/*
     * Basic Data Level
     * 
     * Methods and accessors for handling the basic data level.
     */

    /**
     * Sets the basic data for this profile.
     *
     * @param basicData The basic data to be associated with this profile.
     */
	private void setBasicData(BasicDataImpl basicData) {
		this.basicData = basicData;
	}

	/**
     * Retrieves the basic data associated with this profile.
     *
     * @return The basic data of the profile.
     */
    private BasicData getBasicData() {
        return this.basicData;
    }

    /**
     * Retrieves the UUID from the basic data.
     *
     * @return The UUID of the player.
     */
    @Override
    public UUID getUUID() {
        return getBasicData().getUUID();
    }

    /**
     * Retrieves the name from the basic data.
     *
     * @return The name of the player.
     */
    @Override
    public String getName() {
        return getBasicData().getName();
    }

    /**
     * Sets the name in the basic data.
     *
     * @param name The new name to be assigned to the player.
     */
    @Override
    public void setName(String name) {
        getBasicData().setName(name);
    }
    
    /*
     * Advanced Data Level
     * 
     * Methods and accessors for handling the advanced data level.
     */

    /**
     * Sets the advanced data for this profile.
     *
     * @param advancedData The advanced data to be associated with this profile.
     */
    private void setAdvancedData(AdvancedDataImpl advancedData) {
        this.advancedData = advancedData;
    }

    /**
     * Retrieves the advanced data associated with this profile.
     *
     * @return The advanced data of the profile.
     */
    private AdvancedData getAdvancedData() {
        return this.advancedData;
    }

    /**
     * Retrieves the language setting from the advanced data.
     *
     * @return The language of the player as a HeroProfileLanguage object.
     */
    @Override
    public HeroProfileLanguage getLanguage() {
        return getAdvancedData().getLanguage();
    }
    
    /*
     * Plugin Data
     * 
     * Methods and accessors for handling the plugin-specific data.
     */

    /**
     * Retrieves the plugin-specific data associated with this profile.
     *
     * @return The plugin data of the profile.
     */
    @Override
    public PluginData getPluginData() {
        return this.pluginData;
    }

    /**
     * Sets the plugin data for this profile.
     *
     * @param pluginData The plugin data to be associated with this profile.
     */
    private void setPluginData(PluginDataImpl pluginData) {
        this.pluginData = pluginData;
    }
}
