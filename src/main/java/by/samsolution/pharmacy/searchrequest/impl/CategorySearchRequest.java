package by.samsolution.pharmacy.searchrequest.impl;

import by.samsolution.pharmacy.searchrequest.AbstractSearchRequest;
import by.samsolution.pharmacy.searchrequest.CategorySearchFieldEnum;

public class CategorySearchRequest extends AbstractSearchRequest<CategorySearchFieldEnum> {
    private String categoryName;
    private String description;

    public CategorySearchRequest() {
    }

    public CategorySearchRequest(String categoryName, String description) {
        this.categoryName = categoryName;
        this.description = description;
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
