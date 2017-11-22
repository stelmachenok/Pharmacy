package by.samsolution.pharmacy.comparator.medicament;

import by.samsolution.pharmacy.dto.MedicamentDto;

import java.util.Comparator;

public class MedicamentPackingFormComparator implements Comparator<MedicamentDto> {

    @Override
    public int compare(MedicamentDto o1, MedicamentDto o2) {
        return o1.getPackingForm().compareTo(o2.getPackingForm());
    }
}