package com.mir.servlets.web.command;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.TreeMap;

/**
 * Holder for all commands.
 *
 * @author R.Mirzoiev
 * @see Command
 * @see LoginCommand
 * @see LogoutCommand
 * @see ListUsersCommand
 * @see DeleteUsersCommand
 * @see UpdateUsersCommand
 * @see ExtractForEditCommand
 * @see AddCommand
 * @see AddPageCommand
 * @see NoCommand
 * @since 31.01.2022
 */
public class CommandContainer {

    private static final Logger LOG = Logger.getLogger(CommandContainer.class);

    private static Map<String, Command> commands = new TreeMap<String, Command>();

    static {
        // common commands
        commands.put("login", new LoginCommand());
        commands.put("logout", new LogoutCommand());
        commands.put("userList", new ListUsersCommand());

        commands.put("deleteUser", new DeleteUsersCommand());
        commands.put("update", new UpdateUsersCommand());
        commands.put("editUser", new ExtractForEditCommand());
        commands.put("add", new AddCommand());
        commands.put("addPage", new AddPageCommand());

        commands.put("noCommand", new NoCommand());

        LOG.debug("Command container was successfully initialized");
        LOG.trace("Number of commands --> " + commands.size());
    }

    /**
     * Returns command object with the given name.
     *
     * @param commandName Name of the command.
     * @return Command object.
     */
    public static Command get(String commandName) {
        if (commandName == null || !commands.containsKey(commandName)) {
            LOG.trace("Command not found, name --> " + commandName);
            return commands.get("noCommand");
        }

        return commands.get(commandName);
    }
}