package com.github.aureliano.cli.commands;

import org.apache.commons.cli.Option;

public interface ICommand {

	public abstract void execute(Option option);
}