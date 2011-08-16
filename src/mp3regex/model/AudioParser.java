package mp3regex.model;

import java.io.File;

import mp3regex.model.Song.Type;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

public final class AudioParser {

	public static final Song parse(final File file) {
		Song song = null;
		try {
			final AudioFile f = AudioFileIO.read(file);
			final Tag tag = f.getTag();
			song = new Song(file.getAbsolutePath(),
					Type.parse(file.getName().substring(file.getName().indexOf('.'))),
					tag.getFirst(FieldKey.TITLE),
					tag.getFirst(FieldKey.ARTIST),
					tag.getFirst(FieldKey.ALBUM),
					tag.getFirst(FieldKey.GENRE));
		}
		catch (final Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
		return song;
	}
	
	public static final void update(final Song song) {
		try {
			final AudioFile f = AudioFileIO.read(new File(song.getFile()));
			final Tag tag = f.getTag();
			tag.setField(FieldKey.TITLE, song.getTitle());
			tag.setField(FieldKey.ARTIST, song.getArtist());
			tag.setField(FieldKey.ALBUM, song.getAlbum());
			tag.setField(FieldKey.GENRE, song.getGenre());
			f.commit();
		}
		catch (final Exception e) {
			e.printStackTrace();
			System.exit(1);
		}
	}
	
}
