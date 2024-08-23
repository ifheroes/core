package de.ifheroes.core;

import org.bukkit.plugin.java.JavaPlugin;

public class InfinityHeroesCorePlugin extends JavaPlugin{

	private static InfinityHeroesCoreAPI api = new InfinityHeroesCoreAPIImpl();
	static final InfinityHeroesCoreAPI getAPI() {
		return api;
	}
	
	@Override
	public void onEnable() {
		
		//TODO: set the warehouse in api
		
		super.onEnable();
	}
	
	@Override
	public void onDisable() {
		
		super.onDisable();
	}
}
