package by.samsolution.pharmacy.comparator.pharmacy;

import by.samsolution.pharmacy.dto.PharmacyDto;

import java.util.Comparator;

public class PharmacyNameComparator implements Comparator<PharmacyDto> {

    @Override
    public int compare(PharmacyDto o1, PharmacyDto o2) {
        return o1.getPharmacyName().compareTo(o2.getPharmacyName());
    }
}

//    private String pharmacyName;
//    private String address;
//    private String pharmacistName;
//    private String contactNumber;
//    private String login;
//    private String password;
//    private String category;