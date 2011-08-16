package mp3regex.model;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public final class Project {
	
	private static final Project instance = new Project();
	
	public static Project getInstance() {
		return instance;
	}
	
	private final List<Song> songs = new ArrayList<Song>();
	private String search = "";
	private Song.Tag searchtag = null;
	private Pattern regex = Pattern.compile("");
	private String replace = "";
	private Song.Tag findtag = null;
	private final List<ProjectListener> listeners = new ArrayList<ProjectListener>();
	
	private Project() {}
	
	public List<Song> getSongs() {
		return songs;
	}
	
	public void refresh() {
		fireSongsChange();
	}
	
	public void addSong(final Song song) {
		songs.add(song);
		fireSongsChange();
	}
	
	public void removeSong(final Song song) {
		songs.remove(song);
		fireSongsChange();
	}
	
	public String getSearchString() {
		return search;
	}
	
	public void setSearchString(final String search) {
		this.search = search;
		fireSearchFilterChange();
	}
	
	public Song.Tag getSearchTag() {
		return searchtag;
	}
	
	public void setSearchTag(final Song.Tag tag) {
		this.searchtag = tag;
		fireSearchTagChanged();
	}
	
	public Pattern getFindRegex() {
		return regex;
	}
	
	public void setFindRegex(final Pattern regex) {
		this.regex = regex;
		fireFindRegexChange();
	}
	
	public String getReplaceString() {
		return replace;
	}
	
	public void setReplaceString(final String replace) {
		this.replace = replace;
		fireReplaceStringChange();
	}
	
	public Song.Tag getFindTag() {
		return findtag;
	}
	
	public void setFindTag(final Song.Tag tag) {
		this.findtag = tag;
		fireFindTagChanged();
	}
	
	public void addProjectListener(final ProjectListener listener) {
		synchronized (listener) {
			listeners.add(listener);
		}
	}
	
	public void removeProjectListener(final ProjectListener listener) {
		synchronized (listener) {
			listeners.remove(listener);
		}
	}
	
	private void fireSongsChange() {
		for (final ProjectListener list : listeners)
			list.songsChanged(this);
	}
	
	private void fireSearchFilterChange() {
		for (final ProjectListener list : listeners)
			list.searchFilterChanged(this);
	}
	
	private void fireSearchTagChanged() {
		for (final ProjectListener list : listeners)
			list.findTagChanged(this);
	}
	
	private void fireFindRegexChange() {
		for (final ProjectListener list : listeners)
			list.findRegexChanged(this);
	}
	
	private void fireReplaceStringChange() {
		for (final ProjectListener list : listeners)
			list.replaceStringChanged(this);
	}
	
	private void fireFindTagChanged() {
		for (final ProjectListener list : listeners)
			list.findTagChanged(this);
	}

}
