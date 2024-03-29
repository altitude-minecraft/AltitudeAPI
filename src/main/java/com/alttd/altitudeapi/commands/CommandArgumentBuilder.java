package com.alttd.altitudeapi.commands;

public class CommandArgumentBuilder<T>
{

    private CommandArgument<T> argument;

    private CommandArgumentBuilder()
    {
        argument = new CommandArgument<>();
    }

    /**
     * Sets the name for the argument.
     *
     * @param name the name for the argument.
     *
     * @return the same builder.
     */
    public CommandArgumentBuilder<T> setName(String name)
    {
        argument.setName(name);
        return this;
    }

    /**
     * Sets the parser for the argument.
     *
     * @param parser the parser for the argument.
     *
     * @return the same builder.
     */
    public CommandArgumentBuilder<T> setParser(Parser<T> parser)
    {
        argument.setParser(parser);
        return this;
    }

    /**
     * Sets the required rank for the argument.
     *
     * @param permission the required permission for the argument.
     *
     * @return the same builder.
     */
    public CommandArgumentBuilder<T> setRequiredPermission(String permission)
    {
        argument.setPermission(permission);
        return this;
    }

    /**
     * Adds a validator to be used by the argument.
     *
     * @param validator the new validator.
     *
     * @return the same builder.
     */
    public CommandArgumentBuilder<T> addValidator(Validator<T> validator)
    {
        argument.addValidator(validator);
        return this;
    }

    /**
     * Adds a sender validator to be used by the argument.
     *
     * @param senderValidator the new sender validator.
     *
     * @return the same builder.
     */
    public CommandArgumentBuilder<T> addSenderValidator(SenderValidator senderValidator)
    {
        argument.addSenderValidator(senderValidator);
        return this;
    }

    /**
     * Marks this argument as optional. It defaults to false which is why there is no option to disable it as each
     * option should only be set once.
     *
     * @return the same builder.
     */
    public CommandArgumentBuilder<T> setOptional()
    {
        argument.setOptional(true);
        return this;
    }

    /**
     * Marks this argument as having variable length. It defaults to false which is why there is no option to disable it
     * as each option should only be set to once.
     *
     * @return the same builder.
     */
    public CommandArgumentBuilder<T> setVariableLength()
    {
        argument.setVariableLength(true);
        return this;
    }

    /**
     * Marks this argument as usable by the console. It defaults to false which is why there is no option to disable it
     * as each option should only be set once.
     *
     * @return the same builder
     */
    public CommandArgumentBuilder<T> setAllowsConsole()
    {
        argument.setAllowsConsole(true);
        return this;
    }

    /**
     * Returns the {@link CommandArgument} that has been built. Will throw an {@link IllegalStateException} if the
     * parser has not been set as it is required.
     *
     * @return build the completed argument.
     */
    public CommandArgument<T> build()
    {
        if (argument.getName() == null)
        {
            throw new IllegalArgumentException("Argument name not set");
        }
        if (argument.getParser() == null)
        {
            throw new IllegalStateException("Argument parser not set.");
        }

        return argument;
    }

    public static <T> CommandArgumentBuilder<T> createBuilder(Class<T> type)
    {
        return new CommandArgumentBuilder<>();
    }

}
