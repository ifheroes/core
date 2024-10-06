package de.ifheroes.core.profile.events;

import java.util.UUID;

import org.bukkit.Bukkit;

import de.ifheroes.core.warehouse.Section;

public class EventBound {

	public void callEvent(UUID uuid, Section section, String key, String value) {
		HeroProfileUpdateEvent update = new HeroProfileUpdateEvent(uuid, section, key, value);
        Bukkit.getPluginManager().callEvent(update);
	}
	
}
