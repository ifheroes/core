package de.ifheroes.core;

import java.util.Optional;
import java.util.UUID;

import org.bukkit.entity.Player;

import de.ifheroes.core.data.warehouse.Warehouse;
import de.ifheroes.core.data.warehouse.exceptions.WarehouseNotInitializedException;
import de.ifheroes.core.profile.HeroProfile;

/*
 * Represents the API of the InfinityHeroesCore
 * Access to plugin accessible informations
 */
public interface InfinityHeroesCoreAPI {
	  Optional<HeroProfile> getProfile(Player paramPlayer);
	  
	  Optional<HeroProfile> getProfile(UUID paramUUID);
	  
	  HeroProfile newProfile(UUID paramUUID, String paramString);
	  
	  boolean deleteProfile(UUID paramUUID);
	  
	  void unloadProfile(UUID paramUUID);
	  
	  void setWarehouse(Warehouse paramWarehouse);
	  
	  Warehouse getWarehouse() throws WarehouseNotInitializedException;
	}

