package com.github.aureliano.cli.commands;

/**
 * Command line class executor. Print to standard output
 * a formatted help of this API. 
 */
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;

import com.github.aureliano.cli.OptionsBuilder;

public class HelpCmd implements ICommand {

	public static final String NAME = "help";
	public static final String DESCRIPTION = "Show help";
	
	public HelpCmd() {
		super();
	}

	/**
	 * Execute help command.
	 */
	@Override
	public void execute() {
		this.execute(null);
	}

	/**
	 * Execute help command.
	 * 
	 * @param option - Command line option {@link org.apache.commons.cli.Option}
	 */
	@Override
	public void execute(Option option) {
		String header = "";
		String footer = new StringBuilder("\n----------\nElasticSearch Command Helper")
			.append("\nProject Home  - ")
			.append("https://github.com/aureliano/es-cmd-helper")
			.append("\nSource Code   - ")
			.append("https://github.com/aureliano/es-cmd-helper")
			.append("\nDocumentation - ")
			.append("https://github.com/aureliano/es-cmd-helper/wiki")
			.append("\nJavaDoc       - ")
			.append("")
			.toString();
		
		HelpFormatter formatter = new HelpFormatter();
		formatter.setWidth(150);
		formatter.printHelp("ElasticSearch Command Helpe", header, OptionsBuilder.buildDefaultOptions(), footer);
	}
}