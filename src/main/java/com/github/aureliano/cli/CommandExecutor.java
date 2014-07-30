package com.github.aureliano.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;

import com.github.aureliano.cli.commands.CommandFactory;

/**
 * Command executor. Create dynamically command objects according to
 * command line arguments. After creation it start command execution.
 */
public class CommandExecutor {

	/**
	 * Receive a command line object {@link org.apache.commons.cli.CommandLine}
	 * in order to be translated and executed.
	 * 
	 * @param cli - Command line object
	 */
	public static void executeCommand(CommandLine cli) {
		for (Option option : cli.getOptions()) {
			CommandFactory.command(option.getLongOpt()).execute(option);
		}
	}
}