package com.github.aureliano.cli.commands;

public final class CommandFactory {

	private CommandFactory() {
		super();
	}
	
	public static ICommand command(String name) {
		if (HelpCmd.NAME.equals(name)) {
			return new HelpCmd();
		} else if (VersionCmd.NAME.equals(name)) {
			return new VersionCmd();
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