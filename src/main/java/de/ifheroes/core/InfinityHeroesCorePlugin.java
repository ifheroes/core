package de.ifheroes.core;

import org.bukkit.plugin.java.JavaPlugin;

public class InfinityHeroesCorePlugin extends JavaPlugin{

	private static InfinityHeroesCoreAPI api;
	
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
