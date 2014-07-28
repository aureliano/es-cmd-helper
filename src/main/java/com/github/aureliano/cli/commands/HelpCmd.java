package com.github.aureliano.cli.commands;

import org.apache.commons.cli.HelpFormatter;

import com.github.aureliano.Metadata;
import com.github.aureliano.cli.OptionsBuilder;

public class HelpCmd implements ICommand {

	public static final String NAME = "help";
	public static final String DESCRIPTION = "Show help";
	
	public HelpCmd() {
		super();
	}
	
	@Override
	public void execute() {
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
		
		new HelpFormatter().printHelp(
			metadata.getAppName(),
			header,
			OptionsBuilder.buildDefaultOptions(),
			footer
		);
	}
}