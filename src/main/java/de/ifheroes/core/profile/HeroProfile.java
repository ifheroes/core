package de.ifheroes.core.profile;

import java.util.Optional;

/*
 * Represents the player profile
 */
public interface HeroProfile {

	
	
	/*
	 * Gets the players data from the cloud
	 * 
	 * @params
	 * key is the key to the data (just like in a map)
	 * type is the return type
	 */
	<C> Optional<C> get(HeroProfileKey key, Class<C> type);
	<C> Optional<C> get(String key, Class<C> type);
	
	/*
	 * Sets the players data to the cloud
	 * 
	 * @params
	 * key is the key to the data (just like in a map)
	 * type is the data type
	 * data is the data being set
	 * 
	 */
	<C> void set(HeroProfileKey key, C data);
	<C> void set(String key, C data);
}
