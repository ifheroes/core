package de.ifheroes.core.profile;

import de.ifheroes.core.profile.types.HeroProfileType;

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
	<C extends HeroProfileType> C get(HeroProfileKey key, Class<C> type);
	<C> C get(String key, C type);
	
	/*
	 * Gets the players data from the local server
	 * 
	 * @params
	 * key is the key to the data (just like in a map)
	 * type is the return type
	 */
	<C> C getLocal(HeroProfileKey key, C type);
	<C> C getLocal(String key, C type);
	
	
	/*
	 * Sets the players data to the cloud
	 * 
	 * @params
	 * key is the key to the data (just like in a map)
	 * type is the data type
	 * data is the data being set
	 */
	<C> void set(HeroProfileKey key, C type, C data);
	<C> void set(String key, C type, C data);
	
	/*
	 * Sets the players data from the local server
	 * 
	 * @params
	 * key is the key to the data (just like in a map)
	 * type is the data type
	 * data is the data being set
	 */
	<C> void setLocal(HeroProfileKey key, C type, C data);
	<C> void setLocal(String key, C type, C data);
}
