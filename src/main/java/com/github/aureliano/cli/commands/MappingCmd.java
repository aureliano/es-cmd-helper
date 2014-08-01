package com.github.aureliano.cli.commands;

import java.util.Arrays;

import org.apache.commons.cli.Option;
import org.apache.log4j.Logger;

import com.github.aureliano.annotation.ESIndex;
import com.github.aureliano.es.ElasticSearchCommandHelper;

/**
 * Command line class executor. Execute actions on ElasticSearch
 * in mapping scope. Actions that are covered by this are: create, get and delete. 
 * 
 * <ul>
 *   <li><b>create</b> - Create an index type.</li>
 *   <li><b>get</b> - Retrieve an index type mapping.</li>
 *   <li><b>delete</b> - Delete an index type.</li>
 * </ul>
 */
public class MappingCmd implements ICommand {

	private static final Logger logger = Logger.getLogger(MappingCmd.class);
	private static final String[] ACTIONS = new String[] { "create", "get", "delete" };
	
	public static final String NAME = "mapping";
	public static final String DESCRIPTION = "Execute commands on mapping context like " +
			Arrays.toString(ACTIONS) + " straight to ElasticSearch";
	
	private String action;
	private String mappingBean;
	
	public MappingCmd() {
		super();
	}
	
	/**
	 * Execute mapping command.
	 */
	@Override
	public void execute() {
		logger.info("Execute Mapping command");
		this.validateParamaters();
		
		this.executeAction();
	}
	
	/**
	 * Execute mapping command.
	 * 
	 * @param option - Command line option {@link org.apache.commons.cli.Option}
	 */
	@Override
	public void execute(Option option) {
		logger.info("Execute Mapping command");
		this.loadParameters(option);
		this.validateParamaters();
		
		this.executeAction();
	}
	
	private void executeAction() {
		logger.info("Executing action " + this.action + " mapping");
		
		if (this.action.equals("create")) {
			this.executeCreateMapping();
		} else if (this.action.equals("get")) {
			this.executeGetMapping();
		} else if (this.action.equals("delete")) {
			this.executeDeleteMapping();
		}
	}

	private void executeCreateMapping() {
		Class<?> beanClass = this.getMappingBeanClass();
		ElasticSearchCommandHelper indexHelper = ElasticSearchCommandHelper.getInstance();
		
		if (!indexHelper.indexExist(beanClass)) {
			ESIndex annotation = beanClass.getAnnotation(ESIndex.class);
			String message = "Index %s does not exist. You must create it before you create a type.";
			message = message.replaceFirst("%s", (annotation == null ? "" : annotation.name()));
			
			throw new RuntimeException(message);
		}
		
		boolean acknowledged = ElasticSearchCommandHelper.getInstance().createMapping(beanClass);
		System.out.println("acknowledged:" + acknowledged);
	}

	private void executeGetMapping() {
		System.out.println(ElasticSearchCommandHelper.getInstance().getMapping(this.getMappingBeanClass()));
	}

	private void executeDeleteMapping() {
		boolean acknowledged = ElasticSearchCommandHelper.getInstance().deleteMapping(this.getMappingBeanClass());
		System.out.println("acknowledged:" + acknowledged);
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
	
	public MappingCmd withAction(String action) {
		this.action = action;
		return this;
	}

	public String getMappingBean() {
		return mappingBean;
	}
	
	public MappingCmd withMappingBean(String mappingBean) {
		this.mappingBean = mappingBean;
		return this;
	}
}