package com.alttd.altitudeapi.commands;

import java.util.Arrays;

import com.alttd.altitudeapi.utils.CollectionUtils;
import com.alttd.altitudeapi.utils.MutableValue;
import com.alttd.altitudeapi.utils.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;

public class CommandLang
{
    /**
     * When a non-player tries to run a player-only command.
     */
    public static CommandLang ONLY_PLAYERS = new CommandLang("Only players can run that command.");

    /**
     * The format for a command usage message.
     */
    public static CommandLang USAGE_FORMAT = new CommandLang("&6&lUSAGE &e» &7{message}");

    /**
     * When a {@link CommandSender} does not have permission to use a command.
     */
    public static CommandLang NO_PERMISSION = new CommandLang("&4&lERROR &e» You don't have permission to do that.");

    /**
     * When a player tries to list the available sub-commands.
     */
    public static CommandLang NO_SUBS = new CommandLang("&4&lERROR &e» You don't have access to any sub-commands.");

    public static CommandLang HEADER_FOOTER = new CommandLang("&7&m-----------------------------------");

    private final MutableValue<String> value;

    /**
     * Constructs a new CommandLang to be used in this class.
     *
     * @param value the default message.
     */
    private CommandLang(String value)
    {
        // set the default value
        this.value = new MutableValue<>(value);
    }

    /**
     * Returns the value of this lang option.
     *
     * @return the value of this lang option.
     */
    public String getValue()
    {
        return value.getValue();
    }

    /**
     * Sets the lang message.
     *
     * @param value the new lang value.
     */
    public void setValue(String value)
    {
        this.value.setValue(value);
    }

    /**
     * Render a string with the proper parameters.
     *
     * @param args the placeholders and proper content.
     *
     * @return the rendered string.
     */
    private String renderString(Object... args)
    {
        if (args.length % 2 != 0)
        {
            throw new IllegalArgumentException("Message rendering requires an even number of arguments. " + Arrays.toString(args) + " given.");
        }
        String string = getValue();
        for (int i = 0; i < args.length; i += 2)
        {
            string = string.replace(args[i].toString(), CollectionUtils.firstNonNull(args[i + 1], "").toString());
        }

        return ChatColor.translateAlternateColorCodes('&', string);
    }

    /**
     * Renders this message and returns it.
     *
     * @param parameters all additional arguments to fill placeholders.
     *
     * @return the compiled message.
     */
    private String getMessage(Object... parameters)
    {
        return renderString(parameters);
    }

    /**
     * Sends this {@link CommandLang} object to the {@link CommandSender} target. The parameters replace all
     * placeholders that exist in the String as well.
     *
     * @param sender     the {@link CommandSender} receiving the message.
     * @param parameters all additional arguments to fill placeholders.
     */
    public void send(CommandSender sender, Object... parameters)
    {
        sender.sendMessage(getMessage(parameters));
    }

    /**
     * Sends a usage message to the {@link CommandSender} for a command with the given label and parameters.
     *
     * @param sender     the sender of the message
     * @param label      the labels the command has.
     * @param parameters the parameters a command has.
     */
    protected static void sendUsageMessage(CommandSender sender, String[] label, String[] parameters)
    {
        StringBuilder args = new StringBuilder("/" + StringUtils.compile(label));
        for (String str : parameters)
        {
            args.append(" [").append(str).append("]");
        }

        sender.sendMessage(USAGE_FORMAT.getMessage("{usage}", args.toString()));
    }
}
