# IFHeroes Minigame Core

## Description
Provides basic chat functionality and access to the HeroProfile system for minigames.
Additional features pending...

## Maintainers
List the people who are responsible for this repository:
- **Akabe**: [GitHub Profile](https://github.com/AkabeX86)
- **IDev**: [GitHub Profile](https://github.com/IDev-mc)


## Dependencies
List all the dependencies required to run the project:
- OpenJDK 21
- Spigot API - Version x.x
- PlaceholderAPI - Version x.x


## Version History
If applicable, provide a summary of the version history:
- **Version 1.0.0**: Initial release.
- **Version 1.1.0**: ...
  
## API References
If applicable, provide references to the API and its usage:
- [API Documentation](https://ifheroes.github.io/ifheroes-apis/)

# InfinityHeroes Core API Documentation

## Overview
The InfinityHeroes Core API provides a set of interfaces and classes to manage player profiles, plugin data, and other essential functions of the InfinityHeroes plugin. This documentation serves as a tutorial for using the primary interfaces and classes.

---

## Table of Contents

1. [Accessing the API](#1-accessing-the-api)
2. [InfinityHeroesCoreAPI](#2-infinityheroescoreapi)
3. [HeroProfile](#3-heroprofile)
4. [PluginData](#4-plugindata)
5. [DomainKey](#5-domainkey)



---

## 1. Accessing the API

To access the API, use the following code snippet. This retrieves the instance of the `InfinityHeroesCoreAPI` from the plugin:

```java
InfinityHeroesCoreAPI infinityHeroesCoreAPI = InfinityHeroesCorePlugin.getAPI();
```

---

## 2. InfinityHeroesCoreAPI

**Package:** `de.ifheroes.core`

### Description
The main interface of the InfinityHeroesCore plugin, providing access to player-related information and core functions.

### Methods

#### `HeroProfile getProfile(Player player)`
- **Description:** Retrieves the player profile based on a `Player` object.
- **Parameters:**
  - `Player player`: The player for whom the profile is retrieved.
- **Return Value:**
  - `HeroProfile`: The player profile.
  ```java
    HeroProfile heroProfile = InfinityHeroesCoreAPI.getProfile(PLAYER);
  ```

#### `HeroProfile getProfile(UUID uuid)`
- **Description:** Retrieves the player profile based on the UUID from the Warehouse.
- **Parameters:**
  - `UUID uuid`: The unique ID of the player.
- **Return Value:**
  - `HeroProfile`: The player profile.
  ```java
    HeroProfile heroProfile = InfinityHeroesCoreAPI.getProfile(UUID);
  ```

#### `HeroProfile newProfile(UUID uuid, String name)`
- **Description:** Creates a new player profile.
- **Parameters:**
  - `UUID uuid`: The unique ID of the player.
  - `String name`: The name of the player.
- **Return Value:**
  - `HeroProfile`: The newly created player profile.
  ```java
    HeroProfile heroProfile = InfinityHeroesCoreAPI.newProfile(UUID, NAME);
  ```

#### `boolean deleteProfile(UUID uuid)`
- **Description:** Deletes the player profile based on the UUID.
- **Parameters:**
  - `UUID uuid`: The unique ID of the player.
- **Return Value:**
  - `boolean`: `true` if the profile was successfully deleted; otherwise, `false`.
   ```java
    boolean success = InfinityHeroesCoreAPI.deleteProfile(UUID);
  ```

#### `void unloadProfile(UUID uuid)`
- **Description:** Unloads the player profile from memory.
- **Parameters:**
  - `UUID uuid`: The unique ID of the player.
  ```java
    InfinityHeroesCoreAPI.unloadProfile(UUID);
  ```

---

## 3. HeroProfile

**Package:** `de.ifheroes.core.profile`

### Description
The interface for player profiles, encapsulating basic, advanced, and plugin-specific data.

### Methods

#### `UUID getUUID()`
- **Description:** Retrieves the player's UUID.
- **Return Value:**
  - `UUID`: The unique identifier of the player.
  ```java
    UUID uuid = HeroProfile.getUUID();
  ```

#### `String getName()`
- **Description:** Retrieves the player's name.
- **Return Value:**
  - `String`: The name of the player.
   ```java
    String name = HeroProfile.getName();
  ```

#### `void setName(String name)`
- **Description:** Sets the player's name.
- **Parameters:**
  - `String name`: The new name of the player.
   ```java
     HeroProfile.setName(NAME);
  ```

#### `HeroProfileLanguage getLanguage()`
- **Description:** Retrieves the language of the player's profile.
- **Return Value:**
  - `HeroProfileLanguage`: The player's profile language as an enum based reponse.
   ```java
    HeroProfileLanguage uuid = HeroProfile.getLanguage();
  ```

#### `void setLanguage(HeroProfileLanguage language)`
- **Description:** Sets the language of the player's profile.
- **Parameters:**
  - `HeroProfileLanguage language`: The language to set.
  ```java
    HeroProfile.setLanguage();
  ```

#### `PluginData getPluginData()`
- **Description:** Retrieves the plugin-specific data of the player's profile.
- **Return Value:**
  - `PluginData`: The plugin data object.
  ```java
    PluginData pluginData = HeroProfile.getPluginData();
  ```

#### `String toString()`
- **Description:** Converts the player profile to a string.
- **Return Value:**
  - `String`: The string representation of the player profile.

---

## 4. PluginData

**Package:** `de.ifheroes.core.profile.levelstructur.plugin`

### Description
The interface for managing plugin-specific data within a player's profile.

### Methods

#### `void set(DomainKey domainKey, Object value)`
- **Description:** Sets a value in the plugin data for the specified domain key.
- **Parameters:**
  - `DomainKey domainKey`: The key representing the domain for the value.
  - `Object value`: The value to set.
  ```java
    PluginData.set(DOMAINKEY, VALUE);
  ```

#### `<T> Optional<T> get(DomainKey domainKey, Class<T> clazz)`
- **Description:** Retrieves a value based on the key and type.
- **Parameters:**
  - `DomainKey domainKey`: The domain key.
  - `Class<T> clazz`: The type of the value.
- **Return Value:**
  - `Optional<T>`: The retrieved value.
  ```java
   Optional<T> optionalValue = PluginData.get(DOMAINKEY, T);
   T value = optionalValue.get();
   /*or*/
   T value = optionalValue.orElse(T);
  ```

#### `boolean has(DomainKey domainKey)`
- **Description:** Checks if a value exists for the specified key.
- **Parameters:**
  - `DomainKey domainKey`: The key to check.
- **Return Value:**
  - `boolean`: `true` if a value exists; otherwise, `false`.
  ```java
    boolean hasKey = PluginData.has(DOMAINKEY);
  ```

#### `boolean remove(DomainKey domainKey)`
- **Description:** Removes a value based on the key.
- **Parameters:**
  - `DomainKey domainKey`: The key for the value to remove.
- **Return Value:**
  - `boolean`: `true` if the value was successfully removed; otherwise, `false`.
 ```java
    boolean successfullyRemoved = PluginData.remove(DOMAINKEY);
  ```

#### `Map<String, Map<String, Object>> getAllData()`
- **Description:** Returns all plugin data as a nested map structure.
- **Return Value:**
  - `Map<String, Map<String, Object>>`: The plugin data.
 ```java
    Map<String, Map<String, Object>> data = PluginData.getAllData();
  ```

#### `UUID getUUID()`
- **Description:** Retrieves the player's UUID.
- **Return Value:**
  - `UUID`: The unique ID of the player.
 ```java
    UUID uuid = PluginData.getUUID();
  ```
---

## 5. DomainKey

**Package:** `de.ifheroes.core.profile.levelstructur`

### Description
The `DomainKey` class uniquely identifies a piece of data within a specific domain (plugin). It consists of a domain (typically the name of a plugin) and a key (a specific identifier within that domain).

### Constructors

#### `DomainKey(String pluginName, String key)`
- **Description:** Constructs a new `DomainKey` with the specified domain and key.
- **Parameters:**
  - `String pluginName`: The name of the plugin, serving as the domain.
  - `String key`: The specific key within the domain.
  ```java
    DomainKey domainKey = new DomainKey(PLUGINNAME, KEY);
  ```

#### `DomainKey(Plugin plugin, String key)`
- **Description:** Constructs a new `DomainKey` using a `Plugin` object and a key. The domain is set to the lowercase name of the plugin.
- **Parameters:**
  - `Plugin plugin`: The plugin object whose name will be used as the domain.
  - `String key`: The specific key within the domain.
  ```java
    DomainKey domainKey = new DomainKey(JAVAPLUGIN, KEY);
  ```

### Methods

#### `String getDomain()`
- **Description:** Retrieves the domain of this `DomainKey`.
- **Return Value:**
  - `String`: The domain as the plugin name.
  ```java
    String domain = DomainKey.getDomain();
  ```

#### `String getKey()`
- **Description:** Retrieves the key of this `DomainKey`.
- **Return Value:**
  - `String`: The key.
  ```java
    String key = DomainKey.getKey();
  ```
