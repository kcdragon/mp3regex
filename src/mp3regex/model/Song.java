package mp3regex.model;

public final class Song {
	
	public enum Type {
		MP3;
		//FLAC;
		
		public static Type parse(final String type) {
			if (type.equals("mp3"))
				return MP3;
//			else if (type.equals("flac"))
//				return FLAC;
			else
				return null;
		}
		
		@Override
		public String toString() {
			if (this ==  MP3)
				return "mp3";
//			else if (this == FLAC)
//				return "flac";
			else
				return null;
		}
	}
	
	public enum Tag {
		Artist,
		Album,
		Title//,
		//Genre
	}
	
	private final String file;
	private final Type type;
	private String title;
	private String artist;
	private String album;
	private String genre;
	
	public Song(final String file, final Type type, final String title, final String artist, final String album, final String genre) {
		this.file = file;
		this.type = type;
		this.title = title;
		this.artist = artist;
		this.album = album;
		this.genre = genre;
	}
	
	public String getFile() {
		return file;
	}
	
	public Type getType() {
		return type;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(final String title) {
		this.title = title;
	}
	
	public String getArtist() {
		return artist;
	}
	
	public void setArtist(final String artist) {
		this.artist = artist;
	}
	
	public String getAlbum() {
		return album;
	}
	
	public void setAlbum(final String album) {
		this.album = album;
	}
	
	public String getGenre() {
		return genre;
	}
	
	public void setGenre(final String genre) {
		this.genre = genre;
	}
}
