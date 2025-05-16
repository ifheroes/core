package de.ifheroes.core.helper.gui;

import java.util.UUID;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

import de.ifheroes.core.helper.gui.masks.GUIMask;

public class GUIHolder implements InventoryHolder{
	
	private final UUID id;
	private final GUIMask mask;
	
	public GUIHolder(UUID id, GUIMask mask) {
		this.id = id;
		this.mask = mask;
	}
	
	public UUID getId() {
		return id;
	}
	
	public GUIMask getMask() {
		return mask;
	}
	
	@Override
	public Inventory getInventory() {
		return null;
	}

}
