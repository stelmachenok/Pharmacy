package by.samsolution.pharmacy.dto;

public class PackingFormDto extends BasicDto {
    private String fieldName;
    private final String resPath = "label.packingForm.";

    public PackingFormDto() {
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
