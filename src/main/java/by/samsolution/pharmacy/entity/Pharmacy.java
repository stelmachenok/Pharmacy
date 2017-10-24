package by.samsolution.pharmacy.entity;

/**
 * Created by y50-70 on 20.10.2017.
 */
public class Pharmacy {
    private String pharmacyName;
    private String address;
    private String pharmacistName;
    private String contactNumber;
    private String login;
    private String password;
    private String category;
    private int ID;

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
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
