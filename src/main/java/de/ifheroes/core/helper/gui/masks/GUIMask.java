package de.ifheroes.core.helper.gui.masks;

import java.util.HashMap;
import java.util.Optional;
import java.util.UUID;

import javax.annotation.Nonnull;

import org.bukkit.Bukkit;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;

import de.ifheroes.core.helper.gui.GUIHolder;
import de.ifheroes.core.helper.gui.elements.GUIElement;

public abstract class GUIMask implements IGUIMask{

	private final UUID id;
	private final IGUIMask lastMask;
	private final InventoryType type;
	
	private String titel;
	
	private HashMap<Integer, GUIElement> maskElements = new HashMap<>();
	
	protected GUIMask(IGUIMask lastMask, InventoryType type, String titel) {
		this.id = UUID.randomUUID();
		this.lastMask = lastMask;
		this.type = type;
		this.titel = titel;
	}
	
	@Override
	public void setGUIElement(@Nonnull int slot, @Nonnull GUIElement element) {
		maskElements.put(slot, element);
	}
	
	@Override
	public void setGUIElement(@Nonnull int x, @Nonnull int y, @Nonnull GUIElement element) {
		int slot = (y*9)+x;
		maskElements.put(slot, element);
	}
	
	@Override
	public void setTitle(@Nonnull String titel) {
		this.titel = titel;
	}
	
	@Override
	public Optional<GUIElement> getGUIElement(@Nonnull int slot) {
		return Optional.ofNullable(maskElements.get(slot));
	}
	
	@Override
	public Optional<GUIElement> getGUIElement(@Nonnull UUID id) {
		return maskElements.values().stream()
				.filter(element -> element.getID().compareTo(id) == 0)
				.findFirst();
	}
	
	@Override
	public Optional<GUIElement> getGUIElement(@Nonnull String id) {
		return getGUIElement(UUID.fromString(id));
	}
	
	@Override
	public UUID getID() {
		return this.id;
	}
	
	@Override
	public IGUIMask getLastMask() {
		return this.lastMask;
	}
	
	@Override
	public InventoryType getType() {
		return this.type;
	}
	
	@Override
	public String getTitle() {
		 return this.titel;
	}
	
	@Override
	public boolean removeGUIElement(UUID id) {
		return maskElements.entrySet().removeIf(entry -> entry.getValue().getID().compareTo(id) == 0);
	}
	
	@Override
	public boolean removeGUIElement(GUIElement element) {
		return removeGUIElement(element.getID());
	}
	
	@Override
	public boolean removeGUIElement(String id) {
		return removeGUIElement(UUID.fromString(id));
	}
	
	@Override
	public boolean removeGUIElement(int slot) {
		return maskElements.remove(slot) != null;
	}
	
	@Override
	public Inventory createInventory() {
		Inventory inventory = Bukkit.createInventory(new GUIHolder(id, this), type, titel);
		maskElements.entrySet().forEach(element -> inventory.setItem(element.getKey(), element.getValue().getItem()));
		return inventory;
	}
	
	
	/**
	 * Checks if the resource is open.
	 * @return {@code true} if open, {@code false} otherwise.
	 * 
	 * No Implementation yet
	 */
	@Override
	public boolean isOpen() {
	    return true;
	}

	/**
	 * Called when the resource is closed.
	 * 
	 * No Implementation yet
	 */
	@Override
	public void onClose() {
	    
	}

	/**
	 * Called when the resource is opened.
	 * 
	 * No Implementation yet
	 */
	@Override
	public void onOpen() {
	   
	}

	/**
	 * Updates the resource. Placeholder assumes success.
	 * @return {@code true} if update succeeded, {@code false} otherwise.
	 * 
	 * No Implementation yet
	 */
	@Override
	public boolean update() {
	    return true;
	}
}
