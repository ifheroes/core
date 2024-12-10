package de.ifheroes.core;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import de.ifheroes.core.Logger.LogLevel;
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
		Logger logger = new Logger(LogLevel.INFO);
		
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
		
		logger.info("Checking Warehouse URL and token...");
		if (url.equalsIgnoreCase("") || !isURLandTokenValid(url, key, 1000)) {
			logger.error("Warehouse couldn't be initialized");
			disablePlugin(this);
			return;
		}
		
		api.setWarehouse(new WarehouseImpl(url, key));
		new Logger(LogLevel.INFO).info("Warehouse has been initialized");
		
		/*
		 * Load ProfileRegister
		 */
		Bukkit.getPluginManager().registerEvents(new ProfileRegister(), this);
	}

	private void disablePlugin(Plugin plugin) {
		Bukkit.getPluginManager().disablePlugin(plugin);
	}

	private boolean isURLandTokenValid(String url, String token, int timeout) {
		try {
			HttpURLConnection connection = (HttpURLConnection) URI.create(url).toURL().openConnection();
			connection.setRequestMethod("HEAD");
			connection.setConnectTimeout(timeout);
			connection.setReadTimeout(timeout);
			connection.setRequestProperty("Authorization", token);
			int responseCode = connection.getResponseCode();
			return (200 <= responseCode && responseCode <= 399);
		} catch (IOException e) {
			return false;
		}
	}
}
