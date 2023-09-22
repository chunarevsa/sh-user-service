package com.socialhobbies.shuserservice.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "role")
public class Role  {
	@Id
	@Column (name = "role_id")
	@GeneratedValue (strategy =  GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	@Enumerated (EnumType.STRING)
	@NaturalId
	private ERoleName name;

	@ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
	@JsonIgnore
	private Set<User> users = new HashSet<>();

	public Role() {}
	
	public Role(ERoleName role) {
		this.name = role;
	}

	public boolean isAdminRole() {
		return this.name.equals(ERoleName.ROLE_ADMIN);
  }

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ERoleName getRole() {
		return this.name;
	}

	public void setRole(ERoleName role) {
		this.name = role;
	}

	public Set<User> getUsers() {
		return this.users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "{" +
			" id='" + getId() + "'" +
			", role='" + getRole() + "'" +
			", users='" + getUsers() + "'" +
			"}";
	}

} 

