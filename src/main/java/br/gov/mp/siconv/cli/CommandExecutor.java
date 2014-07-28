package br.gov.mp.siconv.cli;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.Option;

import br.gov.mp.siconv.cli.commands.CommandFactory;

public class CommandExecutor {

	public static void executeCommand(CommandLine cli) {
		for (Option option : cli.getOptions()) {
			CommandFactory.command(option.getLongOpt()).execute();
		}
	}
}