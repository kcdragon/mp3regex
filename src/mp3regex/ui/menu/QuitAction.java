package mp3regex.ui.menu;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JFrame;

public final class QuitAction extends AbstractAction {
	
	private static final long serialVersionUID = -94848653770493414L;
	
	private final JFrame parent;
	
	public QuitAction(final JFrame parent) {
		super();
		this.parent = parent;
		init();
	}
	
	private void init() {
		putValue(NAME, "Quit");
		putValue(MNEMONIC_KEY, (int)'Q');
		putValue(SHORT_DESCRIPTION, "Quit");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		parent.setVisible(false);
		parent.dispose();
	}

}
