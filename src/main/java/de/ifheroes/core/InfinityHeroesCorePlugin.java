package de.ifheroes.core;

import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

import de.ifheroes.core.profile.HeroProfile;
import de.ifheroes.core.warehouse.WarehouseImpl;

public class InfinityHeroesCorePlugin extends JavaPlugin {

	private static InfinityHeroesCoreAPI api = new InfinityHeroesCoreAPIImpl();

	static final InfinityHeroesCoreAPI getAPI() {
		return api;
	}

	@Override
	public void onEnable() {

		// TODO: set the warehouse in api
		
		super.onEnable();
	}

	
	public static void main(String[] args) {

		

		UUID uuid = UUID.fromString("41e84db3-3d9a-4123-a070-22bcf28efe16");
		
		HeroProfile profile = api.getProfile(uuid);
		
		
		
		
		
		
/*		UUID secondUUID = UUID.fromString("2bfafaed-1e79-45d2-beac-2003cd8e57e7");
		HeroProfile newProfile = api.getProfile(secondUUID); */
		
		
		
		
		
		
	}
}
