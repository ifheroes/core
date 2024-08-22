package de.ifheroes.core.profile.levelstructur.basic;

import java.util.UUID;

/**
 * The BasicData interface defines the contract for managing basic-level data
 * within the HeroProfile.
 */
public interface BasicData {

    /**
     * Retrieves the unique identifier (UUID) associated with the hero's profile.
     *
     * @return The UUID of the hero.
     */
    public UUID getUUID();

    /**
     * Retrieves the name associated with the hero's profile.
     *
     * @return The name of the hero.
     */
    public String getName();

    /**
     * Sets the name for the hero's profile.
     *
     * @param name The name to be assigned to the hero.
     */
    public void setName(String name);
	
}
