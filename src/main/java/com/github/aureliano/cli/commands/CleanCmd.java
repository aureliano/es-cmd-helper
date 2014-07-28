package com.github.aureliano.cli.commands;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class CleanCmd implements ICommand {

	public static final String NAME = "clean";
	public static final String DESCRIPTION = "Delete all files created 10 days ago";
	
	public CleanCmd() {
		super();
	}
	
	@Override
	public void execute() {
		Date seed = this.getDateSeed();
		
		this.cleanTmpFolder(seed);
		this.cleanLogFolder(seed);
	}

	private void cleanLogFolder(Date seed) {
		File logDir = new File("log");
		System.out.println("Deleting files created 10 days ago from " + logDir.getAbsolutePath());
		this.deleteFiles(seed, logDir, this.getLogFilenameFilter());
	}

	private void cleanTmpFolder(Date seed) {
		File tmpDir = new File("tmp");
		System.out.println("Deleting files created 10 days ago from " + tmpDir.getAbsolutePath());
		this.deleteFiles(seed, tmpDir, this.getTmpFilenameFilter());
	}
	
	private void deleteFiles(Date seed, File dir, FilenameFilter filtername) {
		if (!dir.exists() || !dir.isDirectory()) {
			return;
		}
		
		int count = 0;
		for (File file : dir.listFiles(filtername)) {
			long diff = seed.getTime() - file.lastModified();
			diff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
			
			if (diff <= 10) {
				file.delete();
				count ++;
			}
		}
		
		System.out.println("Files deleted: " + count);
	}
	
	private Date getDateSeed() {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.DAY_OF_MONTH, -10);
		
		return calendar.getTime();
	}
	
	private FilenameFilter getLogFilenameFilter() {
		return this.getFilenameFilter(".log");
	}
	
	private FilenameFilter getTmpFilenameFilter() {
		return this.getFilenameFilter(".tmp");
	}
	
	private FilenameFilter getFilenameFilter(final String extension) {
		return new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				if (name.lastIndexOf('.') > 0) {
					int lastIndex = name.lastIndexOf('.');
  
					String str = name.substring(lastIndex);
					if (str.equals(extension)) {
						return true;
					}
				}
				return false;
			}
		};
	}
}