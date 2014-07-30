package com.github.aureliano;

import org.apache.commons.cli.BasicParser;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.ParseException;

import com.github.aureliano.cli.CommandExecutor;
import com.github.aureliano.cli.OptionsBuilder;

/**
 * This class can be used to encapsulate command line execution.
 * Gets command line arguments as parameters, parse them and execute
 * commands after.
 */
public class AppCli {
	
	public void executeCommandLine(String[] args) {
		CommandLineParser parser = new BasicParser();
		try {
			CommandExecutor.executeCommand(parser.parse(OptionsBuilder.buildDefaultOptions(), args));
		} catch (ParseException ex) {
			System.err.println(" !-- Parsing command line parameters has failed. Reason: " + ex.getMessage());
		} catch (Exception ex) {
			System.err.println(" !-- An error has ocurred while running. Reason: " + ex.getMessage());
		}
	}
}