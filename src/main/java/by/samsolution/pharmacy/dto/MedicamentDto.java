package by.samsolution.pharmacy.dto;

import by.samsolution.pharmacy.entity.MedicamentEntity;
import by.samsolution.pharmacy.entity.PackingForm;
import by.samsolution.pharmacy.entity.ReleaseForm;
import by.samsolution.pharmacy.formvalidator.Dosage;
import by.samsolution.pharmacy.formvalidator.NotNullOrWhiteSpace;

import javax.validation.constraints.NotNull;


public class MedicamentDto extends BasicDto {

    @NotNullOrWhiteSpace
    private String brandName;
    @NotNullOrWhiteSpace
    private String activeIngredient;
    @Dosage
    private Double dosage;

    private PackingForm packingForm;
    @NotNull
    private String internationalNonproprietaryName;

    private ReleaseForm releaseForm;
    private Long id;

    public MedicamentDto() {
    }

    public MedicamentDto(String brandName, String activeIngredient, Double dosage, PackingForm packingForm, String internationalNonproprietaryName, ReleaseForm releaseForm) {

        this.brandName = brandName;
        this.activeIngredient = activeIngredient;
        this.dosage = dosage;
        this.packingForm = packingForm;
        this.internationalNonproprietaryName = internationalNonproprietaryName;
        this.releaseForm = releaseForm;
    }

    public MedicamentDto(MedicamentEntity medicamentEntity) {
        this.brandName = medicamentEntity.getBrandName();
        this.activeIngredient = medicamentEntity.getActiveIngredient();
        this.dosage = medicamentEntity.getDosage();
        this.packingForm = medicamentEntity.getPackingForm();
        this.internationalNonproprietaryName = medicamentEntity.getInternationalNonproprietaryName();
        this.releaseForm = medicamentEntity.getReleaseForm();
        this.id = medicamentEntity.getId();
    }

    public MedicamentEntity toEntity() {
        return new MedicamentEntity(this);
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

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "MedicamentDto{" +
                "brandName='" + brandName + '\'' +
                ", activeIngredient='" + activeIngredient + '\'' +
                ", dosage='" + dosage + '\'' +
                ", packingForm=" + packingForm +
                ", internationalNonproprietaryName='" + internationalNonproprietaryName + '\'' +
                ", releaseForm=" + releaseForm +
                ", id=" + id +
                '}';
    }
}
