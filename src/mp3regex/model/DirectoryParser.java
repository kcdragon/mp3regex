package mp3regex.model;

import java.io.File;
import java.io.FileFilter;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.List;

public final class DirectoryParser {
	
	private static final FilenameFilter musicfilter = new FilenameFilter() {
		
		public boolean accept(final File dir, final String name) {
	        return name.endsWith("mp3") || name.endsWith("flac");
	    }
		
	};
	
	private static final FileFilter dirfilter = new FileFilter() {
		
		public boolean accept(final File file) {
	        return file.isDirectory();
	    }
		
	};
	
	
	public static List<File> parseDirectory(final File dir) {
		final List<File> files = new ArrayList<File>();
		for (final File file : dir.listFiles(musicfilter))
			files.add(file);
		for (final File subdir : dir.listFiles(dirfilter))
			files.addAll(parseDirectory(subdir));
		return files;
	}
	
}
