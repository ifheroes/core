package de.ifheroes.core.profile;

import java.util.UUID;

import com.google.gson.Gson;

import de.ifheroes.core.profile.levelstructur.advanced.AdvancedData;
import de.ifheroes.core.profile.levelstructur.advanced.AdvancedDataImpl;
import de.ifheroes.core.profile.levelstructur.basic.BasicData;
import de.ifheroes.core.profile.levelstructur.basic.BasicDataImpl;
import de.ifheroes.core.profile.levelstructur.plugin.PluginData;
import de.ifheroes.core.profile.levelstructur.plugin.PluginDataImplGson;
import de.ifheroes.core.profile.types.HeroProfileLanguage;

/**
 * The HeroProfileImpl class provides a concrete implementation of the HeroProfile interface.
 * It encapsulates and manages the different levels of data associated with a player's profile:
 * - Basic data (e.g., UUID, name)
 * - Advanced data (e.g., language settings)
 * - Plugin-specific data
 */

public class HeroProfileImpl implements HeroProfile{
	
	private BasicDataImpl basicData;
	private AdvancedDataImpl advancedData;
	private PluginDataImplGson pluginData;

	public HeroProfileImpl(BasicDataImpl basicData) {
		setBasicData(basicData);
		setAdvancedData(new AdvancedDataImpl(getUUID()));
		setPluginData(new PluginDataImplGson(getUUID()));
	}
	
	@Override
	public void setUUIDs() {
		advancedData.setUUID(getUUID());
		pluginData.setUUID(getUUID());
	}
	

	private void setBasicData(BasicDataImpl basicData) {
		this.basicData = basicData;
	}
    private BasicData getBasicData() {
        return this.basicData;
    }


    @Override
    public UUID getUUID() {
        return getBasicData().getUUID();
    }

    @Override
    public String getName() {
        return getBasicData().getName();
    }

    @Override
    public void setName(String name) {
        getBasicData().setName(name);
    }

    private void setAdvancedData(AdvancedDataImpl advancedData) {
        this.advancedData = advancedData;
    }

    private AdvancedData getAdvancedData() {
        return this.advancedData;
    }

    @Override
    public HeroProfileLanguage getLanguage() {
        return getAdvancedData().getLanguage();
    }
    
    @Override
    public void setLanguage(HeroProfileLanguage heroProfileLanguage) {
    	getAdvancedData().setLanguage(heroProfileLanguage);
    }
    
    @Override
    public PluginData getPluginData() {
        return this.pluginData;
    }

    private void setPluginData(PluginDataImplGson pluginData) {
        this.pluginData = pluginData;
    }
    
    @Override
    public String toString() {
    	return new Gson().toJson(this);
    }
    
}
