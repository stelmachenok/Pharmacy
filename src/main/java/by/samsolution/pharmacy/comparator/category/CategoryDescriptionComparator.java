package by.samsolution.pharmacy.comparator.category;

import by.samsolution.pharmacy.entity.MedicamentCategory;

import java.util.Comparator;

public class CategoryDescriptionComparator implements Comparator<MedicamentCategory> {
    @Override
    public int compare(MedicamentCategory o1, MedicamentCategory o2) {
        return o1.getDescription().compareTo(o2.getDescription());
    }
}
