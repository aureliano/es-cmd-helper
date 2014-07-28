package br.gov.mp.siconv.cli.commands;

public final class CommandFactory {

	private CommandFactory() {
		super();
	}
	
	public static ICommand command(String name) {
		if (HelpCmd.NAME.equals(name)) {
			return new HelpCmd();
		} else if (VersionCmd.NAME.equals(name)) {
			return new VersionCmd();
		} else {
			throw new IllegalArgumentException("There is no such command '" + name + "'");
		}
	}
}