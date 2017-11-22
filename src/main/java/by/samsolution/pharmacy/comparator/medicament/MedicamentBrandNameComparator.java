package by.samsolution.pharmacy.comparator.medicament;

import by.samsolution.pharmacy.dto.MedicamentDto;
import by.samsolution.pharmacy.entity.MedicamentEntity;

import java.util.Comparator;

public class MedicamentBrandNameComparator implements Comparator<MedicamentEntity> {

    @Override
    public int compare(MedicamentEntity o1, MedicamentEntity o2) {
        return o1.getBrandName().compareTo(o2.getBrandName());
    }
}
