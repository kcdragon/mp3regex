package mp3regex.ui.table;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

import mp3regex.model.Project;
import mp3regex.model.ProjectAdapter;
import mp3regex.model.Song;

public abstract class AbstractMusicTagTableModel extends AbstractTableModel {

	private static final long serialVersionUID = -513147880823941472L;
	
	private static final String[] COLUMNS = new String[] {
		"Title", "Artist", "Album"//, "Genre"
	};
	
	protected List<Song> songs = new ArrayList<Song>();
	protected final Object[] columns;
	
	protected AbstractMusicTagTableModel() {
		super();
		this.columns = COLUMNS;
		init();
	}
	
	protected AbstractMusicTagTableModel(final Object[] columns) {
		super();
		this.columns = new String[columns.length + COLUMNS.length];
		System.arraycopy(columns, 0, this.columns, 0, columns.length);
		System.arraycopy(COLUMNS, 0, this.columns, columns.length, COLUMNS.length);
		init();
	}
	
	private void init() {
		Project.getInstance().addProjectListener(new ProjectAdapter(){
			
			@Override
			public void songsChanged(final Project project) {
				update(project);
			}
			
		});
	}
	
	private void update(final Project project) {
		if (songs == null) {
			this.songs = new ArrayList<Song>();
		}
		else {
			this.songs = project.getSongs();
		}
		updateHelper(project);
		super.fireTableStructureChanged();
	}
	
	protected void updateHelper(final Project project) {}
	
	public Song getSong(final int row) {
		return songs.get(row);
	}
	
	@Override
	public int getColumnCount() {
		return columns.length;
	}

	@Override
	public int getRowCount() {
		return songs.size();
	}
	
	@Override
	public String getColumnName(final int col) {
        return columns[col].toString();
    }
	
}
