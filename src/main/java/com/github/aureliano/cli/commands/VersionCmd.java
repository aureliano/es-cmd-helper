package com.github.aureliano.cli.commands;

import com.github.aureliano.Metadata;

public class VersionCmd implements ICommand {

	public static final String NAME = "version";
	public static final String DESCRIPTION = "Show app version";
	
	public VersionCmd() {
		super();
	}

	@Override
	public void execute() {
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