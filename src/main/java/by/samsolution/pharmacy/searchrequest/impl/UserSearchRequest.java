package by.samsolution.pharmacy.searchrequest.impl;

import by.samsolution.pharmacy.entity.UserRole;
import by.samsolution.pharmacy.searchrequest.AbstractSearchRequest;
import by.samsolution.pharmacy.searchrequest.UserSearchFieldEnum;

public class UserSearchRequest extends AbstractSearchRequest<UserSearchFieldEnum> {
    private Long id;
    private String login;
    private String password;
    private UserRole role;

    public UserSearchRequest() {
    }

    public UserSearchRequest(Long id, String login, String password, UserRole role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.role = role;
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
}
