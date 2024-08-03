package de.ifheroes.core;

import org.bukkit.plugin.java.JavaPlugin;

/*
 * Representing the initial class loaded by Bukkit
 */
public class InfinityHeroesCorePlugin extends JavaPlugin{

	/*
	 * The current API
	 * 
	 * This approach might be changed in the future, but shouldn't have major effect on relating interfaces
	 */
	private static InfinityHeroesCoreAPI api;
	
	/*
	 * This method returns the current InfinityHeroesCoreAPI (interface)
	 */
	static final InfinityHeroesCoreAPI getAPI() {
		return api;
	}
	
	
	@Override
	public void onEnable() {
		
		//TODO: set the API
		
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		
		super.onDisable();
	}
}
