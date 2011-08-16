package mp3regex.ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTextField;

import mp3regex.model.AudioParser;
import mp3regex.model.Converter;
import mp3regex.model.Project;
import mp3regex.model.Song;
import mp3regex.model.Song.Tag;

public final class FindReplacePanel extends JPanel {

	private static final long serialVersionUID = 4819534721107751774L;
	
	private final JTextField find = new JTextField(20);
	private final JTextField replace = new JTextField(20);
	private final JComboBox tag = new JComboBox(Tag.values());
	
	public FindReplacePanel() {
		super(new FlowLayout());
		init();
	}
	
	private void init() {
		find.setText("Find");
		find.addFocusListener(new FocusAdapter(){

			@Override
			public void focusLost(final FocusEvent e) {
				if (find.getText().equals("")) {
					find.setText("Find");
					Project.getInstance().setFindRegex(null);
				}
				else
					Project.getInstance().setFindRegex(getPattern(find.getText()));
			}
			
			@Override
			public void focusGained(final FocusEvent e) {
				if (find.getText().equals("Find"))
					find.setText("");
			}
			
		});
		
		replace.setText("Replace");
		replace.addFocusListener(new FocusAdapter(){

			@Override
			public void focusLost(final FocusEvent e) {
				if (replace.getText().equals("")) {
					replace.setText("Replace");
					Project.getInstance().setReplaceString("");
				}
				else
					Project.getInstance().setReplaceString(replace.getText());
			}
			
			@Override
			public void focusGained(final FocusEvent e) {
				if (replace.getText().equals("Replace"))
					replace.setText("");
			}
			
		});
		
		tag.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(final ItemEvent e) {
				setTag();
			}
			
		});
		setTag();
		
		final JButton run = new JButton("Run");
		run.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				apply();
			}
			
		});
		
		add(find);
		add(replace);
		add(tag);
		add(run);
	}
	
	private void apply() {
		final Project proj = Project.getInstance();
		final List<Song> songs = Converter.findAndReplace(proj.getSongs(), proj.getSearchString(), proj.getSearchTag(), proj.getFindRegex(), proj.getReplaceString(), proj.getFindTag());
		for (final Song song : songs)
			AudioParser.update(song);
		proj.refresh();
	}
	
	private void setTag() {
		Project.getInstance().setFindTag((Tag) tag.getSelectedItem());
	}
	
	private Pattern getPattern(final String regex) {
		Pattern pattern;
		try {
			pattern = Pattern.compile(regex);
		}
		catch (PatternSyntaxException ex) {
			pattern = null;
		}
		return pattern;
	}

}
