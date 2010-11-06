package SystemObjects;

/**
 * This is a database object, which holds generic database data for use in large databases and projects.
 * @author Dylan Vorster
 * 
 */
public class DatabaseObject extends SystemObject {
    
    //Identification data
    private static int DatabaseID = -1;
    private static int onlineID = -1;
  
    //Database Information
    private static String partCategory = "Generic Part";
    private static int quantity = 1;

    public DatabaseObject() {
        super("Database Object");
    }


    /**
     * Sets the Databse ID
     * @param DatabaseID
     * ID
     */
    public static void setDatabaseID(int DatabaseID) {
        DatabaseObject.DatabaseID = DatabaseID;
    }
    /**
     * Get Database ID
     * @return
     * ID
     */
    public static int getDatabaseID() {
        return DatabaseID;
    }
    /**
     * Sets the Online Id
     * @param onlineID
     * Online ID
     */
    public static void setOnlineID(int onlineID) {
        DatabaseObject.onlineID = onlineID;
    }
    /**
     * Gets the online ID.
     * @return
     * Online ID
     */
    public static int getOnlineID() {
        return onlineID;
    }
    /**
     * Sets the category for this Database Object.
     * @param partCategory
     * Category
     */
    public static void setPartCategory(String partCategory) {
        DatabaseObject.partCategory = partCategory;
    }
    /**
     * Gets the category for the part.
     * @return
     * Category
     */
    public static String getPartCategory() {
        return partCategory;
    }
    /**
     * Sets the quantity of the object.
     * @param quantity
     * -1 is infinite else the exact amount of this object
     */
    public static void setQuantity(int quantity) {
        DatabaseObject.quantity = quantity;
    }
    /**
     * gets the quantity of this object in the database.
     * @return
     * the quantity, -1 is infinite.
     */
    public static int getQuantity() {
        return quantity;
    }
}
