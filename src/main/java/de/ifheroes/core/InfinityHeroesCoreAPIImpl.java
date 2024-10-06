package de.ifheroes.core;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

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
			return new Gson().fromJson(getWarehouse().get(uuid.toString()).orElse(new JsonObject()), HeroProfileImpl.class);
		} catch (JsonSyntaxException | WarehouseNotInitializedException e) {
			e.printStackTrace();
		} catch (GetRequestFailedException  e) {
			
			//TODO: Change Temp Name to method getNameFromUUID
			
			return newProfile(uuid, "tmp");
		} 
		return null;
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
		HeroProfile profile = new HeroProfileImpl(new BasicDataImpl(uuid, name));
		warehouse.post(uuid.toString(), new PostRequestBody(Section.NEWPLAYERDATA, uuid).put("name", name));
		return profile;
	}
	
	private String getNameFromUUID(UUID uuid) {
		String name = "";
		OfflinePlayer player = Bukkit.getOfflinePlayer(uuid);
		if(player != null) name = player.getName();
		return name;
	}
}
