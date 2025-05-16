package de.ifheroes.core;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

<<<<<<< Updated upstream:src/main/java/de/ifheroes/core/InfinityHeroesCorePlugin.java
import de.ifheroes.core.Logger.LogLevel;
import de.ifheroes.core.warehouse.ProfileRegister;
import de.ifheroes.core.warehouse.WarehouseImpl;
=======
import de.ifheroes.core.data.warehouse.ProfileRegister;
import de.ifheroes.core.data.warehouse.WarehouseImpl;
import de.ifheroes.core.data.warehouse.exceptions.WarehouseNotInitializedException;
import de.ifheroes.core.helper.gui.listeners.GUIInteract;
>>>>>>> Stashed changes:src/main/java/de/ifheroes/core/InfinityHeroesCore.java

public class InfinityHeroesCorePlugin extends JavaPlugin {

	private static final InfinityHeroesCoreAPI api = new WarehouseAPIBridge();

	public static InfinityHeroesCoreAPI getAPI() {
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
<<<<<<< Updated upstream:src/main/java/de/ifheroes/core/InfinityHeroesCorePlugin.java
		
		logger.info("Checking Warehouse URL and token...");
		if (url.equalsIgnoreCase("") || !isURLandTokenValid(url+"/?checkauth", key, 1000)) {
			logger.error("Warehouse couldn't be initialized");
			disablePlugin(this);
			return;
		}
		
		api.setWarehouse(new WarehouseImpl(url, key));
		new Logger(LogLevel.INFO).info("Warehouse has been initialized");
		
=======

		WarehouseLogin warehouseLogin = getWarehouseLogin();
		if (!isWarehouseAvailable(warehouseLogin))
			disablePlugin(this); // Change this to implement a backup solution
		api.setWarehouse(new WarehouseImpl(warehouseLogin.url, warehouseLogin.token));

>>>>>>> Stashed changes:src/main/java/de/ifheroes/core/InfinityHeroesCore.java
		/*
		 * Load ProfileRegister
		 */
		Bukkit.getPluginManager().registerEvents(new ProfileRegister(), this);
<<<<<<< Updated upstream:src/main/java/de/ifheroes/core/InfinityHeroesCorePlugin.java
=======

		/*
		 * Helpers -> GUIManager
		 */
		Bukkit.getPluginManager().registerEvents(new GUIInteract(), this);
		
		
		try {
			System.out.println((api.getWarehouse() != null)+ " warehouse");
		} catch (WarehouseNotInitializedException e) {
			e.printStackTrace();
		}
		
>>>>>>> Stashed changes:src/main/java/de/ifheroes/core/InfinityHeroesCore.java
	}

	private void disablePlugin(Plugin plugin) {
		Bukkit.getPluginManager().disablePlugin(plugin);
	}

	private boolean isURLandTokenValid(String url, String token, int timeout) {
		try {
			HttpURLConnection connection = (HttpURLConnection) URI.create(url).toURL().openConnection();
			connection.setRequestMethod("GET");
			connection.setConnectTimeout(timeout);
			connection.setReadTimeout(timeout);
			connection.setRequestProperty("Authorization", token);
			int responseCode = connection.getResponseCode();
			return (200 <= responseCode && responseCode <= 399);
		} catch (IOException e) {
			return false;
		}
	}
<<<<<<< Updated upstream:src/main/java/de/ifheroes/core/InfinityHeroesCorePlugin.java
=======

	private WarehouseLogin getWarehouseLogin() {
		getConfig().addDefault("warehouseurl", "");
		getConfig().addDefault("bearertoken", "");

		getConfig().options().copyDefaults(true);
		saveConfig();

		return new WarehouseLogin(getConfig().getString("warehouseurl"), getConfig().getString("bearertoken"));
	}

	private boolean isWarehouseAvailable(WarehouseLogin warehouseLogin) {
		Bukkit.getLogger().info("Checking Warehouse URL and token...");
		if (warehouseLogin.url.equalsIgnoreCase("")
				|| !isURLandTokenValid(warehouseLogin.url + "/?checkauth", warehouseLogin.token, 1000)) {
			Bukkit.getLogger().warning("Warehouse couldn't be initialized");
			return false;
		}
		return true;
	}

	private record WarehouseLogin(String url, String token) {}
>>>>>>> Stashed changes:src/main/java/de/ifheroes/core/InfinityHeroesCore.java
}
