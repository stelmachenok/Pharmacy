package by.samsolution.pharmacy.comparator;

import by.samsolution.pharmacy.dto.MedicamentDto;

import java.util.Comparator;

public class MedicamentBrandNameComparator implements Comparator<MedicamentDto> {

    @Override
    public int compare(MedicamentDto o1, MedicamentDto o2) {
        return o1.getBrandName().compareTo(o2.getBrandName());
    }
}
