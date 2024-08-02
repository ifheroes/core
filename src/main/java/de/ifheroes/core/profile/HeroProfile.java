package de.ifheroes.core.profile;

import java.util.Optional;

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
	<C extends HeroProfileType> Optional<C> get(HeroProfileKey key, Class<C> type);
	<C extends HeroProfileType> Optional<C> get(String key, C type);
	<C> Optional<C> getGeneric(HeroProfileKey key, Class<C> type);
	<C> Optional<C> getGeneric(String key, C type);
	
	/*
	 * Gets the players data from the local server
	 * 
	 * @params
	 * key is the key to the data (just like in a map)
	 * type is the return type
	 */
	<C extends HeroProfileType> Optional<C> getLocal(HeroProfileKey key, C type);
	<C extends HeroProfileType> Optional<C> getLocal(String key, C type);
	<C> Optional<C> getLocalGeneric(HeroProfileKey key, C type);
	<C> Optional<C> getLocalGeneric(String key, C type);
	
	
	/*
	 * Sets the players data to the cloud
	 * 
	 * @params
	 * key is the key to the data (just like in a map)
	 * type is the data type
	 * data is the data being set
	 * 
	 */
	<C extends HeroProfileType> void set(HeroProfileKey key, C type, C data);
	<C extends HeroProfileType> void set(String key, C type, C data);
	<C> void setGeneric(HeroProfileKey key, C type, C data);
	<C> void setGeneric(String key, C type, C data);
	
	/*
	 * Sets the players data from the local server
	 * 
	 * @params
	 * key is the key to the data (just like in a map)
	 * type is the data type
	 * data is the data being set
	 */
	<C extends HeroProfileType> void setLocal(HeroProfileKey key, C type, C data);
	<C extends HeroProfileType> void setLocal(String key, C type, C data);
	<C> void setLocalGeneric(HeroProfileKey key, C type, C data);
	<C> void setLocalGeneric(String key, C type, C data);
}
