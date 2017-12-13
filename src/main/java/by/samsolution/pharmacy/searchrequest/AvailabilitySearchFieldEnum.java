package by.samsolution.pharmacy.searchrequest;

public enum AvailabilitySearchFieldEnum implements ISearchFieldEnum {
    PHARMACY_ID("pharmacyID"), MEDICAMENT_ID("medicamentId"), COUNT("count"), LAST_UPDATE("lastUpdate");

    AvailabilitySearchFieldEnum(String fieldName) {
        this.fieldName = fieldName;
    }

    private final String fieldName;

    public String getFieldName() {
        return fieldName;
    }
}
