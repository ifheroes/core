package de.ifheroes.core.profile.levelstructur.basic;

import java.util.UUID;

import de.ifheroes.core.profile.events.EventBound;
import de.ifheroes.core.warehouse.Section;

/**
 * The BasicDataImpl class is a concrete implementation of the BasicData interface.
 * It manages the basic-level data for the HeroProfile.
 */
public class BasicDataImpl extends EventBound implements BasicData {

    private UUID uuid;
    private String name;

    /**
     * Default constructor.
     * Initializes an empty BasicDataImpl object.
     */
    public BasicDataImpl() {}

    /**
     * Parameterized constructor.
     * Initializes the BasicDataImpl object with the specified UUID and name.
     *
     * @param uuid The UUID to be set for the hero's profile.
     * @param name The name to be set for the hero's profile.
     */
    public BasicDataImpl(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
    }

    /**
     * Retrieves the unique identifier (UUID) associated with the hero's profile.
     *
     * @return The UUID of the hero.
     */
    @Override
    public UUID getUUID() {
        return this.uuid;
    }

    /**
     * Retrieves the name associated with the hero's profile.
     *
     * @return The name of the hero.
     */
    @Override
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name for the hero's profile.
     *
     * @param name The name to be assigned to the hero.
     */
    @Override
    public void setName(String name) {
        this.name = name;
        callEvent(uuid, Section.BASICDATA, "name", name);
    }
}
