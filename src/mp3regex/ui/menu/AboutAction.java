package mp3regex.ui.menu;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

public final class AboutAction extends AbstractAction {

	private static final long serialVersionUID = 1210245408484554782L;
	
	public AboutAction() {
		super();
		init();
	}
	
	private void init() {
		putValue(NAME, "About...");
		putValue(MNEMONIC_KEY, (int)'A');
		putValue(SHORT_DESCRIPTION, "About");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO add dialog box for about
	}

}
