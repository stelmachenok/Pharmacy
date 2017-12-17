package by.samsolution.pharmacy.dto;

import java.util.Date;

public class AvailabilityDto extends BasicDto {
    private Long id;
    private Long pharmacyId;
    private Long medicamentId;
    private Long count;
    private Date lastUpdate;
    private String brandName;

    public AvailabilityDto() {
    }

    public Long getId() {
        return id;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public Long getMedicamentId() {
        return medicamentId;
    }

    public Long getCount() {
        return count;
    }

    public Date getLastUpdate() {
        return lastUpdate;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public void setMedicamentId(Long medicamentId) {
        this.medicamentId = medicamentId;
    }

    public void setCount(Long count) {
        this.count = count;
    }

    public void setLastUpdate(Date lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }
}
