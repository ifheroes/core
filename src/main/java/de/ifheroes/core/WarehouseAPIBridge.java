package de.ifheroes.core;

import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import de.ifheroes.core.data.warehouse.PostRequestBody;
import de.ifheroes.core.data.warehouse.Section;
import de.ifheroes.core.data.warehouse.Warehouse;
import de.ifheroes.core.data.warehouse.exceptions.GetRequestFailedException;
import de.ifheroes.core.data.warehouse.exceptions.WarehouseNotInitializedException;
import de.ifheroes.core.profile.HeroProfile;
import de.ifheroes.core.profile.HeroProfileImpl;
import de.ifheroes.core.profile.levelstructur.basic.BasicDataImpl;

/*
 * This class represents the official interaction to others plugins
 */
public class WarehouseAPIBridge implements InfinityHeroesCoreAPI{

	private static ConcurrentHashMap<UUID, CompletableFuture<HeroProfile>> profileCache = new ConcurrentHashMap<>();
	  
	  private Warehouse warehouse;
	  
	  public Optional<HeroProfile> getProfile(Player player) {
	    return getProfile(player.getUniqueId());
	  }
	  
	  public Optional<HeroProfile> getProfile(UUID uuid) {
	    try {
	      return Optional.ofNullable(((CompletableFuture<HeroProfile>)profileCache.computeIfAbsent(uuid, x -> fetchProfile(uuid))).get());
	    } catch (InterruptedException|java.util.concurrent.ExecutionException e) {
	      e.printStackTrace();
	      Bukkit.getLogger().warning("COULD NOT LOAD PROFILE %s".formatted(new Object[] { uuid.toString() }));
	      return Optional.empty();
	    } 
	  }
	  
	  public CompletableFuture<HeroProfile> fetchProfile(UUID uuid) {
	    return CompletableFuture.supplyAsync(() -> {
	          try {
	            HeroProfile profile = (HeroProfile)(new Gson()).fromJson((JsonElement)getWarehouse().get(uuid.toString()).orElse(new JsonObject()), HeroProfileImpl.class);
	            profile.setUUIDs();
	            return profile;
	          } catch (JsonSyntaxException|WarehouseNotInitializedException e) {
	            e.printStackTrace();
	          } catch (GetRequestFailedException e) {
	            return newProfile(uuid, getNameFromUUID(uuid));
	          } 
	          return null;
	        });
	  }
	  
	  public Warehouse getWarehouse() throws WarehouseNotInitializedException {
	    if (this.warehouse == null)
	      throw new WarehouseNotInitializedException(); 
	    return this.warehouse;
	  }
	  
	  public void setWarehouse(Warehouse warehouse) {
	    this.warehouse = warehouse;
	    Bukkit.getLogger().info("Warehouse has been initialized");
	  }
	  
	  public HeroProfile newProfile(UUID uuid, String name) {
	    try {
	      getWarehouse().post(uuid.toString(), (new PostRequestBody(Section.NEWPLAYERDATA, uuid)).put("name", name));
	    } catch (WarehouseNotInitializedException e) {
	      e.printStackTrace();
	    } 
	    return (HeroProfile)new HeroProfileImpl(new BasicDataImpl(uuid, name));
	  }
	  
	  public boolean deleteProfile(UUID uuid) {
	    try {
	      boolean status = getWarehouse().delete(uuid.toString());
	      if (status)
	        unloadProfile(uuid); 
	      return status;
	    } catch (WarehouseNotInitializedException e) {
	      e.printStackTrace();
	      return false;
	    } 
	  }
	  
	  public void unloadProfile(UUID uuid) {
	    profileCache.remove(uuid);
	  }
	  
	  private String getNameFromUUID(UUID uuid) {
	    return Optional.<OfflinePlayer>ofNullable(Bukkit.getOfflinePlayer(uuid))
	      .map(OfflinePlayer::getName)
	      .orElse("null");
	  }
}
