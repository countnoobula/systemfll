package MainClasses;

import DataSystem.LogicDatabase;
import java.io.Serializable;
import DataSystem.PlannerDatabase;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SystemProject implements Serializable {

    private String projectName;
    private String projectAuthor;
    private String projectDate;
    private double saveVersion;
    private ArrayList<PlannerDatabase> plannerDatabase;
    private ArrayList<LogicDatabase> logicDatabase;
    public static int currentPlanner = 0;
    public static int currentLogic = 0;

    /**
     * Create a new System project with the following parameters:
     * @param name
     * The name of the project
     * @param author
     * The project Author
     */
    public SystemProject(String name, String author) {

        this.projectName = name;
        this.projectAuthor = author;

        //computes the exact date of creation
        Date now = new Date();
        DateFormat df = DateFormat.getDateInstance();
        this.projectDate = df.format(now);

        //a checker for checking what version of the program this is compatable with
        saveVersion = SystemInformation.saveVersion;

        //Create the databases
        this.plannerDatabase = new ArrayList<PlannerDatabase>(0);
        this.logicDatabase = new ArrayList<LogicDatabase>(0);

        this.plannerDatabase.add(new PlannerDatabase());
        this.plannerDatabase.add(new PlannerDatabase());
        this.logicDatabase.add(new LogicDatabase());
        this.logicDatabase.add(new LogicDatabase());
        this.logicDatabase.add(new LogicDatabase());
    }

    /**
     * Sets the project Name. Also used for saving a file (append name to file name)
     * @param projectName
     * The project name
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * Returns the project title
     * @return
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * Sets the project Author
     * @param projectAuthor
     * Author name
     */
    public void setProjectAuthor(String projectAuthor) {
        this.projectAuthor = projectAuthor;
    }

    public String getProjectAuthor() {
        return projectAuthor;
    }

    public void setProjectDate(String projectDate) {
        this.projectDate = projectDate;
    }

    public String getProjectDate() {
        return projectDate;
    }

    public PlannerDatabase getPlannerDatabase(int id) {
        return plannerDatabase.get(id);
    }

    public double getSaveVersion() {
        return saveVersion;
    }

    public LogicDatabase getLogicDatabase(int id) {
        return logicDatabase.get(id);
    }

    public void updateData(EngineDepo depo) {

    }
    public int getVisualLogicSize(){
        return this.logicDatabase.size();
    }
    public int getPlannerDatabaseSize(){
        return this.plannerDatabase.size();
    }
}
