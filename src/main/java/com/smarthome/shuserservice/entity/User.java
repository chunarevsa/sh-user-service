package com.smarthome.shuserservice.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
public class User extends DateAudit{
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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "USER_AUTHORITY",
            joinColumns = JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID"),
    inverseJoinColumns = JoinColumn(name = "ROLE_ID", referencedColumnName = "ROLE_ID")
            )
    private Set<Role> roles;
//    private Profile profile;
//    private Account account;

}
