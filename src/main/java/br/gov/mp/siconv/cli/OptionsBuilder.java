package br.gov.mp.siconv.cli;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;

import br.gov.mp.siconv.cli.commands.HelpCmd;
import br.gov.mp.siconv.cli.commands.VersionCmd;

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
		
		return opt;
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