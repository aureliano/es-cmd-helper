package com.github.aureliano.cli.commands;

import org.apache.commons.cli.Option;

/**
 * Interface for command line executors.
 */
public interface ICommand {

	public abstract void execute();
	
	public abstract void execute(Option option);
}