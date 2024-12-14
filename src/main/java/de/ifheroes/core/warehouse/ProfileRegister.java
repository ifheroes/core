package de.ifheroes.core.warehouse;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;

import de.ifheroes.core.InfinityHeroesCorePlugin;

public class ProfileRegister implements Listener{

	public ProfileRegister() {
		Bukkit.getOnlinePlayers().forEach(x -> InfinityHeroesCorePlugin.getAPI().getProfile(x));
	}
	
	@EventHandler
	public void playerLoginEvent(AsyncPlayerPreLoginEvent event) {
		InfinityHeroesCorePlugin.getAPI().getProfile(event.getUniqueId());
	}
	
	@EventHandler
	public void playerQuitEvent(PlayerQuitEvent event) {
		InfinityHeroesCorePlugin.getAPI().unloadProfile(event.getPlayer().getUniqueId());
	}
	
	/*
	 * This might need a method to empty cached players
	 */
	
}
