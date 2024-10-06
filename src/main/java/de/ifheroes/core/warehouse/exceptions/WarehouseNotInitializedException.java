package de.ifheroes.core.warehouse.exceptions;

import de.ifheroes.core.Logger;
import de.ifheroes.core.Logger.LogLevel;

public class WarehouseNotInitializedException extends Exception{

	private static final long serialVersionUID = 1L;
	
	@Override
	public void printStackTrace() {
		super.printStackTrace();
		new Logger(LogLevel.ERROR).error("Couldn't find Warehouse -> WarehouseNotInitialized");
	}
	
}
