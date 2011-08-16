package mp3regex.ui.menu;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.List;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import mp3regex.model.AudioParser;
import mp3regex.model.DirectoryParser;
import mp3regex.model.Project;

public final class OpenDirectoryAction extends AbstractAction {

	private static final long serialVersionUID = -7330082175882866844L;
	
	private final Component parent;
	private final JFileChooser chooser = new JFileChooser();

	public OpenDirectoryAction(final Component parent) {
		super();
		this.parent = parent;
		init();
	}
	
	private void init() {
		putValue(NAME, "Open Directory...");
		putValue(MNEMONIC_KEY, (int)'O');
		putValue(SHORT_DESCRIPTION, "Open Directory");
		
		chooser.setDialogTitle("Open Directory...");
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
	}
	
	@Override
	public void actionPerformed(final ActionEvent e) {
		final int choice = chooser.showOpenDialog(parent);
		if (choice != JFileChooser.CANCEL_OPTION) {
			final File path = chooser.getSelectedFile();
			if (path.isDirectory()) {
				final List<File> files = DirectoryParser.parseDirectory(path);
				for (final File file : files)
					Project.getInstance().addSong(AudioParser.parse(file));
			}
		}
	}

}
