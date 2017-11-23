package by.samsolution.pharmacy.comparator;

import by.samsolution.pharmacy.comparator.category.CategoryDescriptionComparator;
import by.samsolution.pharmacy.comparator.category.CategoryNameComparator;
import by.samsolution.pharmacy.comparator.medicament.*;
import by.samsolution.pharmacy.comparator.pharmacy.*;
import by.samsolution.pharmacy.searchrequest.ISearchFieldEnum;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

import static by.samsolution.pharmacy.searchrequest.CategorySearchFieldEnum.CATEGORY_NAME;
import static by.samsolution.pharmacy.searchrequest.CategorySearchFieldEnum.DESCRIPTION;
import static by.samsolution.pharmacy.searchrequest.MedicineSearchFieldEnum.*;
import static by.samsolution.pharmacy.searchrequest.PharmacySearchFieldEnum.*;

public class ComparatorChooser{

    private Map<ISearchFieldEnum, Comparator> map;

    public ComparatorChooser() {
        map = new HashMap<>();
        map.put(PHARMACY_NAME, new PharmacyNameComparator());
        map.put(ADDRESS, new PharmacyAddressComparator());
        map.put(PHARMACIST_NAME, new PharmacyPharmacistNameComparator());
        map.put(CONTACT_NUMBER, new PharmacyContactNumberComparator());
        map.put(CATEGORY, new PharmacyCategoryComparator());
        map.put(CATEGORY_NAME, new CategoryNameComparator());
        map.put(DESCRIPTION, new CategoryDescriptionComparator());
        map.put(BRAND_NAME, new MedicamentBrandNameComparator());
        map.put(ACTIVE_INGREDIENT, new MedicamentActiveIngredientComparator());
        map.put(DOSAGE, new MedicamentDosageComparator());
        map.put(PACKING_FORM, new MedicamentPackingFormComparator());
        map.put(INTERNATIONAL_NONPROPRIENTARY_NAME, new MedicamentInternationalNonproprietaryNameComparator());
        map.put(RELEASE_FORM, new MedicamentReleaseFormComparator());
    }

    public Comparator choose(ISearchFieldEnum fieldEnum) {
        return map.get(fieldEnum);
    }
}
