package com.alttd.altitudeapi;

import java.io.File;

import com.alttd.altitudeapi.utils.items.ItemDb;
import org.bukkit.plugin.java.JavaPlugin;

public class AltitudeAPI extends JavaPlugin
{
    private static AltitudeAPI instance;

    private static ItemDb itemDb;

    public void onEnable()
    {
        instance = this;

        saveDefaultConfig();

        if (getConfig().getBoolean("use-items"))
        {
            saveResource("items.csv", true);
            itemDb = new ItemDb(new File(getDataFolder(), "items.csv"));
        }
    }

    /**
     * Returns the {@link ItemDb} loaded by this plugin.
     *
     * @return the {@link ItemDb} loaded by this plugin.
     */
    public static ItemDb getItemDb()
    {
        if (itemDb == null)
        {
            throw new IllegalStateException("Enable the item api in the config file.");
        }
        return itemDb;
    }

    /**
     * Returns the singleton instance of this API.
     *
     * @return the singleton instance of this API.
     */
    public static AltitudeAPI getInstance()
    {
        return instance;
    }
}
