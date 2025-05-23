package de.ifheroes.core.helper.gui.listeners;

import java.util.Optional;
import java.util.UUID;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import de.ifheroes.core.helper.gui.GUIHolder;
import de.ifheroes.core.helper.gui.elements.GUIElement;
import de.ifheroes.core.helper.gui.masks.GUIMask;

public class GUIInteract implements Listener{

	@EventHandler
	public void guiClick(InventoryClickEvent event) {
		Inventory inventory = event.getClickedInventory();
		if(inventory.getHolder() == null) return;
		if(!(inventory.getHolder() instanceof GUIHolder)) return;
		
		Optional<UUID> elementID = GUIElement.getIDFromItemStack(event.getCurrentItem());
		if(elementID.isEmpty()) return;
		
		GUIHolder guiHolder = (GUIHolder) inventory.getHolder();
		GUIMask guiMask = guiHolder.getMask();
		GUIElement guiElement = guiMask.getGUIElement(elementID.get()).get();
		
		guiElement.onClick(event, guiMask);
		event.setCancelled(true);
		
	}
}
