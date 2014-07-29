package com.github.aureliano.cli.commands;

import java.util.Arrays;

import org.apache.commons.cli.Option;
import org.apache.log4j.Logger;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;

import com.github.aureliano.es.EsIndexWriter;

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
		System.out.println("Index " + this.indexName + " exist? " + EsIndexWriter.getInstance().indexExist(this.indexName));
	}

	private void executeCreateIndex() {
		CreateIndexResponse response = EsIndexWriter.getInstance().createIndex(this.indexName);
		System.out.println("acknowledged:" + response.isAcknowledged());
	}

	private void executeDeleteIndex() {
		DeleteIndexResponse response = EsIndexWriter.getInstance().deleteIndex(this.indexName);
		System.out.println("acknowledged:" + response.isAcknowledged());
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

	public void setAction(String action) {
		this.action = action;
	}

	public String getIndexName() {
		return indexName;
	}

	public void setIndexName(String indexName) {
		this.indexName = indexName;
	}
}