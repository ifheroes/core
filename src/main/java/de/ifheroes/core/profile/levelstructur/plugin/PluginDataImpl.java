package de.ifheroes.core.profile.levelstructur.plugin;

import java.util.HashMap;
import java.util.Map;

import de.ifheroes.core.profile.levelstructur.DomainKey;

/**
 * The PluginDataImpl class provides a concrete implementation of the PluginData interface.
 * It manages plugin-specific data using a nested map structure where the first level key represents
 * the domain (usually the plugin name), and the second level key represents specific data identifiers.
 */
public class PluginDataImpl implements PluginData {

    private Map<String, Map<String, Object>> values;

    /**
     * Default constructor.
     * Initializes the internal map to an empty HashMap.
     */
    public PluginDataImpl() {
        values = new HashMap<>();
    }

    /**
     * Sets a value in the plugin data associated with the specified domain key.
     * 
     * @param domainKey The key representing the domain and specific data identifier.
     * @param value The value to be set for the given domain key.
     */
    @Override
    public void set(DomainKey domainKey, Object value) {
        values
            .computeIfAbsent(domainKey.getDomain(), x -> new HashMap<>())
            .put(domainKey.getKey(), value);
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
    public <T> T get(DomainKey domainKey, Class<T> clazz) {
        Object value = values
            .getOrDefault(domainKey.getDomain(), new HashMap<>())
            .get(domainKey.getKey());

        try {
            return clazz.cast(value);
        } catch (ClassCastException ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Checks if a value is present for the specified domain key.
     * 
     * @param domainKey The key representing the domain and specific data identifier.
     * @return True if a value exists for the domain key, otherwise false.
     */
    @Override
    public boolean has(DomainKey domainKey) {
        return values.containsKey(domainKey.getDomain())
            && values.get(domainKey.getDomain()).containsKey(domainKey.getKey());
    }

    /**
     * Removes the value associated with the specified domain key.
     * 
     * @param domainKey The key representing the domain and specific data identifier.
     * @return True if the value was successfully removed, otherwise false.
     */
    @Override
    public boolean remove(DomainKey domainKey) {
        if (values.containsKey(domainKey.getDomain())) {
            values.get(domainKey.getDomain()).remove(domainKey.getKey());
            return true;
        }
        return false;
    }

    /**
     * Retrieves all plugin data as a nested map structure.
     * 
     * @return A map where the first level key is the domain or plugin name,
     *         and the second level key is the specific data identifier.
     */
    @Override
    public Map<String, Map<String, Object>> getAllData() {
        return values;
    }

    /**
     * Retrieves a map of plugin data associated with a specific domain key.
     * 
     * @param domainKey The domain key representing the specific domain.
     * @return A map where the key is a data identifier and the value is the associated data object.
     */
    @Override
    public Map<String, Object> getPluginData(DomainKey domainKey) {
        return getPluginData(domainKey.getDomain());
    }

    /**
     * Retrieves a map of plugin data associated with a specific plugin name.
     * 
     * @param pluginName The name of the plugin whose data is to be retrieved.
     * @return A map where the key is a data identifier and the value is the associated data object.
     */
    @Override
    public Map<String, Object> getPluginData(String pluginName) {
        return values.computeIfAbsent(pluginName, x -> new HashMap<>());
    }
}