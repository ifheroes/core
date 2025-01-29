package de.ifheroes.core.helper.gui.masks;

import java.util.Optional;
import java.util.UUID;

import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import de.ifheroes.core.helper.gui.elements.GUIElement;

public interface IGUIMask {

	//TODO: Find purpose (Chris please notice me about this if you read this) 
	public void onOpen();
	public void onClose();
	
	public void setGUIElement(int slot, GUIElement element);
	public void setGUIElement(int x, int y, GUIElement element);
	
	public void setTitle(String titel);
	
	public Optional<GUIElement> getGUIElement(int slot);
	public Optional<GUIElement> getGUIElement(String id);
	public Optional<GUIElement> getGUIElement(UUID id);
	
	public UUID getID();
	public IGUIMask getLastMask();
	public InventoryType getType();
	public String getTitle();
	
	public boolean removeGUIElement(GUIElement element);
	public boolean removeGUIElement(int slot);
	public boolean removeGUIElement(String id);
	public boolean removeGUIElement(UUID id);
	
	public boolean isOpen();
	
	public Inventory createInventory();
	public boolean update();
}
