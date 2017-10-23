package entity;

/**
 * Created by y50-70 on 20.10.2017.
 */
public class MedicamentCategory {
    private String categoryName;
    private String description;
    private int ID;

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
