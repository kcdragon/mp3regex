package mp3regex.ui;

import javax.swing.JComboBox;

import mp3regex.model.Song;

public class MusicTypeDropDown extends JComboBox {

	private static final long serialVersionUID = 8122410658299464777L;

	public MusicTypeDropDown() {
		addItem("All");
		for (Object item : Song.Type.values())
			addItem(item);
	}
	
}
