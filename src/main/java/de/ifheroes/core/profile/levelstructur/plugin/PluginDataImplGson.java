package de.ifheroes.core.profile.levelstructur.plugin;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nonnull;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

import de.ifheroes.core.data.warehouse.Section;
import de.ifheroes.core.profile.events.EventBound;
import de.ifheroes.core.profile.levelstructur.DomainKey;

/**
 * The PluginDataImpl class provides a concrete implementation of the PluginData interface.
 * It manages plugin-specific data using a nested map structure where the first level key represents
 * the domain (usually the plugin name), and the second level key represents specific data identifiers.
 */
public class PluginDataImplGson extends EventBound implements PluginData {

    private JsonObject values;
    private UUID uuid;

    /**
     * Default constructor.
     * Initializes the internal map to an empty HashMap.
     */
    public PluginDataImplGson(UUID uuid) {
        this.values = new JsonObject();
        this.uuid = uuid;
    }

    /**
     * Sets a value in the plugin data associated with the specified domain key.
     * 
     * @param domainKey The key representing the domain and specific data identifier.
     * @param value The value to be set for the given domain key.
     */
    @Override
    public void set(DomainKey domainKey, @Nonnull Object value) {
    	String domain = domainKey.getDomain();
    	
    	Optional<JsonObject> oElement = Optional.ofNullable(values.getAsJsonObject(domain));
    	JsonObject domainData = oElement.orElseGet(JsonObject::new);
    
    	domainData.add(domainKey.getKey(), new Gson().toJsonTree(value));
        
        callEvent(uuid, Section.PLUGINDATA, "updater", values.getAsString());
    }

    /**
     * Retrieves a value from the plugin data associated with the specified domain key.
     * 
     * @param domainKey The key representing the domain and specific data identifier.
     * @param clazz The class type of the value to be retrieved.
     * @param <T> The type of the value.
     * @return The value associated with the domain key, cast to the specified type.
     *         Returns null if the value cannot be cast or is not present.
     */
    @Override
    public <T> Optional<T> get(DomainKey domainKey, Class<T> clazz) {
    	String domain = domainKey.getDomain();

        JsonElement element = values.get(domain);
        if (element == null || element.isJsonNull()) {
            return Optional.empty();
        }

        Gson gson = new Gson();
        try {
            T value = gson.fromJson(element, clazz);
            return Optional.ofNullable(value);
        } catch (JsonSyntaxException e) {
            return Optional.empty();
        }
    }

    /**
     * Checks if a value is present for the specified domain key.
     * 
     * @param domainKey The key representing the domain and specific data identifier.
     * @return True if a value exists for the domain key, otherwise false.
     */
    @Override
    public boolean has(DomainKey domainKey) {
        return values.has(domainKey.getDomain())
            && values.getAsJsonObject(domainKey.getDomain()).has(domainKey.getKey());
    }

    /**
     * Removes the value associated with the specified domain key.
     * 
     * @param domainKey The key representing the domain and specific data identifier.
     * @return True if the value was successfully removed, otherwise false.
     */
    @Override
    public boolean remove(DomainKey domainKey) {
    	return values.has(domainKey.getDomain())
    			&& values.getAsJsonObject(domainKey.getDomain()).remove(domainKey.getKey()) != null;
    }

    /**
     * Retrieves all plugin data as a nested map structure.
     * 
     * @return A map where the first level key is the domain or plugin name,
     *         and the second level key is the specific data identifier.
     */
    @Override
    public Map<String, Map<String, Object>> getAllData() {
    	/*
    	 * tmp
    	 * needs remapping
    	 */
        return null;
    }

    /**
     * Retrieves a map of plugin data associated with a specific domain key.
     * 
     * @param domainKey The domain key representing the specific domain.
     * @return A map where the key is a data identifier and the value is the associated data object.
     */
    @Override
    public Map<String, Object> getRawPluginData(DomainKey domainKey) {
        return getRawPluginData(domainKey.getDomain());
    }

    /**
     * Retrieves a map of plugin data associated with a specific plugin name.
     * 
     * @param pluginName The name of the plugin whose data is to be retrieved.
     * @return A map where the key is a data identifier and the value is the associated data object.
     */
    @Override
    public Map<String, Object> getRawPluginData(String pluginName) {
    	/*
    	 * tmp needs remapping
    	 */
       return null;
    }

	@Override
	public UUID getUUID() {
		return uuid;
	}
	
	public void setUUID(UUID uuid) {
		this.uuid = uuid;
	}
}