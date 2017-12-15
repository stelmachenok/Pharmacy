package by.samsolution.pharmacy.searchrequest.impl;

import by.samsolution.pharmacy.searchrequest.AbstractSearchRequest;
import by.samsolution.pharmacy.searchrequest.AvailabilitySearchFieldEnum;

import java.util.Date;

public class AvailabilitySearchRequest extends AbstractSearchRequest<AvailabilitySearchFieldEnum>{
    private Long pharmacyId;
    private Long medicamentId;
    private Long count;
    private Date lastUpdate;

    public AvailabilitySearchRequest() {
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
