package by.samsolution.pharmacy.dto;

import by.samsolution.pharmacy.entity.UserRole;

public class UserDto extends BasicDto {
    private Long id;
    private String login;
    private UserRole role;
    private Long pharmacyId;
    private Boolean enabled;

    public UserDto() {
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public UserRole getRole() {
        return role;
    }

    public Long getPharmacyId() {
        return pharmacyId;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public void setPharmacyId(Long pharmacyId) {
        this.pharmacyId = pharmacyId;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }
}
