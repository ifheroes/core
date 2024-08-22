package de.ifheroes.core.profile.levelstructur.plugin;

import java.util.Map;

import de.ifheroes.core.profile.levelstructur.DomainKey;

/**
 * The PluginData interface defines the contract for managing plugin-specific data
 * within a hero's profile. It provides methods to set, get, check, remove, and retrieve all data.
 */
public interface PluginData {

    /**
     * Sets a value in the plugin data associated with the specified domain key.
     *
     * @param domainKey The key representing the domain for which the value is being set.
     * @param value The value to be set. It can be of any type.
     */
    public void set(DomainKey domainKey, Object value);

    /**
     * Retrieves a value from the plugin data associated with the specified domain key.
     *
     * @param domainKey The key representing the domain from which the value is to be retrieved.
     * @param clazz The class type of the value to be retrieved.
     * @param <T> The type of the value being retrieved.
     * @return The value associated with the domain key, cast to the specified type.
     * @throws ClassCastException If the retrieved value cannot be cast to the specified class type.
     */
    public <T> T get(DomainKey domainKey, Class<T> clazz);

    /**
     * Checks if a value is present for the specified domain key.
     *
     * @param domainKey The key representing the domain to be checked.
     * @return True if a value exists for the domain key, otherwise false.
     */
    public boolean has(DomainKey domainKey);

    /**
     * Removes the value associated with the specified domain key.
     *
     * @param domainKey The key representing the domain for which the value is to be removed.
     * @return True if the value was successfully removed, otherwise false.
     */
    public boolean remove(DomainKey domainKey);

    /**
     * Retrieves all plugin data as a nested map structure.
     * 
     * @return A map where the first level key is a domain, the second level key is a specific data key, and the value is the data object.
     */
    public Map<String, Map<String, Object>> getAllData();
    
    /**
     * Retrieves a map of plugin data associated with a specific domain key.
     *
     * @param domainKey The domain key representing the specific domain of the plugin data.
     * @return A map where the key is a string representing the data identifier, and the value is the associated data object.
     */
    public Map<String, Object> getPluginData(DomainKey domainKey);

    /**
     * Retrieves a map of plugin data associated with a specific plugin name.
     *
     * @param pluginName The name of the plugin whose data is to be retrieved.
     * @return A map where the key is a string representing the data identifier, and the value is the associated data object.
     */
    public Map<String, Object> getPluginData(String pluginName);
}
