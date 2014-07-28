package com.github.aureliano.cli;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

import com.github.aureliano.cli.commands.CleanCmd;
import com.github.aureliano.cli.commands.HelpCmd;
import com.github.aureliano.cli.commands.VersionCmd;


public final class OptionsBuilder {

	private static Options options;
	
	private OptionsBuilder() {
		super();
	}
	
	/**
	 * clean, help, version, indexes, put-map, get-map, collect-logs
	 * @return
	 */
	public static final Options buildDefaultOptions() {
		if (options == null) {
			options = createDefaultOptions();
		}
		
		return options;
	}
	
	private static Options createDefaultOptions() {
		Options opt = new Options();
		opt.addOption(buildHelpOption());
		opt.addOption(buildVersionOption());
		opt.addOption(buildCleanOption());
		
		return opt;
	}
	
	@SuppressWarnings("static-access")
	private static final Option buildCleanOption() {
		return OptionBuilder
				.hasArg(false)
				.isRequired(false)
				.withLongOpt(CleanCmd.NAME)
				.withDescription(CleanCmd.DESCRIPTION)
				.create('c');
	}
	
	@SuppressWarnings("static-access")
	private static final Option buildHelpOption() {
		return OptionBuilder
			.hasArg(false)
			.isRequired(false)
			.withLongOpt(HelpCmd.NAME)
			.withDescription(HelpCmd.DESCRIPTION)
			.create('h');
	}
	
	@SuppressWarnings("static-access")
	private static final Option buildVersionOption() {
		return OptionBuilder
			.hasArg(false)
			.isRequired(false)
			.withLongOpt(VersionCmd.NAME)
			.withDescription(VersionCmd.DESCRIPTION)
			.create('v');
	}
}