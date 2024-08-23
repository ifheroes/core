package de.ifheroes.core;

import org.bukkit.entity.Player;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import de.ifheroes.core.profile.HeroProfile;
import de.ifheroes.core.profile.HeroProfileImpl;
import de.ifheroes.core.warehouse.Warehouse;
import de.ifheroes.core.warehouse.exceptions.WarehouseNotInitializedException;

/*
 * This class represents the official interaction to others plugins
 */
public class InfinityHeroesCoreAPIImpl implements InfinityHeroesCoreAPI{

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
		return getProfile(player.getUniqueId().toString());
	}

	/*
	 * This Method creates a HeroProfile based on the received JsonObject/JsonString from the Warehouse
	 * 
	 * @params uuid is the key in the warehouse
	 * 
	 * @returns HeroProfile interface
	 */
	@Override
	public HeroProfile getProfile(String uuid) {
		try {
			return new Gson().fromJson(getWarehouse().get(uuid).orElse(new JsonObject()), HeroProfileImpl.class);
		} catch (JsonSyntaxException | WarehouseNotInitializedException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	/*
	 * Gets the current Warehouse
	 * 
	 * @returns Warehouse interface
	 */
	private Warehouse getWarehouse() throws WarehouseNotInitializedException {
		if(warehouse == null) throw new WarehouseNotInitializedException();
		return this.warehouse;
	}

	@Override
	public void setWarehouse(Warehouse warehouse) {
		this.warehouse = warehouse;
	}
	
	
	
}
