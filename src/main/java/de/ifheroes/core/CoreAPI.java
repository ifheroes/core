package de.ifheroes.core;

import org.bukkit.entity.Player;

import de.ifheroes.core.profile.HeroProfile;
import de.ifheroes.core.warehouse.Warehouse;

public class CoreAPI implements InfinityHeroesCoreAPI{

	private String url;
	private String restAPIKey;
	private Warehouse warehouse;
	
	public CoreAPI(String url, String restAPIKey) {
		this.url = url;
		this.restAPIKey = restAPIKey;
		
		//TODO: Implement warehouse
	}
	
	@Override
	public HeroProfile getProfile(Player player) {
		
		return null;
	}

	@Override
	public HeroProfile getProfile(String uuid) {
		
		return null;
	}

}
