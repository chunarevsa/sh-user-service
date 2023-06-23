package com.smarthome.shuserservice.entity;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "users")
public class User extends DateAudit {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(nullable = false)
    private Boolean isActive;

    @JoinColumn(nullable = false, unique = true, updatable = false)
    private String login;

    @JoinColumn(nullable = false)
    private String password;

    @Column(name = "IS_EMAIL_VERIFIED", nullable = false)
    private Boolean isEmailVerified;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_authority",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "role_id")})
    private Set<Role> roles = new HashSet<>();

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_id", unique = true)
    private Profile profile;

    @OneToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "account_id", unique = true)
    private Account account;

    public User() {
        super();
    }

    public User(String login,
                String password,
                Boolean isEmailVerified,
                Set<Role> roles,
                Profile profile) {
        this.isActive = true;
        this.login = login;
        this.password = password;
        this.isEmailVerified = isEmailVerified;
        this.roles = roles;
        this.profile = profile;
        this.account = new Account();
    }

    public void addRole(Role role) {
        roles.add(role);
        role.getUsers().add(this);
    }

    public void addRoles(Set<Role> roles) {
        roles.forEach(this::addRole);
    }

    public void verificationConfirmed() {
        setEmailVerified(true);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean isActive() {
        return isActive;
    }

    public void setActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean isEmailVerified() {
        return isEmailVerified;
    }

    public void setEmailVerified(Boolean isEmailVerified) {
        this.isEmailVerified = isEmailVerified;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", isActive=" + isActive +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", roles=" + roles +
                '}';
    }
}
