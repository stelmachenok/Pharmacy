package by.samsolution.pharmacy.entity;

import by.samsolution.pharmacy.dto.BasicDto;

public class User extends BasicEntity {
    private Long id;
    private String login;
    private String password;
    private UserRole role;
    private Long pharmacyId;
    private Boolean enabled;

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    public UserRole getRole() {
        return role;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }
}

