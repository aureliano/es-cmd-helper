package com.github.aureliano.cli.commands;

import org.apache.commons.cli.Option;

import com.github.aureliano.Metadata;

/**
 * Command line class executor. Print to standard output
 * a data about this API like: API name, API release version,
 * API release date and which Java version was used to compile it. 
 */
public class VersionCmd implements ICommand {

	public static final String NAME = "version";
	public static final String DESCRIPTION = "Show app version";
	
	public VersionCmd() {
		super();
	}

	/**
	 * Execute version command.
	 * 
	 * @param Command line option <option> {@link org.apache.commons.cli.Option}
	 */
	@Override
	public void execute(Option option) {
		Metadata metadata = Metadata.getInstance();
		StringBuilder s = new StringBuilder()
			.append(metadata.getAppName())
			.append(" ")
			.append(metadata.getReleaseVersion())
			.append(" (" + metadata.getReleaseDate() + ")")
			.append(" [Java " + metadata.getJavaVersion() + "]");
		System.out.println(s.toString());
	}
}