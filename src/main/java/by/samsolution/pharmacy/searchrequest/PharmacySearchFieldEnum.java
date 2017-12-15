package by.samsolution.pharmacy.searchrequest;

public enum PharmacySearchFieldEnum implements ISearchFieldEnum {
    PHARMACY_NAME("pharmacyName"), ADDRESS("address"), PHARMACIST_NAME("pharmacistName"),
    CONTACT_NUMBER("contactNumber"), CATEGORY("category");

    PharmacySearchFieldEnum(String fieldName) {
        this.fieldName = fieldName;
    }

    private final String fieldName;

    public String getFieldName() {
        return fieldName;
    }
}