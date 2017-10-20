package entity;

/**
 * Created by y50-70 on 20.10.2017.
 */
public class Medicament {
    private String brandName;
    private String activeIngredient;
    private int dosage;
    private String packingForm; //todo replace by enum
    private String internationalNonproprietaryName;
    private String releaseMedicament; //todo replace by enum
    private int GUID;

    public Medicament() {
    }

    public Medicament(String brandName, String activeIngredient, int dosage, String packingForm, String internationalNonproprietaryName, String releaseMedicament, int GUID) {
        this.brandName = brandName;
        this.activeIngredient = activeIngredient;
        this.dosage = dosage;
        this.packingForm = packingForm;
        this.internationalNonproprietaryName = internationalNonproprietaryName;
        this.releaseMedicament = releaseMedicament;
        this.GUID = GUID;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getActiveIngredient() {
        return activeIngredient;
    }

    public int getDosage() {
        return dosage;
    }

    public String getPackingForm() {
        return packingForm;
    }

    public String getInternationalNonproprietaryName() {
        return internationalNonproprietaryName;
    }

    public String getReleaseMedicament() {
        return releaseMedicament;
    }

    public int getGUID() {
        return GUID;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setActiveIngredient(String activeIngredient) {
        this.activeIngredient = activeIngredient;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public void setPackingForm(String packingForm) {
        this.packingForm = packingForm;
    }

    public void setInternationalNonproprietaryName(String internationalNonproprietaryName) {
        this.internationalNonproprietaryName = internationalNonproprietaryName;
    }

    public void setReleaseMedicament(String releaseMedicament) {
        this.releaseMedicament = releaseMedicament;
    }

    public void setGUID(int GUID) {
        this.GUID = GUID;
    }
}
