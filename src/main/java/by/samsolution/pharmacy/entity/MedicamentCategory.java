package by.samsolution.pharmacy.entity;

import java.util.UUID;

/**
 * Created by y50-70 on 20.10.2017.
 */
public class MedicamentCategory extends BasicEntity{
    private String categoryName;
    private String description;
    private UUID guid;
    private Long id;

    public MedicamentCategory(){
        this.guid = UUID.randomUUID();
    }

    public MedicamentCategory(String categoryName, String description) {
        this.categoryName = categoryName;
        this.description = description;
        this.guid = UUID.randomUUID();
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getDescription() {
        return description;
    }

    public java.util.UUID getGuid() {
        return guid;
    }

    public Long getId() {
        return id;
    }

    public void setGuid(UUID guid) {
        this.guid = guid;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
