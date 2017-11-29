package by.samsolution.pharmacy.entity;

import java.util.UUID;

/**
 * Created by y50-70 on 20.10.2017.
 */
public class Pharmacy extends BasicEntity{
    private String pharmacyName;
    private String address;
    private String pharmacistName;
    private String contactNumber;
    private String login;
    private String password;
    private String category;    //change to enum
    private UUID uuid;
    private Long id;

    public Pharmacy() {
        uuid = UUID.randomUUID();
    }

    public Pharmacy(String pharmacyName, String address, String pharmacistName, String contactNumber, String login, String password, String category) {
        this.pharmacyName = pharmacyName;
        this.address = address;
        this.pharmacistName = pharmacistName;
        this.contactNumber = contactNumber;
        this.login = login;
        this.password = password;
        this.category = category;
        uuid = UUID.randomUUID();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setPharmacyName(String pharmacyName) {
        this.pharmacyName = pharmacyName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPharmacistName(String pharmacistName) {
        this.pharmacistName = pharmacistName;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPharmacyName() {
        return pharmacyName;
    }

    public String getAddress() {
        return address;
    }

    public String getPharmacistName() {
        return pharmacistName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public String getCategory() {
        return category;
    }
}
