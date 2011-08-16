package mp3regex.ui.table;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.RowFilter;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableRowSorter;

import mp3regex.model.Project;
import mp3regex.model.ProjectAdapter;

public class MusicTagTable extends JTable {
	
	private static final long serialVersionUID = 2562006519088716883L;
	
	private final TableRowSorter<AbstractMusicTagTableModel> sorter;

	public MusicTagTable(final AbstractMusicTagTableModel model) {
		super(model);
		sorter = new TableRowSorter<AbstractMusicTagTableModel>(model);
		init();
	}
	
	private void init() {
		setRowSorter(sorter);
		Project.getInstance().addProjectListener(new ProjectAdapter() {
			
			@Override
			public void searchFilterChanged(final Project project) {
				update(project);
			}
			
			@Override
			public void searchTagChanged(final Project project) {
				update(project);
			}
			
		});
	}
	
	private void update(final Project project) {
		setFilter(new TagRowFilter(project.getSearchTag(), project.getSearchString()));
	}
	
	private void setFilter(final RowFilter<AbstractMusicTagTableModel, Integer> filter) {
		sorter.setRowFilter(filter);
	}
	
	// TODO figure out why this is breaking the selection of a row and making text invisible, probably has to do with the color of text not changing
//	@Override
//    public Component prepareRenderer(TableCellRenderer renderer,int row, int col) {
//		final Component component = super.prepareRenderer(renderer, row, col);
//		if (row % 2 == 0 && !isCellSelected(row, col))
//			component.setBackground(Color.LIGHT_GRAY);
//		else
//			component.setBackground(Color.WHITE);
//		return component;
//	}
	
}
