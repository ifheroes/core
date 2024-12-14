package de.ifheroes.core;

import java.io.IOException;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public class File {

	private java.io.File f;
	private YamlConfiguration yml;
	private String name;
	private String path;

	
	public File(String name) {
		this(name, null);
	}
	
	public File(String name, String path) {
		this.name = name.endsWith(".yml") ? name : name+".yml";
		this.path = path;

		if (path == null) {
			this.path = JavaPlugin.getPlugin(InfinityHeroesCorePlugin.class).getDataFolder().toString();
		}
		
		
		f = new java.io.File(this.path, name);
		if (!f.exists()) {
            f.getParentFile().mkdirs();
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        yml = YamlConfiguration.loadConfiguration(f);
 
	}

	public String getName() {
		return name;
	}

	public String getPath() {
		return path;
	}

	public YamlConfiguration getYml() {
		return yml;
	}

	public java.io.File getFile() {
		return f;
	}

	public Object getObject(String path) {
		return yml.get(path);
	}

	public <T> T get(String path, Class<T> clazz) {
        Object value = yml.get(path);
        if (value == null) {
            return null;
        }
        return clazz.cast(value);
    }
	
	public void set(String path, Object obj) {
		yml.set(path, obj);
		save();
	}

	public void save() {
        try {
            yml.save(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
}
