package br.gov.mp.siconv.cli.commands;

import org.apache.commons.cli.HelpFormatter;

import br.gov.mp.siconv.Metadata;
import br.gov.mp.siconv.cli.OptionsBuilder;

public class HelpCmd implements ICommand {

	public static final String NAME = "help";
	public static final String DESCRIPTION = "Show help";
	
	public HelpCmd() {
		super();
	}
	
	@Override
	public void execute() {
		String header = "";
		String footer = new StringBuilder("\n\nProject Home  - ")
			.append(Metadata.PROJECT_HOME)
			.append("\nSource Code   - ")
			.append(Metadata.SCM_HOME)
			.append("\nDocumentation - ")
			.append(Metadata.DOCUMENTATION)
			.append("\nJavaDoc       - ")
			.append(Metadata.JAVADOC)
			.toString();
		
		new HelpFormatter().printHelp(
			Metadata.APP_NAME,
			header,
			OptionsBuilder.buildDefaultOptions(),
			footer
		);
	}
}