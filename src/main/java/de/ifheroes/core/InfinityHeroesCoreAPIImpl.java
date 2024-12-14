package de.ifheroes.core;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import de.ifheroes.core.Logger.LogLevel;
import de.ifheroes.core.profile.HeroProfile;
import de.ifheroes.core.profile.HeroProfileImpl;
import de.ifheroes.core.profile.levelstructur.basic.BasicDataImpl;
import de.ifheroes.core.warehouse.PostRequestBody;
import de.ifheroes.core.warehouse.Section;
import de.ifheroes.core.warehouse.Warehouse;
import de.ifheroes.core.warehouse.exceptions.GetRequestFailedException;
import de.ifheroes.core.warehouse.exceptions.WarehouseNotInitializedException;

/*
 * This class represents the official interaction to others plugins
 */
public class InfinityHeroesCoreAPIImpl implements InfinityHeroesCoreAPI{

	private static ConcurrentHashMap<UUID, CompletableFuture<HeroProfile>> profileCache = new ConcurrentHashMap<>();
	
	private Warehouse warehouse;
	
	/*
	 * This Method refers to the getProfile(String uuid) method.
	 * 
	 * @params player is being used to hand over the uuid to the other method
	 * 
	 * @returns HeroProfile
	 */
	@Override
	public HeroProfile getProfile(Player player) {
		return getProfile(player.getUniqueId());
	}

	/*
	 * This Method creates a HeroProfile based on the received JsonObject/JsonString from the Warehouse
	 * 
	 * @params uuid is the key in the warehouse
	 * 
	 * @returns HeroProfile interface
	 */
	@Override
	public HeroProfile getProfile(UUID uuid) {
		try {
			return profileCache.computeIfAbsent(uuid, x -> fetchProfile(uuid)).get();
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		new Logger(LogLevel.ERROR).error("COULD NOT LOAD PROFILE %s".formatted(uuid.toString()));
		return null;
	}
	
	
	public CompletableFuture<HeroProfile> fetchProfile(UUID uuid) {
		return CompletableFuture.supplyAsync(() -> {
			try {
				HeroProfile profile = new Gson().fromJson(getWarehouse().get(uuid.toString()).orElse(new JsonObject()), HeroProfileImpl.class);
				profile.setUUIDs();
				return profile;
			} catch (JsonSyntaxException | WarehouseNotInitializedException e) {
				e.printStackTrace();
			} catch (GetRequestFailedException  e) {
				return newProfile(uuid, getNameFromUUID(uuid));
			} 
			return null;
		});
	}
	
	/*
	 * Gets the current Warehouse
	 * 
	 * @returns Warehouse interface
	 */
	public Warehouse getWarehouse() throws WarehouseNotInitializedException {
		if(warehouse == null) throw new WarehouseNotInitializedException();
		return this.warehouse;
	}

	@Override
	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}

	@Override
	public HeroProfile newProfile(UUID uuid, String name) {
		try {
			getWarehouse().post(uuid.toString(), new PostRequestBody(Section.NEWPLAYERDATA, uuid).put("name", name));
		} catch (WarehouseNotInitializedException e) {
			e.printStackTrace();
		}
		return new HeroProfileImpl(new BasicDataImpl(uuid, name));
	}
	
	@Override
	public boolean deleteProfile(UUID uuid) {
		try {
			boolean status =  getWarehouse().delete(uuid.toString());
			if(status) unloadProfile(uuid);
			return status;
		} catch (WarehouseNotInitializedException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	@Override
	public void unloadProfile(UUID uuid) {
		profileCache.remove(uuid);
	}
	
	private String getNameFromUUID(UUID uuid) {
		return Optional.ofNullable(Bukkit.getOfflinePlayer(uuid))
				.map(OfflinePlayer::getName)
				.orElse("null");
	}
}
