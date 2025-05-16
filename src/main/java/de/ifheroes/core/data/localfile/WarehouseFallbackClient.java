package de.ifheroes.core.data.localfile;

import java.util.Optional;
import java.util.UUID;

import org.bukkit.entity.Player;

import de.ifheroes.core.InfinityHeroesCoreAPI;
import de.ifheroes.core.data.warehouse.Warehouse;
import de.ifheroes.core.data.warehouse.exceptions.WarehouseNotInitializedException;
import de.ifheroes.core.profile.HeroProfile;

public class WarehouseFallbackClient implements InfinityHeroesCoreAPI{

	@Override
	public Optional<HeroProfile> getProfile(Player player) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public Optional<HeroProfile> getProfile(UUID uuid) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	@Override
	public HeroProfile newProfile(UUID uuid, String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean deleteProfile(UUID uuid) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void unloadProfile(UUID uuid) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setWarehouse(Warehouse warehouse) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Warehouse getWarehouse() throws WarehouseNotInitializedException {
		// TODO Auto-generated method stub
		return null;
	}

}
