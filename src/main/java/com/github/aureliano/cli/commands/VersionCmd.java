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
		StringBuilder s = new StringBuilder()
			.append(Metadata.APP_NAME)
			.append(" ")
			.append(Metadata.RELEASE_VERSION)
			.append(" (" + Metadata.RELEASE_DATE + ")")
			.append(" [Java " + Metadata.JAVA_VERSION + "]");
		System.out.println(s.toString());
	}
}