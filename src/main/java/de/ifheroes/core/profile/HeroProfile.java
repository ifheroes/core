package de.ifheroes.core.profile;

import java.util.UUID;

import de.ifheroes.core.profile.levelstructur.plugin.PluginData;
import de.ifheroes.core.profile.types.HeroProfileLanguage;

/*
 * Represents the player profile, encapsulating various levels of data such as basic, advanced, and plugin-specific data.
 */
public interface HeroProfile {
	
	
	/**
	 * Retrieves the unique identifier (UUID) for the player.
	 *
	 * @return the UUID of the player.
	 */
	public UUID getUUID();
	
	/**
	 * Retrieves the name of the player.
	 *
	 * @return the name of the player as a String.
	 */
	public String getName();
	
	/**
	 * Sets the name of the player.
	 *
	 * @param name the new name to be assigned to the player.
	 */
	public void setName(String name);
	
	
	
	
	
	/**
	 * Advanced Data Level
	 *
	 * Retrieves the language setting associated with the player's profile.
	 *
	 * @return the player's language as a HeroProfileLanguage object.
	 */
	public HeroProfileLanguage getLanguage();
	public void setLanguage(HeroProfileLanguage heroProfileLanguage);
	
	
	
	
	
	
	/**
	 * Plugin Data
	 *
	 * Retrieves the plugin-specific data associated with the player's profile.
	 *
	 * @return the PluginData object containing custom plugin data.
	 */
	public PluginData getPluginData();
	
	/**
	 * 
	 * Converte the Hero Profile to String data
	 * 
	 * @return A string of the HeroProfile
	 */
	public String toString();
}
