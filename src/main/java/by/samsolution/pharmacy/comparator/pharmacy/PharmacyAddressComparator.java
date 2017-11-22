package by.samsolution.pharmacy.comparator.pharmacy;

import by.samsolution.pharmacy.entity.Pharmacy;

import java.util.Comparator;

public class PharmacyAddressComparator implements Comparator<Pharmacy> {

    @Override
    public int compare(Pharmacy o1, Pharmacy o2) {
        return o1.getAddress().compareTo(o2.getAddress());
    }
}