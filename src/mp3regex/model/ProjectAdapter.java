package mp3regex.model;

public abstract class ProjectAdapter implements ProjectListener {

	@Override
	public void findRegexChanged(final Project project) {}

	@Override
	public void replaceStringChanged(final Project project) {}

	@Override
	public void searchFilterChanged(final Project project) {}

	@Override
	public void songsChanged(final Project project) {}
	
	@Override
	public void findTagChanged(final Project project) {}
	
	@Override
	public void searchTagChanged(final Project project) {}

}
