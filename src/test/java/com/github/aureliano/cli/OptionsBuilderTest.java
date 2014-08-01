package com.github.aureliano.cli;

import org.apache.commons.cli.Options;
import org.junit.Assert;
import org.junit.Test;

import com.github.aureliano.cli.commands.CleanCmd;
import com.github.aureliano.cli.commands.HelpCmd;
import com.github.aureliano.cli.commands.IndexCmd;
import com.github.aureliano.cli.commands.MappingCmd;

public class OptionsBuilderTest {

	@Test
	public void testBuildDefaultOptions() {
		Options opt = OptionsBuilder.buildDefaultOptions();
		Assert.assertEquals(4, opt.getOptions().size());
		
		Assert.assertTrue(opt.hasOption(HelpCmd.NAME));
		Assert.assertTrue(opt.hasOption(CleanCmd.NAME));
		Assert.assertTrue(opt.hasOption(MappingCmd.NAME));
		Assert.assertTrue(opt.hasOption(IndexCmd.NAME));
	}
}