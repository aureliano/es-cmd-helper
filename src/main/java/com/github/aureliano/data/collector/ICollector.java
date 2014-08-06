package com.github.aureliano.data.collector;

public interface ICollector {

	public abstract void before();
	
	public abstract void after();
	
	public abstract void execute();
}