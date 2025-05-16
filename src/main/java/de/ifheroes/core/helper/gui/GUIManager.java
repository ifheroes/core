package de.ifheroes.core.helper.gui;

import org.bukkit.event.inventory.InventoryType;

import de.ifheroes.core.helper.gui.masks.GUIMask;

public class GUIManager {

	private GUIManager() {}
	
	/*
	 * Redundant -> should be removed with next commit
	 */
	public static GUIMask createGUIMask(GUIMask lastMask, InventoryType type, String titel) {
		return new GUIMask(lastMask, type, titel) {};
	}
}
