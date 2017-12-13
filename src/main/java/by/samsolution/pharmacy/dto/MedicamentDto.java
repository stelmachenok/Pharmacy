package by.samsolution.pharmacy.dto;

import by.samsolution.pharmacy.entity.PackingForm;
import by.samsolution.pharmacy.entity.ReleaseForm;

public class MedicamentDto extends BasicDto {

    private String brandName;
    private String activeIngredient;
    private Double dosage;
    private PackingForm packingForm;
    private String internationalNonproprietaryName;
    private ReleaseForm releaseForm;
    private CategoryDto category;
    private Long categoryDtoId;
    private Long id;

    public MedicamentDto() {
    }

    public MedicamentDto(String brandName, String activeIngredient, Double dosage, PackingForm packingForm, String internationalNonproprietaryName, ReleaseForm releaseForm, CategoryDto category) {
        this.brandName = brandName;
        this.activeIngredient = activeIngredient;
        this.dosage = dosage;
        this.packingForm = packingForm;
        this.internationalNonproprietaryName = internationalNonproprietaryName;
        this.releaseForm = releaseForm;
        this.category = category;
    }

    public String getLabel() {
        return brandName + " " + category.getCategoryName() + " " + activeIngredient;
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

    public CategoryDto getCategory() {
        return category;
    }

    public Long getCategoryDtoId() {
        return categoryDtoId;
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

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public void setCategoryDtoId(Long categoryDtoId) {
        this.categoryDtoId = categoryDtoId;
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
