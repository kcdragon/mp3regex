package mp3regex.ui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import mp3regex.model.Project;
import mp3regex.model.Song.Type;
import mp3regex.ui.menu.AboutAction;
import mp3regex.ui.menu.OpenDirectoryAction;
import mp3regex.ui.menu.OpenFileAction;
import mp3regex.ui.menu.QuitAction;
import mp3regex.ui.table.FilterMusicTagTableModel;
import mp3regex.ui.table.MusicTagTable;
import mp3regex.ui.table.PreviewMusicTagTableModel;

public final class Launcher extends JFrame {
	
	private static final long serialVersionUID = -4354223303336469958L;

	public Launcher() {
		super("mp3 Regex");
		init();
	}
	
	public void init() {
		setLayout(new BorderLayout());
		setJMenuBar(buildMenus());
		add(createComponents(), BorderLayout.CENTER);
		pack();
	}
	
	public Component createComponents() {
		final JPanel main = new JPanel(new BorderLayout());
		
		final JPanel top = new JPanel(new FlowLayout());
		top.add(new SearchPanel());
		top.add(new FindReplacePanel());
		
		final JTable filter = new MusicTagTable(new FilterMusicTagTableModel());
		final JTable preview = new MusicTagTable(new PreviewMusicTagTableModel());
		
		final JScrollPane scrollleft = new JScrollPane(filter);
		final JScrollPane scrollright = new JScrollPane(preview);
		
		final JPanel tables = new JPanel(new GridLayout(1,2));
		tables.add(scrollleft);
		tables.add(scrollright);
		
		main.add(top, BorderLayout.NORTH);
		main.add(tables, BorderLayout.CENTER);
		
		return main;
	}
	
	private JMenuBar buildMenus() {
		final JMenuBar bar = new JMenuBar();
		bar.add(buildFileMenu());
		bar.add(buildHelpMenu());
		return bar;
	}
	
	private JMenu buildFileMenu() {
		final JMenu menu = new JMenu("File");
		menu.setMnemonic('F');
		menu.add(new JMenuItem(new OpenDirectoryAction(this)));
		menu.add(new JMenuItem(new OpenFileAction(this)));
		menu.addSeparator();
		menu.add(new JMenuItem(new QuitAction(this)));
		return menu;
	}
	
	private JMenu buildHelpMenu() {
		final JMenu menu = new JMenu("Help");
		menu.setMnemonic('H');
		menu.add(new JMenuItem(new AboutAction()));
		return menu;
	}
	
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException {
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		
		EventQueue.invokeLater(new Runnable() {
			
			@Override
			public void run()
			{
				final JFrame frame = new Launcher();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
				frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
				
				// TODO test code, get rid of when releasing
//				for (int i = 0; i < 100; ++i)
//					Project.getInstance().addSong(new mp3regex.model.Song("file" + i, Type.MP3, "title" + i, "artist" + i, "album" + i, "genre" + i));
			}
			
		});
	}
	
}
