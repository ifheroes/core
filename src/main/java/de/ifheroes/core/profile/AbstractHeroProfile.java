package de.ifheroes.core.profile;

import java.util.Map;
import java.util.Optional;

public abstract class AbstractHeroProfile implements HeroProfile{

	/*
	 * TODO: Need to replace Object with actual data type
	 */
	public Map<String, Object> data;
	
	@Override
	public <C> Optional<C> get(HeroProfileKey key, Class<C> type) {
		return get(key.toString(), type);
	}

	/*
	 * TODO: Rework check -> with actual data type
	 */
	@SuppressWarnings("unchecked")
	@Override
	public <C> Optional<C> get(String key, Class<C> type) {
		Object value = data.get(key);
		if(type.isInstance(value)) {
			return Optional.of((C) value);
		}
		return Optional.of(null);
	}
	
	
	@Override
	public <C> void set(HeroProfileKey key, C data) {
		set(key.toString(), data);
	}
	
	@Override
	public <C> void set(String key, C data) {
		this.data.put(key, data);
	}
}
