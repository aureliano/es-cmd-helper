package com.github.aureliano.cli.commands;

import org.junit.Assert;
import org.junit.Test;

public class CommandFactoryTest {

	@Test
	public void testCommand() {
		Assert.assertEquals(HelpCmd.class, CommandFactory.command(HelpCmd.NAME).getClass());
		Assert.assertEquals(CleanCmd.class, CommandFactory.command(CleanCmd.NAME).getClass());
		Assert.assertEquals(MappingCmd.class, CommandFactory.command(MappingCmd.NAME).getClass());
		Assert.assertEquals(IndexCmd.class, CommandFactory.command(IndexCmd.NAME).getClass());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCommandNullCommand() {
		CommandFactory.command(null);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCommandEmptyCommand() {
		CommandFactory.command("");
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testCommandNonExistent() {
		CommandFactory.command("test");
	}
}