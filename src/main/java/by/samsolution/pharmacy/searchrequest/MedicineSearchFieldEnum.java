package by.samsolution.pharmacy.searchrequest;

import by.samsolution.pharmacy.searchrequest.ISearchFieldEnum;

public enum MedicineSearchFieldEnum implements ISearchFieldEnum {
    BRAND_NAME("brandName"), ACTIVE_INGREDIENT("activeIngredient"), DOSAGE("dosage"), PACKING_FORM("packingForm"),
    INTERNATIONAL_NONPROPRIENTARY_NAME("internationalNonproprietaryName"), RELEASE_FORM("releaseForm");

    MedicineSearchFieldEnum(String fieldName) {
        this.fieldName = fieldName;
    }
    private final String fieldName;

    public String getFieldName() {
        return fieldName;
    }
}
