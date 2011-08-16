package mp3regex.ui.menu;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import mp3regex.model.AudioParser;
import mp3regex.model.Project;

public class OpenFileAction extends AbstractAction {

	private static final long serialVersionUID = -3587785877389782497L;
	
	private final Component parent;
	private final JFileChooser chooser = new JFileChooser();

	public OpenFileAction(final Component parent) {
		super();
		this.parent = parent;
		init();
	}
	
	private void init() {
		putValue(NAME, "Open File...");
		putValue(MNEMONIC_KEY, (int)'O');
		putValue(SHORT_DESCRIPTION, "Open File");
		
		chooser.setDialogTitle("Open File...");
		chooser.setCurrentDirectory(new java.io.File("."));
		chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
		chooser.setAcceptAllFileFilterUsed(false);
	}
	
	@Override
	public void actionPerformed(final ActionEvent e) {
		final int choice = chooser.showOpenDialog(parent);
		if (choice != JFileChooser.CANCEL_OPTION) {
			final File[] files = chooser.getSelectedFiles();
			for (final File file : files)
				if (!file.isDirectory())
					Project.getInstance().addSong(AudioParser.parse(file));
		}
	}

}
