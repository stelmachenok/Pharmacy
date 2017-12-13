package by.samsolution.pharmacy.dto;

public class ReleaseFormDto extends BasicDto {
    private String fieldName;
    private final String resPath = "label.releaseForm.";

    public ReleaseFormDto() {
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
