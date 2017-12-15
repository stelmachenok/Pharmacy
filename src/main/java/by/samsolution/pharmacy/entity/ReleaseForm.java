package by.samsolution.pharmacy.entity;

public enum ReleaseForm {
    WITHOUT_RECIPE("WITHOUT_RECIPE"), USUAL_RECIPE("USUAL_RECIPE"), PINK_RECIPE("PINK_RECIPE");

    ReleaseForm(String fieldName) {
        this.fieldName = fieldName;
    }
    private final String fieldName;

    public String getFieldName() {
        return fieldName;
    }
}
