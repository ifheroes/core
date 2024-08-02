package de.ifheroes.core;

import org.bukkit.entity.Player;

import de.ifheroes.core.profile.HeroProfile;

/*
 * Represents the API of the InfinityHeroesCore
 * Access to plugin accessible informations
 */
public interface InfinityHeroesCoreAPI {

	public HeroProfile getProfile(Player player);
	public HeroProfile getProfile(String uuid);
	
}
