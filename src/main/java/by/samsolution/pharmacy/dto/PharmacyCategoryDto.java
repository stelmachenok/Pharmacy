package by.samsolution.pharmacy.dto;

public class PharmacyCategoryDto extends BasicDto {
    private String fieldName;
    private final String resPath = "label.pharmacyCategory.";
    private String translatedName;

    public PharmacyCategoryDto() {
    }

    public String getTranslatedName() {
        return translatedName;
    }

    public void setTranslatedName(String translatedName) {
        this.translatedName = translatedName;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getResPath() {
        return resPath + fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
}
