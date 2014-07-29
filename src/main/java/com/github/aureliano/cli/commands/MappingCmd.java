package com.github.aureliano.cli.commands;

import java.util.Arrays;

import org.apache.commons.cli.Option;
import org.apache.log4j.Logger;
import org.elasticsearch.action.admin.indices.mapping.delete.DeleteMappingResponse;
import org.elasticsearch.action.admin.indices.mapping.put.PutMappingResponse;

import com.github.aureliano.es.EsIndexWriter;

public class MappingCmd implements ICommand {

	private static final Logger LOGGER = Logger.getLogger(MappingCmd.class);
	private static final String[] ACTIONS = new String[] { "create", "get", "delete" };
	
	public static final String NAME = "mapping";
	public static final String DESCRIPTION = "Execute commands on mapping context like " +
			Arrays.toString(ACTIONS) + " straight to ElasticSearch";
	
	private String action;
	private String mappingBean;
	
	public MappingCmd() {
		super();
	}
	
	@Override
	public void execute(Option option) {
		LOGGER.info("Execute Mapping command");
		this.loadParameters(option);
		this.validateParamaters();
		
		this.executeAction();
	}
	
	private void executeAction() {
		LOGGER.info("Executing action " + this.action + " mapping");
		
		if (this.action.equals("create")) {
			this.executeCreateMapping();
		} else if (this.action.equals("get")) {
			this.executeGetMapping();
		} else if (this.action.equals("delete")) {
			this.executeDeleteMapping();
		}
	}

	private void executeCreateMapping() {
		PutMappingResponse response = EsIndexWriter.getInstance().putMapping(this.getMappingBeanClass());
		System.out.println("acknowledged:" + response.isAcknowledged());
	}

	private void executeGetMapping() {
		System.out.println(EsIndexWriter.getInstance().getMapping(this.getMappingBeanClass()));
	}

	private void executeDeleteMapping() {
		DeleteMappingResponse response = EsIndexWriter.getInstance().deleteMapping(this.getMappingBeanClass());
		System.out.println("acknowledged:" + response.isAcknowledged());
	}
	
	private Class<?> getMappingBeanClass() {
		try {
			return Class.forName(this.mappingBean);
		} catch (ClassNotFoundException ex) {
			throw new RuntimeException(ex);
		}
	}

	private void loadParameters(Option option) {
		String[] values = option.getValues();
		if (values.length != 2) {
			throw new IllegalArgumentException("You must specify <action> <mapping-bean> arguments in order to use this mapping command");
		}
		
		this.action = values[0];
		this.mappingBean = values[1];
	}
	
	private void validateParamaters() {
		if (!Arrays.asList(ACTIONS).contains(this.action)) {
			throw new IllegalArgumentException("Mapping action must be one of " +
					Arrays.toString(ACTIONS) + ". Found: " + this.action);
		}
	}

	public String getAction() {
		return action;
	}

	public String getMappingBean() {
		return mappingBean;
	}
}