package com.github.aureliano.cli.commands;

/**
 * This class aims to be a factory of commands.
 */
public final class CommandFactory {

	private CommandFactory() {
		super();
	}
	
	/**
	 * Get a command executor by its name. Each command executor
	 * must have a public class attribute NAME.
	 *  
	 * @param name - The command executor name (Command.NAME)
	 * @return An executor {@link com.github.aureliano.cli.commands.ICommand}
	 */
	public static ICommand command(String name) {
		if (HelpCmd.NAME.equals(name)) {
			return new HelpCmd();
		} else if (CleanCmd.NAME.equals(name)) {
			return new CleanCmd();
		} else if (MappingCmd.NAME.equals(name)) {
			return new MappingCmd();
		} else if (IndexCmd.NAME.equals(name)) {
			return new IndexCmd();
		} else {
			throw new IllegalArgumentException("There is no such command '" + name + "'");
		}
	}
}