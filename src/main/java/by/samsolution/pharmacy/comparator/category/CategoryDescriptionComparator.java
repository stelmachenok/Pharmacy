package by.samsolution.pharmacy.comparator.category;

import by.samsolution.pharmacy.dto.CategoryDto;

import java.util.Comparator;

public class CategoryDescriptionComparator implements Comparator<CategoryDto> {
    @Override
    public int compare(CategoryDto o1, CategoryDto o2) {
        return o1.getDescription().compareTo(o2.getDescription());
    }
}
