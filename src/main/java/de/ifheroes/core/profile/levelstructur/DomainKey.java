package de.ifheroes.core.profile.levelstructur;

import org.bukkit.plugin.Plugin;

/**
 * The DomainKey class is used to uniquely identify a piece of data within a specific domain (plugin).
 * It consists of a domain (typically the name of a plugin) and a key (a specific identifier within that domain).
 */
public class DomainKey {

    private String domain;
    private String key;

    /**
     * Constructs a new DomainKey with the specified domain (plugin name) and key.
     * 
     * @param pluginName The name of the plugin, serving as the domain.
     * @param key The specific key within the domain.
     */
    DomainKey(String pluginName, String key) {
        this.domain = pluginName;
        this.key = key;
    }

    /**
     * Constructs a new DomainKey using a Plugin object and a key.
     * The domain is set to the lowercase name of the plugin.
     * 
     * @param plugin The Plugin object whose name will be used as the domain.
     * @param key The specific key within the domain.
     */
    public DomainKey(Plugin plugin, String key) {
        this(plugin.getName().toLowerCase(), key);
    }

    /**
     * Retrieves the domain of this DomainKey.
     * 
     * @return The domain as a String.
     */
    public String getDomain() {
        return this.domain;
    }

    /**
     * Retrieves the key of this DomainKey.
     * 
     * @return The key as a String.
     */
    public String getKey() {
        return this.key;
    }
}