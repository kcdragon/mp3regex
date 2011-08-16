package mp3regex.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Converter {

	public static List<Song> findAndReplace(final List<Song> songs, final String search, final Song.Tag searchtag, final Pattern find, final String replace, final Song.Tag findtag) {
		final List<Song> changed = new ArrayList<Song>();
		for (final Song song : songs)
			if ((searchtag == Song.Tag.Title && song.getTitle().contains(search)) ||
					(searchtag == Song.Tag.Artist && song.getArtist().contains(search)) ||
					(searchtag == Song.Tag.Album && song.getAlbum().contains(search)))// ||
					//(searchtag == Song.Tag.Genre && song.getGenre().contains(search)))
				changed.add(song);
		for (final Song song : changed)
			findAndReplace(song, find, replace, findtag);
		return changed;
	}
	
	public static void findAndReplace(final Song song, final Pattern find, final String replace, final Song.Tag tag) {
		if (tag == Song.Tag.Title)
			song.setTitle(find.matcher(song.getTitle()).replaceAll(replace));
		else if (tag == Song.Tag.Artist)
			song.setArtist(find.matcher(song.getArtist()).replaceAll(replace));
		else if (tag == Song.Tag.Album)
			song.setAlbum(find.matcher(song.getAlbum()).replaceAll(replace));
//		else if (tag == Song.Tag.Genre)
//			song.setGenre(find.matcher(song.getGenre()).replaceAll(replace));
	}
	
}