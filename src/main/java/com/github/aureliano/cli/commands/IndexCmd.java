package com.github.aureliano.cli.commands;

import java.util.Arrays;

import org.apache.commons.cli.Option;
import org.apache.log4j.Logger;

import com.github.aureliano.es.ElasticSearchCommandHelper;

/**
 * Command line class executor. Execute actions on ElasticSearch
 * in index scope. Actions that are covered by this are: exist, create and delete.
 * 
 * <ul>
 *   <li><b>exist</b> - Verify if an index exist.</li>
 *   <li><b>create</b> - Create an index.</li>
 *   <li><b>delete</b> - Delete an index.</li>
 * </ul>
 */
public class IndexCmd implements ICommand {

	private static final Logger logger = Logger.getLogger(MappingCmd.class);
	private static final String[] ACTIONS = new String[] { "exist", "create", "delete" };
	
	public static final String NAME = "index";
	public static final String DESCRIPTION = "Execute commands on index context like " +
			Arrays.toString(ACTIONS) + " straight to ElasticSearch";
	
	private String action;
	private String indexName;
	
	public IndexCmd() {
		super();
	}
	
	/**
	 * Execute index command.
	 */
	@Override
	public void execute() {
		logger.info("Execute Index command");
		this.validateParamaters();
		
		this.executeAction();
	}
	
	/**
	 * Execute index command.
	 * 
	 * @param option - Command line option {@link org.apache.commons.cli.Option}
	 */
	@Override
	public void execute(Option option) {
		logger.info("Execute Index command");
		this.loadParameters(option);
		this.validateParamaters();
		
		this.executeAction();
	}

	private void executeAction() {
		logger.info("Executing action " + this.action + " index");
		
		if (this.action.equals("exist")) {
			this.executeExistIndex();
		} else if (this.action.equals("create")) {
			this.executeCreateIndex();
		} else if (this.action.equals("delete")) {
			this.executeDeleteIndex();
		}
	}

	private void executeExistIndex() {
		System.out.println("Index " + this.indexName + " exist? " + ElasticSearchCommandHelper.getInstance().indexExist(this.indexName));
	}

	private void executeCreateIndex() {
		boolean acknowledged = ElasticSearchCommandHelper.getInstance().createIndex(this.indexName);
		System.out.println("acknowledged:" + acknowledged);
	}

	private void executeDeleteIndex() {
		boolean acknowledged = ElasticSearchCommandHelper.getInstance().deleteIndex(this.indexName);
		System.out.println("acknowledged:" + acknowledged);
	}

	private void loadParameters(Option option) {
		String[] values = option.getValues();
		if (values.length != 2) {
			throw new IllegalArgumentException("You must specify <action> <index-name> arguments in order to use this index command");
		}
		
		this.action = values[0];
		this.indexName = values[1];
	}

	private void validateParamaters() {
		if (!Arrays.asList(ACTIONS).contains(this.action)) {
			throw new IllegalArgumentException("Index action must be one of " +
					Arrays.toString(ACTIONS) + ". Found: " + this.action);
		}
	}

	public String getAction() {
		return action;
	}

	public IndexCmd withAction(String action) {
		this.action = action;
		return this;
	}

	public String getIndexName() {
		return indexName;
	}

	public IndexCmd withIndexName(String indexName) {
		this.indexName = indexName;
		return this;
	}
}