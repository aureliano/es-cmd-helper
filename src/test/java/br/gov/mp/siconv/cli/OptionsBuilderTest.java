package br.gov.mp.siconv.cli;

import org.apache.commons.cli.Options;
import org.junit.Assert;
import org.junit.Test;

import br.gov.mp.siconv.cli.commands.CleanCmd;
import br.gov.mp.siconv.cli.commands.HelpCmd;
import br.gov.mp.siconv.cli.commands.VersionCmd;

public class OptionsBuilderTest {

	@Test
	public void testBuildDefaultOptions() {
		Options opt = OptionsBuilder.buildDefaultOptions();
		Assert.assertEquals(3, opt.getOptions().size());
		
		Assert.assertTrue(opt.hasOption(HelpCmd.NAME));
		Assert.assertTrue(opt.hasOption(VersionCmd.NAME));
		Assert.assertTrue(opt.hasOption(CleanCmd.NAME));
	}
}