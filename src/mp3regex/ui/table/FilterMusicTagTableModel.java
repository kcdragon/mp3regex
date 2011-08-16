package mp3regex.ui.table;

public final class FilterMusicTagTableModel extends AbstractMusicTagTableModel {
	
	private static final long serialVersionUID = -3936181499045366254L;
	
	//private static final int CHECK_COLUMN = 0;
	private static final int FILE_COLUMN = 0;
	private static final int TITLE_COLUMN = 1;
	private static final int ARTIST_COLUMN = 2;
	private static final int ALBUM_COLUMN = 3;
	private static final int GENRE_COLUMN = 4;
	
	//private List<Boolean> checks = new ArrayList<Boolean>();
	
	public FilterMusicTagTableModel() {
		//super(new String[] {"Check", "File"});
		super(new String[] {"File"});
	}
	
//	@Override
//	protected void updateHelper(final Project project) {
//		for (int i = 0; i < songs.size(); ++i)
//			checks.add(true);
//	}

	@Override
	public Object getValueAt(int row, int col) {
		final Object value;
//		if (col == CHECK_COLUMN)
//			value = checks.get(row);
		if (col == FILE_COLUMN)
			value = songs.get(row).getFile();
		else if (col == TITLE_COLUMN)
			value = songs.get(row).getTitle();
		else if (col == ARTIST_COLUMN)
			value = songs.get(row).getArtist();
		else if (col == ALBUM_COLUMN)
			value = songs.get(row).getAlbum();
		else if (col == GENRE_COLUMN)
			value = songs.get(row).getGenre();
		else
			value = null;
		return value;
	}
	
//	@Override
//	public void setValueAt(final Object value, final int row, final int col) {
//		if (col == CHECK_COLUMN)
//			checks.set(row, (Boolean)value);
//	}
	
//	@Override
//	public boolean isCellEditable(int row, int col) {
//		return col == CHECK_COLUMN;
//	}
	
//	@Override
//	public Class<?> getColumnClass(final int col) {
//		if (col == CHECK_COLUMN)
//			return Boolean.class;
//        return super.getColumnClass(col);
//    }
	
}
