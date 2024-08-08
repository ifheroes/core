package de.ifheroes.core;

import org.bukkit.entity.Player;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import de.ifheroes.core.profile.HeroProfile;
import de.ifheroes.core.profile.HeroProfileClass;
import de.ifheroes.core.warehouse.Warehouse;
import de.ifheroes.core.warehouse.WarehouseImpl;

public class CoreAPI implements InfinityHeroesCoreAPI{

	private Warehouse warehouse;
	
	public CoreAPI(String url, String restAPIKey) {
		warehouse = new WarehouseImpl(url, restAPIKey);
	}
	
	@Override
	public HeroProfile getProfile(Player player) {
		return getProfile(player.getUniqueId().toString());
	}

	@Override
	public HeroProfile getProfile(String uuid) {
		return new Gson().fromJson(getWarehouse().get(uuid).orElse(new JsonObject()), HeroProfileClass.class);
	}

	private Warehouse getWarehouse() {
		return this.warehouse;
	}
	
	
	
}
