package com.github.aureliano.cli;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

import com.github.aureliano.cli.commands.CleanCmd;
import com.github.aureliano.cli.commands.HelpCmd;
import com.github.aureliano.cli.commands.IndexCmd;
import com.github.aureliano.cli.commands.MappingCmd;

/**
 * Command line options builder. This class is intended to create
 * options which will be shown on console/terminal.
 */
public final class OptionsBuilder {

	private static Options options;
	
	private OptionsBuilder() {
		super();
	}
	
	/**
	 * Build default application options.
	 * <ul>
	 *   <li>index</li>
	 *   <li>mapping</li>
	 *   <li>clean</li>
	 *   <li>version</li>
	 *   <li>help</li>
	 * </ul>
	 * 
	 * @return default options {@link org.apache.commons.cli.Options}
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
		opt.addOption(buildCleanOption());
		opt.addOption(buildIndexOption());
		opt.addOption(buildMappingOption());
		
		return opt;
	}
	
	@SuppressWarnings("static-access")
	private static final Option buildIndexOption() {
		return OptionBuilder
			.hasArgs(2)
			.withValueSeparator(' ')
			.isRequired(false)
			.withLongOpt(IndexCmd.NAME)
			.withDescription(IndexCmd.DESCRIPTION)
			.withArgName("action> <index-name")
			.create('i');
	}
	
	@SuppressWarnings("static-access")
	private static final Option buildMappingOption() {
		return OptionBuilder
			.hasArgs(2)
			.withValueSeparator(' ')
			.isRequired(false)
			.withLongOpt(MappingCmd.NAME)
			.withDescription(MappingCmd.DESCRIPTION)
			.withArgName("action> <mapping-bean")
			.create('m');
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
}