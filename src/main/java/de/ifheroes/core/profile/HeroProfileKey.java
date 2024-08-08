package de.ifheroes.core.profile;

/*
 * Representing default key entrys for the hero profile
 */
public enum HeroProfileKey {

	LANGUAGE("lang"), DEFAULT("def");
	
	private String name;
	
	private HeroProfileKey(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
	public static HeroProfileKey fromString(String name) {
		for(HeroProfileKey keys : values()) {
			if(keys.toString().equals(name)) return keys;
		}
		return HeroProfileKey.DEFAULT;
	}
}
