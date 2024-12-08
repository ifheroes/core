package de.ifheroes.core;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import de.ifheroes.core.Logger.LogLevel;
import de.ifheroes.core.profile.HeroProfile;
import de.ifheroes.core.warehouse.ProfileRegister;
import de.ifheroes.core.warehouse.WarehouseImpl;

public class InfinityHeroesCorePlugin extends JavaPlugin {

	private static InfinityHeroesCoreAPI api = new InfinityHeroesCoreAPIImpl();
	
	public static final InfinityHeroesCoreAPI getAPI() {
		return api;
	}
	
	@Override
	public void onEnable() {
		super.onEnable();
		
		/*
		 * Config
		 */
		getConfig().addDefault("warehouseurl", "");
		getConfig().addDefault("bearertoken", "");
		
		getConfig().options().copyDefaults(true);
		saveConfig();
		
		String url = getConfig().getString("warehouseurl");
		String key = getConfig().getString("bearertoken");

		/*
		 * Warehouse
		 */
		
		if(url.equalsIgnoreCase("") || url == null) {
			new Logger(LogLevel.ERROR).error("Warehouse couldn't be initialized");
			api = null;
			return;
		}
		
		//TODO: Check URL for validity
		
		api.setWarehouse(new WarehouseImpl(url, key));
		new Logger(LogLevel.INFO).info("Warehouse has been initialized");
		
		/*
		 * Load ProfileRegister
		 */
		Bukkit.getPluginManager().registerEvents(new ProfileRegister(), this);
	}
}
