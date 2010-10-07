package MainClasses;

import java.io.Serializable;

import DataSystem.ObjectDatabase;
import DataSystem.PlannerDatabase;

import java.text.DateFormat;

import java.util.Date;

public class SystemProject implements Serializable {

    private String projectName;
    private String projectAuthor;
    private String projectDate;
    private double saveVersion;

    private ObjectDatabase objectDatabase;
    private PlannerDatabase plannerDatabase;

    /**
     * Create a new System project with the following paramaters:
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
        this.objectDatabase = new ObjectDatabase();
        this.plannerDatabase = new PlannerDatabase();
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

    public ObjectDatabase getObjectDatabase() {
        return objectDatabase;
    }

    public PlannerDatabase getPlannerDatabase() {
        return plannerDatabase;
    }

    public double getSaveVersion() {
        return saveVersion;
    }
}
