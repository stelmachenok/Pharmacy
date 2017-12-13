package by.samsolution.pharmacy.entity;

public enum PackingForm{
    TABLET("TABLET"), POWDER("POWDER"), CAPSULE("CAPSULE"), DRAGEES("DRAGEES"), GRANULE("GRANULE"), CARAMEL("CARAMEL"),
    OINTMENT("OINTMENT"), CREAM("CREAM"), PASTE("PASTE"), GEL("GEL"), SOLUTION("SOLUTION"), TINCTURE("TINCTURE"),
    SUSPENSION("SUSPENSION"), EMULSION("EMULSION"), DROP("DROP"), SYRUP("SYRUP"), POTION("POTION"), AEROSOL("AEROSOL");
    PackingForm(String fieldName) {
        this.fieldName = fieldName;
    }
    private final String fieldName;

    public String getFieldName() {
        return fieldName;
    }
}
