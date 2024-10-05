package de.ifheroes.core;

import java.util.UUID;

import org.bukkit.entity.Player;

import de.ifheroes.core.profile.HeroProfile;
import de.ifheroes.core.warehouse.Warehouse;

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
	HeroProfile getProfile(Player player);
	
	/*
	 * This Method creates a HeroProfile based on the received JsonObject/JsonString from the Warehouse
	 * 
	 * @params uuid is the key in the warehouse
	 * 
	 * @returns HeroProfile interface
	 */
	HeroProfile getProfile(UUID uuid);
	HeroProfile newProfile(UUID uuid, String name);
	
	void setWarehouse(Warehouse warehouse);
}
