package SystemObjects;

import java.awt.Rectangle;
import java.io.Serializable;
import java.text.DateFormat;
import java.util.Date;

/**
 * This is the building block for all objects which can be used in the Object Manager.
 * It contains information which all objects will inherit such as author information and
 * inventory data
 */
public abstract class SystemObject implements Serializable {
    
    //Data used for the object indexing
    private int ID;
    private static int total;
    private String type;
    private String title = "System Object";
    private String description = "A generic Object";
    private Rectangle rect;
    //Author Data
    private String author = "Anonoymous";
    private String email = "-";
    private String creationDate = "";

    public SystemObject(String type){
        total++;
        ID = total;
        //computes the exact date of creation
        Date now = new Date();
        DateFormat df = DateFormat.getDateInstance();
        this.creationDate = df.format(now);
    }


    /**
     * Sets the ID object
     * @param ID
     * The new ID of the object
     */
    public void setID(int ID) {
        this.ID = ID;
    }
    /**
     * Gets the current ID of the object.
     * @return
     * The ID;
     */
    public int getID() {
        return ID;
    }
    /**
     * Sets the Title (name) of the object
     * @param title
     * The new name of the object
     */
    public void setTitle(String title) {
        this.title = title;
    }
    /**
     * Gets the current title (name) of the object
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }
    /**
     * Sets the description of the object for human purposes
     * @param description
     * The new description for the object
     */
    public void setDescription(String description) {
        this.description = description;
    }
    /**
     * Gets the current description for the object
     * @return
     * The description
     */
    public String getDescription() {
        return description;
    }
    /**
     * Sets the name of the Author for this object
     * @param author
     * The author's name
     */
    public void setAuthor(String author) {
        this.author = author;
    }
    /**
     * Gets the author name for this object
     * @return
     * The author's name
     */
    public String getAuthor() {
        return author;
    }
    /**
     * Sets the authors email address
     * @param email
     * Email Address
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * Gets the author's email address
     * @return
     * Email Address
     */
    public String getEmail() {
        return email;
    }
    /**
     * Sets the creation date
     * @param creationDate
     * creation date
     */
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
    /**
     * Gets the creation date
     * @return
     * creation date
     */
    public String getCreationDate() {
        return creationDate;
    }
}
