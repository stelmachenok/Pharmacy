package by.samsolution.pharmacy.entity;

import java.util.UUID;

/**
 * Created by y50-70 on 20.10.2017.
 */
public class Medicament {
    private String brandName;
    private String activeIngredient;
    private String dosage;
    private PackingForm packingForm;
    private String internationalNonproprietaryName;
    private ReleaseForm releaseForm;
    private final UUID guid;
    private Long id;

    public Medicament() {
        this.guid = UUID.randomUUID();
    }

    public Medicament(String brandName, String activeIngredient, String dosage, PackingForm packingForm, String internationalNonproprietaryName, ReleaseForm releaseForm) {
        this.brandName = brandName;
        this.activeIngredient = activeIngredient;
        this.dosage = dosage;
        this.packingForm = packingForm;
        this.internationalNonproprietaryName = internationalNonproprietaryName;
        this.releaseForm = releaseForm;
        this.guid = UUID.randomUUID();
    }

    public String getBrandName() {
        return brandName;
    }

    public String getActiveIngredient() {
        return activeIngredient;
    }

    public String getDosage() {
        return dosage;
    }

    public PackingForm getPackingForm() {
        return packingForm;
    }

    public String getInternationalNonproprietaryName() {
        return internationalNonproprietaryName;
    }

    public ReleaseForm getReleaseForm() {
        return releaseForm;
    }

    public UUID getGuid() {
        return guid;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {

        return id;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setActiveIngredient(String activeIngredient) {
        this.activeIngredient = activeIngredient;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public void setPackingForm(PackingForm packingForm) {
        this.packingForm = packingForm;
    }

    public void setInternationalNonproprietaryName(String internationalNonproprietaryName) {
        this.internationalNonproprietaryName = internationalNonproprietaryName;
    }

    public void setReleaseForm(ReleaseForm releaseForm) {
        this.releaseForm = releaseForm;
    }

    @Override
    public String toString() {
        return "Medicament{" +
                "brandName='" + brandName + '\'' +
                ", activeIngredient='" + activeIngredient + '\'' +
                ", dosage='" + dosage + '\'' +
                ", packingForm=" + packingForm +
                ", internationalNonproprietaryName='" + internationalNonproprietaryName + '\'' +
                ", releaseForm=" + releaseForm +
                ", guid=" + guid +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Medicament)) return false;

        Medicament that = (Medicament) o;

        if (brandName != null ? !brandName.equals(that.brandName) : that.brandName != null) return false;
        if (activeIngredient != null ? !activeIngredient.equals(that.activeIngredient) : that.activeIngredient != null)
            return false;
        if (dosage != null ? !dosage.equals(that.dosage) : that.dosage != null) return false;
        if (packingForm != that.packingForm) return false;
        if (internationalNonproprietaryName != null ? !internationalNonproprietaryName.equals(that.internationalNonproprietaryName) : that.internationalNonproprietaryName != null)
            return false;
        return releaseForm == that.releaseForm;
    }

    @Override
    public int hashCode() {
        int result = brandName != null ? brandName.hashCode() : 0;
        result = 31 * result + (activeIngredient != null ? activeIngredient.hashCode() : 0);
        result = 31 * result + (dosage != null ? dosage.hashCode() : 0);
        result = 31 * result + (packingForm != null ? packingForm.hashCode() : 0);
        result = 31 * result + (internationalNonproprietaryName != null ? internationalNonproprietaryName.hashCode() : 0);
        result = 31 * result + (releaseForm != null ? releaseForm.hashCode() : 0);
        return result;
    }
}
