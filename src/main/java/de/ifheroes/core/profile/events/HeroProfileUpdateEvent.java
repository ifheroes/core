package de.ifheroes.core.profile.events;

import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import de.ifheroes.core.InfinityHeroesCoreAPI;
import de.ifheroes.core.InfinityHeroesCorePlugin;
import de.ifheroes.core.Logger;
import de.ifheroes.core.Logger.LogLevel;
import de.ifheroes.core.data.warehouse.PostRequestBody;
import de.ifheroes.core.data.warehouse.Section;
import de.ifheroes.core.data.warehouse.exceptions.WarehouseNotInitializedException;

public class HeroProfileUpdateEvent {
	
	private static final ExecutorService executor = Executors.newCachedThreadPool();
	
	private final UUID uuid;
	private final Section section;
	private final String key;
	private final String value;
	
	public HeroProfileUpdateEvent(UUID uuid, Section section, String key, String value) {
		this.uuid = uuid;
		this.section = section;
		this.key = key;
		this.value = value;
		
		
		
		postUpdate();
	}
	
	public void postUpdate() {
		InfinityHeroesCoreAPI api = InfinityHeroesCorePlugin.getAPI();
		executor.submit(() -> {
			try {
				api.getWarehouse().post(key, new PostRequestBody(section, uuid).put(key, value));
			} catch (WarehouseNotInitializedException e) {
				new Logger(LogLevel.ERROR).error("Failed to update profile %s due to the Warehouse not beeing initialized.".formatted(uuid));
			}
		});
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
}
