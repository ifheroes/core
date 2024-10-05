package de.ifheroes.core.warehouse;

public enum Section {

	NEWPLAYERDATA("newPlayerData"), BASICDATA("basicData"), ADVANCEDDATA("advancedData"), PLUGINDATA("pluginData");
	
	private String value;
	
	private Section(String value) {
		this.value = value;
	}
	
	public String getValue() {
		return value;
	}
	
}
