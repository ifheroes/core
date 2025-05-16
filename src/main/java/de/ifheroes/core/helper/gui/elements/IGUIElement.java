package de.ifheroes.core.helper.gui.elements;

import java.util.List;
import java.util.UUID;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import de.ifheroes.core.helper.gui.masks.GUIMask;

public interface IGUIElement {
	
	boolean onClick(InventoryClickEvent event, GUIMask mask);

    IGUIElement setItem(ItemStack itemStack);
    IGUIElement setItem(Material material);
    IGUIElement setName(String name);
    IGUIElement setLore(List<String> list);

    UUID getID();
    ItemStack getItem();
    String getName();
    List<String> getLore();
	
	
}
