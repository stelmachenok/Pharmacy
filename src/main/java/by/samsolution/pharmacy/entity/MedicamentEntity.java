package by.samsolution.pharmacy.entity;

import by.samsolution.pharmacy.dto.MedicamentDto;

import java.util.UUID;

/**
 * Created by y50-70 on 20.10.2017.
 */
public class MedicamentEntity extends BasicEntity{
    private String brandName;
    private String activeIngredient;
    private Double dosage;
    private PackingForm packingForm;
    private String internationalNonproprietaryName;
    private ReleaseForm releaseForm;
    private final UUID guid;
    private Long id;

    public MedicamentEntity() {
        this.guid = UUID.randomUUID();
    }

    public MedicamentEntity(String brandName, String activeIngredient, Double dosage, PackingForm packingForm, String internationalNonproprietaryName, ReleaseForm releaseForm) {
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

    public Double getDosage() {
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

    public void setDosage(Double dosage) {
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
        return "MedicamentEntity{" +
                "brandName='" + brandName + '\'' +
                ", activeIngredient='" + activeIngredient + '\'' +
                ", dosage='" + dosage + '\'' +
                ", packingForm=" + packingForm +
                ", internationalNonproprietaryName='" + internationalNonproprietaryName + '\'' +
                ", releaseForm=" + releaseForm +
                ", guid=" + guid +
                '}';
    }
}
