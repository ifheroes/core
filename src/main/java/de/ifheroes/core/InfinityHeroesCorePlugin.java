package de.ifheroes.core;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import de.ifheroes.core.data.warehouse.ProfileRegister;
import de.ifheroes.core.data.warehouse.Warehouse;
import de.ifheroes.core.data.warehouse.WarehouseImpl;
import de.ifheroes.core.data.warehouse.exceptions.WarehouseNotInitializedException;
import de.ifheroes.core.helper.gui.listeners.GUIInteract;

public class InfinityHeroesCorePlugin extends JavaPlugin {

	private static final InfinityHeroesCoreAPI api = new WarehouseAPIBridge();
	  
	  public static InfinityHeroesCoreAPI getAPI() {
	    return api;
	  }
	  
	  public void onEnable() {
	    super.onEnable();
	    WarehouseLogin warehouseLogin = getWarehouseLogin();
	    if (!isWarehouseAvailable(warehouseLogin))
	      disablePlugin((Plugin)this); 
	    api.setWarehouse((Warehouse)new WarehouseImpl(warehouseLogin.url, warehouseLogin.token));
	    Bukkit.getPluginManager().registerEvents((Listener)new ProfileRegister(), (Plugin)this);
	    Bukkit.getPluginManager().registerEvents((Listener)new GUIInteract(), (Plugin)this);
	    try {
	      System.out.println("" + ((api.getWarehouse() != null) ? 1 : 0) + " warehouse");
	    } catch (WarehouseNotInitializedException e) {
	      e.printStackTrace();
	    } 
	  }
	  
	  private void disablePlugin(Plugin plugin) {
	    Bukkit.getPluginManager().disablePlugin(plugin);
	  }
	  
	  private boolean isURLandTokenValid(String url, String token, int timeout) {
	    try {
	      HttpURLConnection connection = (HttpURLConnection)URI.create(url).toURL().openConnection();
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
	  
	  private WarehouseLogin getWarehouseLogin() {
	    getConfig().addDefault("warehouseurl", "");
	    getConfig().addDefault("bearertoken", "");
	    getConfig().options().copyDefaults(true);
	    saveConfig();
	    return new WarehouseLogin(getConfig().getString("warehouseurl"), getConfig().getString("bearertoken"));
	  }
	  
	  private boolean isWarehouseAvailable(WarehouseLogin warehouseLogin) {
	    Bukkit.getLogger().info("Checking Warehouse URL and token...");
	    if (warehouseLogin.url.equalsIgnoreCase("") || 
	      !isURLandTokenValid(warehouseLogin.url + "/?checkauth", warehouseLogin.token, 1000)) {
	      Bukkit.getLogger().warning("Warehouse couldn't be initialized");
	      return false;
	    } 
	    return true;
	  }

	private record WarehouseLogin(String url, String token) {}

}
