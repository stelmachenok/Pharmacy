package by.samsolution.pharmacy.comparator.medicament;

import by.samsolution.pharmacy.entity.MedicamentEntity;

import java.util.Comparator;

public class MedicamentCategoryComparator implements Comparator<MedicamentEntity> {
    @Override
    public int compare(MedicamentEntity o1, MedicamentEntity o2) {
        return o1.getCategory().getCategoryName().compareTo(o2.getCategory().getCategoryName());
    }
}
