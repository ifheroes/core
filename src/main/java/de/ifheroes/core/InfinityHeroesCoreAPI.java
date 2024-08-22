package de.ifheroes.core;

import org.bukkit.entity.Player;

import de.ifheroes.core.profile.HeroProfile;

/*
 * Represents the API of the InfinityHeroesCore
 * Access to plugin accessible informations
 */
public interface InfinityHeroesCoreAPI {
	
	/*
	 * This Method refers to the getProfile(String uuid) method.
	 * 
	 * @params player is being used to hand over the uuid to the other method
	 * 
	 * @returns HeroProfile
	 */
	public HeroProfile getProfile(Player player);
	
	/*
	 * This Method creates a HeroProfile based on the received JsonObject/JsonString from the Warehouse
	 * 
	 * @params uuid is the key in the warehouse
	 * 
	 * @returns HeroProfile interface
	 */
	public HeroProfile getProfile(String uuid);
	
}
