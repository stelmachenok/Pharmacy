package by.samsolution.pharmacy.comparator.category;

import by.samsolution.pharmacy.entity.MedicamentCategory;

import java.util.Comparator;

public class CategoryNameComparator implements Comparator<MedicamentCategory> {
    @Override
    public int compare(MedicamentCategory o1, MedicamentCategory o2) {
        return o1.getCategoryName().compareTo(o2.getCategoryName());
    }
}
