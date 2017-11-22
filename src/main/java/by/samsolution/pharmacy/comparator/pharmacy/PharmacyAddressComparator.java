package by.samsolution.pharmacy.comparator.pharmacy;

import by.samsolution.pharmacy.dto.PharmacyDto;

import java.util.Comparator;

public class PharmacyAddressComparator implements Comparator<PharmacyDto> {

    @Override
    public int compare(PharmacyDto o1, PharmacyDto o2) {
        return o1.getAddress().compareTo(o2.getAddress());
    }
}