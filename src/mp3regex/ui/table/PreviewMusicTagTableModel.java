package mp3regex.ui.table;

import java.util.regex.Pattern;

import mp3regex.model.Project;
import mp3regex.model.ProjectAdapter;
import mp3regex.model.Song;

public class PreviewMusicTagTableModel extends AbstractMusicTagTableModel {

	private static final long serialVersionUID = -2432382397329710434L;
	
	private static final int TITLE_COLUMN = 0;
	private static final int ARTIST_COLUMN = 1;
	private static final int ALBUM_COLUMN = 2;
	private static final int GENRE_COLUMN = 3;
	
	public PreviewMusicTagTableModel() {
		super();
		init();
	}
	
	private void init() {
		Project.getInstance().addProjectListener(new ProjectAdapter() {
			
			@Override
			public void findRegexChanged(final Project project) {
				update(project);
			}

			@Override
			public void replaceStringChanged(final Project project) {
				update(project);
			}
			
			@Override
			public void findTagChanged(final Project project) {
				update(project);
			}
			
		});
	}
	
	private void update(final Project project) {
		super.fireTableStructureChanged();
	}

	// TODO clean up this method
	@Override
	public Object getValueAt(int row, int col) {
		final Song.Tag tag = Project.getInstance().getFindTag();
		final Object value;
		if (col == TITLE_COLUMN)
			if (tag == Song.Tag.Title)
				value = findAndReplace(songs.get(row).getTitle());
			else
				value = songs.get(row).getTitle();
		else if (col == ARTIST_COLUMN)
			if (tag == Song.Tag.Artist)
				value = findAndReplace(songs.get(row).getArtist());
			else
				value = songs.get(row).getArtist();
		else if (col == ALBUM_COLUMN)
			if (tag == Song.Tag.Album)
				value = findAndReplace(songs.get(row).getAlbum());
			else
				value = songs.get(row).getAlbum();
//		else if (col == GENRE_COLUMN)
//			if (tag == Song.Tag.Genre)
//				value = findAndReplace(songs.get(row).getGenre());
//			else
//				value = songs.get(row).getGenre();
		else
			value = null;
		return value;
	}
	
	private Object findAndReplace(final String string) {
		final Pattern find = Project.getInstance().getFindRegex();
		final String replace = Project.getInstance().getReplaceString();
		if (find == null)
			return string;
		return find.matcher(string).replaceAll(replace);
	}
	
}
