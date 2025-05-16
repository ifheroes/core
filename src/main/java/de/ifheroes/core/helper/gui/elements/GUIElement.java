package de.ifheroes.core.helper.gui.elements;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

import de.ifheroes.core.InfinityHeroesCorePlugin;

public abstract class GUIElement implements IGUIElement {

	public static final NamespacedKey namespacedKey = new NamespacedKey(JavaPlugin.getPlugin(InfinityHeroesCorePlugin.class), "guielementid");
	
	private final UUID id;
	private ItemStack item;

	public GUIElement() {
		this.id = UUID.randomUUID();
	}
	
	public GUIElement(ItemStack item) {
		this();
		this.item = item;
	}
	
	@Override
	public UUID getID() {
		return this.id;
	}

	@Override
	public IGUIElement setItem(ItemStack itemStack) {
		this.item = itemStack;
		return this;
	}

	@Override
	public IGUIElement setItem(Material material) {
		this.item = new ItemStack(material);
		return this;
	}

	@Override
	public IGUIElement setLore(List<String> list) {
		getItemMeta(item).ifPresent(meta -> {
			meta.setLore(list);
			item.setItemMeta(meta);
		});
		return this;
	}

	@Override
	public IGUIElement setName(String name) {
		getItemMeta(item).ifPresent(meta -> {
			meta.setDisplayName(name);
			item.setItemMeta(meta);
		});
		return this;
	}

	@Override
	public ItemStack getItem() {
		return addMark();
	}

	@Override
	public List<String> getLore() {
		return getItemMeta(item).map(ItemMeta::getLore).orElse(Collections.emptyList());
	}

	@Override
	public String getName() {
		return getItemMeta(item).map(ItemMeta::getDisplayName).orElse("");
	}

	private ItemStack addMark() {
		getItemMeta(item).ifPresent(meta -> {
			PersistentDataContainer persistentDataContainer = meta.getPersistentDataContainer();
			persistentDataContainer.set(namespacedKey, PersistentDataType.STRING, getID().toString());
			item.setItemMeta(meta);
		});
		return item;
	}

	private static Optional<ItemMeta> getItemMeta(ItemStack itemStack) {
		return Optional.ofNullable(itemStack).map(ItemStack::getItemMeta);
	}

	public static Optional<UUID> getIDFromItemStack(ItemStack itemStack) {
		return getItemMeta(itemStack)
				.map(meta -> meta.getPersistentDataContainer().get(namespacedKey, PersistentDataType.STRING))
				.map(UUID::fromString);
	}
}
