package by.samsolution.pharmacy.entity;

public enum PharmacyCategory {
    FIRST("FIRST"), SECOND("SECOND"), THIRD("THIRD"), FOURTH("FOURTH"), FIFTH("FIFTH");

    PharmacyCategory(String fieldName) {
        this.fieldName = fieldName;
    }
    private final String fieldName;

    public String getFieldName() {
        return fieldName;
    }
}
