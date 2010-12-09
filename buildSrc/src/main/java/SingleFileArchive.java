import org.gradle.api.internal.file.FileResolver;
import org.gradle.api.internal.file.copy.CopyActionImpl;
import org.gradle.api.internal.file.copy.FileCopyActionImpl;
import org.gradle.api.internal.file.copy.FileCopySpecVisitor;
import org.gradle.api.tasks.AbstractCopyTask;
import org.gradle.api.tasks.bundling.AbstractArchiveTask;

public class SingleFileArchive extends AbstractArchiveTask {
    private CopyActionImpl action;
    private FileResolver resolver;

    public SingleFileArchive() {
        resolver = getServices().get(FileResolver.class);
        //action = new FileCopyAction(resolver);
        action = new FileCopyActionImpl(getServices().get(FileResolver.class), new FileCopySpecVisitor());
        System.err.println("In SingleFileArchive Constructor");
    }

    @Override
    public AbstractCopyTask into(Object destDir) {
        action.into(destDir);
        setDestinationDir(resolver.resolve(destDir));
        return this;
    }

    /*
    @Override
    public File getArchivePath() {
        File result = super.getArchivePath();
        System.out.println("Outer getArchivePath returned:"+result.getPath());
        return result;    //To change body of overridden methods use File | Settings | File Templates.
    }
    */

    @Override
    protected CopyActionImpl getCopyAction() {
        return action;
    }
}