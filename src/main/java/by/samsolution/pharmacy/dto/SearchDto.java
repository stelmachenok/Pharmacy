package by.samsolution.pharmacy.dto;

import by.samsolution.pharmacy.entity.PackingForm;

import java.util.Date;

public class SearchDto {
    private String brandName;
    private PackingForm packingForm;
    private String activeIngredient;
    private Long count;
    private Date lastUpdate;
    private String pharmacyName;
    private String address;
    private String contactNumber;
    private Long pharmacyId;
    private Long medicamentId;
    private String packingFormName;

    public SearchDto() {
    }

    public String getPackingFormName() {
        return packingFormName;
    }

    public String getLabel() {
        return brandName + ", " + packingFormName + ", " + activeIngredient;
    }

    public PackingForm getPackingForm() {
        return packingForm;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getActiveIngredient() {
        return activeIngredient;
    }

    public Long getCount() {
        return count;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public String getAddress() {
        return address;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public Long getMedicamentId() {
        return medicamentId;
    }

    public void setPackingForm(PackingForm packingForm) {
        this.packingForm = packingForm;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public void setActiveIngredient(String activeIngredient) {
        this.activeIngredient = activeIngredient;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public void setMedicamentId(Long medicamentId) {
        this.medicamentId = medicamentId;
    }

    public void setPackingFormName(String packingFormName) {
        this.packingFormName = packingFormName;
    }
}
