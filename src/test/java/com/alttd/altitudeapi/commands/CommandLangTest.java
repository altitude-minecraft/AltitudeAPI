package com.alttd.altitudeapi.commands;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CommandLangTest
{
    private FileConfiguration config;

    @Before
    public void setup()
    {
        File file = new File("src/main/resources/lang.yml");

        config = YamlConfiguration.loadConfiguration(file);
    }

    @Test
    public void test_file_contains_options()
    {
        Field[] fields = CommandLang.class.getDeclaredFields();

        for (Field field : fields)
        {
            if (Modifier.isStatic(field.getModifiers()))
            {
                assertTrue("Missing value in file: " + field.getName(), config.contains(field.getName().toLowerCase().replace("_", "-")));
            }
        }
    }
}
