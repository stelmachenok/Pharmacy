package by.samsolution.pharmacy.entity;

import java.util.Date;

public class AvailabilityEntity extends BasicEntity{
    private Long pharmacyId;
    private Long medicamentId;
    private Long count;
    private Date lastUpdate;

    public AvailabilityEntity() {
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
}
