package mp3regex.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mp3regex.model.Project;
import mp3regex.model.Song;
import mp3regex.model.Song.Tag;

public final class SearchPanel extends JPanel {

	private static final long serialVersionUID = 2497056231292753907L;

	private final JTextField search = new JTextField(20);
	private final JComboBox tag = new JComboBox(Song.Tag.values());
	
	public SearchPanel() {
		super(new FlowLayout());
		init();
	}
	
	private void init() {
		search.setText("Search");
		search.addFocusListener(new FocusAdapter() {

			@Override
			public void focusLost(final FocusEvent e) {
				if (search.getText().equals("")) {
					search.setText("Search");
					search("");
				}
				else
					search(search.getText());
			}
			
			@Override
			public void focusGained(final FocusEvent e) {
				if (search.getText().equals("Search"))
					search.setText("");
			}
			
		});
		
		tag.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(final ItemEvent e) {
				setTag();
			}
			
		});
		setTag();
		
		add(search, BorderLayout.CENTER);
		add(tag, BorderLayout.EAST);
	}
	
	private void search(final String text) {
		final Song.Tag tag = (Song.Tag) this.tag.getSelectedItem();
		Project.getInstance().setSearchString(text);
		Project.getInstance().setSearchTag(tag);
	}
	
	private void setTag() {
		Project.getInstance().setSearchTag((Tag) tag.getSelectedItem());
	}
	
}
