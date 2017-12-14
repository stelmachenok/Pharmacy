package by.samsolution.pharmacy.dto;

public class PackingFormDto extends BasicDto {
    private String fieldName;
    private final String resPath = "label.packingForm.";
    private String translatedName;

    public PackingFormDto() {
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
