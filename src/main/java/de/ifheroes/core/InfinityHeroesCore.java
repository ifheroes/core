package de.ifheroes.core;

import org.bukkit.plugin.java.JavaPlugin;

import de.ifheroes.core.profile.HeroProfileKey;
import de.ifheroes.core.profile.types.HeroProfileLanguage;

/*
 * Representing the initial class loaded by Bukkit
 */
public class InfinityHeroesCore extends JavaPlugin{

	/*
	 * The current API
	 * 
	 * This approach might be changed in the future, but shouldn't have major effect on relating interfaces
	 */
	private static final InfinityHeroesCoreAPI api = new CoreAPI();
	
	/*
	 * This method returns the current InfinityHeroesCoreAPI (interface)
	 */
	static final InfinityHeroesCoreAPI getAPI() {
		return api;
	}
	
	
	@Override
	public void onEnable() {
		HeroProfileLanguage profileLanguage = getAPI().getProfile("hahaha").get(HeroProfileKey.LANGUAGE, HeroProfileLanguage.class);
		System.out.println(profileLanguage);
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		
		super.onDisable();
	}
}
