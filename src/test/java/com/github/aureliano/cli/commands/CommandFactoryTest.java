package com.github.aureliano.cli.commands;

import org.junit.Assert;
import org.junit.Test;

import com.github.aureliano.cli.commands.CleanCmd;
import com.github.aureliano.cli.commands.CommandFactory;
import com.github.aureliano.cli.commands.HelpCmd;
import com.github.aureliano.cli.commands.VersionCmd;

public class CommandFactoryTest {

	@Test
	public void testCommand() {
		Assert.assertEquals(HelpCmd.class, CommandFactory.command(HelpCmd.NAME).getClass());
		Assert.assertEquals(VersionCmd.class, CommandFactory.command(VersionCmd.NAME).getClass());
		Assert.assertEquals(CleanCmd.class, CommandFactory.command(CleanCmd.NAME).getClass());
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