package ai.timefold.solver.examples.common.app;

import java.awt.Component;
import java.io.File;
import java.util.Collections;
import java.util.Set;
import java.util.function.BiConsumer;

import javax.swing.WindowConstants;

import ai.timefold.solver.core.api.domain.solution.PlanningSolution;
import ai.timefold.solver.core.api.solver.SolverFactory;
import ai.timefold.solver.examples.common.business.SolutionBusiness;
import ai.timefold.solver.examples.common.persistence.AbstractSolutionExporter;
import ai.timefold.solver.examples.common.persistence.AbstractSolutionImporter;
import ai.timefold.solver.examples.common.swingui.SolutionPanel;
import ai.timefold.solver.examples.common.swingui.SolverAndPersistenceFrame;
import ai.timefold.solver.persistence.common.api.domain.solution.SolutionFileIO;
import ai.timefold.solver.swing.impl.SwingUncaughtExceptionHandler;
import ai.timefold.solver.swing.impl.SwingUtils;

/**
 * @param <Solution_> the solution type, the class with the {@link PlanningSolution} annotation
 */
public abstract class CommonApp<Solution_> extends LoggingMain {

    /**
     * The path to the data directory, preferably with unix slashes for portability.
     * For example: -D{@value #DATA_DIR_SYSTEM_PROPERTY}=sources/data/
     */
    public static final String DATA_DIR_SYSTEM_PROPERTY = "ai.timefold.solver.examples.dataDir";

    public static File determineDataDir(String dataDirName) {
        String dataDirPath = System.getProperty(DATA_DIR_SYSTEM_PROPERTY, "data/");
        File dataDir = new File(dataDirPath, dataDirName);
        if (!dataDir.exists()) {
            throw new IllegalStateException("The directory dataDir (" + dataDir.getAbsolutePath()
                    + ") does not exist.\n" +
                    " Either the working directory should be set to the directory that contains the data directory" +
                    " (which is not the data directory itself), or the system property "
                    + DATA_DIR_SYSTEM_PROPERTY + " should be set properly.\n" +
                    " The data directory is different in a git clone (timefold/timefold-solver-examples/data)" +
                    " and in a release zip (examples/sources/data).\n" +
                    " In an IDE (IntelliJ, Eclipse, VSCode), open the \"Run configuration\""
                    + " to change \"Working directory\" (or add the system property in \"VM options\").");
        }
        return dataDir;
    }

    /**
     * Some examples are not compatible with every native LookAndFeel.
     */
    public static void prepareSwingEnvironment() {
        SwingUncaughtExceptionHandler.register();
        SwingUtils.fixateLookAndFeel();
    }

    protected final String name;
    protected final String description;
    protected final String solverConfigResource;
    protected final String dataDirName;
    protected final String iconResource;

    protected SolverAndPersistenceFrame<Solution_> solverAndPersistenceFrame;
    protected SolutionBusiness<Solution_, ?> solutionBusiness;

    protected CommonApp(String name, String description, String solverConfigResource, String dataDirName, String iconResource) {
        this.name = name;
        this.description = description;
        this.solverConfigResource = solverConfigResource;
        this.dataDirName = dataDirName;
        this.iconResource = iconResource;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getSolverConfigResource() {
        return solverConfigResource;
    }

    public String getDataDirName() {
        return dataDirName;
    }

    public String getIconResource() {
        return iconResource;
    }

    public void init() {
        init(null, true);
    }

    public void init(Component centerForComponent, boolean exitOnClose) {
        solutionBusiness = createSolutionBusiness();
        solverAndPersistenceFrame = new SolverAndPersistenceFrame<>(solutionBusiness, createSolutionPanel(),
                createExtraActions());
        solverAndPersistenceFrame
                .setDefaultCloseOperation(exitOnClose ? WindowConstants.EXIT_ON_CLOSE : WindowConstants.DISPOSE_ON_CLOSE);
        solverAndPersistenceFrame.init(centerForComponent);
        solverAndPersistenceFrame.setVisible(true);
    }

    public SolutionBusiness<Solution_, ?> createSolutionBusiness() {
        SolutionBusiness<Solution_, ?> solutionBusiness = new SolutionBusiness<>(this,
                SolverFactory.createFromXmlResource(solverConfigResource));
        solutionBusiness.setDataDir(determineDataDir(dataDirName));
        solutionBusiness.setSolutionFileIO(createSolutionFileIO());
        solutionBusiness.setImporters(createSolutionImporters());
        solutionBusiness.setExporters(createSolutionExporters());
        solutionBusiness.updateDataDirs();
        return solutionBusiness;
    }

    protected abstract SolutionPanel<Solution_> createSolutionPanel();

    protected ExtraAction<Solution_>[] createExtraActions() {
        return new ExtraAction[0];
    }

    /**
     * Used for the unsolved and solved directories,
     * not for the import and output directories, in the data directory.
     *
     * @return never null
     */
    public abstract SolutionFileIO<Solution_> createSolutionFileIO();

    protected Set<AbstractSolutionImporter<Solution_>> createSolutionImporters() {
        return Collections.emptySet();
    }

    protected Set<AbstractSolutionExporter<Solution_>> createSolutionExporters() {
        return Collections.emptySet();
    }

    public interface ExtraAction<Solution_> extends BiConsumer<SolutionBusiness<Solution_, ?>, SolutionPanel<Solution_>> {

        String getName();

    }

}
