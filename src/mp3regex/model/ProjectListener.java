package mp3regex.model;

public interface ProjectListener {
	void songsChanged(final Project project);
	void searchFilterChanged(final Project project);
	void searchTagChanged(final Project project);
	void findRegexChanged(final Project project);
	void replaceStringChanged(final Project project);
	void findTagChanged(final Project project);
}
