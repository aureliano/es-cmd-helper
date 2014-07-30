package com.github.aureliano.cli.commands;

/**
 * Command line class executor. Print to standard output
 * a formatted help of this API. 
 */
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;

import com.github.aureliano.Metadata;
import com.github.aureliano.cli.OptionsBuilder;

public class HelpCmd implements ICommand {

	public static final String NAME = "help";
	public static final String DESCRIPTION = "Show help";
	
	public HelpCmd() {
		super();
	}

	/**
	 * Execute help command.
	 * 
	 * @param Command line option <option> {@link org.apache.commons.cli.Option}
	 */
	@Override
	public void execute(Option option) {
		Metadata metadata = Metadata.getInstance();
		String header = "";
		String footer = new StringBuilder("\n\nProject Home  - ")
			.append(metadata.getProjectHome())
			.append("\nSource Code   - ")
			.append(metadata.getScmHome())
			.append("\nDocumentation - ")
			.append(metadata.getDocumentation())
			.append("\nJavaDoc       - ")
			.append(metadata.getJavadoc())
			.toString();
		
		HelpFormatter formatter = new HelpFormatter();
		formatter.setWidth(150);
		formatter.printHelp(metadata.getAppName(), header, OptionsBuilder.buildDefaultOptions(), footer);
	}
}