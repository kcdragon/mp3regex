package mp3regex.ui.table;

import javax.swing.RowFilter;

import mp3regex.model.Song;

public final class TagRowFilter extends RowFilter<AbstractMusicTagTableModel, Integer> {
	
	private final Song.Tag tag;
	private final String match;
	
	public TagRowFilter(final Song.Tag tag, final String match) {
		this.tag = tag;
		this.match = match;
	}

	@Override
	public boolean include(RowFilter.Entry<? extends AbstractMusicTagTableModel, ? extends Integer> entry) {
		final AbstractMusicTagTableModel songs = entry.getModel();
		final Song song = songs.getSong(entry.getIdentifier());
		if (tag == Song.Tag.Title)
			return song.getTitle().contains(match);
		else if (tag == Song.Tag.Artist)
			return song.getArtist().contains(match);
		else if (tag == Song.Tag.Album)
			return song.getAlbum().contains(match);
//		else if (tag == Song.Tag.Genre)
//			return song.getGenre().contains(match);
		else
			return true;
	}

}