package de.ifheroes.core;

import java.util.UUID;

import org.bukkit.plugin.java.JavaPlugin;

import com.google.gson.Gson;

import de.ifheroes.core.profile.HeroProfile;
import de.ifheroes.core.profile.events.HeroProfileUpdateEvent;
import de.ifheroes.core.profile.levelstructur.DomainKey;
import de.ifheroes.core.warehouse.Section;
import de.ifheroes.core.warehouse.WarehouseImpl;

public class InfinityHeroesCorePlugin extends JavaPlugin {

	private static InfinityHeroesCoreAPI api = new InfinityHeroesCoreAPIImpl();

	public static final InfinityHeroesCoreAPI getAPI() {
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
		
		
		System.out.println(new Gson().toJson(profile.getPluginData().getAllData()));
		
	//	new HeroProfileUpdateEvent(uuid, Section.BASICDATA, "name", "test");

		
		
		
		
	}
}
