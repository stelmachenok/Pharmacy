package by.samsolution.pharmacy.dto;

public class CategoryDto  extends BasicDto{
    private String categoryName;
    private String description;
    private Long id;

    public CategoryDto(){

    }

    public CategoryDto(String categoryName, String description) {
        this.categoryName = categoryName;
        this.description = description;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CategoryDto{" +
                "categoryName='" + categoryName + '\'' +
                ", description='" + description + '\'' +
                ", id=" + id +
                '}';
    }
}
