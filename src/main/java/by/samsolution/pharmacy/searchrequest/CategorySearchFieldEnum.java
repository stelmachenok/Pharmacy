package by.samsolution.pharmacy.searchrequest;

public enum CategorySearchFieldEnum implements ISearchFieldEnum {
    CATEGORY_NAME("categoryName"), DESCRIPTION("description");

    CategorySearchFieldEnum(String fieldName) {
        this.fieldName = fieldName;
    }

    private final String fieldName;

    public String getFieldName() {
        return fieldName;
    }
}