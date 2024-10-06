package de.ifheroes.core.profile.events;

import java.util.UUID;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import de.ifheroes.core.InfinityHeroesCoreAPI;
import de.ifheroes.core.InfinityHeroesCorePlugin;
import de.ifheroes.core.warehouse.PostRequestBody;
import de.ifheroes.core.warehouse.Section;
import de.ifheroes.core.warehouse.exceptions.WarehouseNotInitializedException;

public class HeroProfileUpdateEvent extends Event{
	
	private final UUID uuid;
	private final Section section;
	private final String key;
	private final String value;
	
	public HeroProfileUpdateEvent(UUID uuid, Section section, String key, String value) {
		this.uuid = uuid;
		this.section = section;
		this.key = key;
		this.value = value;
		
		InfinityHeroesCoreAPI api = InfinityHeroesCorePlugin.getAPI();
		try {
			api.getWarehouse().post(key, new PostRequestBody(section, uuid).put(key, value));
		} catch (WarehouseNotInitializedException e) {
			e.printStackTrace();
		}
	}
	
	public Section getSection() {
		return section;
	}
	
	public String getKey() {
		return key;
	}
	
	public String getValue() {
		return value;
	}
	
	public UUID getUuid() {
		return uuid;
	}
	
	@Override
	public HandlerList getHandlers() {
		return this.getHandlers();
	}
}
