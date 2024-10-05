package de.ifheroes.core.warehouse;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class PostRequestBody {
	
	private String section;
	private Map<String, String> schema;
	
	public PostRequestBody(Section section, UUID uuid) {
		this.section = section.getValue();
		this.schema = new HashMap<>();
		
		this.schema.put("uuid", uuid.toString());
	}
	
	public PostRequestBody put(String key, String value) {
		this.schema.put(key, value);
		return this;
	}
	
	public String getSection() {
		return section;
	}
}
