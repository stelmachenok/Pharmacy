package by.samsolution.pharmacy.comparator.pharmacy;

import by.samsolution.pharmacy.dto.PharmacyDto;

import java.util.Comparator;

public class PharmacyCategoryComparator implements Comparator<PharmacyDto> {

    @Override
    public int compare(PharmacyDto o1, PharmacyDto o2) {
        return o1.getCategory().compareTo(o2.getCategory());
    }
}