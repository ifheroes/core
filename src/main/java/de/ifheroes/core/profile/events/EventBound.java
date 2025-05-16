package de.ifheroes.core.profile.events;

import java.util.UUID;

import de.ifheroes.core.data.warehouse.Section;

public class EventBound {

	public void callEvent(UUID uuid, Section section, String key, String value) {
		new HeroProfileUpdateEvent(uuid, section, key, value);
	/*	HeroProfileUpdateEvent update = new HeroProfileUpdateEvent(uuid, section, key, value);
		Bukkit.getPluginManager().callEvent(update);
	 	TODO: Implement Custom Event Manager */
	}
	
}
