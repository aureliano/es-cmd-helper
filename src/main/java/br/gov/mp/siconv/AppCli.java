package br.gov.mp.siconv;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.ParseException;

import br.gov.mp.siconv.cli.CommandExecutor;
import br.gov.mp.siconv.cli.OptionsBuilder;

public class AppCli {

	public void executeCommandLine(String[] args) {
		CommandLineParser parser = new BasicParser();
		try {
			CommandExecutor.executeCommand(parser.parse(OptionsBuilder.buildDefaultOptions(), args));
		} catch (ParseException ex) {
			System.err.println(" !-- Parsing command line parameters has failed. Reason: " + ex.getMessage());
		}
	}
}